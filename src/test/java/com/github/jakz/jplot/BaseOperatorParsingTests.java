package com.github.jakz.jplot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.Number;
import com.github.jakz.jplot.ast.Variable;

import static com.github.jakz.jplot.ast.Expression.*;
import static com.github.jakz.jplot.parser.ExpressionBuilder.of;

public class BaseOperatorParsingTests
{  
  @Test
  public void testUnaryMinusExpressions()
  {
    assertEquals(neg(num(5)), of("-5"));
    assertEquals(neg(neg(num(5))), of("- -5"));
    assertEquals(sub(num(2), var("b")), of("2-b"));
    assertEquals(sub(num(2), neg(var("b"))), of("2 - -b"));
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
