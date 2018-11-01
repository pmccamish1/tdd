package guru.springframework;

import java.util.HashMap;

public class Bank {

	private static HashMap<String, Integer> rates = new HashMap<>();

	public Money reduce(Expression source, String toCurrency) {
		return source.reduce(toCurrency);
	}

	public void addRate(String fromCurrency, String toCurrency, int exchangeRate) {
		String key = fromCurrency + "-" + toCurrency;
		rates.put(key, exchangeRate);
	}

	public int rate(String fromCurrency, String toCurrency) {
		String key = fromCurrency + "-" + toCurrency;
		if (fromCurrency.equals(toCurrency)) {
			return 1;
		} else {
			return rates.get(key);
		}
	}

}
