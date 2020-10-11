import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Login;
import business.UserBusinessService;

@ManagedBean
@ViewScoped
public class LoginController {

	public String onSubmit(Login login) {
		//Forwards to view along with the user bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("login", login);

		UserBusinessService userService = new UserBusinessService();
		if(userService.login(login.getUsername(), login.getPassword())) {
			return "homePage.xhtml";
		}
		return "loginFail.xhtml";
	}
	
}
