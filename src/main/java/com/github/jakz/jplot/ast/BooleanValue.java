package com.github.jakz.jplot.ast;

import com.github.jakz.jplot.cas.Environment;

public class BooleanValue extends Expression
{
  private final boolean value;
  
  public BooleanValue(boolean value)
  {
    this.value = value;
  }
  
  public boolean value()
  {
    return value;
  }
  
  @Override
  public Type type()
  {
    return Type.BOOLEAN;
  }
  
  @Override
  public Expression dupe()
  {
    return new BooleanValue(value);
  }
  
  @Override public String toTeX()
  {
    return Boolean.toString(value);
  }
  
  @Override public String toTextual()
  {
    return  Boolean.toString(value);
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
  
  @Override
  public Expression evaluate(Environment env)
  {
    return this;
  }
  
  @Override public boolean equals(Object other)
  {
    return other instanceof BooleanValue && ((BooleanValue)other).value == value;
  }
  
  
  
  public static BooleanValue of(boolean value) { return new BooleanValue(value); }

}
