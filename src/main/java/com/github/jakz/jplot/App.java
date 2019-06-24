package com.github.jakz.jplot;

import com.github.jakz.jplot.ui.PlotPanel;
import com.pixbits.lib.ui.UIUtils;

public class App
{
  public static void main(String[] args)
  {
    var frame = UIUtils.buildFrame(new PlotPanel(), "Plot Panel");
    frame.centerOnScreen();
    frame.exitOnClose();
    frame.setVisible(true);
  }
}