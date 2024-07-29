package vendormachine.users.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WalletTEST {

	// Test constructors
	@Test
	public void testWallet() {
		Wallet wallet1 = new Wallet();
		assertEquals(0.0, wallet1.getAllCredit(), "Credit should be 0.0");

		Wallet wallet2 = new Wallet(10.0f);
		assertEquals(5.0, wallet2.getCredit(5), "Credit should be 5.0");
		assertEquals(0, wallet2.getCredit(500), "Credit should be 0");

		Wallet wallet3 = new Wallet("Generic", 10.0f);
		assertEquals("Generic", wallet3.getBrand("Generic"), "Brand should be Generic");
		assertEquals(5.0, wallet3.getCredit(5), "Credit should be 5.0");
		assertEquals(0, wallet3.getCredit(500), "Credit should be 0");
	}

	// Test getters
	@Test
	public void testGetAllCredit() {
		Wallet wallet = new Wallet(10.0f);
		assertEquals(10.0, wallet.getAllCredit(), "Credit should be 10.0");
	}

	@Test
	public void testGetBrand() {
		Wallet wallet = new Wallet("Generic", 10.0f);
		assertEquals("Generic", wallet.getBrand("Generic"), "Brand should be Generic");
	}

	// Test setters
	@Test
	public void testSetCredit() {
		Wallet wallet = new Wallet(10.0f);
		wallet.setCredit(20.0f);
		assertEquals(20.0, wallet.getAllCredit(), "Credit should be 20.0");
	}

	@Test
	public void testSetBrand() {
		Wallet wallet = new Wallet("Generic", 10.0f);
		wallet.setBrand("Test");
		assertEquals("Test", wallet.getBrand("Test"), "Brand should be Test");
	}

	// Test addCredit
	@Test
	public void testAddCredit() {
		Wallet wallet = new Wallet(10.0f);
		wallet.addCredit(5.0f);
		assertEquals(15.0, wallet.getAllCredit(), "Credit should be 15.0");
	}

	// Test getCredit
	@Test
	public void testGetCredit() {
		Wallet wallet = new Wallet(10.0f);
		assertEquals(5.0, wallet.getCredit(5), "Credit should be 5.0");
		assertEquals(0, wallet.getCredit(500), "Credit should be 0");
	}
}