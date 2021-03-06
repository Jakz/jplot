package com.github.jakz.jplot.ast;

public class PrintVisitor extends Visitor
{
  int indent = 0;
  
  @Override public void enter(Expression node)
  {
    indent += 2;
  }
  
  @Override public void leave(Expression node)
  {
    indent -= 2;
  }
  
  @Override public void visit(Number value)
  {
    System.out.printf("%" + indent + "s%s\n", "", value.value().toString());
  }
  
  @Override public void visit(Operation operation)
  {
    System.out.printf("%" + indent + "s%s\n", "", operation.operator().name);
  }
  
  @Override public void visit(Variable variable)
  {
    System.out.printf("%" + indent + "s%s\n", "", variable.name());
  }
}
