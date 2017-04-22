package divers;
import outils.* ;


public class NoteDetector {
	


	public static final double note(double[] input, double fe){ 
		
		double[] fft = Utilities.absFft(input) ; 
		int maxIndex = Utilities.argMax(fft) ; 
		return (maxIndex*fe)/fft.length ; 
		
	}

	
}