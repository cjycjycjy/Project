package sherlockphonez.login.model;

public class User {
	String phoneNum;
	String email;
	String name;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String phoneNum, String email, String name) {
		// TODO Auto-generated constructor stub
		this.phoneNum = phoneNum;
		this.email = email;
		this.name = name;
	}

	public String getPhoneNum(){
		return phoneNum;
	}
	public String getEmail(){
		return email;
	}
	public String getName() {
		return name;
	}
}
