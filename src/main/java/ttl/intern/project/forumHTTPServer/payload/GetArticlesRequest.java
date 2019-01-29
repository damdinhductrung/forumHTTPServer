package ttl.intern.project.forumHTTPServer.payload;

public class GetArticlesRequest extends HttpRequestHeader {

	public GetArticlesRequest() {
		super();
	}

	public GetArticlesRequest(String token) {
		super(token);
	}
	
}
