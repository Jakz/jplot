package com.github.jakz.jplot.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class ExpressionPanel extends JPanel
{
  private String latex;
  private TeXFormula formula;
  private TeXIcon icon;
  
  public ExpressionPanel()
  {
    
  }
  
  public void setFormula(String latex)
  {
    this.latex = latex;
    this.formula = new TeXFormula(latex);
    this.icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
    
    this.removeAll();
    this.add(new JLabel(icon));
  }
}
