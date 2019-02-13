package ttl.intern.project.forumHTTPServer.exception;

public class LoginException extends RuntimeException {
	public LoginException() {
		super("Invalid username or password");
	}
	
	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}
}
