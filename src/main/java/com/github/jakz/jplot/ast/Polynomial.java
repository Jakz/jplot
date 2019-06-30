package com.github.jakz.jplot.ast;

import java.util.ArrayList;
import java.util.List;

import com.github.jakz.jplot.cas.Environment;

public class Polynomial implements Expression
{
  private class Term
  {
    Variable variable;
    Value exponent; //TODO: should become expression
    Expression coefficient;
    
    public Term(Variable variable, Value exponent, Expression coefficient)
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
  
  public void addTerm(Variable variable, Value exponent, Expression coefficient)
  {
    terms.add(new Term(variable, exponent, coefficient));
  }
  
  @Override
  public Value evaluate(Environment env) {
    // TODO Auto-generated method stub
    return new Value(0);
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
