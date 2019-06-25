package com.github.jakz.jplot.ast;

import com.github.jakz.jplot.cas.Environment;

// http://www.math.wpi.edu/IQP/BVCalcHist/calc5.html#_Toc407004388

public interface Expression
{
  Value evaluate(Environment env);
  
  public static Value integral(long value) { return new Value(value); }
  public static Expression sum(Expression o1, Expression o2) { return new Operation(Operators.ADDITION, o1, o2); }
}
