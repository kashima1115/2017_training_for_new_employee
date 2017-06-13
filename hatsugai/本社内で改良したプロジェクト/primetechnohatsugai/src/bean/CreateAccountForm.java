package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

public class CreateAccountForm extends ValidatorForm {
	private String accountNumber;
	private String password;
	private String passwordconf;
	private String name;
	private String katakana;
	private String fMonth;
	private String fDate;
	private String bYear;
	private String gender;
	private String uPrefAddress;
	private String uCityAddress;
	private String uConstructure;
	private String tel;
	private String mobile;
	private String mail;
	private String zokusei;
	private String passno;
	private String passnoconf;
	private String worknm;
	private String wPrefAddress;
	private String wCityAddress;
	private String wConstructure;
	private String worktel;

	private List<LabelValueBean> monthList;
	private List<LabelValueBean> dayList;
	private List<LabelValueBean> bYearList;

	public CreateAccountForm(){
		monthList = new ArrayList<LabelValueBean>();
		dayList = new ArrayList<LabelValueBean>();
		bYearList=new ArrayList<LabelValueBean>();

		Calendar cal=Calendar.getInstance();
 	    int year = cal.get(Calendar.YEAR);
 	   for(int i = 1; i <= 12; i++){
	    	monthList.add(new LabelValueBean(String.valueOf(i),String.valueOf(i)));
	    }
	   for(int i = 1; i <= 31; i++){
	    	dayList.add(new LabelValueBean(String.valueOf(i),String.valueOf(i)));
	    }
	   for(int i = year;i>=year-130;i--){
		   bYearList.add(new LabelValueBean(String.valueOf(i),String.valueOf(i)));
	   }
	   this.fMonth = "";
		this.fDate = "";
		this.bYear = "";
	}

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

	public String getPasswordconf() {
		return passwordconf;
	}

	public void setPasswordconf(String passwordconf) {
		this.passwordconf = passwordconf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKatakana() {
		return katakana;
	}

	public void setKatakana(String katakana) {
		this.katakana = katakana;
	}

	public String getfMonth() {
		return fMonth;
	}

	public void setfMonth(String fMonth) {
		this.fMonth = fMonth;
	}

	public String getfDate() {
		return fDate;
	}

	public void setfDate(String fDate) {
		this.fDate = fDate;
	}

	public String getbYear() {
		return bYear;
	}

	public void setbYear(String bYear) {
		this.bYear = bYear;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getuPrefAddress() {
		return uPrefAddress;
	}

	public void setuPrefAddress(String uPrefAddress) {
		this.uPrefAddress = uPrefAddress;
	}

	public String getuCityAddress() {
		return uCityAddress;
	}

	public void setuCityAddress(String uCityAddress) {
		this.uCityAddress = uCityAddress;
	}

	public String getuConstructure() {
		return uConstructure;
	}

	public void setuConstructure(String uConstructure) {
		this.uConstructure = uConstructure;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getZokusei() {
		return zokusei;
	}

	public void setZokusei(String zokusei) {
		this.zokusei = zokusei;
	}

	public String getPassno() {
		return passno;
	}

	public void setPassno(String passno) {
		this.passno = passno;
	}

	public String getPassnoconf() {
		return passnoconf;
	}

	public void setPassnoconf(String passnoconf) {
		this.passnoconf = passnoconf;
	}

	public String getWorknm() {
		return worknm;
	}

	public void setWorknm(String worknm) {
		this.worknm = worknm;
	}

	public String getwPrefAddress() {
		return wPrefAddress;
	}

	public void setwPrefAddress(String wPrefAddress) {
		this.wPrefAddress = wPrefAddress;
	}

	public String getwCityAddress() {
		return wCityAddress;
	}

	public void setwCityAddress(String wCityAddress) {
		this.wCityAddress = wCityAddress;
	}

	public String getwConstructure() {
		return wConstructure;
	}

	public void setwConstructure(String wConstructure) {
		this.wConstructure = wConstructure;
	}

	public String getWorktel() {
		return worktel;
	}

	public void setWorktel(String worktel) {
		this.worktel = worktel;
	}

	public List<LabelValueBean> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<LabelValueBean> monthList) {
		this.monthList = monthList;
	}

	public List<LabelValueBean> getDayList() {
		return dayList;
	}

	public void setDayList(List<LabelValueBean> dayList) {
		this.dayList = dayList;
	}

	public List<LabelValueBean> getbYearList() {
		return bYearList;
	}

	public void setbYearList(List<LabelValueBean> bYearList) {
		this.bYearList = bYearList;
	}



}
