package business;

import data.UserDataService;

public class UserBusinessService {
	public boolean login(String username, String password) {
		UserDataService user = new UserDataService();
		if(user.login(username, password)) {
			return true;
		}
		else {
			return false;
		}
	}
}
