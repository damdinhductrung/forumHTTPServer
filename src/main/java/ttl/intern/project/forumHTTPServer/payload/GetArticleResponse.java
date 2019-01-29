package ttl.intern.project.forumHTTPServer.payload;

import ttl.intern.project.forumHTTPServer.model.Article;

public class GetArticleResponse extends HttpResponseHeader {
	private Article article;

	public GetArticleResponse(String errorCode, String errorMessage, Article article) {
		super(errorCode, errorMessage);
		this.article = article;
	}

	public GetArticleResponse() {
		super();
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}
