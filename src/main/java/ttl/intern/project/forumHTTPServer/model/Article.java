package ttl.intern.project.forumHTTPServer.model;

public class Article {
	private String id;
	private String title;
	private String content;
	private String createdDay;
	private String username;
	
	public Article() {
		
	}

	public Article(String id, String title, String content, String createdDay, String username) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdDay = createdDay;
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCreatedDay() {
		return createdDay;
	}

	public void setCreatedDay(String createdDay) {
		this.createdDay = createdDay;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	
}
