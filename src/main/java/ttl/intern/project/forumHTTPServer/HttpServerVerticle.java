package ttl.intern.project.forumHTTPServer;

import com.hazelcast.com.eclipsesource.json.Json;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.RequestParameters;
import io.vertx.ext.web.handler.BodyHandler;
import ttl.intern.project.forumHTTPServer.exception.LoginException;
import ttl.intern.project.forumHTTPServer.payload.LoginRequest;
import ttl.intern.project.forumHTTPServer.payload.LoginResponse;
import ttl.intern.project.forumHTTPServer.payload.SignupRequest;
import ttl.intern.project.forumHTTPServer.payload.SignupResponse;
import ttl.intern.project.forumHTTPServer.validation.DeleteArticleRequestValidationHandler;
import ttl.intern.project.forumHTTPServer.validation.GetArticleRequestValidationHandler;
import ttl.intern.project.forumHTTPServer.validation.GetArticlesRequestValidationHandler;
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
		
		router.get("/").handler(new GetArticlesRequestValidationHandler()).handler(this::getArticles);
		router.get("/articles").handler(new GetArticlesRequestValidationHandler()).handler(this::getArticles);
		router.get("/articles/:articleid").handler(new GetArticleRequestValidationHandler()).handler(this::getArticle);
		
		
		
		server.requestHandler(router).listen(8080);
	}
	
	public void login(RoutingContext rc) {
		RequestParameters params = rc.get("parsedParameters");
		LoginRequest login = new LoginRequest(params.formParameter("username").getString(), params.formParameter("password").getString());
		
		vertx.eventBus().send("mongo.auth", JsonObject.mapFrom(login), new DeliveryOptions().addHeader("action", "user-login"), res -> {
			if (res.succeeded()) {
				LoginResponse response = new LoginResponse(null, null, res.result().body().toString());
				rc.response().putHeader("content-type", "application/json").end(JsonObject.mapFrom(response).encodePrettily());
			} else {
				//TODO
			}
		});
	}
	
	public void signup(RoutingContext rc) {
		RequestParameters params = rc.get("parsedParameters");
		SignupRequest signup = new SignupRequest(params.formParameter("username").getString(), params.formParameter("password").getString());
		
		vertx.eventBus().send("mongo.auth", JsonObject.mapFrom(signup), new DeliveryOptions().addHeader("action", "user-signup"), res -> {
			if (res.succeeded()) {
				SignupResponse response = new SignupResponse();
				rc.response().putHeader("content-type", "application/json").end(JsonObject.mapFrom(response).encodePrettily());
			} else {
				//TODO
			}
		});
				
	}
	
	public void getArticles(RoutingContext rc) {
		rc.response().end("get articles");
		//return list of articles
	}
	
	public void getArticle(RoutingContext rc) {
		rc.response().end("get article");
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
	
	
	
}
