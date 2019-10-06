package com.github.jakz.jplot.ast;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

import com.github.jakz.jplot.ast.simplification.SubstituteSubtree;

public class Operators
{
  private static Function<Expression[], String> functionCallRenderer(String name) {
    return exprs -> Arrays.stream(exprs).map(Expression::toTeX).collect(Collectors.joining(", ", name + "(", ")"));
  }
  
  public static final Operator ADDITION = new Operator("+", Type.NUMBER, 2, true, 
  (env, exprs) -> {
    return Arrays.stream(exprs)
        .reduce(
            new Number(0), 
            (v, e) -> new Number(v.value().add(e.evaluate(env).number().value())), 
            (v1, v2) -> new Number(v1.value().add(v2.value()))
        );
  },
  exprs -> Arrays.stream(exprs).map(Expression::toTeX).collect(Collectors.joining(" + ", "(", ")"))
  );
  
  public static final Operator SUBTRACTION = new Operator("-", Type.NUMBER, 2, false, 
  (env, exprs) -> {
    return Arrays.stream(exprs)
        .reduce(
            new Number(0), 
            (v, e) -> new Number(v.value().subtract(e.evaluate(env).number().value())), 
            (v1, v2) -> new Number(v1.value().subtract(v2.value()))
        );
  },
  exprs -> Arrays.stream(exprs).map(Expression::toTeX).collect(Collectors.joining(" - ", "(", ")"))
  );
  
  public static final Operator MULTIPLICATION = new Operator("*", Type.NUMBER, 2, true, 
  (env, exprs) -> {
    return Arrays.stream(exprs)
        .reduce(
            new Number(0), 
            (v, e) -> new Number(v.value().multiply(e.evaluate(env).number().value())), 
            (v1, v2) -> new Number(v1.value().multiply(v2.value()))
        );
  },
  exprs -> Arrays.stream(exprs).map(Expression::toTeX).collect(Collectors.joining("\\cdot", "(", ")"))
  );
  
  public static final Operator DIVISION = new Operator("/", Type.NUMBER, 2, false, 
  (env, exprs) -> {
    return Arrays.stream(exprs)
        .reduce(
            new Number(0), 
            (v, e) -> new Number(v.value().divide(e.evaluate(env).number().value())), 
            (v1, v2) -> new Number(v1.value().divide(v2.value()))
        );
  },
  exprs -> Arrays.stream(exprs).map(Expression::toTeX).collect(Collectors.joining("/", "(", ")"))
  );
  
  public static final Operator EXPONENTIATION = new Operator("^", Type.NUMBER, 2, false,
  (env, exprs) -> {
    return Arrays.stream(exprs)
        .reduce(
            new Number(0), 
            //TODO: wrong because pow only accepts int
            (v, e) -> new Number(ApfloatMath.pow(v.value(), e.evaluate(env).number().value())), 
            (v1, v2) -> new Number(ApfloatMath.pow(v1.value(), v2.value()))
        );
  },
  exprs -> Arrays.stream(exprs).map(Expression::toTeX).collect(Collectors.joining("^", "(", ")"))  
  );
  
  
  public static final Operator NEGATION = new Operator("-", Type.NUMBER, 1, false,
  (env, exprs) -> new Number(exprs[0].evaluate(env).number().value().negate()),
  exprs -> "-" + exprs[0].toTeX()
  );
  
  
  public static final Operator FACTORIAL = new Operator("!", Type.NUMBER, 1, false,
    (env, exprs) -> {
      Apfloat n = exprs[0].evaluate(env).number().value();
      
      Apfloat r = new Apfloat(1), c = new Apfloat(1);
      
      while (c.compareTo(n) <= 0)
      {
        r = r.multiply(c);
        c = c.add(new Apfloat(1));
      }
      
      return new Number(r); 
    },
    
    exprs -> exprs[0].toTeX() + "!"
  );

  
  public static class Combinatorial
  {
    public static final Operator BINOMIAL = new Operator("binomial", Type.NUMBER, 2, false,
      (env, exprs) -> {
        Expression n = exprs[0], k = exprs[1];
        
        // n! / (k! * (n-k)!)
        
        // (n 0) == (n n) == 1
        
        return Expression.div(
            Expression.fun("!", n),
            Expression.mul(
                Expression.fun("!", k),
                Expression.fun("!", Expression.sub(n, k))
            )   
        );
        
      },        
      functionCallRenderer("binomial")
    );
  }
  
  
  public static class Trigonometry
  {
    public static final Operator SIN = new Operator("sin", Type.NUMBER, 1, false, (env, exprs) -> new Number(Math.sin(exprs[0].evaluate(env).number().dvalue())), functionCallRenderer("sin"));
    
    public static final Operator ATAN2 = new Operator("atan2", Type.NUMBER, 2, false, (env, exprs) -> 
      new Number(Math.atan2(exprs[0].evaluate(env).number().dvalue(), exprs[1].evaluate(env).number().dvalue())),
      functionCallRenderer("atan2")
    );
  }
  
  public static class Meta
  {
    public static final Operator SUBSTITUTE = new Operator("substitute", Type.NUMBER, 3, false, (env, exprs) -> {
        return exprs[0].transformToNew(new SubstituteSubtree(exprs[1], exprs[2]));
      },
      functionCallRenderer("substitute")
    );
    
    public static final Operator FREE_OF = new Operator("freeOf", Type.BOOLEAN, 2, false, (env, exprs) -> {
        FindVisitor visitor = new FindVisitor(exprs[1]);
        exprs[0].accept(visitor);
        return new BooleanValue(!visitor.found());      
      },
      functionCallRenderer("freeOf")
    );
  }
  
  private static final Map<String, Operator> operators =
    Map.ofEntries(
      Map.entry("+", ADDITION),
      Map.entry("-", SUBTRACTION),
      Map.entry("*", MULTIPLICATION),
      Map.entry("/", DIVISION),
      Map.entry("^", EXPONENTIATION),
      
      Map.entry("!", FACTORIAL),
      Map.entry("binomial", Combinatorial.BINOMIAL),
      
      Map.entry("sin", Trigonometry.SIN),
      Map.entry("atan2", Trigonometry.ATAN2),
      
      Map.entry("substitute", Meta.SUBSTITUTE),
      Map.entry("freeOf", Meta.FREE_OF)
    );
  
  public static Operator of(String mnemonic)
  { 
    Operator op = operators.get(mnemonic);
    
    if (op == null)
      throw new IllegalArgumentException("Unknown operator: "+mnemonic);

    return op;
  }
}
