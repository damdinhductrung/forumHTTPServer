package ttl.intern.project.forumHTTPServer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import ttl.intern.project.forumHTTPServer.payload.LoginResponse;

public class MainVerticle extends AbstractVerticle {
	public static void main(String[] args) {
		LoginResponse loginResponse = new LoginResponse("123", "456", "789");

		System.out.println(loginResponse.toString());
	}

	@Override
	public void start(Future<Void> startFuture) throws Exception {

	}

}
