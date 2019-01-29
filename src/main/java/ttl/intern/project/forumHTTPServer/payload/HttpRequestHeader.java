package ttl.intern.project.forumHTTPServer.payload;

public class HttpRequestHeader {
	private String jwt;

	public HttpRequestHeader(String token) {
		this.jwt = token;
	}

	public HttpRequestHeader() {
		super();
	}

	public String getToken() {
		return jwt;
	}

	public void setToken(String token) {
		this.jwt = token;
	}
}
