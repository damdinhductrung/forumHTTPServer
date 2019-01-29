package ttl.intern.project.forumHTTPServer.payload;

import java.util.List;

import ttl.intern.project.forumHTTPServer.model.Article;

public class GetArticlesResponse extends HttpResponseHeader {
	private List<Article> articlesList;

	public GetArticlesResponse() {
		super();
	}

	public GetArticlesResponse(String errorCode, String errorMessage, List<Article> articlesList) {
		super(errorCode, errorMessage);
		this.articlesList = articlesList;
	}

	public List<Article> getArticlesList() {
		return articlesList;
	}

	public void setArticlesList(List<Article> articlesList) {
		this.articlesList = articlesList;
	}
}
