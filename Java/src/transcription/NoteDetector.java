package transcription;
import outils.* ; 


public class NoteDetector {
	


	public static final double note(double[] input, double fe){ 
		
		double[] fft = Utilities.fft(input) ; 
		int maxIndex = Utilities.argMax(fft) ; 
		return (maxIndex*fe)/fft.length ; 
		
	}

	
}
