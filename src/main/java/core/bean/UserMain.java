package core.bean;

public class UserMain {

	public UserMain( String user_name, String last_name) {
		this.user_name=user_name;
		this.last_name=last_name;
	}

	private String user_name;
	private String last_name;

	public String getUsername() {
		return user_name;
	}
	public void setUsername(String user_name) {
		this.user_name = user_name;
	}
	public String getlastname() {
		return last_name;
	}
	public void setlastname(String last_name) {
		this.last_name = last_name;
	}

}
