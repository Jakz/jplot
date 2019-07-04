package com.github.jakz.jplot.ast.simplification;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.Transformer;
import com.github.jakz.jplot.parser.ExpressionBuilder;

public class SubstituteSubtree implements Transformer
{
  private final Expression from, to;
   
  public SubstituteSubtree(Expression from, Expression to)
  {
    this.from = from.strip();
    this.to = to.strip();
  }
  
  public SubstituteSubtree(String from, String to)
  {
    this(ExpressionBuilder.of(from), ExpressionBuilder.of(to));
  }
  
  @Override
  public Expression apply(Expression parent, Expression... children)
  {
    return parent.equals(from) ? to : parent;
  }
}
