package com.github.jakz.jplot;

import static org.junit.Assert.assertEquals;

import static com.github.jakz.jplot.ast.Expression.*;
import static com.github.jakz.jplot.parser.ExpressionBuilder.*;

import org.junit.Test;

import com.github.jakz.jplot.ast.simplification.SubstituteSubtree;

public class SubstituteSubtreeTransformerTest
{
  @Test
  public void testWholeExpressionTransformation()
  {
    assertEquals(of("b+1"), of("a").transform(new SubstituteSubtree(of("a"), of("b+1"))));
    assertEquals(of("x-1^5"), of("a+5").transform(new SubstituteSubtree(of("a+5"), of("x-1^5"))));
    
    assertEquals(of("x^2"), of("x^2").transform(new SubstituteSubtree(of("y"), of("x"))));
  }
  
  @Test
  public void testWholeExpressionAsOperator()
  {
    assertEquals(of("x"), of("substitute(y, y, x)"));
  }
}
