package divers;

import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;

import outils.Utilities;

public class NoteDetectorAutoCor {
	
	public static double findFreq(double[] signal){ //finds frequency by auto-corelation method
	
		ArrayList <Double> frequencies = new ArrayList<Double>() ;
		Complex[] xcorr = Utilities.xcorr(signal) ;
		
		double j = 82.5;
		double q = 0;
		double z = 0;
		
		while (j<2000){
			z = (double) xcorr[(int) Math.floor(44100/j)].getReal();
			frequencies.add(z);
			j *= Math.pow(2,(float)1/12);
		}
		q = Utilities.argMax(frequencies) ; 
		return (double) Math.pow((82.5*2), ((q)/12));
		
	}

}
