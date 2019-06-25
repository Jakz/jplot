package com.github.jakz.jplot.ast.simplification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.Operation;
import com.github.jakz.jplot.ast.Operator;
import com.github.jakz.jplot.ast.Transformer;

public class CommutativeOperatorLeveler implements Transformer
{

  @Override
  public Expression apply(Expression parent, Expression... children)
  {
    if (!(parent instanceof Operation))
      return parent;
      
    Operator operator = ((Operation)parent).operator();
    
    if (!operator.commutative)
      return parent;
    
    List<Expression> operands = new ArrayList<>();
    
    for (Expression operand : children)
    {
      if (operand instanceof Operation)
      {
        Operation inner = (Operation)operand;
        
        if (inner.operator().equals(operator))
        {
          operands.addAll(Arrays.asList(inner.operands()));
        }
      }
      else
        operands.add(operand);
    }
    
    return new Operation(operator, operands.toArray(new Expression[operands.size()]));
  }
}
