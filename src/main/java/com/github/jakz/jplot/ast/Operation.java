package com.github.jakz.jplot.ast;

import java.util.function.Function;

import com.github.jakz.jplot.cas.Environment;

public class Operation implements Expression
{
  private Operator operator;
  private Expression[] operands;
  
  public Operation(Operator operator, Expression... operands)
  {
    if (operands.length != operator.args)
      throw new IllegalArgumentException(String.format("Call to operator %s must have %d arguments but has %d instead", operator.name, operator.args, operands.length));

    this.operator = operator;
    this.operands = operands;
  }
  
  @Override public Value evaluate(Environment env)
  {
    return operator.apply(env, operands).evaluate(env);
  }
}
