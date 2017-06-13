package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

public class HistorySearchForm extends ValidatorForm {
	private String tYear;//transhistory検索用
	private String tMonth;
	private String tDate;
	private String tnYear;
	private String tnMonth;
	private String tnDate;

	private List<LabelValueBean> yearList;
	private List<LabelValueBean> monthList;
	private List<LabelValueBean> dayList;

	public HistorySearchForm(){
		yearList = new ArrayList<LabelValueBean>();
		monthList = new ArrayList<LabelValueBean>();
		dayList = new ArrayList<LabelValueBean>();

		Calendar cal=Calendar.getInstance();
 	    int year = cal.get(Calendar.YEAR);
 	    for(int i = year; i <= year - 5 ;i--){
 	    yearList.add(new LabelValueBean(String.valueOf(i),String.valueOf(i)));
 	    }
 	    for(int i = 1; i <= 12; i++){
 	    	monthList.add(new LabelValueBean(String.valueOf(i),String.valueOf(i)));
 	    }
 	    for(int i = 1; i <= 31; i++){
	    	dayList.add(new LabelValueBean(String.valueOf(i),String.valueOf(i)));
	    }

   	    this.tYear = "";
		this.tMonth = "";
		this.tDate = "";

		this.tnYear="";
		this.tnMonth="";
		this.tnDate="";
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




}
