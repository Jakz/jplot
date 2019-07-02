package com.github.jakz.jplot.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.Value;
import com.github.jakz.jplot.ast.Variable;

import static com.github.jakz.jplot.ast.Expression.*;
import static com.github.jakz.jplot.parser.ExpressionBuilder.of;

public class LiteralTest
{
  @Test
  public void parseIntegralToValue()
  {    
    assertEquals(new Value(5), of("5"));
    assertEquals(new Value(123), of("123"));
    assertEquals(new Value(23), of("0023"));
    assertEquals(new Value(0), of("0"));
  }
  
  @Test
  public void parseIdentifier()
  {
    assertEquals(new Variable("x"), of("x"));
    assertEquals(new Variable("foo"), of("foo"));
  }
  
  @Test
  public void testSimpleBinaryExpressions()
  {
    assertEquals(sum(integral(2), integral(5)), of("2 + 5"));
    assertEquals(subtraction(integral(2), integral(5)), of("2 - 5"));
    assertEquals(multiplication(integral(2), integral(5)), of("2 * 5"));
    assertEquals(division(integral(2), integral(5)), of("2 / 5"));
  }
  
  @Test
  public void testSimpleAdditiveTernaryExpressions()
  {
    assertEquals(sum(sum(integral(2), integral(5)), integral(3)), of("2 + 5 + 3"));
    assertEquals(sum(subtraction(integral(2), integral(5)), integral(3)), of("2 - 5 + 3"));
    assertEquals(subtraction(sum(integral(2), integral(5)), integral(3)), of("2 + 5 - 3"));
  }
  
  @Test
  public void testSimpleAdditiveMultiplicativeExpressions()
  {
    assertEquals(mul(mul(integral(2), integral(5)), integral(3)), of("2 * 5 * 3"));
    assertEquals(mul(div(integral(2), integral(5)), integral(3)), of("2 / 5 * 3"));
    assertEquals(div(mul(integral(2), integral(5)), integral(3)), of("2 * 5 / 3"));
  }
  
  @Test
  public void testExponentiationAssociativity()
  {
    assertEquals(exp(num(2), num(3)), of("2^3"));
    assertEquals(exp(num(2), exp(num(3), num(4))), of("2^3^4"));
    assertEquals(exp(exp(num(2), num(3)), num(4)), of("(2^3)^4"));
  }
}
