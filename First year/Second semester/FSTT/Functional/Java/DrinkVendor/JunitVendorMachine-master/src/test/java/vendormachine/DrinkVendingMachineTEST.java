package vendormachine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import vendormachine.users.Person;
import vendormachine.users.util.Wallet;
import vendormachine.vendors.DrinkVendingMachine;
import vendormachine.vendors.enums.BRANDS;
import vendormachine.vendors.item.Snack;

public class DrinkVendingMachineTEST {

	private DrinkVendingMachine drinkVendingMachine;

	@BeforeEach
	public void setUp() {
		drinkVendingMachine = new DrinkVendingMachine(20f, BRANDS.CaramelSprinkle);
	}

	// Test constructors
	@Test
	public void testDrinkVendingMachine() {
		assertEquals(20f, drinkVendingMachine.getAvailableCredit());
		assertEquals(BRANDS.CaramelSprinkle, drinkVendingMachine.getBrandName());

		DrinkVendingMachine drinkVendingMachine2 = new DrinkVendingMachine();
		assertEquals(0.0f, drinkVendingMachine2.getAvailableCredit());
		assertEquals(BRANDS.Generic, drinkVendingMachine2.getBrandName());
	}

	// Test getters
	@Test
	public void testGetAvailableCredit() {
		assertEquals(20f, drinkVendingMachine.getAvailableCredit());
	}

	@Test
	public void testGetBrandName() {
		assertEquals(BRANDS.CaramelSprinkle, drinkVendingMachine.getBrandName());
	}

	@Test
	public void testGetDrinkList() {
		assertEquals(4, drinkVendingMachine.getDrinkList().size());
	}

	// Test setters
	@Test
	public void testSetAvailableCredit() {
		drinkVendingMachine.setAvailableCredit(10f);
		assertEquals(10f, drinkVendingMachine.getAvailableCredit());
	}

	@Test
	public void testSetBrandName() {
		drinkVendingMachine.setBrandName(BRANDS.CaramelSprinkle);
		assertEquals(BRANDS.CaramelSprinkle, drinkVendingMachine.getBrandName());
	}

	@Test
	public void testSetDrinkList() {
		ArrayList<Snack> snacks = new ArrayList<Snack>();
		snacks.add(new Snack("testsnack", 2.5f));
		drinkVendingMachine.setDrinkList(snacks);
		assertEquals(1, drinkVendingMachine.getDrinkList().size());
	}

	// Test has drink
	@Test
	public void testHasDrink() {

		// Test if the vending machine has a drink
		Snack drink = new Snack("testsnack", 2.5f);
		drinkVendingMachine.addDrink(drink);
		assertTrue(drinkVendingMachine.hasDrink(drink));

		// Test if the vending machine does not have a drink
		Snack drink2 = new Snack("testsnack2", 2.5f);
		assertFalse(drinkVendingMachine.hasDrink(drink2));
	}

	@Test
	public void testAddDrink() {
		// Test adding a drink to the vending machine
		Snack drink = new Snack("testsnack", 2.5f);
		drinkVendingMachine.addDrink(drink);
		assertTrue(drinkVendingMachine.hasDrink(drink));
	}

	@Test
	public void testRemoveDrink() {
		// Test removing a drink from the vending machine
		Snack drink = new Snack("testsnack", 2.5f);
		drinkVendingMachine.addDrink(drink);
		drinkVendingMachine.removeDrink(drink);
		assertFalse(drinkVendingMachine.hasDrink(drink));
	}

	// Add more test methods here...
	@Test
	public void testSelectDrink() {
		// Test selecting a drink from the vending machine
		Snack drink = new Snack("testsnack", 2.5f);
		drinkVendingMachine.addDrink(drink);
		Snack sel = drinkVendingMachine.selectDrink(4);
		assertEquals(drink.getName(), sel.getName());
		assertTrue(drinkVendingMachine.hasDrink(drink));

		// Test not having a drink
		Snack sel2 = drinkVendingMachine.selectDrink(5);
		assertEquals(null, sel2);

		// Test not having enough credit
		Person user = new Person("testuser", new Wallet(10.0f));
		drinkVendingMachine.giveCredit(user, 5.0f);
		Snack sel3 = drinkVendingMachine.selectDrink(5);
		assertEquals(null, sel3);

		// Test with index  == -1
		Snack sel4 = drinkVendingMachine.selectDrink(-1);
		assertEquals(null, sel4);
	}

	// Test giveCredit
	@Test
	public void testGiveCredit() {
		// Test giving credit to the vending machine
		Person user = new Person("testuser", new Wallet(10.0f));
		drinkVendingMachine.giveCredit(user, 5.0f);
		assertEquals(25f, drinkVendingMachine.getAvailableCredit());

		Person user2 = new Person("testuser2");
		drinkVendingMachine.giveCredit(user2, 5.0f);
		assertEquals(25f, drinkVendingMachine.getAvailableCredit());
	}

}
