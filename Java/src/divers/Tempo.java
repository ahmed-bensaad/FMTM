package divers;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;

import outils.Utilities;
import outils.Visualizer;

public class Tempo {
	
	public static final double tempo(double[] signal, double fe){ //finds frequency by auto-corelation method
	
		int ms300 = (int) fe/300 ; 
		int ms1000 = (int) fe/1 ; 
		
		double[] xcorr = new double[ms1000 - ms300] ; 
		for (int lag = ms300 ; lag < ms1000 ; lag++){
			xcorr[lag-ms300] = 0; 
			for (int k = 0 ; k < signal.length - lag ; k++) xcorr[lag-ms300] += signal[k]*signal[k+lag] ;
		}
		
		double argMax = Utilities.argMax(xcorr) ; 
		return (fe/(ms300 + argMax - 1))*60 ; 
		
	}

}
