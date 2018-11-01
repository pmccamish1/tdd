package guru.springframework;

public class Bank {

	public Money reduce(Expression source, String toCurrency) {
		if(((Money) source).currency.equals(toCurrency)) {
			return ((Money) source);
		}
		return ((Money) source);
	}

}
