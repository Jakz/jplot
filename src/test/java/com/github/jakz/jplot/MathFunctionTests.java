package com.github.jakz.jplot;

import static com.github.jakz.jplot.ast.Expression.*;
import static com.github.jakz.jplot.parser.ExpressionBuilder.of;
import static com.github.jakz.jplot.parser.ExpressionBuilder.ofd;
import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class MathFunctionTests
{
  @Test
  public void testFactorial()
  {
    assertEquals(num(1), of("0!").operation().apply());
    assertEquals(num(1), of("1!").operation().apply());
    assertEquals(num(2), of("2!").operation().apply());
    assertEquals(num(6), of("3!").operation().apply());
    assertEquals(num(24), of("4!").operation().apply());
    assertEquals(num(120), of("5!").operation().apply());
  }
  
  @Test
  public void testBinomial()
  {
    assertEquals(of("n! / (k! * ((n - k)!) )"), of("binomial(n,k)").operation().apply());
    
    
    assertEquals(num(10), of("binomial(0,0)").evaluate(null));
    
    assertEquals(num(1), of("binomial(1,0)").evaluate(null));
    assertEquals(num(1), of("binomial(1,1)").evaluate(null));
   
    assertEquals(num(1), of("binomial(2,0)").evaluate(null));
    assertEquals(num(2), of("binomial(2,1)").evaluate(null));
    assertEquals(num(1), of("binomial(2,2)").evaluate(null));
    
    assertEquals(num(1), of("binomial(3,0)").evaluate(null));
    assertEquals(num(3), of("binomial(3,1)").evaluate(null));
    assertEquals(num(3), of("binomial(3,2)").evaluate(null));
    assertEquals(num(1), of("binomial(3,3)").evaluate(null));
  }
}
