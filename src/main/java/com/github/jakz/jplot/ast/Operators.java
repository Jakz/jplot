package com.github.jakz.jplot.ast;

public class Operators
{

  
  public static final Operator ADDITION = new Operator("+", 2, (env, exprs) -> {
    Value v1 = exprs[0].evaluate(env);
    Value v2 = exprs[1].evaluate(env);
    return new Value(v1.value().add(v2.value()));
  });
}
