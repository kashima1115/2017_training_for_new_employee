package gForm;

import org.apache.struts.validator.ValidatorForm;

public class PlayGForm extends ValidatorForm {
	private int chipRate1;
	private int chipRate2;

	public PlayGForm(){
		this.chipRate1=5;
		this.chipRate2=50;
	}

	public int getChipRate1() {
		return chipRate1;
	}

	public void setChipRate1(int chipRate1) {
		this.chipRate1 = chipRate1;
	}

	public int getChipRate2() {
		return chipRate2;
	}

	public void setChipRate2(int chipRate2) {
		this.chipRate2 = chipRate2;
	}

}
