package com.github.jakz.jplot.ast;

import com.github.jakz.jplot.cas.Environment;

public class Variable implements Expression
{
  private String name;
  
  public Variable(String name)
  {
    this.name = name;
  }
  
  public String name() { return name; }
  
  @Override
  public Value evaluate(Environment env)
  {
    //TODO: if env has value for variable then return value otherwise unresolved term
    return new Value(0);
  }

  @Override
  public void accept(Visitor visitor)
  {
    visitor.enter(this);
    visitor.doVisit(this);
    visitor.leave(this);
  }

  @Override
  public Expression transform(Transformer transformer)
  {
    return transformer.apply(this);
  }

  @Override
  public String toTeX()
  {
    return name;
  }
  
  @Override
  public boolean equals(Object other)
  {
    return other instanceof Variable && ((Variable)other).name.equals(name);
  }
}
