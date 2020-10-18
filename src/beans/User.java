package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;

@ManagedBean
@ViewScoped
public class User {
	@NotNull(message = "Please enter your first name. This is a required field.")
	String firstName;
	@NotNull(message = "Please enter your last name. This is a required field.")
	String lastName;
	@NotNull(message = "Please enter your email address. This is a required field.")
	String email;
	@NotNull(message = "Please enter your gender. This is a required field.")
	String gender;
	@NotNull(message = "Please enter your age. This is a required field.")
	int age;
	@NotNull(message = "Please enter your state. This is a required field.")
	String state;
	@NotNull(message = "Please enter your username. This is a required field.")
	String username;
	@NotNull(message = "Please enter your password. This is a required field.")
	String password;
	
	public User() {
		firstName = "";
		lastName = "";
		email = "";
		gender = "";
		age = 0;
		state = "";
		username = "";
		password = "";
	}
	
	public User(String firstName, String lastName, String email, String gender, int age, String state, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.state = state;
		this.username = username;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
