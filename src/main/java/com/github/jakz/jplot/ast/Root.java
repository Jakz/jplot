package com.github.jakz.jplot.ast;

import com.github.jakz.jplot.cas.Environment;

public class Root implements Expression
{
  private Expression expression;
  
  public Root(Expression expression)
  {
    this.expression = expression;
  }

  @Override
  public Value evaluate(Environment env)
  {
    return expression.evaluate(env);
  }

  @Override
  public void accept(Visitor visitor)
  {
    expression.accept(visitor);
  }

  @Override
  public Expression transform(Transformer transformer)
  {
    expression = expression.transform(transformer);
    return this;
  }
}
