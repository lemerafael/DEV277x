package calculator;

import java.util.Scanner;

public class FractionCalculator {
	
	public String getOperation(Scanner input) {
		System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
		boolean isValid = false;
		String userInput = null;
		while (!isValid) {
			userInput = input.nextLine();
			switch (userInput) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "=":
			case "Q":
			case "q":
				isValid = true;
				break;
				
			default:
				isValid = false;
				System.out.print("Invalid operation (+, -, /, *, = or Q to quit): ");
				break;
			}
		}
		return userInput;
	}
	
	public boolean validFraction(String input) {
		// The fraction may start with -
		if (input.startsWith("-")) {
			// Remove the -
			input = input.replaceFirst("-", "");
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
	
	public Fraction getFraction(Scanner input) {
		System.out.print("Please enter a fraction (a/b) or integer (a): ");
		boolean isValid = false;
		String userInput = null;
		while (!isValid) {
			userInput = input.nextLine();
			if (!validFraction(userInput)) {
				System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
			} else {
				String[] fractionString = userInput.split("/");
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
		System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");
		System.out.println("-------------------------------------------------------------------------------");
		FractionCalculator calculator = new FractionCalculator();
		Scanner input = new Scanner(System.in);
		String operation = "";
		boolean run = true;
		while (run) {
			operation = calculator.getOperation(input);
			Fraction fraction1 = null;
			Fraction fraction2 = null;
			if (!operation.equalsIgnoreCase("Q")) {
				fraction1 = calculator.getFraction(input);
				fraction2 = calculator.getFraction(input);
			}
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
				try {
					result = fraction1.divide(fraction2);
					result.toLowestTerms();					
					System.out.println(fraction1.toString()+" "+operation+" "+fraction2+" = "+result.toString());
				} catch (IllegalArgumentException e) {
					System.out.println(fraction1.toString()+" "+operation+" "+fraction2+" = Undefined");
				}
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
			case "Q":
			case "q":
				run = false;
				break;
				
			default:
				break;
			}
		}
	}
}
