package com.github.jakz.jplot.ast;

import com.github.jakz.jplot.cas.Environment;

public class Subtree extends Expression
{
  public String identifier;
  
  public Subtree(String identifier)
  {
    this.identifier = identifier;
  }

  @Override
  public Type type()
  {
    // TODO Auto-generated method stub
    return Type.SUBTREE;
  }

  @Override
  public Expression evaluate(Environment env)
  {
    throw new CasException("SubTree expressions can't be evaluated");
  }

  @Override
  public void accept(Visitor visitor)
  {
    
  }

  @Override
  public Expression transform(Transformer transformer)
  {
    return this;
  }

  @Override
  public Expression dupe()
  {
    return new Subtree(identifier);
  }

  @Override
  public String toTeX()
  {
    return "SubTree(" + identifier + ")";
  }

  @Override
  public String toTextual()
  {
    return "SubTree(" + identifier + ")";
  }
  
  @Override
  public boolean equals(Object o)
  {
    return o instanceof Subtree && ((Subtree)o).identifier.equals(o);
  }
  
}
