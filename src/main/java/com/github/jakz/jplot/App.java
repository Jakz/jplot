package com.github.jakz.jplot;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.PrintVisitor;
import com.github.jakz.jplot.ast.Root;
import com.github.jakz.jplot.ast.Value;
import com.github.jakz.jplot.ast.simplification.CommutativeOperatorLeveler;
import com.github.jakz.jplot.ui.PlotPanel;
import com.pixbits.lib.ui.UIUtils;

public class App
{
  public static void main(String[] args)
  {
    Expression e = new Root(Expression.sum(Expression.integral(10), Expression.integral(20), Expression.integral(50), Expression.sum(Expression.integral(5), Expression.integral(15))));
    Value v = e.evaluate(null);
    
    e.transform(new CommutativeOperatorLeveler());
    e.accept(new PrintVisitor());
    
    System.out.println(v.value());
    
    
    /*var frame = UIUtils.buildFrame(new PlotPanel(), "Plot Panel");
    frame.centerOnScreen();
    frame.exitOnClose();
    frame.setVisible(true);*/
  }
}