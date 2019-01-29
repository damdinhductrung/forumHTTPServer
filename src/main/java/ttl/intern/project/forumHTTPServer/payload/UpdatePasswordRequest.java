package ttl.intern.project.forumHTTPServer.payload;

public class UpdatePasswordRequest extends HttpRequestHeader{
	private String oldPassword;
	private String newPassword;
	
	public UpdatePasswordRequest(String jwt, String oldPassword, String newPassword) {
		super(jwt);
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
