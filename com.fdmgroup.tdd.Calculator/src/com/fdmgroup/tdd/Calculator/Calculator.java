package com.fdmgroup.tdd.Calculator;

/**
 * This class acts as a calculator that evaluates mathematical expressions provided as strings.
 * It supports basic arithmetic operations, exponentiation, and the handling of parentheses.
 * The calculator follows the standard order of operations (BODMAS). All operations are made 
 * to be evaluated in a recursive manner. No loops were used in the implementation of any function.
 * All whitespace is removed throughout all operations.
 *
 * 
 * @author Matthew Chanco
 * @version 19/12/2024
 */

public class Calculator implements ICalculator {

	// Basic math operators: Addition, Subtraction, Multiplication, Division

	/**
	 * Basic addition operation
	 * @param x First number
	 * @param y Second number
	 * @return The sum of x and y.
	 */

	public double add(double x, double y) {
		return x + y;	
	}


	/**
	 * Basic subtraction operation
	 * @param x First number
	 * @param y Second number
	 * @return The difference between x and y.
	 */

	public double minus(double x, double y) {
		return x - y;
	}


	/**
	 * Basic multiplication operation
	 * @param x First number
	 * @param y Second number
	 * @return The product of x and y.
	 */

	public double multiply(double x, double y) {
		return x * y;
	}


	/**
	 * Basic division operation. Throws an exception if dividing by zero.
	 * @param x Dividend
	 * @param y Divisor
	 * @return The quotient of x when divided by y.
	 */

	public double divide(double x, double y) {

		if ( y == 0 ) {

			throw new IllegalArgumentException("Dividing by zero. Not a valid operation.");
		}

		return x / y;
	}


	// Exponential operator: with ^ as the operator, integer exponents for now

	/**
	 * Evaluates the power of a base number raised to an exponent. It evaluates the 
	 * expression recursively.
	 * @param base Base number
	 * @param exponent Exponent
	 * @return The result of base raised to the power of the exponent.
	 */

	public double power(double base, double exponent) {

		if ( exponent == 0 ) {

			return 1;
		}

		if ( exponent < 0 ) {

			return 1 / power(base, -exponent);
		}

		return base * power(base, exponent - 1);
	}

	// Cleaner Methods

	// Method to adjust expressions with combinations of operators ex. "++", "--", "+-", "-+", ...

	/**
	 * Simplifies the expression by combining redundant operations such as:
	 * "++", "+-", "--", "-+", "*+", and "/+"
	 * @param expression Mathematical expression to be simplified.
	 * @return Simplified expression
	 */

	private String simplifyExpression ( String expression ) {

		if ( expression.contains("++") ) {

			int index = expression.indexOf("++");
			return simplifyExpression(expression.substring(0, index) + "+" + (index + 2 < expression.length() ? expression.substring(index + 2) : ""));

		} else if ( expression.contains("--") ) {

			int index = expression.indexOf("--");
			return simplifyExpression(expression.substring(0, index) + "+" + (index + 2 < expression.length() ? expression.substring(index + 2) : "") );

		} else if ( expression.contains("+-") ) {

			int index = expression.indexOf("+-");
			return simplifyExpression(expression.substring(0, index) + "-" + (index + 2 < expression.length() ? expression.substring(index + 2) : "") );

		} else if ( expression.contains("-+") ) {

			int index = expression.indexOf("-+");
			return simplifyExpression(expression.substring(0, index) + "-" + (index + 2 < expression.length() ? expression.substring(index + 2) : "") );

		} else if ( expression.contains("*+") ) {

			int index = expression.indexOf("*+");
			return simplifyExpression(expression.substring(0, index) + "*" + (index + 2 < expression.length() ? expression.substring(index + 2) : "") );

		} else if ( expression.contains("/+") ) {

			int index = expression.indexOf("/+");
			return simplifyExpression(expression.substring(0, index) + "/" + (index + 2 < expression.length() ? expression.substring(index + 2) : "") );

		}

		return expression;
	}

	// Method to adjust expression if leading with a '+' or '-'

	/**
	 * Cleans the expression by removing any leading '+' or '-' signs to make it
	 * readable by the code.
	 * @param expression Mathematical expression to be cleaned
	 * @return Cleaned operation
	 */

	private String cleanExpression( String expression ) {

		if ( expression.charAt(0) == '+' ) {

			return expression.substring(1);

		} else if ( expression.charAt(0) == '-' ) {

			return "0" + expression;
		}

		return expression;
	}

	// Method to check for invalid input.

	/**
	 * Checks for any invalid operations within the expression such as:
	 * "+*", "-*", "**", "/*", "+/", "-/", "//"..
	 * It does not return anything.
	 * 
	 * @param expression Mathematical expression to be checked.
	 * @throws IllegalArgumentException If invalid operators are detected.
	 */

	private void checkInvalidOperations( String expression ) {
		if ( expression.contains("-*") || expression.contains("+*") || expression.contains("-/")
				|| expression.contains("+/") || expression.contains("**") || expression.contains("//")) {

			throw new IllegalArgumentException("Invalid argument detected.");
		}
	}

	
	// BODMAS Methods

	// Check for Parenthesis

	/**
	 * This is the first function to be called when running the code.
	 * 
	 * Evaluates the mathematical expression inside parentheses (B) and recursively 
	 * replaces the expression with the evaluated value to remove the brackets "()"
	 * until all are evaluated.
	 * 
	 * It finds the innermost parenthesis first then works its way outwards until
	 * all the parentheses are removed. It then calls the function for evaluating
	 * exponents.
	 * 
	 * @param expression Mathematical expression to be evaluated that contains "()"
	 * @return Calls the function again to detect and evaluate expressions in parenthesis.
	 * If none, calls the function for evaluating exponents.
	 */

	private double performParenthesesOperations(String expression) {
		expression = expression.replaceAll(" ", "");
		expression = cleanExpression(expression);
		expression = simplifyExpression(expression);
		

		if (!expression.contains("(")) {
			return performExponentsOperations(expression);
		}
		

		// Find the innermost parentheses then evaluates
		int start = expression.lastIndexOf('(');
		int end = findClosingParenthesis(expression, start);
		String innerExpression = expression.substring(start + 1, end);
		double innerResult = performExponentsOperations(innerExpression);


		// Updates the expression by removing brackets and calls the function again with the new expression
		String newExpression = expression.substring(0, start) + innerResult + expression.substring(end + 1);


		return performParenthesesOperations(newExpression);
	}

	
	
	
	// This method detects the first closing bracket with respect to the first opening bracket.
	private int findClosingParenthesis ( String expression, int index ) {

		if ( expression.charAt(index) == ')' ) {

			return index;
		}

		return findClosingParenthesis ( expression, index + 1);

	}


	// Check for Exponents

	/**
	 * This is the second function to be called after clearing all the expressions in parenthesis.
	 * 
	 * Evaluates any exponential(O) operations symbolized by a caret "^" recursively until all
	 * carets are evaluated. Note that it only evaluates the first number succeeding the symbol
	 * as the exponent and the first number preceding the symbol as the base. Any other inputs
	 * must be enclosed in a bracket to be properly evaluated.
	 * 
	 * @param expression Mathematical expression to be evaluated that contains ^
	 * @return Calls the function again to detect and evaluate exponential expressions.
	 * If none, calls the function for evaluating multiplication/division operations.
	 */

	private double performExponentsOperations ( String expression ) {
		expression = expression.replaceAll(" ", "");
		expression = cleanExpression(expression);
		expression = simplifyExpression(expression);
		

		if (!expression.contains("^")) {
			return performMDOperations( expression );
		}

		// Find the rightmost exponential operation then evaluates
		int caretIndex = expression.lastIndexOf('^');
		
		// Splits the exponential expression into base and exponent
		int baseStart = findBaseStart( expression, caretIndex - 1);
		int exponentEnd = findExponentEnd( expression, caretIndex + 1);
		
		double base = Double.parseDouble(expression.substring(baseStart, caretIndex));
		double exponent = Double.parseDouble(expression.substring(caretIndex + 1, exponentEnd + 1));
		
		double result = power(base, exponent);


		// Updates the expression to remove the ^ symbol then calls the function with the new expression
		String newExpression = expression.substring(0, baseStart) + result + expression.substring(exponentEnd + 1);

		return performExponentsOperations(newExpression);
	}

	
	
	
	// This method finds the first number to the left of the ^ symbol
	private int findBaseStart(String expression, int index) {
		
		// Base case: Start of the string or previous character is an operator
		if (index < 0 || isOperator(expression.charAt(index))) {
			return index + 1;
		}
		

		return findBaseStart(expression, index - 1);
	}

	// This method finds the first number to the right of the ^ symbol
	private int findExponentEnd(String expression, int index) {
		
		// Base case: End of the string or next character is an operator
		if (index >= expression.length() - 1 || isOperator(expression.charAt(index + 1))) {
			return index;
		}

		return findExponentEnd(expression, index + 1);
	}



	// Check for Multiplication/Division Operations
	
	/**
	 * This is the next function to be called after evaluating exponential expressions.
	 * 
	 * Evaluates any multiplication(M) or division(D) operations in the expression, 
	 * symbolized by an asterisk "*" or a backslash "/". Recursively calls the function
	 * until all MD operations are done.
	 * 
	 * The code works such that it splits the expression into two at the index where
	 * the operator is. It then extracts the numbers directly next to the operator
	 * on both sides, including the '-' sign, indicating if it is a subtraction operator
	 * or if it signifies a negative sign.
	 *  
	 * @param expression Mathematical expression to evaluate * and / operators
	 * @return Calls the function again to detect and evaluate MD expressions.
	 * If none, calls the function for evaluating addition/subtraction operations.
	 */

	private double performMDOperations(String expression) {
		
		// Cleaner methods to ensure string readability for the code
		expression = cleanExpression(expression);
		expression = simplifyExpression(expression);
		

		if (!expression.contains("*") && !expression.contains("/")) {
			return performASOperations(expression);
		}
		
		
		// Compares the indices of MD operators and executes whichever comes first
		int mulIndex = expression.indexOf('*');
		int divIndex = expression.indexOf('/');
		

		int index;

		if ( mulIndex != -1 && (divIndex == -1 || mulIndex < divIndex) )  {
			index = mulIndex;

			// This is where the expression is split into two parts
			String left = expression.substring(extractLeftNumber(expression, index - 1), index);
			String right = expression.substring(index + 1, extractRightNumber(expression, index+1));

			double result = multiply(performMDOperations(left), performMDOperations(right));

			// Updates the expression then calls the function again with the new expression
			String newExpression = expression.substring(0, extractLeftNumber(expression, index))
					+ result
					+ expression.substring(extractRightNumber(expression, index + 1));
			

			return performMDOperations(newExpression);
		}

		if ( divIndex != -1 && (mulIndex == -1 || divIndex < mulIndex) ) {
			index = divIndex;

			// This is where the expression is split into two parts
			String left = expression.substring(extractLeftNumber(expression, index - 1), index);
			String right = expression.substring(index + 1, extractRightNumber(expression, index+1));

			double result = divide(performMDOperations(left), performMDOperations(right));

			// Updates the expression then calls the function again with the new expression
			String newExpression = expression.substring(0, extractLeftNumber(expression, index))
					+ result
					+ expression.substring(extractRightNumber(expression, index + 1));
			

			return performMDOperations(newExpression);
		}

		return performMDOperations(expression);

	}

	
	

	// Methods to extract the numbers on both sides of the operator
	private int extractLeftNumber(String expression, int index) {

		// Base case: Start of the string or previous character is an operator
		if (index <= 0 || isOperator(expression.charAt(index - 1))) {
			return index;
		}

		return extractLeftNumber(expression, index - 1);
	}


	private int extractRightNumber(String expression, int index) {

		// Base case: End of the string
		if (index >= expression.length()) {
			return index;
		}

		if (isOperator(expression.charAt(index))) {

			if ( expression.charAt(index) == '-' && !isOperator(expression.charAt(index-1)) ) {

				return index;
			}

			// Directly handles negative numbers
			if (expression.charAt(index) == '-' && index + 1 < expression.length() 
					&& !isOperator(expression.charAt(index + 1))) {


				return extractRightNumber(expression, index + 1);
			}
			return index;
		}

		return extractRightNumber(expression, index + 1);
	}

	
	
	
	// Method to detect if the character in check is an operator
	private boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
	}


	// Check for Addition/Subtraction Operations

	/**
	 * This is the last function to be called after performing multiplication and division.
	 * 
	 * Evaluates any addition(A) or subtraction(S) operations in the expression, 
	 * symbolized by a plus "+" or a minus "-". Recursively calls the function
	 * until all AS operations are done. It is expected that all other operators
	 * do not exist at this point except for "+" and "-".
	 *  
	 * @param expression Mathematical expression to evaluate + and - operators
	 * @return Calls the function again to detect and evaluate AS expressions.
	 * If none, parses the final expression into a double.
	 */
	
	
	private double performASOperations ( String expression ) {
		expression = cleanExpression(expression);
		expression = simplifyExpression(expression);
		

		if ( expression.charAt(0) == '+' || expression.charAt(0) == '-' ) {

			expression = cleanExpression(expression);
			expression = simplifyExpression(expression);

		}

		if ( expression.contains("+") ) {

			int index = expression.lastIndexOf("+");

			String left = expression.substring(0, index);
			String right = expression.substring(index + 1);


			return add(performASOperations(left), performASOperations(right));

		} else if ( expression.contains("-") ) {

			int index = expression.lastIndexOf("-");

			String left = expression.substring(0, index);
			String right = expression.substring(index + 1);


			return minus(performASOperations(left), performASOperations(right));
		}

		return Double.parseDouble(expression);
	}


	// Interface method for the user to use the calculator.
	@Override
	public double evaluate(String expression) {
		
		// Cleans the expression of whitespace
		expression = expression.replaceAll(" ", "");
		
		// Preliminary check for invalid operations
		checkInvalidOperations(expression);
		
		// Cleans redundant operators
		expression = simplifyExpression(expression);

		return performParenthesesOperations(expression);
	}

}
