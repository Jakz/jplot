package com.github.jakz.jplot.ast;

import com.github.jakz.jplot.cas.Environment;

// http://www.math.wpi.edu/IQP/BVCalcHist/calc5.html#_Toc407004388

public interface Expression
{

  Value evaluate(Environment env);
  void accept(Visitor visitor);
  Expression transform(Transformer transformer);
  
  String toTeX();
  
  default Operator operator()
  { 
    if (this instanceof Operator)
      return ((Operation)this).operator();
    return null;
  }
  
  public static Value integral(long value) { return new Value(value); }
  public static Expression sum(Expression... operands) { return new Operation(Operators.ADDITION, operands); }
  public static Expression multiplication(Expression... operands) { return new Operation(Operators.MULTIPLICATION, operands); }

  public static Expression negation(Expression operand) { return new Operation(Operators.NEGATION, operand); }
}
