package mogipuro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;

public class MakeForm extends ValidatorForm {

	private String ankName;
	private String ankEnd;
	private int answered;
	private String point;
	private String type;
	private String year;
	private String month;
	private String day;
	private String question1;
	private String question2;
	private String question3;
	private String question4;
	private String question5;
	private String choices11;
	private String choices12;
	private String choices13;
	private String choices14;
	private String choices21;
	private String choices22;
	private String choices23;
	private String choices24;
	private String choices31;
	private String choices32;
	private String choices33;
	private String choices34;
	private String choices41;
	private String choices42;
	private String choices43;
	private String choices44;
	private String choices51;
	private String choices52;
	private String choices53;
	private String choices54;
	private String logid;
	private String logpass;
	private String[] choices;
	private String[] check;
	private String radio;
	private String question;
	private String choice;
	private String select;
	private String week;
	private String endName;

	// 年月日、時分のListを定義（LabelValueBean型）
	private List<LabelValueBean> yearList;
	private List<LabelValueBean> monthList;
	private List<LabelValueBean> dayList;

	public MakeForm() {

		ankName = null;
		ankEnd = null;
		point = null;
		type = null;
		year = null;
		month = null;
		day = null;
		question1 = null;
		question2 = null;
		question3 = null;
		question4 = null;
		question5 = null;
		choices11 = null;
		choices12 = null;
		choices13 = null;
		choices14 = null;
		choices21 = null;
		choices22 = null;
		choices23 = null;
		choices24 = null;
		choices31 = null;
		choices32 = null;
		choices33 = null;
		choices34 = null;
		choices41 = null;
		choices42 = null;
		choices43 = null;
		choices44 = null;
		choices51 = null;
		choices52 = null;
		choices53 = null;
		choices54 = null;
		logid = null;
		logpass = null;
		choices = null;
		radio = null;
		choice = null;
		question = null;
		week = null;
		endName = null;

		// 年月日、時分のListをインスタンス化して中身を生成
		// →JSP側もoptionsCollectionタグにする

		yearList = new ArrayList<LabelValueBean>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = year; i <= year + 5; ++i) {
			yearList.add(new LabelValueBean(String.valueOf(i), String
					.valueOf(i)));

		}
		monthList = new ArrayList<LabelValueBean>();

		for (int i = 1; i <= 12; ++i) {
			monthList.add(new LabelValueBean(String.valueOf(i), String
					.valueOf(i)));

		}

		dayList = new ArrayList<LabelValueBean>();

		for (int i = 1; i <= 31; ++i) {
			dayList.add(new LabelValueBean(String.valueOf(i), String.valueOf(i)));

		}

	}


	public String getEndName() {
		return endName;
	}


	public void setEndName(String endName) {
		this.endName = endName;
	}


	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String[] getCheck() {
		return check;
	}

	public void setCheck(String[] check) {
		this.check = check;
	}

	public String[] getChoices() {
		return choices;
	}

	public void setChoices(String[] choices) {
		this.choices = choices;
	}

	public String getAnkName() {
		return ankName;
	}

	public void setAnkName(String ankName) {
		this.ankName = ankName;
	}

	public String getAnkEnd() {
		return ankEnd;
	}

	public void setAnkEnd(String ankEnd) {
		this.ankEnd = ankEnd;
	}

	public int getAnswered() {
		return answered;
	}

	public void setAnswered(int answered) {
		this.answered = answered;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	public String getQuestion4() {
		return question4;
	}

	public void setQuestion4(String question4) {
		this.question4 = question4;
	}

	public String getQuestion5() {
		return question5;
	}

	public void setQuestion5(String question5) {
		this.question5 = question5;
	}

	public String getChoices11() {
		return choices11;
	}

	public void setChoices11(String choices11) {
		this.choices11 = choices11;
	}

	public String getChoices12() {
		return choices12;
	}

	public void setChoices12(String choices12) {
		this.choices12 = choices12;
	}

	public String getChoices13() {
		return choices13;
	}

	public void setChoices13(String choices13) {
		this.choices13 = choices13;
	}

	public String getChoices14() {
		return choices14;
	}

	public void setChoices14(String choices14) {
		this.choices14 = choices14;
	}

	public String getChoices21() {
		return choices21;
	}

	public void setChoices21(String choices21) {
		this.choices21 = choices21;
	}

	public String getChoices22() {
		return choices22;
	}

	public void setChoices22(String choices22) {
		this.choices22 = choices22;
	}

	public String getChoices23() {
		return choices23;
	}

	public void setChoices23(String choices23) {
		this.choices23 = choices23;
	}

	public String getChoices24() {
		return choices24;
	}

	public void setChoices24(String choices24) {
		this.choices24 = choices24;
	}

	public String getChoices31() {
		return choices31;
	}

	public void setChoices31(String choices31) {
		this.choices31 = choices31;
	}

	public String getChoices32() {
		return choices32;
	}

	public void setChoices32(String choices32) {
		this.choices32 = choices32;
	}

	public String getChoices33() {
		return choices33;
	}

	public void setChoices33(String choices33) {
		this.choices33 = choices33;
	}

	public String getChoices34() {
		return choices34;
	}

	public void setChoices34(String choices34) {
		this.choices34 = choices34;
	}

	public String getChoices41() {
		return choices41;
	}

	public void setChoices41(String choices41) {
		this.choices41 = choices41;
	}

	public String getChoices42() {
		return choices42;
	}

	public void setChoices42(String choices42) {
		this.choices42 = choices42;
	}

	public String getChoices43() {
		return choices43;
	}

	public void setChoices43(String choices43) {
		this.choices43 = choices43;
	}

	public String getChoices44() {
		return choices44;
	}

	public void setChoices44(String choices44) {
		this.choices44 = choices44;
	}

	public String getChoices51() {
		return choices51;
	}

	public void setChoices51(String choices51) {
		this.choices51 = choices51;
	}

	public String getChoices52() {
		return choices52;
	}

	public void setChoices52(String choices52) {
		this.choices52 = choices52;
	}

	public String getChoices53() {
		return choices53;
	}

	public void setChoices53(String choices53) {
		this.choices53 = choices53;
	}

	public String getChoices54() {
		return choices54;
	}

	public void setChoices54(String choices54) {
		this.choices54 = choices54;
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

		if (question1 == null || question1.equals("") || choices11.equals("")
				|| choices11 == null || choices12.equals("")
				|| choices12 == null) {
			errors.add("quest_error", new ActionMessage("error.quest"));
		}

		if (year.equals("") || month.equals("") || day.equals("")) {
			errors.add("kikan_error", new ActionMessage("error.kikan"));
		} else {

			if (Integer.parseInt(year) < Calendar.getInstance().get(
					Calendar.YEAR)) {
				// 時刻逆転の入力チェック
				errors.add("date", new ActionMessage("error.over"));
			} else if (Integer.parseInt(year) == Calendar.getInstance().get(
					Calendar.YEAR)
					&& Integer.parseInt(month) < (Calendar.getInstance().get(
							Calendar.MONTH) + 1)) {
				errors.add("date", new ActionMessage("error.over"));
			} else if (Integer.parseInt(year) == Calendar.getInstance().get(
					Calendar.YEAR)
					&& Integer.parseInt(month) == (Calendar.getInstance().get(
							Calendar.MONTH) + 1)
					&& Integer.parseInt(day) < Calendar.getInstance().get(
							Calendar.DATE)) {
				errors.add("date", new ActionMessage("error.over"));
			}
		}


		if(ankName.length() > 50 || question1.length() > 50
				|| choices11.length() > 50 || choices12.length() >50
				|| choices13.length() > 50 || choices14.length() >50
				|| question2.length() > 50
				|| choices21.length() > 50 || choices22.length() >50
				|| choices23.length() > 50 || choices24.length() >50
				|| question3.length() > 50
				|| choices31.length() > 50 || choices32.length() >50
				|| choices33.length() > 50 || choices34.length() >50
				|| question4.length() > 50
				|| choices41.length() > 50 || choices42.length() >50
				|| choices43.length() > 50 || choices44.length() >50
				|| question5.length() > 50
				|| choices51.length() > 50 || choices52.length() >50
				|| choices53.length() > 50 || choices54.length() >50){
			errors.add("word_error", new ActionMessage("error.word"));
		}

		// ActionErrorsオブジェクトをreturn
		return errors;
	}

}
