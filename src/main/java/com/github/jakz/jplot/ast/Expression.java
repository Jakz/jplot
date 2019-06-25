package com.github.jakz.jplot.ast;

import com.github.jakz.jplot.cas.Environment;

// http://www.math.wpi.edu/IQP/BVCalcHist/calc5.html#_Toc407004388

public interface Expression
{

  Value evaluate(Environment env);
  void accept(Visitor visitor);
  Expression transform(Transformer transformer);
  
  public static Value integral(long value) { return new Value(value); }
  public static Expression sum(Expression... operands) { return new Operation(Operators.ADDITION, operands); }
}
