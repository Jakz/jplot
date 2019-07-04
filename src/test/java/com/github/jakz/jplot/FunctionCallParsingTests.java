package com.github.jakz.jplot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import static com.github.jakz.jplot.ast.Expression.*;
import static com.github.jakz.jplot.parser.ExpressionBuilder.*;

public class FunctionCallParsingTests
{
  @Test
  public void testOneArgument()
  {
    assertEquals(fun("sin", num(10)), of("sin(10)"));
  }
  
  @Test
  public void testTwoArguments()
  {
    assertEquals(fun("atan2", var("x"), num(5)), of("atan2(x,5)"));
  }
  
}
