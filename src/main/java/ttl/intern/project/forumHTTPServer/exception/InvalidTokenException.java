package ttl.intern.project.forumHTTPServer.exception;

public class InvalidTokenException extends RuntimeException {
	public InvalidTokenException() {
		super("Please login");
	}
}
