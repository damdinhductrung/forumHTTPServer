package ttl.intern.project.forumHTTPServer.payload;

public class LoginResponse extends HttpResponseHeader{
	private String token;
	
	public LoginResponse() {
		super();
	}

	public LoginResponse(String errorCode, String errorMessage, String token) {
		super(errorCode, errorMessage);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
}
