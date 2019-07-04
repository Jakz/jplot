package com.github.jakz.jplot;

import static com.github.jakz.jplot.parser.ExpressionBuilder.of;
import static com.github.jakz.jplot.ast.Expression.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.jakz.jplot.ast.Number;
import com.github.jakz.jplot.ast.Variable;
import com.github.jakz.jplot.parser.ParseException;

public class TerminalParsingTests
{
  @Test
  public void parseIntegralToValue()
  {    
    assertEquals(num(5), of("5"));
    assertEquals(num(123), of("123"));
    assertEquals(num(23), of("0023"));
    assertEquals(num(0), of("0"));
  }
  
  @Test
  public void parseSimpleIdentifier()
  {
    assertEquals(var("x"), of("x"));
    assertEquals(var("foo"), of("foo"));
  }
  
  @Test
  public void parseGreekIdentifier()
  {
    assertEquals(var("α"), of("α"));
    assertEquals(var("Δx"), of("Δx"));
  }
  
  @Test(expected = ParseException.class)
  public void parseInvalidIdentifierStartingWithDigit()
  {
    of("1foo");
  }
  
  @Test(expected = ParseException.class)
  public void parseInvalidIdentifierStartingWithUnderscore()
  {
    of("_foo");
  }
  
}
