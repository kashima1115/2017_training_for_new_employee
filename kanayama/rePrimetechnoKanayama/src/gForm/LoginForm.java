package gForm;

import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm {
	private String loginId;
	private String pass;


	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}
