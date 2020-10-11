package data;

public class UserDataService {
	public boolean login(String username, String password) {
		if (username.compareTo("dev") == 0 && password.compareTo("test") == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
