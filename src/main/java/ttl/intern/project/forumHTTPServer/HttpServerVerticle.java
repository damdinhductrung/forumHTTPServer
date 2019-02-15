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
import ttl.intern.project.forumHTTPServer.payload.GetArticleResponse;
import ttl.intern.project.forumHTTPServer.payload.GetArticlesResponse;
import ttl.intern.project.forumHTTPServer.payload.LoginRequest;
import ttl.intern.project.forumHTTPServer.payload.LoginResponse;
import ttl.intern.project.forumHTTPServer.payload.SignupRequest;
import ttl.intern.project.forumHTTPServer.payload.SignupResponse;
import ttl.intern.project.forumHTTPServer.validation.DeleteArticleRequestValidationHandler;
import ttl.intern.project.forumHTTPServer.validation.LoginRequestValidationHandler;
import ttl.intern.project.forumHTTPServer.validation.SaveArticleRequestValidationHandler;
import ttl.intern.project.forumHTTPServer.validation.SignupRequestValidationHandler;

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
		router.put("/articles/:articleid").handler(new SaveArticleRequestValidationHandler()).handler(this::updateArticle);
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
		vertx.eventBus().send("mongo.article", new JsonObject(), new DeliveryOptions().addHeader("action", "getArticle"), res -> {
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
		//return message
	}
	
	public void updateArticle(RoutingContext rc) {
		rc.response().end("update article");
		//return message
	}
	
	public void deleteArticle(RoutingContext rc) {
		//return message
	}
	
	private void setResponseHeader(HttpServerResponse response) {
		response
		.putHeader("Content-type", "application/json")
		.putHeader("Access-Control-Allow-Origin", "*")
		.putHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
	}
	
}
