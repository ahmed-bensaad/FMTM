package Body.accompagnement;

import java.util.ArrayList;
import java.util.Arrays;

import Body.outils.Utilities;
import Body.transcription.TransientDetector;

public class Tempo {
	
	public static final double tempo(int length, double fe, ArrayList<Integer> locations){
		
		double[] transients = new double[length] ;
		for (int loc : locations) transients[loc] = 1 ; 
		
		int ms300 = (int) fe/300 ; 
		int ms1000 = (int) fe/1 ; 
		
		double[] xcorr = Utilities.xcorr(transients) ; 
		
		double argMax = Utilities.argMax(Arrays.copyOfRange(xcorr, ms300, ms1000)) ; 
		return (fe/(ms300 + argMax - 1))*60 ; 
		
	}

}