package ttl.intern.project.forumHTTPServer.payload;

public class DeleteArticleResponse extends HttpResponseHeader {

	public DeleteArticleResponse() {
		super();
	}

	public DeleteArticleResponse(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
	
}
