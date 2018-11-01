package guru.springframework;

import java.util.Objects;

public class Pair {
	private final String fromCurrency;
	private final String toCurrency;
	
	public Pair(String fromCurrency, String toCurrency) {
		super();
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		Pair pair = (Pair) o;
//		if(this.fromCurrency.equals(pair.fromCurrency) && this.toCurrency.equals(pair.fromCurrency)) {
//			return true;
//		} else {
//			return false;
//		}
		return Objects.equals(fromCurrency, pair.fromCurrency) && Objects.equals(toCurrency, pair.toCurrency);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fromCurrency,toCurrency);	
	}
	
}
