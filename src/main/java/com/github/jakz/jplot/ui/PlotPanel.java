package com.github.jakz.jplot.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

import javax.swing.JPanel;

public class PlotPanel extends JPanel
{
  private final Color BACKGROUND_COLOR = Color.WHITE;
  
  DoubleUnaryOperator horizontal, vertical;
  DoubleUnaryOperator function;
  
  private float backingBufferScale = 2;
  private int pixelSize = (int)backingBufferScale;
  private BufferedImage image;
  
  
  public PlotPanel()
  {
    final int w = 512, h = 512;
    
    setPreferredSize(new Dimension(w, h));
    
    horizontal = f -> f * 20.0 - 10.0;
    vertical = f -> (f + 10.0) / 20.0;
    
    function = x -> (6*x*x - 3*x + 4) / (2*x*x - 8);
    
    image = new BufferedImage(w*4, h*4, BufferedImage.TYPE_INT_ARGB);
    
    addComponentListener(new ComponentAdapter() {
      @Override public void componentResized(ComponentEvent e) { onResize(); }
    });
  }
  
  //TODO: add timer
  private void onResize()
  {
    final int w = getWidth(), h = getHeight();
    image = new BufferedImage((int)(backingBufferScale * w), (int)(backingBufferScale * h), BufferedImage.TYPE_INT_ARGB);
  }
  
  public void drawOnCanvas(Graphics2D g, int w, int h)
  {    
    g.setColor(BACKGROUND_COLOR);
    g.fillRect(0, 0, w, h);
    
    g.setColor(Color.BLACK);
    g.drawLine(w/2, 0, w/2, h);
    g.drawLine(0, h/2, w, h/2);
    
    g.setColor(Color.RED);
    
    final double incr = 1.0 / (w*5);
    for (double x = 0; x <= 1.0; x += incr)
    {
      double y = function.applyAsDouble(horizontal.applyAsDouble(x));
      double sc = vertical.applyAsDouble(y);
            
      if (sc >= 0.0 && sc <= 1.0)
      {
        int sy = (int)((1.0 - sc) * h);
        int sx = (int)(x * w);
        
        g.drawRect(sx, sy, pixelSize, pixelSize);
      }
      
    }
  }
  
  
  @Override
  public void paintComponent(Graphics g1)
  {
    Graphics2D g = (Graphics2D)g1;
    
    final int w = getWidth(), h = getHeight();
    
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    
    //drawOnCanvas(g, w, h);
    
    drawOnCanvas((Graphics2D)image.getGraphics(), image.getWidth(), image.getHeight());

    g.drawImage(image, 0, 0, w, h, 0, 0, image.getWidth(), image.getHeight(), null);
  }
}
