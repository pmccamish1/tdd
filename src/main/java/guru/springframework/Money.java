package guru.springframework;

public class Money implements Expression {
	protected int amount;
	protected String currency;
	
	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public Money times(int multiplier) {
		return new Money(this.amount * multiplier, this.currency);
	}
	
	public static Money dollar(int amount) {
		return new Money(amount, "USD");
	}
	
	public static Money franc(int amount) {
		return new Money(amount, "CHF");
	}
	
	protected String currency() {
		return currency;
	}
	
	@Override
	public boolean equals(Object arg0) {
		Money money = (Money) arg0;
		return this.amount == money.amount && this.currency.equals(money.currency);
	}

	public Expression plus(Money added) {
		return new Money(amount + added.amount, currency);
	}

}
