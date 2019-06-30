package com.github.jakz.jplot.ast.simplification;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.Operation;
import com.github.jakz.jplot.ast.Operator;
import com.github.jakz.jplot.ast.Operators;
import com.github.jakz.jplot.ast.Transformer;

public class NegationToMultiplication implements Transformer
{

  @Override
  public Expression apply(Expression parent, Expression... children)
  {
    Operator operation = parent.operator();
    
    if (Operators.NEGATION.equals(operation))
      return Expression.multiplication(Expression.integral(-1), children[0]);
    
    return parent;
  }

}
