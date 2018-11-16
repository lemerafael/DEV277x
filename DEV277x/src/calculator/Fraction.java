package calculator;

public class Fraction {

	private int numerator;
	private int denominator;
	
	public Fraction () {
		this(0, 1);
	}
	
	public Fraction (int number) {
		this(number, 1);
	}
	
	public Fraction (int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		if (denominator == 0) {
			throw new IllegalArgumentException();
		} else if (denominator < 0) {
			numerator = -numerator;
			denominator = -denominator;
		}
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}
	
	public String toString() {
		if (denominator != 1) {
			return numerator+"/"+denominator;
		} else {
			return String.valueOf(numerator);
		}
	}
	
	public double toDouble() {
		return (double)numerator/denominator;
	}
	
	public Fraction add(Fraction fraction) {
		int resNumerator = 0;
		int resDenominator = 0;
		resDenominator = this.denominator*fraction.getDenominator();
		resNumerator = this.denominator*fraction.getNumerator()+this.numerator*fraction.getDenominator();
		Fraction result = new Fraction(resNumerator,resDenominator);
		return result;
	}
	
	public Fraction subtract(Fraction fraction) {
		int resNumerator = 0;
		int resDenominator = 0;
		resDenominator = this.denominator*fraction.getDenominator();
		resNumerator = this.numerator*fraction.getDenominator()-fraction.getNumerator()*this.denominator;
		Fraction result = new Fraction(resNumerator,resDenominator);
		return result;
	}
	
	public Fraction multiply(Fraction fraction) {
		int resNumerator = 0;
		int resDenominator = 0;
		resDenominator = this.denominator*fraction.getDenominator();
		resNumerator = this.numerator*fraction.getNumerator();
		Fraction result = new Fraction(resNumerator,resDenominator);
		return result;
	}
	
	public Fraction divide(Fraction fraction) {
		int resNumerator = 0;
		int resDenominator = 0;
		if (fraction.numerator == 0) {
			throw new IllegalArgumentException();
		}
		resDenominator = this.denominator*fraction.getNumerator();
		resNumerator = this.numerator*fraction.getDenominator();
		Fraction result = new Fraction(resNumerator,resDenominator);
		return result;
	}
	
	public boolean equals(Object object) {
		if (object instanceof Fraction) {
			Fraction fraction = (Fraction)object;
			double value = fraction.getNumerator() / fraction.getDenominator();
			double thisValue = this.numerator / this.denominator;
			if (value == thisValue) {
				return true;
			}
		}
		return false;
	}
	
	public void toLowestTerms() {
		int gcd = Fraction.gcd(numerator, denominator);
		this.numerator = this.numerator/gcd;
		this.denominator = this.denominator/gcd;
	}
	
	public static int gcd(int num, int den) {
		while (num != 0 && den !=0 ) {
			int rem = num % den;
			num = den;
			den = rem;
		}
	    return num;
	}
	
}
