package com.github.jakz.jplot;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.PrintVisitor;
import com.github.jakz.jplot.ast.Root;
import com.github.jakz.jplot.ast.Value;
import com.github.jakz.jplot.ast.simplification.CommutativeOperatorLeveler;
import com.github.jakz.jplot.ast.simplification.NegationToMultiplication;
import com.github.jakz.jplot.ui.ExpressionPanel;
import com.pixbits.lib.ui.UIUtils;

public class App
{
  public static void main(String[] args)
  {
    Expression e = new Root(Expression.multiplication(Expression.negation(Expression.integral(10)), Expression.integral(20)));
    Value v = e.evaluate(null);
    
    e.transform(new NegationToMultiplication());
    e.accept(new PrintVisitor());
    
    System.out.println(v.value());

    //var frame = UIUtils.buildFrame(new PlotPanel(), "Plot Panel");
    var frame = UIUtils.buildFrame(new ExpressionPanel(), "Panel");
    frame.panel().setFormula(e.toTeX());
    frame.pack();
    frame.centerOnScreen();
    frame.exitOnClose();
    frame.setVisible(true);
  }
}