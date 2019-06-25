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
}
