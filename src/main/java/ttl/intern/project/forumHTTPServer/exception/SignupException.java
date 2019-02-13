package ttl.intern.project.forumHTTPServer.exception;

public class SignupException extends RuntimeException {
	String username;
	
	public SignupException(String username) {
		super("Username: " + username + " has already been taken");
		this.username = username;
	}
	
	public SignupException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
