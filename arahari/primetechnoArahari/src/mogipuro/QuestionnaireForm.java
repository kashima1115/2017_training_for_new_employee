package mogipuro;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class QuestionnaireForm extends ValidatorForm {

	private String logid;
	private String logpass;
	private String id;
	private String pass;
	private String passkaku;

	public QuestionnaireForm() {

		id = null;
		pass = null;
		logid = null;
		logpass = null;
		passkaku = null;
	}

	public String getPasskaku() {
		return passkaku;
	}

	public void setPasskaku(String passkaku) {
		this.passkaku = passkaku;
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getLogpass() {
		return logpass;
	}

	public void setLogpass(String logpass) {
		this.logpass = logpass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	// validateメソッドの実装
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		// ActionErrorsオブジェクト取得
		ActionErrors errors = super.validate(mapping, request);

		if (!pass.equals(passkaku)) {
			errors.add("itti_error", new ActionMessage("error.itti"));
		}

		if(id.length() > 50 || pass.length() > 50){
			errors.add("word_error", new ActionMessage("error.word"));
		}



		// ActionErrorsオブジェクトをreturn
		return errors;
	}

}
