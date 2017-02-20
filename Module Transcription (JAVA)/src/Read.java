import java.io.*;
import javax.swing.JFrame;
import org.math.plot.*;
import wavfile.*;

public class Read {
	
	static double[] frames = null ; 
	static double[] indices = null ; 
	
	public static void main(String[] args){
		
		try {
			
			// Open the wav file specified as the first argument
			WavFile wavFile = WavFile.openWavFile(new File(args[0]));
			wavFile.display();
			int numFrames = (int) wavFile.getNumFrames() ; 
			System.out.println(numFrames) ; 
			
			// Give the right size to the array of frames
			frames = new double[numFrames] ; 
			indices = new double[numFrames] ; 
			
			// Create an array of indices 
			for (int i = 0 ; i < numFrames ; i++){
				indices[i] = i ; 
			}
//			
			// Read frames
			wavFile.readFrames(frames, (int)wavFile.getNumFrames());
			
			// Close the wavFile
			wavFile.close();
			
		}
		
		catch (Exception e){
			
			System.err.println(e);
		}
	

//	  // create your PlotPanel (you can use it as a JPanel)
	  Plot2DPanel plot = new Plot2DPanel();
//
//	  // add a line plot to the PlotPanel
	  plot.addLinePlot("Signal", indices, frames) ;
//	  
//	  // put the PlotPanel in a JFrame, as a JPanel
	  JFrame frame = new JFrame("Signal");
	  frame.setContentPane(plot);
	  frame.setVisible(true) ;
	}

	
}

