package ttl.intern.project.forumHTTPServer.payload;

public class GetArticleRequest extends HttpRequestHeader {
	private String id;
	
	public GetArticleRequest() {
		super();
	}

	public GetArticleRequest(String token, String id) {
		super(token);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
