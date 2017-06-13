package outstanding;
import java.io.Serializable;
public class OutstandingBean implements Serializable{
	private String reservedDate;
	private String otherID;
	private String furigana;
	private String otherAccountNumber;
	private String transCash;//getメソッドはlong型なので注意
	private String transID;
	public String getReservedDate() {
		return reservedDate;
	}
	public void setReservedDate(String reservedDate) {
		this.reservedDate = reservedDate;
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
	public String getOtherAccountNumber() {
		return otherAccountNumber;
	}
	public void setOtherAccountNumber(String otherAccountNumber) {
		this.otherAccountNumber = otherAccountNumber;
	}
	public long getTransCash() {
		return Long.parseLong(transCash);
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
}
