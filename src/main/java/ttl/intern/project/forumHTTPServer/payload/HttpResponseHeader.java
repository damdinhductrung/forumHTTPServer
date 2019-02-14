package ttl.intern.project.forumHTTPServer.payload;

public class HttpResponseHeader {
	private String errorCode;
	private String message;
	
	public HttpResponseHeader() {
		
	}
	
	public HttpResponseHeader(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.message = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return message;
	}

	public void setErrorMessage(String errorMessage) {
		this.message = errorMessage;
	}
}
