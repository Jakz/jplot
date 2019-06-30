package com.github.jakz.jplot.ast;

import java.math.BigDecimal;

import com.github.jakz.jplot.cas.Environment;

public class Value implements Expression
{
  private BigDecimal value;
  
  public Value(BigDecimal value)
  {
    this.value = value;
  }
  
  public Value(long value)
  {
    this.value = BigDecimal.valueOf(value);
  }
  
  public Value evaluate(Environment env)
  {
    return this;
  }
  
  public BigDecimal value()
  {
    return value;
  }
  
  @Override public String toTeX()
  {
    return value.toString();
  }
  
  @Override public Expression transform(Transformer transformer)
  {
    return transformer.apply(this, new Expression[0]);
  }

  @Override public void accept(Visitor visitor)
  {
    visitor.enter(this);
    visitor.doVisit(this);
    visitor.leave(this);
  }
}
