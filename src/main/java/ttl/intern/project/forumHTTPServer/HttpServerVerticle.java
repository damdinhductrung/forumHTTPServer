package ttl.intern.project.forumHTTPServer;

import java.util.ArrayList;
import java.util.List;

import ttl.intern.project.forumHTTPServer.model.*;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.RequestParameters;
import io.vertx.ext.web.handler.BodyHandler;
import ttl.intern.project.forumHTTPServer.payload.DeleteArticleRequest;
import ttl.intern.project.forumHTTPServer.payload.GetArticleResponse;
import ttl.intern.project.forumHTTPServer.payload.GetArticlesResponse;
import ttl.intern.project.forumHTTPServer.payload.LoginRequest;
import ttl.intern.project.forumHTTPServer.payload.LoginResponse;
import ttl.intern.project.forumHTTPServer.payload.SaveArticleRequest;
import ttl.intern.project.forumHTTPServer.payload.SaveArticleResponse;
import ttl.intern.project.forumHTTPServer.payload.SignupRequest;
import ttl.intern.project.forumHTTPServer.payload.SignupResponse;
import ttl.intern.project.forumHTTPServer.payload.UpdateArticleRequest;
import ttl.intern.project.forumHTTPServer.payload.UpdateArticleResponse;
import ttl.intern.project.forumHTTPServer.validation.DeleteArticleRequestValidationHandler;
import ttl.intern.project.forumHTTPServer.validation.LoginRequestValidationHandler;
import ttl.intern.project.forumHTTPServer.validation.SaveArticleRequestValidationHandler;
import ttl.intern.project.forumHTTPServer.validation.SignupRequestValidationHandler;
import ttl.intern.project.forumHTTPServer.validation.UpdateArticleRequestValidationHandler;

public class HttpServerVerticle extends AbstractVerticle {
	
	@Override
	public void start() {
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);
			
		//TODO: exception handler
		router.post().handler(BodyHandler.create());
		router.put().handler(BodyHandler.create());
		
		router.post("/login").handler(new LoginRequestValidationHandler()).handler(this::login);
		router.post("/signup").handler(new SignupRequestValidationHandler()).handler(this::signup);
		router.post("/articles").handler(new SaveArticleRequestValidationHandler()).handler(this::saveArticle);
		router.put("/articles/:articleid").handler(new UpdateArticleRequestValidationHandler()).handler(this::updateArticle);
		router.delete("/articles/:articleid").handler(new DeleteArticleRequestValidationHandler()).handler(this::deleteArticle);
		
		router.get("/").handler(this::getArticles);
		router.get("/articles").handler(this::getArticles);
		router.get("/articles/:articleid").handler(this::getArticle);
		
		
		
		server.requestHandler(router).listen(8080);
	}
	
	public void login(RoutingContext rc) {
		setResponseHeader(rc.response());
		
		RequestParameters params = rc.get("parsedParameters");
		LoginRequest login = new LoginRequest(params.formParameter("username").getString(), params.formParameter("password").getString());
		
		vertx.eventBus().send("mongo.auth", JsonObject.mapFrom(login), new DeliveryOptions().addHeader("action", "user-login"), res -> {
			if (res.succeeded()) {
				LoginResponse response = new LoginResponse("0", "", res.result().body().toString());
				rc.response().end(JsonObject.mapFrom(response).encodePrettily());
			} else {
				//TODO
			}
		});
	}
	
	public void signup(RoutingContext rc) {
		setResponseHeader(rc.response());
		
		RequestParameters params = rc.get("parsedParameters");
		SignupRequest signup = new SignupRequest(params.formParameter("username").getString(), params.formParameter("password").getString());
		
		vertx.eventBus().send("mongo.auth", JsonObject.mapFrom(signup), new DeliveryOptions().addHeader("action", "user-signup"), res -> {
			if (res.succeeded()) {
				SignupResponse response = new SignupResponse("0", "");
				rc.response().end(JsonObject.mapFrom(response).encodePrettily());
			} else {
				//TODO
			}
		});
				
	}
	
	public void getArticles(RoutingContext rc) {
		setResponseHeader(rc.response());
		
		vertx.eventBus().send("mongo.article", new JsonObject(), new DeliveryOptions().addHeader("action", "indexArticle"), res -> {
			if (res.succeeded()) {
				ArrayList<Article> list = new ArrayList<>();
				list = Json.decodeValue(res.result().body().toString(), list.getClass());
				GetArticlesResponse response = new GetArticlesResponse("0", "", list);
				rc.response().end(new JsonObject().mapFrom(response).encodePrettily());
			} else {
				//TODO
			}
		});
	}
	
	public void getArticle(RoutingContext rc) {
		setResponseHeader(rc.response());
		
		vertx.eventBus().send("mongo.article", new JsonObject().put("_id", rc.request().getParam("articleid")), new DeliveryOptions().addHeader("action", "getArticle"), res -> {
			if (res.succeeded()) {
				
				JsonObject json = new JsonObject(res.result().body().toString());
				
				Article article = new Article(json.getString("_id"), json.getString("title"), json.getString("content"), json.getJsonObject("createdDay").getString("$date"), json.getString("username"));
				
				GetArticleResponse response = new GetArticleResponse("0", "", article);
				rc.response().end(new JsonObject().mapFrom(response).encodePrettily());
			} else {
				//TODO
			}
		});
	}
	
	public void saveArticle(RoutingContext rc) {
		setResponseHeader(rc.response());
		
		RequestParameters params = rc.get("parsedParameters");
		SaveArticleRequest request = new SaveArticleRequest(params.headerParameter("Authorization").getString().substring(7), params.formParameter("title").getString(), params.formParameter("content").getString());
		
		vertx.eventBus().send("mongo.auth", new JsonObject().put("jwt", request.getToken()), new DeliveryOptions().addHeader("action", "user-authorization"), res -> {
			if (res.succeeded()) {
				
				JsonObject user = new JsonObject(res.result().body().toString());
				
				vertx.eventBus().send("mongo.article", new JsonObject().put("title", request.getTitle()).put("content", request.getContent()).put("username", user.getString("username")), new DeliveryOptions().addHeader("action", "saveArticle"), response -> {
					if (response.succeeded()) {
						SaveArticleResponse httpResponse = new SaveArticleResponse("0", "", new JsonObject(response.result().body().toString()).getString("_id"));
						
						rc.response().end(new JsonObject().mapFrom(httpResponse).encodePrettily());
					} else {
						response.cause().printStackTrace();
						//TODO
					}
				});
				
			} else {
				res.cause().printStackTrace();
				//TODO
			}
		});
	}
	
	public void updateArticle(RoutingContext rc) {
		setResponseHeader(rc.response());
		
		RequestParameters params = rc.get("parsedParameters");
		UpdateArticleRequest request = new UpdateArticleRequest(params.headerParameter("Authorization").getString().substring(7), params.pathParameter("articleid").getString(), params.formParameter("content").getString());
		
		vertx.eventBus().send("mongo.auth", new JsonObject().put("jwt", request.getToken()), new DeliveryOptions().addHeader("action", "user-authorization"), res -> {
			if (res.succeeded()) {
				
				JsonObject user = new JsonObject(res.result().body().toString());
				
				vertx.eventBus().send("mongo.article", new JsonObject().put("_id", request.getId()).put("content", request.getContent()).put("username", user.getString("username")), new DeliveryOptions().addHeader("action", "updateArticle"), response -> {
					if (response.succeeded()) {
											
						UpdateArticleResponse httpResponse = new UpdateArticleResponse("0", "");
						
						rc.response().end(new JsonObject().mapFrom(httpResponse).encodePrettily());
					} else {
						response.cause().printStackTrace();
						//TODO
					}
				});
				
			} else {
				res.cause().printStackTrace();
				//TODO
			}
		});
		
	}
	
	public void deleteArticle(RoutingContext rc) {
		setResponseHeader(rc.response());

		RequestParameters params = rc.get("parsedParameters");
		DeleteArticleRequest request = new DeleteArticleRequest(params.headerParameter("Authorization").getString().substring(7), params.pathParameter("articleid").getString());
		
		vertx.eventBus().send("mongo.auth", new JsonObject().put("jwt", request.getToken()), new DeliveryOptions().addHeader("action", "user-authorization"), res -> {
			if (res.succeeded()) {
				
				JsonObject user = new JsonObject(res.result().body().toString());
				
				vertx.eventBus().send("mongo.article", new JsonObject().put("_id", request.getId()).put("username", user.getString("username")), new DeliveryOptions().addHeader("action", "deleteArticle"), response -> {
					if (response.succeeded()) {
											
						UpdateArticleResponse httpResponse = new UpdateArticleResponse("0", "");
						
						rc.response().end(new JsonObject().mapFrom(httpResponse).encodePrettily());
					} else {
						response.cause().printStackTrace();
						//TODO
					}
				});
				
			} else {
				res.cause().printStackTrace();
				//TODO
			}
		});
	}
	
	private void setResponseHeader(HttpServerResponse response) {
		response
		.putHeader("Content-type", "application/json")
		.putHeader("Access-Control-Allow-Origin", "*")
		.putHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
	}
	
}
