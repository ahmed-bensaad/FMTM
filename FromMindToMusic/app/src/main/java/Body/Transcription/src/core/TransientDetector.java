package Body.Transcription.src.core;

import java.util.*;
import biz.source_code.dsp.filter.*;


public class TransientDetector {
	
	public static final double[] envelope(double[] input){

		// set the coeffs for an envelope detector (low pass filtering)
		double[] a = new double[2] ; 
		double[] b = new double[1] ; 
		b[0] = 1 ;
		a[0] = 1 ; 
		a[1] = -0.99 ; 
		
		// return the envelope
		return filter(b,a,energy(input)) ; 

	}
	
	public static final double[] filter(double[] b, double[] a, double[] input){
		
		// building of the filter with the right coefficients 
		IirFilterCoefficients coeff = new IirFilterCoefficients() ; 
		coeff.a = a ; 
		coeff.b = b ; 
		IirFilter filter = new IirFilter(coeff) ;
		
		// init of the output ArrayList 
		double[] output = new double[input.length] ; 
		
		// filtering 
		for (int i = 0 ; i < input.length; i++) output[i] = filter.step(input[i]) ; 
		
		return output ; 
		
	}

	public static final double[] energy(double[] input){
		
		// returning the squared input
		double[] output = new double[input.length] ; 
		for (int i = 0 ; i < input.length ; i++) output[i] = input[i]*input[i] ; 
		return output ;
		}

	public static final double[] transients(double[] input, int N){
		
		// get the transients vectors, keep the positive part and normalize it 
		
		double[] transients = largeDeriv(envelope(input), N) ; 
		Utilities.positivePart(transients) ; 
		Utilities.normalize(transients) ; 
		return transients ; 
		
	}

	public static final double[] largeDeriv(double[] input, int N){
		
		// set the right coeffs for a large derivation (deriv on N points)
		double[] a = new double[1] ; 
		double[] b  = new double[2*N] ; 
		for(int i = 0  ; i < N ; i++) b[i] = 1 ; 
		for (int i = N ; i < 2*N ; i++) b[i] = -1 ; 		
		a[0] = 1 ; 
		
		// return the large derivation 
		return filter(b,a,input) ; 
		
	}
		
//	public static final ArrayList<MaxObject> findpeaks (double[] input){
//
//		Utilities.positivePart(input);
//		// Calculate derivative and second derivative of the input vector
//		double[] dInput = Utilities.deriv(input) ;
//		double[] d2Input = Utilities.deriv(dInput) ;
//		ArrayList <MaxObject> output = new ArrayList<MaxObject>() ;
//
//		// find the max indices and heights
//		for (int i = 0 ; i < input.length ; i++){
//			if ((dInput[i] < 0.015 && dInput[i] > - 0.015) && d2Input[i] < 0){
//				MaxObject max = new MaxObject(i,input[i],null) ;
//				output.add(max) ;
//			}
//		}
//
//		// calculate the prominences
//		for (MaxObject m : output){
//
//			double maxHeight = m.getHeight() ;
//			int maxIndex = m.getIndex();
//
//			int rightIndex  = input.length ;
//			int leftIndex = 0 ;
//
//			int j = maxIndex + 1 ;
//			while (rightIndex == input.length && j < input.length){
//				if (input[j] == maxHeight) rightIndex = j ;
//				j++ ;
//			}
//
//			j = maxIndex - 1 ;
//			while(leftIndex == 0 && j > 0){
//				if (input[j] == maxHeight) leftIndex = j ;
//				j-- ;
//			}
//
//
//
//
//			double[] rightSide = Arrays.copyOfRange(input,maxIndex, rightIndex) ;
//			double[] leftSide = Arrays.copyOfRange(input,leftIndex,maxIndex) ;
//			double leftMin = Collections.min(Utilities.toList(leftSide)) ;
//			double rightMin = Collections.min(Utilities.toList(rightSide)) ;
//			double referenceLevel = Double.max(leftMin, rightMin) ;
//			m.setProminence(maxHeight - referenceLevel) ;
//
//
//		}
//
//		return output ;
//
//	}
			
	public static final ArrayList<Integer> transientLocations(double[] input, int N, double fe){ 
		
		double [] transients = TransientDetector.transients(input, N) ; 
		ArrayList <Integer> transientLocList = new ArrayList <Integer>() ; 
		int i = 0  ; 
		while (i < transients.length){ 
			if (transients[i] > 0.1){ 
				transientLocList.add(i) ; 
				while (i < transients.length && transients[i] > 0) i = i+1 ;
			}
			i = i+1 ;
		}
		return transientLocList ; 
	}
		
		
}


			
	
        
    