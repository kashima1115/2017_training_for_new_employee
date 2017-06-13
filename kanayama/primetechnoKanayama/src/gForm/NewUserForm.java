package gForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class NewUserForm extends ValidatorForm {
	private String newId;
	private String newPass;
	private String passCheck;

	public String getNewId() {
		return newId;
	}
	public void setNewId(String newId) {
		this.newId = newId;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getPassCheck() {
		return passCheck;
	}
	public void setPassCheck(String passCheck) {
		this.passCheck = passCheck;
	}

	 public ActionErrors validate(ActionMapping mapping
			 , HttpServletRequest request) {
		 ActionErrors errors = super.validate(mapping, request);
		 if(!this.newPass.equals(this.passCheck)){
			 errors.add("pass_notDouble_error",new ActionMessage("error.newPass.notDouble"));
		 }

		 return errors;
	 }

}
