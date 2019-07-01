package com.github.jakz.jplot.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.Value;

public class LiteralTest
{
  @Test
  public void parseIntegralToValue()
  {    
    assertEquals(ExpressionBuilder.of("5"), new Value(5));
    assertEquals(ExpressionBuilder.of("123"), new Value(123));
    assertEquals(ExpressionBuilder.of("0023"), new Value(23));
    assertEquals(ExpressionBuilder.of("0"), new Value(0));

  }
}
