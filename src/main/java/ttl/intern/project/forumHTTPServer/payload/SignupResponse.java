package ttl.intern.project.forumHTTPServer.payload;

public class SignupResponse extends HttpResponseHeader{
	public SignupResponse() {
		
	}
	
	public SignupResponse(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
}
