package com.github.jakz.jplot.ast;

import java.util.Arrays;

public class Operators
{

  
  public static final Operator ADDITION = new Operator("+", 2, true, (env, exprs) -> {
    return Arrays.stream(exprs)
        .reduce(
            new Value(0), 
            (v, e) -> new Value(v.value().add(e.evaluate(env).value())), 
            (v1, v2) -> new Value(v1.value().add(v2.value()))
        );
  });
}
