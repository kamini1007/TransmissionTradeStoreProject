package trade.transmission.store;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Kamini Rai
 *
 */
public class TradeTransmissionStore {
	public static HashMap<String, Trade> tradeData = new HashMap<String, Trade>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Add Trade
	 * 
	 * @param trade
	 * @throws Exception
	 */
	public void addTrade(Trade trade) throws Exception {
		// Checks version
		boolean version_check = checkVersion(trade);
		if (version_check) {
			tradeData.replace(trade.getTradeId() + trade.getVersion(), trade);
		}

		if (tradeData.containsKey(trade.getTradeId() + trade.getVersion())) {
			if (checkMaturityDate(trade.getMaturityDate())) {
				
				tradeData.replace(trade.getTradeId() + trade.getVersion(), trade);
				System.out.println(trade.getTradeId() + " is added to the Store");
			} else {
				System.out.println("Not able to add " + trade.getTradeId()
						+ " in the store as maturity date is lower than current date");
			}
		} else {

			if (checkMaturityDate(trade.getMaturityDate())) {
				
				tradeData.put(trade.getTradeId() + trade.getVersion(), trade);
				System.out.println(trade.getTradeId() + " is added to the Store");

			} else {
				System.out.println("Not able to add " + trade.getTradeId()
						+ " in the store as maturity date is lower than current date");
			}
		}

	}

	/**
	 * Check if the lower version is being received by the store it will reject
	 * the trade and throw an exception. If the version is same it will override
	 * the existing record
	 * 
	 * @param trade
	 * @throws Exception
	 */
	public boolean checkVersion(Trade trade) throws Exception {
		boolean check_version = false;
		for (String strKey : tradeData.keySet()) {
			if (tradeData.containsKey(trade.getVersion())) {
				if (trade.getVersion() < tradeData.get(strKey).getVersion()) {
					throw new Exception(trade.getVersion() + " is less than " + tradeData.get(strKey).getVersion());
				} else if (trade.getVersion() == tradeData.get(strKey).getVersion()) {
					check_version = true;
				}
			}
		}
		return check_version;
	}

	/**
	 * Fetch the Trade
	 * 
	 * @param tId
	 * @return Trade
	 * @throws Exception
	 */
	public Trade getTrade(String tradeId) throws Exception {
		if (tradeData.containsKey(tradeId)) {
			return tradeData.get(tradeId);
		}
		throw new Exception("Trade with " + tradeId + " not Found");

	}

	/**
	 * Check Maturity date is less then todays date.
	 * 
	 * @param maturityDate
	 * @return boolean
	 */
	public boolean checkMaturityDate(Date maturityDate) {
		Date todayDate = new Date();

		if (sdf.format(todayDate).compareTo(sdf.format(maturityDate)) > 0) {
			return false;
		}
		return true;
	}

	/**
	 * Check Expired date is less then todays date.
	 * 
	 * @param maturityDate
	 * @return boolean
	 */
	public void checkExpiredDates() {
		Date currentDate = new Date();
		sdf.format(currentDate);
		for (String strKey : tradeData.keySet()) {
			if (currentDate.compareTo(tradeData.get(strKey).getMaturityDate()) > 0) {
				Trade t = tradeData.get(strKey);
				t.setExpired('Y');
				tradeData.replace(strKey, t);
			}
		}

	}

	public void printTrade() {
		for (String tId : tradeData.keySet()) {
			System.out.println(tradeData.get(tId).toString());
		}
	}

	static {
		Date todaysDate = Calendar.getInstance().getTime();
		Date createdDate = Calendar.getInstance().getTime();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		TradeTransmissionStore transmission = new TradeTransmissionStore();
		sd.format(todaysDate);
		Date maturityDate;
		try {
			maturityDate = sd.parse("20/05/2020");
			Trade t1 = new Trade("T1", 1, "CP-1", "B1", maturityDate, todaysDate, 'N');
			tradeData.put(t1.getTradeId() + t1.getVersion(), t1);
			maturityDate = sd.parse("20/05/2021");
			Trade t2 = new Trade("T2", 2, "CP-2", "B1", maturityDate, todaysDate, 'N');
			tradeData.put(t2.getTradeId() + t2.getVersion(), t2);
			maturityDate = sd.parse("20/05/2021");
			createdDate = sd.parse("14/03/2015");
			Trade t3 = new Trade("T2", 1, "CP-1", "B1", maturityDate, createdDate, 'N');
			tradeData.put(t3.getTradeId() + t3.getVersion(), t3);
			maturityDate = sd.parse("20/05/2014");
			Trade t4 = new Trade("T3", 3, "CP-3", "B2", maturityDate, todaysDate, 'N');
			tradeData.put(t4.getTradeId() + t4.getVersion(), t4);
			transmission.checkExpiredDates();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
