package com.github.jakz.jplot.ast.simplification;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.Operation;
import com.github.jakz.jplot.ast.Transformer;

public class SubstituteSubtreeRule implements Transformer
{
  private final Expression from;
  private final Expression to;
  
  public SubstituteSubtreeRule(Expression from, Expression to)
  {
    this.from = from.strip();
    this.to = to.strip();
    
    if (!(this.from instanceof Operation))
    {
      throw new IllegalArgumentException("rule in a SubstituteSubtreeRule must be an operation");
    }
  }
  
  
  @Override
  public Expression apply(Expression parent, Expression... children)
  {
    if (parent instanceof Operation)
    {
      //TODO:
      return null;
    }
    else
      return parent;
  }

}
