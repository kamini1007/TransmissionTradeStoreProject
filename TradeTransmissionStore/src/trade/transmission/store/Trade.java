package trade.transmission.store;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kamini Rai Basic structure of Trade
 *
 */
public class Trade {

	private String tradeId;
	private int version;
	private String counterPartyId;
	private String bookId;
	private Date maturityDate;
	private Date createdDate;
	private char expired;

	public Trade(String tid, int ver, String cpid, String bkid, Date mdate, Date cdate, char exp) {
		tradeId = tid;
		version = ver;
		counterPartyId = cpid;
		bookId = bkid;
		maturityDate = mdate;
		createdDate = cdate;
		expired = exp;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
		return "Trade [tradeId=" + tradeId + ", version=" + version + ", counterPartyId=" + counterPartyId + ", bookId="
				+ bookId + ", maturityDate=" + sdformat.format(maturityDate) + ", createdDate="
				+ sdformat.format(createdDate) + ", expired=" + expired + "]";
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public char getExpired() {
		return expired;
	}

	public void setExpired(char expired) {
		this.expired = expired;
	}

}
