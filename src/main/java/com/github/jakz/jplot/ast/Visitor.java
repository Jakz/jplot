package com.github.jakz.jplot.ast;

public class Visitor
{  
  public void enter(Expression expression) { }
  public void leave(Expression expression) { }
  
  public void visit(Expression expression) { }
  public void visit(Operation operator) { }
  public void visit(Value value) { }
  public void visit(Variable variable) { }
  
  public void doVisit(Polynomial polynomial)
  {
    visit((Expression)polynomial);
    visit(polynomial);
  }
  
  
  public void doVisit(Operation operation)
  { 
    visit((Expression)operation);
    visit(operation);
  }
  
  public void doVisit(Value value)
  {
    visit((Expression)value);
    visit(value);
  }
  
  public void doVisit(Variable value)
  {
    visit((Expression)value);
    visit(value);
  }
}
