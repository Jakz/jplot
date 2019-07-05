package com.github.jakz.jplot;

import static com.github.jakz.jplot.ast.Expression.*;
import static com.github.jakz.jplot.parser.ExpressionBuilder.of;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FreeOfMetaOperatorTest
{
  @Test
  public void testDirectSearch()
  {
    assertEquals(bool(false), of("freeOf(x, x)").operation().apply());
    assertEquals(bool(true), of("freeOf(x, y)").operation().apply());
  }
}
