package com.github.jakz.jplot.ast;

import java.util.function.Function;

import com.github.jakz.jplot.cas.Environment;

public class Operation implements Expression
{
  private Operator operator;
  private Expression[] operands;
  
  public Operation(Operator operator, Expression... operands)
  {
    //TODO: enhance for operators which allows n+ operands (eg commutative sum)
    if (operands.length < operator.args)
      throw new IllegalArgumentException(String.format("Call to operator %s must have %d arguments but has %d instead", operator.name, operator.args, operands.length));

    this.operator = operator;
    this.operands = operands;
  }
  
  public Operator operator() { return operator; }
  public Expression[] operands() { return operands; }
  
  @Override public String toTeX()
  {
    return operator.toTeX(operands);
  }
  
  @Override public Value evaluate(Environment env)
  {
    return operator.apply(env, operands).evaluate(env);
  }
  
  public Expression transform(Transformer transformer)
  {
    for (int i = 0; i < operands.length; ++i)
      operands[i] = operands[i].transform(transformer);
    
    return transformer.apply(this, operands);
  }
  
  @Override public void accept(Visitor visitor)
  {
    visitor.enter(this);
    visitor.doVisit(this);
    for (Expression operand : operands) 
      operand.accept(visitor);
    visitor.leave(this);
  }
}
