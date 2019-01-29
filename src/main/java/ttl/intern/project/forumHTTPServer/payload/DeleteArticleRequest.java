package ttl.intern.project.forumHTTPServer.payload;

public class DeleteArticleRequest extends HttpRequestHeader {
	private String id;

	public DeleteArticleRequest(String token, String id) {
		super(token);
		this.id = id;
	}

	public DeleteArticleRequest() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
