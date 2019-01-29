package ttl.intern.project.forumHTTPServer.payload;

public class UpdateArticleRequest extends HttpRequestHeader{
	private String id;
	private String content;
	
	public UpdateArticleRequest() {
		super();
	}

	public UpdateArticleRequest(String token, String id, String content) {
		super(token);
		this.id = id;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
