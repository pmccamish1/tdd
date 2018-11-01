package guru.springframework;

public class Sum implements Expression {

	public Expression augmend;
	public Expression addend;

	public Sum(Expression augmend, Expression addend) {
		this.augmend = augmend;
		this.addend = addend;
	}

	@Override
	public Money reduce(Bank bank, String toCurrency) {
		Money result = new Money(augmend.reduce(bank, toCurrency).amount + addend.reduce(bank, toCurrency).amount,toCurrency);
		return new Money(result.amount / bank.rate(result.currency, toCurrency), toCurrency);
	}

	@Override
	public Expression plus(Expression added) {
		return new Sum(this, added);
	}

	@Override
	public Expression times(int multiplier) {
		return new Sum(augmend.times(multiplier), addend.times(multiplier));
	}

}
