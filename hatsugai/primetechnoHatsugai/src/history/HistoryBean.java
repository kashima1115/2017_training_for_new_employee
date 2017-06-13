package history;
import java.io.Serializable;
public class HistoryBean implements Serializable{
	private String transDate;
	private String otherID;
	private String furigana;
	private String otherAccountNo;
	private String transCash;//getメソッドの型はlong型なので注意
	private String IOFlag;
	private String lastBalance;

	public int getLastBalance() {
		return Integer.parseInt(lastBalance);
	}
	public void setLastBalance(String lastBalance) {
		this.lastBalance = lastBalance;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getOtherID() {
		return otherID;
	}
	public void setOtherID(String otherID) {
		this.otherID = otherID;
	}
	public String getFurigana() {
		return furigana;
	}
	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}
	public String getOtherAccountNo() {
		return otherAccountNo;
	}
	public void setOtherAccountNo(String otherAccountNo) {
		this.otherAccountNo = otherAccountNo;
	}
	public Long getTransCash() {
		return Long.parseLong(transCash);
	}
	public void setTransCash(String transCash) {
		this.transCash = transCash;
	}
	public String getIOFlag() {
		return IOFlag;
	}
	public void setIOFlag(String iOFlag) {
		IOFlag = iOFlag;
	}

}
