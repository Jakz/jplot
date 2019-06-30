package com.github.jakz.jplot.ast;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Operators
{

  
  public static final Operator ADDITION = new Operator("+", 2, true, 
  (env, exprs) -> {
    return Arrays.stream(exprs)
        .reduce(
            new Value(0), 
            (v, e) -> new Value(v.value().add(e.evaluate(env).value())), 
            (v1, v2) -> new Value(v1.value().add(v2.value()))
        );
  },
  exprs -> Arrays.stream(exprs).map(Expression::toTeX).collect(Collectors.joining(" + ", "(", ")"))
  );
  
  public static final Operator MULTIPLICATION = new Operator("*", 2, true, 
  (env, exprs) -> {
    return Arrays.stream(exprs)
        .reduce(
            new Value(0), 
            (v, e) -> new Value(v.value().multiply(e.evaluate(env).value())), 
            (v1, v2) -> new Value(v1.value().multiply(v2.value()))
        );
  },
  exprs -> Arrays.stream(exprs).map(Expression::toTeX).collect(Collectors.joining("\\cdot", "(", ")"))
  );
  
  public static final Operator NEGATION = new Operator("-", 1, false,
  (env, exprs) -> new Value(exprs[0].evaluate(env).value().negate()),
  exprs -> "-" + exprs[0].toTeX()
  );
}
