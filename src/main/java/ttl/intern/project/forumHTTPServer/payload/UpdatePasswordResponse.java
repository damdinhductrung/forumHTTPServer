package ttl.intern.project.forumHTTPServer.payload;

public class UpdatePasswordResponse extends HttpResponseHeader {

	public UpdatePasswordResponse() {
		super();
	}

	public UpdatePasswordResponse(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
	
}
