package ttl.intern.project.forumHTTPServer.payload;

public class SaveArticleResponse extends HttpResponseHeader{
	private String id;

	public SaveArticleResponse() {
		super();
	}

	public SaveArticleResponse(String errorCode, String errorMessage, String id) {
		super(errorCode, errorMessage);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
