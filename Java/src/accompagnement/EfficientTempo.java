package accompagnement;

import java.util.Arrays;

import outils.Utilities;

public class EfficientTempo {
	
	public static final double tempo(double[] signal, double fe){ 
		
		int ms300 = (int) fe/300 ; 
		int ms1000 = (int) fe/1 ; 
		
		double[] xcorr = Utilities.xcorr(signal) ; 
		
		double argMax = Utilities.argMax(Arrays.copyOfRange(xcorr, ms300, ms1000)) ; 
		return (fe/(ms300 + argMax - 1))*60 ; 
		
	}

}
