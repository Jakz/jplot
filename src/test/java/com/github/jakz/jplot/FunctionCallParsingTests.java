package com.github.jakz.jplot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import static com.github.jakz.jplot.ast.Expression.*;
import static com.github.jakz.jplot.parser.ExpressionBuilder.*;

public class FunctionCallParsingTests
{
  @Test
  public void testSin()
  {
    assertEquals(fun("sin", num(10)), ofd("sin(10)"));
  }
}
