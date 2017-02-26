package core;

import java.util.*;
import biz.source_code.dsp.filter.*;

public class TransientDetector {
	

	public static final double[] envelope(double input[]){

		// setting of the coeff for an envelope detector (low pass filtering)
		double[] a = new double[2] ; 
		double[] b = new double[1] ; 
		b[0] = 1 ;
		a[0] = 1 ; 
		a[1] = -0.99 ; 
		
		// getting the envelope
		ArrayList <Double> envelopeList = filter(b,a,energy(input)) ; 
		
		// converting from ArrayList to primtive type double[]
		double[] envelope = new double[input.length] ; 
		for (int i = 0 ; i < envelope.length ;  i ++){
			envelope[i] = envelopeList.get(i) ; 
		}
		return envelope ; 
	}
	
	public static final ArrayList <Double> filter(double[] b, double[] a, double[] input){
		
		// building of the filter with the right coefficients 
		IirFilterCoefficients coeff = new IirFilterCoefficients() ; 
		coeff.a = a ; 
		coeff.b = b ; 
		IirFilter filter = new IirFilter(coeff) ;
		
		// init of the output ArrayList 
		ArrayList <Double> output = new ArrayList <Double>(input.length) ; 
		
		// filtering 
		for (int i = 0 ; i < input.length ; i++) output.add(filter.step(input[i])) ;
		
		return output ; 
		
	}

	public static final double[] energy(double[] input){
		
		// returning the squared input
		double[] output = new double[input.length] ; 
		for (int i = 0 ; i < output.length ; i++){
			output[i] = input[i]*input[i] ;
		}
		return output ;
			
		}

	public static final double[] transients(double[] input, int N){
		
		// setting the right coeff for a transient detection (deriv on N points)
		double[] a = new double[1] ; 
		double[] b  = new double[2*N] ; 
		for(int i = 0  ; i < N ; i++) b[i] = 1 ; 
		for (int i = N ; i < 2*N ; i++) b[i] = -1 ; 		
		a[0] = 1 ; 
		
		// getting the transients 
		ArrayList <Double> transientsList = filter(b,a,envelope(input)) ; 
		
		// keeping the positive values
		for (int i = 0 ; i < input.length ; i++) transientsList.set(i, Double.max(transientsList.get(i), 0)) ;
		
		// converting from ArrayList to double[] and normalizing 
		double[] transients = new double[input.length] ;
		Double max = Collections.max(transientsList) ; 
		for (int i = 0 ; i < transientsList.size(); i++){ 
			transientsList.set(i, transientsList.get(i)/max) ; 
			transients[i] = transientsList.get(i) ; 
		}
		return transients ; 
		
	}

	public static final ArrayList<Integer> transientLocations(double[] input, int N, double fe){ 
		
		double [] transients = TransientDetector.transients(input, N) ; 
		ArrayList <Integer> transientLocList = new ArrayList <Integer>() ; 
		int i = 0  ; 
		while (i < transients.length){ 
			if (transients[i] > 0.1){ 
				transientLocList.add(i) ; 
				while (transients[i] > 0) i = i+1 ; 
			}
			i = i+1 ;
		}
		return transientLocList ; 
	}

}
        
    