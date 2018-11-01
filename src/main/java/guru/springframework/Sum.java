package guru.springframework;

public class Sum implements Expression {

	public Money augmend;
	public Money addend;

	public Sum(Money augmend, Money addend) {
		this.augmend = augmend;
		this.addend = addend;
	}

	@Override
	public Money reduce(String toCurrency) {
		Money temp = new Money(augmend.amount + addend.amount,augmend.currency);
		String currency = temp.currency;
		if (currency.equals(toCurrency)) {
			return new Money(temp.amount, toCurrency);
		} else {
			int rate = new Bank().rate(currency, toCurrency);
			return new Money(temp.amount / rate, toCurrency);
		}

	}

}
