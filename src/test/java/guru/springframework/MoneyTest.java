package guru.springframework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoneyTest {

	@Test
	void testMultiplication() {
		Money dollar = Money.dollar(5);
		assertEquals(Money.dollar(10), dollar.times(2));
		assertEquals(Money.dollar(15), dollar.times(3));
		
		Money franc = Money.franc(5);
		assertEquals(Money.franc(10), franc.times(2));
		assertEquals(Money.franc(15), franc.times(3));
	}

	@Test
	void testEquality() {
		assertEquals(Money.dollar(5), Money.dollar(5));
		assertNotEquals(Money.dollar(5), Money.dollar(8));
		assertNotEquals(Money.dollar(5), Money.franc(5));
		
		assertEquals(Money.franc(5), Money.franc(5));
		assertNotEquals(Money.franc(5), Money.franc(8));
	}

	@Test
	void testCurrency() {
		assertEquals("USD", Money.dollar(1).currency());
		assertEquals("CHF", Money.franc(1).currency());
	}
	
	@Test
	void testSimpleAddition() {
		Money dollar = Money.dollar(5);
		Expression sum = dollar.plus(dollar);
		Bank bank = new Bank();
		Money reduced = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(10), reduced);
	}
}
