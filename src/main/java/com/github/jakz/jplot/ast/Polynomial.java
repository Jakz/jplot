package com.github.jakz.jplot.ast;

import java.util.ArrayList;
import java.util.List;

import com.github.jakz.jplot.cas.Environment;

public class Polynomial extends Expression
{
  public static class Term
  {
    Variable variable;
    Number exponent; //TODO: should become expression
    Expression coefficient;
    
    public Term(Variable variable, Number exponent, Expression coefficient)
    {
      this.variable = variable;
      this.exponent = exponent;
      this.coefficient = coefficient;
    }
  }
  
  private final List<Term> terms;
  
  public Polynomial()
  {
    terms = new ArrayList<>();
  }
  
  public Expression dupe()
  {
    //TODO
    return null;
  }
  
  @Override
  public Type type()
  {
    return Type.NUMBER;
  }
  
  public List<Term> terms()
  {
    return terms;
  }
  
  public void addTerm(Variable variable, Number exponent, Expression coefficient)
  {
    terms.add(new Term(variable, exponent, coefficient));
  }
  
  @Override
  public Number evaluate(Environment env) {
    // TODO Auto-generated method stub
    return new Number(0);
  }

  @Override
  public void accept(Visitor visitor)
  {
    visitor.enter(this);
    visitor.doVisit(this);
    
    for (Term term : terms)
    {
      term.variable.accept(visitor);
      term.exponent.accept(visitor);
      term.coefficient.accept(visitor);
    }
    
    visitor.leave(this);
    
  }

  @Override
  public Expression transform(Transformer transformer)
  {
    //TODO
    return this;
  }
  
  @Override public String toTextual()
  {
    //TODO
    return "";
  }

  @Override
  public String toTeX() {
    StringBuilder o = new StringBuilder();
    
    for (Term term : terms)
    {
      o.append(term.coefficient.toTeX());
      o.append(term.variable.toTeX());
      o.append("^").append(term.exponent.toTeX());
    }
    
    return o.toString();
  }

}
