package com.github.jakz.jplot.ast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

import com.github.jakz.jplot.cas.Environment;

public class Operation extends Expression
{
  private Operator operator;
  private Expression[] operands;

  public Operation(Operator operator, Expression... operands)
  {
    this.operator = Objects.requireNonNull(operator);
    this.operands = operands;
    
    //TODO: enhance for operators which allows n+ operands (eg commutative sum)
    if (operands.length < operator.args)
      throw new IllegalArgumentException(String.format("Call to operator %s must have %d arguments but has %d instead", operator.name, operator.args, operands.length));
  }
  
  public Operation(Operator operator, Collection<Expression> operands)
  {
    this(operator, operands.toArray(new Expression[operands.size()]));
  }
  
  public Operator operator() { return operator; }
  public Expression[] operands() { return operands; }
  
  @Override public Expression dupe()
  {
    return new Operation(operator, Arrays.stream(operands).map(Expression::dupe).toArray(i -> new Expression[i]));
  }
  
  @Override public Type type()
  {
    return operator.type();
  }
  
  @Override public String toTeX()
  {
    return operator.toTeX(operands);
  }
  
  @Override public String toTextual()
  {
    return operator.toTextual(operands);
  }
  
  @Override public Expression evaluate(Environment env)
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
  
  @Override public boolean equals(Object other)
  {  
    if (other instanceof Operation)
    {
      Operation op = (Operation)other;
      return op.operator.equals(operator) && Arrays.equals(op.operands, operands);
    }
    else
      return false;
  }
}
