package com.github.jakz.jplot.ast;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.github.jakz.jplot.cas.Environment;

public class Operator
{
  public final BiFunction<Environment, Expression[], Expression> function;
  public final int args;
  public final String name;
  
  public Operator(String name, int args, BiFunction<Environment, Expression[], Expression> function)
  {
    this.function = function;
    this.args = args;
    this.name = name;
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
}