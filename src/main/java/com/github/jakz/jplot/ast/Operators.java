package com.github.jakz.jplot.ast;

import java.util.Arrays;
import java.util.Map;
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
  
  public static final Operator SUBTRACTION = new Operator("-", 2, false, 
  (env, exprs) -> {
    return Arrays.stream(exprs)
        .reduce(
            new Value(0), 
            (v, e) -> new Value(v.value().subtract(e.evaluate(env).value())), 
            (v1, v2) -> new Value(v1.value().subtract(v2.value()))
        );
  },
  exprs -> Arrays.stream(exprs).map(Expression::toTeX).collect(Collectors.joining(" - ", "(", ")"))
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
  
  public static final Operator DIVISION = new Operator("/", 2, false, 
  (env, exprs) -> {
    return Arrays.stream(exprs)
        .reduce(
            new Value(0), 
            (v, e) -> new Value(v.value().divide(e.evaluate(env).value())), 
            (v1, v2) -> new Value(v1.value().divide(v2.value()))
        );
  },
  exprs -> Arrays.stream(exprs).map(Expression::toTeX).collect(Collectors.joining("/", "(", ")"))
  );
  
  public static final Operator EXPONENTIATION = new Operator("^", 2, false,
  (env, exprs) -> {
    return Arrays.stream(exprs)
        .reduce(
            new Value(0), 
            //TODO: wrong because pow only accepts int
            (v, e) -> new Value(v.value().pow(e.evaluate(env).value().intValue())), 
            (v1, v2) -> new Value(v1.value().pow(v2.value().intValue()))
        );
  },
  exprs -> Arrays.stream(exprs).map(Expression::toTeX).collect(Collectors.joining("^", "(", ")"))  
  );
  
  
  public static final Operator NEGATION = new Operator("-", 1, false,
  (env, exprs) -> new Value(exprs[0].evaluate(env).value().negate()),
  exprs -> "-" + exprs[0].toTeX()
  );
  
  private static final Map<String, Operator> operators =
    Map.ofEntries(
      Map.entry("+", ADDITION),
      Map.entry("-", SUBTRACTION),
      Map.entry("*", MULTIPLICATION),
      Map.entry("/", DIVISION),
      Map.entry("^", EXPONENTIATION)
    );
  
  public static Operator of(String mnemonic)
  { 
    Operator op = operators.get(mnemonic);
    
    if (op == null)
      throw new IllegalArgumentException("Unknown operator: "+mnemonic);
    
    return op;
  }
}
