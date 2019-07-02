package com.github.jakz.jplot.ast;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.github.jakz.jplot.cas.Environment;

public class Operator
{
  public final BiFunction<Environment, Expression[], Expression> function;
  public final Function<Expression[], String> renderer;
  public final int args;
  public final boolean commutative;
  public final String name;
  
  public Operator(String name, int args, boolean commutative, BiFunction<Environment, Expression[], Expression> function, Function<Expression[], String> renderer)
  {
    this.function = function;
    this.args = args;
    this.commutative = commutative;
    this.name = name;
    this.renderer = renderer;
  }
  
  public Operator(String name, int args, boolean commutative, BiFunction<Environment, Expression[], Expression> function)
  {
    this(name, args, commutative, function, o -> "");
  }
  
  @Override
  public boolean equals(Object o)
  {
    return (o instanceof Operator) && ((Operator)o).name.equals(name);
  }
  
  @Override
  public int hashCode()
  {
    return name.hashCode();
  }
  
  Expression apply(Environment env, Expression... args)
  {
    return function.apply(env, args);
  }

  public String toTeX(Expression... operands)
  {
    return renderer.apply(operands);
  }
  
  public String toTextual(Expression... operands)
  {
    //TODO
    return renderer.apply(operands);
  }
}
