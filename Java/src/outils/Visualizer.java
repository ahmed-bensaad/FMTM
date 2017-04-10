package outils;

import javax.swing.*;
import org.math.plot.*;

public class Visualizer {
	
	public final static void temporalShow(double[] signal, double fe){
		
		  double[] indices = new double[signal.length] ; 
		  
		  // Create an array of indices 
		  for (int i = 0 ; i < signal.length ; i++) indices[i] = i*(1/fe) ; 
			
		  // create your PlotPanel (you can use it as a JPanel)
		  Plot2DPanel plot = new Plot2DPanel();

		  // add a line plot to the PlotPanel
		  plot.addLinePlot("Signal", indices, signal) ;
		  
		  // put the PlotPanel in a JFrame, as a JPanel
		  JFrame frame = new JFrame("Signal");
		  frame.setContentPane(plot);
		  frame.setVisible(true);
		
	}
		
	public final static void spectralShow(double[] signal, double fe){
		
		  double[] indices = new double[signal.length] ; 
		  
		  // Create an array of indices 
		  for (int i = 0 ; i < signal.length ; i++) indices[i] = (i*fe)/signal.length ;  
			
		  // create your PlotPanel (you can use it as a JPanel)
		  Plot2DPanel plot = new Plot2DPanel();

		  // add a line plot to the PlotPanel
		  plot.addLinePlot("Signal", indices, signal) ;
		  
		  // put the PlotPanel in a JFrame, as a JPanel
		  JFrame frame = new JFrame("Signal");
		  frame.setContentPane(plot);
		  frame.setVisible(true);
		  
		  
		
	}
	
	public final static void superpoze(double[] signal, double[] transients, double fe){
		
	      double[] copy  = signal.clone() ; 
	      Utilities.normalize(copy);
		  double[] indices = new double[signal.length] ; 
		  
		  // Create an array of indices 
		  for (int i = 0 ; i < signal.length ; i++) indices[i] = i*(1/fe) ; 
			
		  // create your PlotPanel (you can use it as a JPanel)
		  Plot2DPanel plot = new Plot2DPanel();

		  // add a line plot to the PlotPanel
		  plot.addLinePlot("Signal", indices, copy) ;
		  plot.addLinePlot("Signal", indices, transients) ;
		  
		  // put the PlotPanel in a JFrame, as a JPanel
		  JFrame frame = new JFrame("Signal");
		  frame.setContentPane(plot);
		  frame.setVisible(true);
		
	}

}
