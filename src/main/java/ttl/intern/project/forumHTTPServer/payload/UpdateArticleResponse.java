package ttl.intern.project.forumHTTPServer.payload;

public class UpdateArticleResponse extends HttpResponseHeader{

	public UpdateArticleResponse() {
		super();
	}

	public UpdateArticleResponse(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
	
}
