package accompagnement;

import java.util.ArrayList;
import java.util.Collections;

import outils.Utilities;

public class Tempo2 {
	
	public static final double tempo(double[] signal, double fe){
		
		ArrayList<Double> max = new ArrayList<Double>() ;
		for (int bpm = 60 ; bpm < 200 ; bpm++){
			int offset = (int) ((4*60*fe)/bpm) ; 
			double cov = 0 ; 
			for (int i = 0 ; i < signal.length - offset ; i++) cov+=signal[i]*signal[i+offset] ; 
			cov /= signal.length - offset ; 
			max.add(cov) ; 
			
		}
		return (4*60*fe)/Collections.max(max) ;
		
	}

}
