package com.github.jakz.jplot.ast;

import java.math.BigDecimal;

import com.github.jakz.jplot.cas.Environment;

public class Number extends Expression
{
  private BigDecimal value;
  
  public Number(BigDecimal value)
  {
    this.value = value;
  }
  
  public Number(long value)
  {
    this.value = BigDecimal.valueOf(value);
  }
  
  public Number(double value)
  {
    this.value = BigDecimal.valueOf(value);
  }
  
  @Override
  public Type type()
  {
    return Type.NUMBER;
  }

  @Override
  public Expression evaluate(Environment env)
  {
    return this;
  }
  
  public BigDecimal value()
  {
    return value;
  }
  
  public double dvalue()
  {
    return value.doubleValue();
  }
  
  @Override
  public Expression dupe()
  {
    return new Number(value);
  }
  
  @Override public String toTeX()
  {
    return value.toString();
  }
  
  @Override public String toTextual()
  {
    return value.toString();
  }
  
  @Override public Expression transform(Transformer transformer)
  {
    return transformer.apply(this);
  }

  @Override public void accept(Visitor visitor)
  {
    visitor.enter(this);
    visitor.doVisit(this);
    visitor.leave(this);
  }
  
  @Override public boolean equals(Object other)
  {
    return other instanceof Number && ((Number)other).value.equals(value);
  }
}
