package mogipuro;

import org.apache.struts.action.ActionForm;

public class PointForm extends ActionForm {

	private int point;
	private String logid;

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

}
