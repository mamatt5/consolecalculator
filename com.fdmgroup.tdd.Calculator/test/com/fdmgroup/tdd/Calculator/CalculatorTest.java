package com.fdmgroup.tdd.Calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CalculatorTest {
	
	Calculator calculator = new Calculator();
	
	// Tests for basic math operations
	
	@Test
	void basic_addition_2_plus_3_equals_5() {
		
		assertEquals(5, calculator.add(2, 3));
		
	}
	
	@Test
	void basic_subtraction_3_minus_2_equals_1() {
		
		assertEquals(1, calculator.minus(3, 2));
		
	}
	
	@Test
	void basic_multiplication_3_multiply_2_equals_6() {
		
		assertEquals(6, calculator.multiply(3, 2));
	}
	
	@Test
	void basic_division_6_divide_2_equals_3() {
		
		assertEquals(3, calculator.divide(6, 2));
	}
	
	@Test
	void basic_division_dividing_by_zero() {
		
		assertThrows(IllegalArgumentException.class, () -> {calculator.divide(0, 0);});
	}
	
	
	
	// Tests for invalid arguments
	
	@Test
	void invalid_operator_leading_asterisk_expression() {
		
		assertThrows(IllegalArgumentException.class, () -> {calculator.evaluate("1+*1");});
	}
	
	@Test
	void invalid_operator2_leading_asterisk_expression() {
		
		assertThrows(IllegalArgumentException.class, () -> {calculator.evaluate("1-*1");});
	}
	
	@Test
	void invalid_operator_leading_slash_expression() {
		
		assertThrows(IllegalArgumentException.class, () -> {calculator.evaluate("1+/1");});
	}
	
	@Test
	void invalid_operator2_leading_slash_expression() {
		
		assertThrows(IllegalArgumentException.class, () -> {calculator.evaluate("1-/1");});
	}
	
	@Test
	void invalid_operator_leading_asterisk_at_the_end() {
		
		assertThrows(IllegalArgumentException.class, () -> {calculator.evaluate("1+1+*1");});
	}
	
	@Test
	void invalid_operator_leading_slash_at_the_end() {
		
		assertThrows(IllegalArgumentException.class, () -> {calculator.evaluate("1+1+/1");});
	}
	
	
	
	// Tests for performing basic operations from string input
	
	@Test
	void addition_1_plus_2_equals_3_from_string() {
		double result = 1+2;
		
		assertEquals(result, calculator.evaluate("1+2"));
	}
	
	@Test
	void subtraction_2_minus_1_equals_1_from_string() {
		double result = 2-1;
		
		assertEquals(result, calculator.evaluate("2-1"));
	}
	
	@Test
	void subtraction_0_minus_1_equals_negative_1_from_string() {
		double result = 0-1;
		
		assertEquals(result, calculator.evaluate("0-1"));
	}
	
	@Test
	void multiplication_times_1_equals_2_from_string() {
		double result = 2*1;
		
		assertEquals(result, calculator.evaluate("2*1"));
	}
	
	@Test
	void division_divide_1_equals_2_from_string() {
		double result = 2/1;
		
		assertEquals(result, calculator.evaluate("2/1"));
	}
	
	
	
	// Tests for performing double operations "+-", "--", "*-", etc.
	
	@Test
	void double_operator_plus_minus_operation() {
		double result = 1+-1;
		
		assertEquals(result, calculator.evaluate("1+-1"));
	}
	
	@Test
	void double_operator_minus_plus_operation() {
		double result = 1-+1;
		
		assertEquals(result, calculator.evaluate("1-+1"));
	}
	
	@Test
	void double_operator_minus_minus_operation() {
		double result = 1-(-1);
		
		assertEquals(result, calculator.evaluate("1--1"));
	}
	
	@Test
	void double_operator_timus_plus_operation() {
		double result = 3*+5;
		
		assertEquals(result, calculator.evaluate("3*+5"));
	}
	
	@Test
	void double_operator_divide_plus_operation() {
		double result = 3.0/+5.0;
		
		assertEquals(result, calculator.evaluate("3/+5"));
	}
	
	@Test
	void double_operator_times_minus_operation() {
		double result = 1*-4;
		
		assertEquals(result, calculator.evaluate("1*-4"));
	}
	
	@Test
	void double_operator_divide_minus_operation() {
		double result = 4/-1;
		
		assertEquals(result, calculator.evaluate("4/-1"));
	}
	
	@Test
	void double_operator_negative_divide_minus_operation() {
		double result = -4/-1;
		
		assertEquals(result, calculator.evaluate("-4/-1"));
	}
	
	
	
	// Tests for multiple operations
	
	@Test
	void multiple_operations_1_plus_2_plus_3_equals_6() {
		double result = 1+2+3;
		
		assertEquals(result, calculator.evaluate("1+2+3"));
	}
	
	@Test
	void multiple_operations_1_minus_2_plus_3_equals_2() {
		double result = 1-2+3;
		
		assertEquals(result, calculator.evaluate("1-2+3"));
	}
	
	@Test
	void multiple_operations_1_plus_2_plus_3_minus_1_equals_5() {
		double result = 1+2+3-1;
		
		assertEquals(result, calculator.evaluate("1+2+3-1"));
	}
	
	@Test
	void multiple_operations_1_minus_2_minus_3_equals_negative_4() {
		double result = 1-2-3;
		
		assertEquals(result, calculator.evaluate("1-2-3"));
	}
	
	@Test
	void multiple_double_operators_1_plus_2_plus_3_equals_6() {
		
		assertEquals(6, calculator.evaluate("1++2++3"));
	}
	
	@Test
	void multiple_double_operators_times_minus() {
		double result = 1*-4*-4;
		
		assertEquals(result, calculator.evaluate("1*-4*-4"));
	}
	
	@Test
	void multiple_double_operators_times_plus() {
		double result = 1*+4*+4;
		
		assertEquals(result, calculator.evaluate("1*+4*+4"));
	}
	
	@Test
	void multiple_double_operators_plus_minus() {
		double result = 1+-2+-3;
				
		assertEquals(result, calculator.evaluate("1+-2+-3"));
	}
	
	@Test
	void multiple_double_operators_minus_plus() {
		double result = 1-+2-+3;
				
		assertEquals(result, calculator.evaluate("1-+2-+3"));
	}
	
	@Test
	void multiple_operations_times_then_plus() {
		double result = 11*2+3;
		
		assertEquals(result, calculator.evaluate("11*2+3"));
	}
	
	@Test
	void multiple_operations_plus_then_times() {
		double result = 1+22*3;
		
		assertEquals(result, calculator.evaluate("1+22*3"));
	}
	
	@Test
	void multiple_operations_times_and_plus() {
		double result = 1+22*3+44*5;
		
		assertEquals(result, calculator.evaluate("1+22*3+44*5"));
	}
	
	@Test
	void multiple_operations_times_and_minus() {
		double result = 1-2*2-3;
		
		assertEquals(result, calculator.evaluate("1-2*2-3"));
	}
	
	@Test
	void multiple_operations2_times_and_minus() {
		double result = 1-2*22-33;
		
		assertEquals(result, calculator.evaluate("1-2*22-33"));
	}
	
	
	@Test
	void multiple_operations_with_decimals() {
		double result = 10.4+1.3*2.6+3.1*4.1/5.1;
		
		assertEquals(result, calculator.evaluate("10.4+1.3*2.6+3.1*4.1/5.1"));
	}
	
	
	@Test
	void mutiple_operations_with_leading_minus_sign() {
		double result = -10+1*2+3*4/2;
		
		assertEquals(result, calculator.evaluate("-10+1*2+3*4/2"));
	}
	
	@Test
	void mutiple_operations_with_leading_plus_sign() {
		double result = +10+1*2+3*4/2;
		
		assertEquals(result, calculator.evaluate("+10+1*2+3*4/2"));
	}
	
	@Test
	void multiple_operations_with_divide_minus_double_operator_multiply_divide_order_check() {
		double result = 8/2*7/-4*9;
		
		assertEquals(result, calculator.evaluate("8/2*7/-4*9"));
	}
	
	@Test
	void multiple_operations_with_times_plus_double_operator_divided_by_negative() {
		double result = 1*+44-5/-1;
		
		assertEquals(result, calculator.evaluate("1*+44-5/-1"));
	}
	
	
	
	// Tests for brackets handling
	
	@Test
	void single_bracket_addition() {
		double result = (1+2);
		
		assertEquals(result, calculator.evaluate("(1+2)"));
	}
	
	@Test
	void single_bracket_minus() {
		double result = (4-5);
		
		assertEquals(result, calculator.evaluate("(4-5)"));
	}
	
	@Test
	void single_bracket_times() {
		double result = (3*7);
		
		assertEquals(result, calculator.evaluate("(3*7)"));
	}
	
	@Test
	void single_bracket_divide() {
		double result = (6/3);
		
		assertEquals(result, calculator.evaluate("(6/3)"));
	}
	
	@Test
	void single_bracket_multiple_operations() {
		double result = (1+1-1);
		
		assertEquals(result, calculator.evaluate("(1+1-1)"));
	}
	
	@Test
	void single_bracket_multiple_operations_outside_bracket() {
		double result = (1-1)+1;
		
		assertEquals(result, calculator.evaluate("(1-1)+1"));
	}
	
	@Test
	void single_bracket_multiple_operations_outside_bracket_times_inside() {
		double result = (1*1)+1;
		
		assertEquals(result, calculator.evaluate("(1*1)+1"));
	}
	
	@Test
	void single_bracket_multiple_operations_outside_bracket_divide_inside() {
		double result = (1/1)+1;
		
		assertEquals(result, calculator.evaluate("(1/1)+1"));
	}
	
	@Test
	void single_bracket_multiple_operations_outside_bracket_divide_inside_times_plus_outside() {
		double result = 5*(6/3)+1;
		
		assertEquals(result, calculator.evaluate("5*(6/3)+1"));
	}
	
	@Test
	void single_bracket_multiple_operations_outside_bracket_with_negative_sign_inside() {
		double result = 67*87+(-68+89)-38*2;
		
		assertEquals(result, calculator.evaluate("67*87+(-68+89)-38*2"));
	}
	
	@Test
	void single_bracket_multiple_operations_outside_bracket_with_negative_sign_inside_divided_by_bracket() {
		double result = 67.0*87.0/(-68.0+89.0)-38.0*2.0;
		
		assertEquals(result, calculator.evaluate("67*87/(-68+89)-38*2"));
	}
	
	@Test
	void nested_bracket_single_operation_plus() {
		double result = ((1+1));
		
		assertEquals(result, calculator.evaluate("((1+1))"));
	}
	
	@Test
	void nested_bracket_single_operation_minus() {
		double result = ((1-1));
		
		assertEquals(result, calculator.evaluate("((1-1))"));
	}
	
	@Test
	void nested_bracket_single_operation_times() {
		double result = ((1*1));
		
		assertEquals(result, calculator.evaluate("((1*1))"));
	}
	
	@Test
	void nested_bracket_single_operation_divide() {
		double result = ((1/1));
		
		assertEquals(result, calculator.evaluate("((1/1))"));
	}
	
	@Test
	void nested_brackets_with_multiple_operations_next_to_bracket() {
		double result = ((1/1)+3)*3;
		
		assertEquals(result, calculator.evaluate("((1/1)+3)*3"));
	}
	
	@Test
	void multiple_nested_brackets_with_multiple_operations_next_to_bracket() {
		double result = ((1+3)+4*6+(7*8))*8;
		
		assertEquals(result, calculator.evaluate("((1+3)+4*6+(7*8))*8"));
	}
	
	@Test
	void multiple_nested_brackets_with_multiple_operations_next_to_bracket_with_operation_outside_bracket() {
		double result = ((1+3)+4*6+(7*8)-(4-(8*3)))*8;
		
		assertEquals(result, calculator.evaluate("((1+3)+4*6+(7*8)-(4-(8*3)))*8"));
	}
	
	
	
	// Tests for simple exponential operations
	
	@Test
	void single_exponent_operation() {
		double result = Math.pow(2, 2);
		
		assertEquals(result, calculator.evaluate("2^2"));
	}
	
	@Test
	void single_exponent_operation_zero_exponent() {
		double result = Math.pow(2, 0);
		
		assertEquals(result, calculator.evaluate("2^0"));
	}
	
	@Test
	void single_exponent_operation_negative_exponent() {
		double result = Math.pow(2, -1);
		
		assertEquals(result, calculator.evaluate("2^-1"));
	}
	
	@Test
	void single_exponent_operation_one_exponent() {
		double result = Math.pow(2, 1);
		
		assertEquals(result, calculator.evaluate("2^1"));
	}
	
	@Test
	void single_exponent_zero_raised_to_zero() {
		
		assertThrows(IllegalArgumentException.class, () -> {calculator.power(0,0);});
	}
	
	@Test
	void single_exponent_operation_decimal_base() {
		double result = Math.pow(2.8, 2);
		
		assertEquals(result, calculator.evaluate("2.8^2"));
	}
	
	@Test
	void single_exponent_operation_with_multiple_operations() {
		double result = 3*Math.pow(2, 2)/5;
		
		assertEquals(result, calculator.evaluate("3*2^2/5"));
	}
	
	@Test
	void single_exponent_operation_with_multiple_operations_and_negative() {
		double result = -3*-1*Math.pow(2, 2)/5+5*8;
		
		assertEquals(result, calculator.evaluate("-3*-2^2/5+5*8"));
	}
	
	@Test
	void multiple_exponent_operation_with_multiple_operations() {
		double result = -3*-1*Math.pow(2, 2)/5+5*8+Math.pow(3, 2);
		
		assertEquals(result, calculator.evaluate("-3*-2^2/5+5*8+3^2"));
	}
	
	
	
	// Tests for exponential operations with parenthesis
	
	@Test
	void exponent_operation_with_brackets() {
		double result = Math.pow(2,(2*2));
		
		assertEquals(result, calculator.evaluate("2^(2*2)"));
	}
	
	
	@Test
	void nested_exponent_operation_with_brackets() {
		
		assertEquals(196631, calculator.evaluate("68+2^(2*2)^2*3-45"));
	}
	
	
	
	// Tests for more complex calculations
	
	@Test
	void nested_brackets_multiple_operations() {
		double result = 4+(3*(2+1)) - Math.pow(3,2);
		
		assertEquals(result, calculator.evaluate("4 + (3 * (2 + 1)) - (5 - 2)^2"));
	}
	
	@Test
	void multiple_brackets_with_multiple_exponent_operations() {
		double result = (8+1) * (3+16);
		
		assertEquals(result, calculator.evaluate("(2^3 + 1) * (3 + 4^2)"));
	}
	
	@Test
	void nested_brackets_with_multiple_operations_and_outside_exponent() {
		double result =2*Math.pow((3+(4-1)*3),2);
		
		assertEquals(result, calculator.evaluate("2 * (3 + (4 - 1) * (6 / 2))^2"));
	}
	
	@Test
	void BODMAS_check() {
		double result = 5-4+8*3/4*Math.pow(2, 2)-(7-4);
		
		assertEquals(result, calculator.evaluate("5-4+8*3/4*2^2-(7-4)"));
	}
	@Test
	void final_test_all_operations() {
		
		assertEquals(-90.5041667, calculator.evaluate("-3*((2)^2/5+5*8)-(4+3*(2.5^2-(1/3)^2)/4)+(7-2.5)*(6/2)^2"), 0.0001);
	}

	@Test
	void final_test_all_operations_2()
	{
		double result = 2*((2+Math.pow(1+3,2)*2+2)+5+(2.5*20/(1+5-5*4)+2)+8/2*7+2+8*(200/5-15*5));
		assertEquals(result, calculator.evaluate("2*((2+(1+3)^2*2+2)+5+(2.5*20/(1+5-5*4)+2)+8/2*7+2--8*(200/5-15*5))"));
	}
	
	
}
