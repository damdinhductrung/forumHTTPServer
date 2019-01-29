package ttl.intern.project.forumHTTPServer.payload;

public class SaveArticleRequest extends HttpRequestHeader{
	private String title;
	private String content;
	
	public SaveArticleRequest(String token, String title, String content) {
		super(token);
		this.title = title;
		this.content = content;
	}

	public SaveArticleRequest() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
