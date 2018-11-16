package calculator;

import java.util.Scanner;

public class FractionCalculatorAdvanced {
	
	public boolean isOperationValid(String input) {
		boolean isValid = false;
			switch (input) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "=":
			case "Q":
				isValid = true;
				break;
				
			default:
				isValid = false;
				System.out.print("Invalid operation (+, -, /, *, = or Q to quit): ");
				break;
			}
		return isValid;
	}
	
	public boolean validFraction(String input) {
		// The fraction may start with -
		if (input.startsWith("-")) {
			// Remove the -
			input.replaceFirst("-", "");
		}
		// The fraction may not have any other -
		if (input.contains("-")) {
			return false;
		}
		// If the fraction does not contain /
		if (!input.contains("/")) {
			try {
				Integer.parseInt(input);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			String[] fraction = input.split("/");
			try {
				if (fraction.length != 2) {
					return false;
				}
				Integer.parseInt(fraction[0]);
				int denominator = Integer.parseInt(fraction[1]);
				if (denominator == 0) {
					return false;
				}
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}
	
	public Fraction getFraction(String input) {
		boolean isValid = false;
		while (!isValid) {
			if (!validFraction(input)) {
				return null;
			} else {
				String[] fractionString = input.split("/");
				Fraction fraction = null;
				try {
					int numerator = Integer.parseInt(fractionString[0]);
					int denominator = Integer.parseInt(fractionString[1]);
					fraction = new Fraction(numerator, denominator);
				} catch (Exception e) {
					int numerator = Integer.parseInt(fractionString[0]);
					fraction = new Fraction(numerator);
				}
				return fraction;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("This program is a fraction calculator");
		System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
		System.out.println("Valid operations are of the form \"[FRAC] [OPERATION] [FRAC]\"");
		System.out.println("[FRAC] can be either a single integer or two integers separated by \"/\".");
		System.out.println("[OPERATION] can be +, -, /, * or =.");
		System.out.println("-------------------------------------------------------------------------------");
		FractionCalculatorAdvanced calculator = new FractionCalculatorAdvanced();
		Scanner input = new Scanner(System.in);
		String operation = "";
		boolean run = true;
		while (run) {
			System.out.print("Enter an operation. (Q to quit): ");
			try {
				String userInput = input.nextLine();
				String[] split = userInput.split(" ");
				if (split[0].equalsIgnoreCase("Q")) {
					run = false;
					break;
				}
				String fraction1String = split[0];
				String fraction2String = split[2];
				operation = split[1];
				Fraction fraction1 = calculator.getFraction(fraction1String);
				Fraction fraction2 = calculator.getFraction(fraction2String);
				Fraction result;
				switch (operation) {
				case "+":
					result = fraction1.add(fraction2);
					result.toLowestTerms();
					System.out.println(fraction1.toString()+" "+operation+" "+fraction2+" = "+result.toString());
					break;
				case "-":
					result = fraction1.subtract(fraction2);
					result.toLowestTerms();
					System.out.println(fraction1.toString()+" "+operation+" "+fraction2+" = "+result.toString());
					break;
				case "/":
					result = fraction1.divide(fraction2);
					result.toLowestTerms();
					System.out.println(fraction1.toString()+" "+operation+" "+fraction2+" = "+result.toString());
					break;
				case "*":
					result = fraction1.multiply(fraction2);
					result.toLowestTerms();
					System.out.println(fraction1.toString()+" "+operation+" "+fraction2+" = "+result.toString());
					break;
				case "=":
					boolean compare = fraction1.equals(fraction2);
					System.out.println(fraction1.toString()+" "+operation+" "+fraction2+" is "+compare);
					break;
					
				default:
					break;
				}				
			} catch (Exception e) {
				System.out.print("Invalid operation. Must be \"[FRAC] [OPERATION] [FRAC]\"");
			}
		}
		input.close();
	}
}
