package guru.springframework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoneyTest {

	@Test
	void testMultiplication() {
		Expression dollar = Money.dollar(5);
		assertEquals(Money.dollar(10), dollar.times(2));
		assertEquals(Money.dollar(15), dollar.times(3));
		
		Expression franc = Money.franc(5);
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
		Expression dollar = Money.dollar(5);
		Expression sum = dollar.plus(dollar);
		Bank bank = new Bank();
		Expression reduced = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(10), reduced);
	}
	
	@Test
	void testPlusReturnsSum() {
		Expression five = Money.dollar(5);
		Expression result = five.plus(five);
		Sum sum = (Sum) result;
		assertEquals(five, sum.augmend);
		assertEquals(five, sum.addend);
	}
	
	@Test
	void testReduceSum() {
		Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
		Bank bank = new Bank();
		Expression result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(7), result);
	}
	
	@Test
	void testReduceMoney() {
		Bank bank = new Bank();
		Expression result = bank.reduce(Money.dollar(1), "USD");
		assertEquals(Money.dollar(1), result);
	}
	
	@Test
	void testReduceMoneyDifferentCurrency() {
		Bank bank = new Bank();
		bank.addRate("CHF","USD",2);
		Expression result = bank.reduce(Money.franc(2), "USD");
		assertEquals(Money.dollar(1), result);
	}
	
	@Test
	void testIdentityRate() {
		assertEquals(1, new Bank().rate("USD", "USD"));
		assertEquals(1, new Bank().rate("CHF", "CHF"));
	}
	
	@Test
	void testMixedAddition() {
		Expression fiveD = Money.dollar(5);
		Expression tenF = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression result = bank.reduce(fiveD.plus(tenF), "USD");
		assertEquals(result, Money.dollar(10));
	}
	
	@Test
	void testSumPlusMoney() {
		Expression fiveD = Money.dollar(5);
		Expression tenF = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression sumPlus = new Sum(fiveD,tenF).plus(fiveD);
		Money result = (Money) bank.reduce(sumPlus, "USD");
		assertEquals(Money.dollar(15), result);
	}
	
	@Test
	void testSumTimes() {
		Expression fiveD = Money.dollar(5);
		Expression tenF = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression sumTimes = new Sum(fiveD,tenF).times(2);
		Money result = (Money) bank.reduce(sumTimes, "USD");
		assertEquals(Money.dollar(20),result);
	}
}
