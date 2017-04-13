package transcription;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;

import outils.Utilities;
import outils.Visualizer;

public class NoteDetector {
	
	public static final double note(double[] signal, double fe){ //finds frequency by auto-corelation method
	
		int ms2 = (int) fe/500 ; 
		int ms20 = (int) fe/50 ; 
		
		double[] xcorr = new double[ms20 - ms2] ; 
		for (int lag = ms2 ; lag < ms20 ; lag++){
			xcorr[lag-ms2] = 0; 
			for (int k = 0 ; k < signal.length - lag ; k++) xcorr[lag-ms2] += signal[k]*signal[k+lag] ;
		}
		double argMax = Utilities.argMax(xcorr) ; 
		return fe/(ms2 + argMax - 1) ; 
		
	}

}
