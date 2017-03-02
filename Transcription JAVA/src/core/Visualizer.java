package core;
import javax.swing.*;
import org.math.plot.*;

public class Visualizer {
	
	public final static void show(double[] signal, double fe){
		
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

}
