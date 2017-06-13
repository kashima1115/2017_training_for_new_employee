package gForm;

import org.apache.struts.validator.ValidatorForm;

public class PlayGForm extends ValidatorForm {
	private int chipRate1;

	public PlayGForm(){
		this.chipRate1=5;
	}

	public int getChipRate1() {
		return chipRate1;
	}

	public void setChipRate1(int chipRate1) {
		this.chipRate1 = chipRate1;
	}

}
