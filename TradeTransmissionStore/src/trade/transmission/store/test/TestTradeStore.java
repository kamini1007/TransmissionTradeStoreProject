package trade.transmission.store.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import trade.transmission.store.Trade;
import trade.transmission.store.TradeTransmissionStore;

public class TestTradeStore {

	TradeTransmissionStore transmission = new TradeTransmissionStore();
	Date todaysDate = Calendar.getInstance().getTime();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	// Check if new version added is less then added version of trade
	@Test
	public void testAddVersionTrade() {
		Date maturityDate;
		try {
			maturityDate = dateFormat.parse("20/05/2021");
			dateFormat.format(todaysDate);
			Trade t1 = new Trade("T3", 2, "CP-3", "B2", maturityDate, todaysDate, 'N');
			transmission.addTrade(t1);
		} catch (ParseException e) {
			// throwed error
			fail("Version check failed : " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateSameVersionTrade() {
		Date maturityDate;
		try {
			maturityDate = dateFormat.parse("20/05/2021");
			dateFormat.format(todaysDate);
			Trade t1 = new Trade("T3", 3, "CP-3", "B2", maturityDate, todaysDate, 'N');
			transmission.addTrade(t1);
			assertEquals(maturityDate,TradeTransmissionStore.tradeData.get("T33").getMaturityDate());
		} catch (ParseException e) {
			// throwed error
			fail("Version check failed : " + e.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testMaturityDateTrade() throws Exception {

		Date maturityDate;
		maturityDate = dateFormat.parse("1/05/2021");
		dateFormat.format(todaysDate);
		Trade t1 = new Trade("T3", 4, "CP-3", "B2", maturityDate, todaysDate, 'N');
		transmission.addTrade(t1);
		equals("Not able to add T3 in the store as maturity date is lower than current date");
	}

	@Test
	public void testUpdateMaturityDateTrade() throws Exception {

		Date maturityDate;
		maturityDate = dateFormat.parse("21/05/2021");
		dateFormat.format(todaysDate);
		Trade t2 = new Trade("T2", 2, "CP-2", "B1", maturityDate, todaysDate, 'N');
		transmission.addTrade(t2);
		assertEquals(maturityDate,TradeTransmissionStore.tradeData.get("T22").getMaturityDate());

	}
	
	@Test
	public void testUpdateHigherVersionLowerMaturityDateTrade() throws Exception {

		Date maturityDate = dateFormat.parse("20/05/2020");
		dateFormat.format(todaysDate);
		Trade t1 = new Trade("T1", 3, "CP-1", "B1", maturityDate, todaysDate, 'N');
		transmission.addTrade(t1);
		equals("Not able to add T3 in the store as maturity date is lower than current date");

	}

	@Test
	public void testUpdateExpiryTrade() {
		transmission.checkExpiredDates();
		assertEquals('Y', TradeTransmissionStore.tradeData.get("T11").getExpired());
	}

	// Add new Trade
	@Test
	public void testAddTrade() throws Exception {
		Date maturityDate = dateFormat.parse("2/05/2021");
		dateFormat.format(todaysDate);
		Trade t1 = new Trade("T1", 2, "CP-1", "B1", maturityDate, todaysDate, 'N');
		transmission.addTrade(t1);
		// 1 trade added
		assertEquals(6, TradeTransmissionStore.tradeData.size());
		transmission.printTrade();
	}

}
