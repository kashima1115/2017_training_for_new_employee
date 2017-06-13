package bank.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

public class BankForm extends ValidatorForm {
	private String accountNumber;
	private String password;
	private String name;
	private String balance;//getメソッドでlong型なので注意
	private String otherAccountNumber;
	private String katakana;
	private String furikomi;
	private Integer offset;
	private Integer view;
	private String fYear;
	private String fMonth;
	private String fDate;
	private Long furikomigaku;

	private String reservedDate;
	private String furigana;
	private String transCash;
	private String transID;

	private String tYear;//transhistory検索用
	private String tMonth;
	private String tDate;
	private String tnYear;
	private String tnMonth;
	private String tnDate;

	private List<LabelValueBean> hYearList;
	private List<LabelValueBean> yearList;
	private List<LabelValueBean> monthList;
	private List<LabelValueBean> dayList;

	public BankForm(){
		hYearList= new ArrayList<LabelValueBean>();
		yearList = new ArrayList<LabelValueBean>();
		monthList = new ArrayList<LabelValueBean>();
		dayList = new ArrayList<LabelValueBean>();

		Calendar cal=Calendar.getInstance();
 	    int year = cal.get(Calendar.YEAR);
 	    for(int i = year; i <= year - 5 ;i--){
 	    	hYearList.add(new LabelValueBean(String.valueOf(i),String.valueOf(i)));
 	 	}
 	    for(int i = year; i <= year + 1 ;i++){
 	    	yearList.add(new LabelValueBean(String.valueOf(i),String.valueOf(i)));
 	    }
 	    for(int i = 1; i <= 12; i++){
 	    	monthList.add(new LabelValueBean(String.valueOf(i),String.valueOf(i)));
 	    }
 	   for(int i = 1; i <= 31; i++){
	    	dayList.add(new LabelValueBean(String.valueOf(i),String.valueOf(i)));
	    }

   	    this.fYear = "";
		this.fMonth = "";
		this.fDate = "";

		this.tYear = "";
		this.tMonth = "";
		this.tDate = "";

		this.tnYear="";
		this.tnMonth="";
		this.tnDate="";
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBalance() {
		return Long.parseLong(balance);
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getOtherAccountNumber() {
		return otherAccountNumber;
	}
	public void setOtherAccountNumber(String otherAccountNumber) {
		this.otherAccountNumber = otherAccountNumber;
	}
	public String getKatakana() {
		return katakana;
	}
	public void setKatakana(String katakana) {
		this.katakana = katakana;
	}
	public String getFurikomi() {
		return furikomi;
	}
	public void setFurikomi(String furikomi) {
		this.furikomi = furikomi;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getView() {
		return view;
	}
	public void setView(Integer view) {
		this.view = view;
	}
	public String getfYear() {
		return fYear;
	}
	public void setfYear(String fYear) {
		this.fYear = fYear;
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

	public Long getFurikomigaku() {
		return furikomigaku;
	}
	public void setFurikomigaku(Long furikomigaku) {
		this.furikomigaku = furikomigaku;
	}
	public String getReservedDate() {
		return reservedDate;
	}
	public void setReservedDate(String reservedDate) {
		this.reservedDate = reservedDate;
	}
	public String getFurigana() {
		return furigana;
	}
	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}
	public String getTransCash() {
		return transCash;
	}
	public void setTransCash(String transCash) {
		this.transCash = transCash;
	}
	public String getTransID() {
		return transID;
	}
	public void setTransID(String transID) {
		this.transID = transID;
	}
	public List<LabelValueBean> getYearList() {
		return yearList;
	}

	public void setYearList(List<LabelValueBean> yearList) {
		this.yearList = yearList;
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
	public String gettYear() {
		return tYear;
	}
	public void settYear(String tYear) {
		this.tYear = tYear;
	}
	public String gettMonth() {
		return tMonth;
	}
	public void settMonth(String tMonth) {
		this.tMonth = tMonth;
	}
	public String gettDate() {
		return tDate;
	}
	public void settDate(String tDate) {
		this.tDate = tDate;
	}
	public String getTnYear() {
		return tnYear;
	}
	public void setTnYear(String tnYear) {
		this.tnYear = tnYear;
	}
	public String getTnMonth() {
		return tnMonth;
	}
	public void setTnMonth(String tnMonth) {
		this.tnMonth = tnMonth;
	}
	public String getTnDate() {
		return tnDate;
	}
	public void setTnDate(String tnDate) {
		this.tnDate = tnDate;
	}
	public List<LabelValueBean> gethYearList() {
		return hYearList;
	}
	public void sethYearList(List<LabelValueBean> hYearList) {
		this.hYearList = hYearList;
	}

}
