package com.github.jakz.jplot.ast;

public interface Transformer
{
  Expression apply(Expression parent, Expression... children);
}
