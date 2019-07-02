package com.github.jakz.jplot.ast;

import com.github.jakz.jplot.cas.Environment;

// http://www.math.wpi.edu/IQP/BVCalcHist/calc5.html#_Toc407004388
// https://en.wikipedia.org/wiki/Rewriting

public abstract class Expression
{
  public abstract Value evaluate(Environment env);
  public abstract void accept(Visitor visitor);
  public abstract Expression transform(Transformer transformer);
  
  public abstract String toTeX();
  public abstract String toTextual();
  
  public Operator operator()
  { 
    if (this instanceof Operation)
      return ((Operation)this).operator();
    return null;
  }
  
  public String toString()
  {
    return toTextual();
  }
  
  public static Value integral(long value) { return new Value(value); }
  public static Value num(long value) { return new Value(value); }

  
  public static Expression addition(Expression... operands) { return new Operation(Operators.ADDITION, operands); }
  public static Expression subtraction(Expression... operands) { return new Operation(Operators.SUBTRACTION, operands); }
  public static Expression multiplication(Expression... operands) { return new Operation(Operators.MULTIPLICATION, operands); }
  public static Expression division(Expression... operands) { return new Operation(Operators.DIVISION, operands); }

  public static Expression sum(Expression... operands) { return addition(operands); }
  public static Expression sub(Expression... operands) { return subtraction(operands); }
  public static Expression mul(Expression... operands) { return multiplication(operands); }
  public static Expression div(Expression... operands) { return division(operands); }
  
  public static Expression exp(Expression lhs, Expression rhs) { return new Operation(Operators.EXPONENTIATION, lhs, rhs); }
  public static Expression negation(Expression operand) { return new Operation(Operators.NEGATION, operand); }
}
