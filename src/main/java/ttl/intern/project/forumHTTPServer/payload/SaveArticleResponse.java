package ttl.intern.project.forumHTTPServer.payload;

public class SaveArticleResponse extends HttpResponseHeader{

	public SaveArticleResponse() {
		super();
	}

	public SaveArticleResponse(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
	
}
