package mogipuro;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm {

	private String id;
	private String pass;
	private String passkaku;
	private String logid;
	private String logpass;
	private int point;

	public LoginForm() {

		id = null;
		pass = null;
		passkaku = null;
		logid = null;
		logpass = null;

	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
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

	// validateメソッドの実装
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		// ActionErrorsオブジェクト取得
		ActionErrors errors = super.validate(mapping, request);

		if (logid == null || logid.equals("") || logpass.equals("")
				|| logpass == null) {
			errors.add("hissu_error", new ActionMessage("error.hissu"));
		}

		// ActionErrorsオブジェクトをreturn
		return errors;
	}

}
