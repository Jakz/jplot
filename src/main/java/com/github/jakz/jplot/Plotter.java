package com.github.jakz.jplot;

import java.util.ArrayList;
import java.util.List;

// https://github.com/victorliu/S4/blob/master/modules/function_sampler_1d.c

public class Plotter
{
  private static class Sample
  {
    double x, y;
    double badness;
    int id;
  }
  
  private static class SampleList
  {
    List<Sample> samples;
    int nbad;
    
    List<Double> tmp;
    
    double x0, x1, y0, y1;
    
    SampleList()
    {
      samples = new ArrayList<>();
      tmp = new ArrayList<>();
      
      clear();
    }
    
    void clear()
    {
      x0 = y0 = Double.MAX_VALUE;
      x1 = y1 = -Double.MAX_VALUE;
      
      nbad = 0;
    }
    
    boolean isDone() { return nbad == 0; }
    int numSamples() { return samples.size(); }

  }
  
  private static class Options
  {
    double minDyAbs;
    double minDyRel;
    
    double maxCurvature;
    double minDx;
    
    int rangeBias;
    
    public Options()
    {
      minDyAbs = 0;
      minDyRel = 1e-3;
      
      maxCurvature = 0.1736;
      
      minDx = 1e-6;
      rangeBias = 0;
    }
  }
}
