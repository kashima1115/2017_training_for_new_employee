package account.form;

import org.apache.struts.validator.ValidatorForm;

public class AccountForm extends ValidatorForm{
	private String accountNumber;
	private String password;
	private String passno;

	private String bankcode;
	private String blanchcode;
	private String otherAccountNumber;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassno() {
		return passno;
	}
	public void setPassno(String passno) {
		this.passno = passno;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getBlanchcode() {
		return blanchcode;
	}
	public void setBlanchcode(String blanchcode) {
		this.blanchcode = blanchcode;
	}
	public String getOtherAccountNumber() {
		return otherAccountNumber;
	}
	public void setOtherAccountNumber(String otherAccountNumber) {
		this.otherAccountNumber = otherAccountNumber;
	}

}
