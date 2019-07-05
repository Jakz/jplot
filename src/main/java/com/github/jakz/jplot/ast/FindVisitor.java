package com.github.jakz.jplot.ast;

public class FindVisitor extends Visitor
{
  private boolean found;
  private final Expression target;
  
  public FindVisitor(Expression target)
  {
    this.target = target;
    found = false;
  }
  
  @Override
  public void visit(Expression e)
  {
    found |= e.equals(target);
  }
  
  public boolean found()
  {
    return found;
  }
}
