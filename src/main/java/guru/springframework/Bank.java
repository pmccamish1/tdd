package guru.springframework;

import java.util.HashMap;

public class Bank {

	//private static HashMap<String, Integer> rates = new HashMap<>();
	private HashMap<Pair, Integer> rateMap = new HashMap<>();
	
	public Expression reduce(Expression source, String toCurrency) {
		return source.reduce(this, toCurrency);
	}

	public void addRate(String fromCurrency, String toCurrency, int exchangeRate) {
		rateMap.put(new Pair(fromCurrency,toCurrency), exchangeRate);
	}

	public int rate(String fromCurrency, String toCurrency) {
		if (fromCurrency.equals(toCurrency)) {
			return 1;
		} else {
			return rateMap.get(new Pair(fromCurrency,toCurrency));
		}
	}
}
