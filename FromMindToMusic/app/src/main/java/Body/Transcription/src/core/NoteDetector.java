package Body.Transcription.src.core;

import java.util.*;
import org.apache.commons.math3.complex.*;
import org.apache.commons.math3.transform.*;

public class NoteDetector {
	


	public static final double note(double[] input, double fe){ 
		
		double[] fft = Utilities.fft(input) ;
		ArrayList <Double> fftList = new ArrayList<Double>(fft.length) ; 
		for (int i = 0 ; i < fft.length ; i++){ 
			fftList.add(fft[i]) ; 
		}
		int maxIndex = fftList.indexOf(Collections.max(fftList)) ; 
		return (maxIndex*fe)/fft.length ; 
		
	}

	
}
