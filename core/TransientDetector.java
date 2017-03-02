package core;

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
		
//	public static final int[] transientsLocations (ArrayList<Double> input, int N, double fe, double prominence){
//		
//			
////		ArrayList <Double> transients = TransientDetector.transients(input, N) ; 
////			
////		ArrayList <Integer> maxLocList = new ArrayList <Integer>() ; 
////		ArrayList<Double> prominencesList = new ArrayList <Double>() ; 
////		double[] dTransients = TransientDetector.deriv(input); 
////		double[] d2Transients = TransientDetector.deriv(dTransients) ; 
////		for (int i = 0 ; i < input.length ; i++){
////			if (dTransients[i] == 0 && d2Transients[i] < 0) maxLocList.add(i) ; 
////			}
////		for (int i = 0 ; i < maxLocList.size()  ; i++){
////			int maxIndex = maxLocList.get(i) ; 
////			double maxValue = input[maxIndex] ; 
////			int leftBorderIndex ; 
////			double leftBorderValue ; 
////			int rightBorderIndex ; 
////			double rightBorderValue ; 
////			for (int j = maxIndex ; i < input.length ; i++){
////				if (input[j] == maxValue){
////					rightBorderIndex = j ; 
////					rightBorderValue = input[j] ; 
////					}
////				else {
////					rightBorderIndex = input.length - 1 ; 
////					rightBorderValue = maxValue ; 
////					}		
////				}
////				for (int j = maxIndex ; i >= 0 ; i--){
////					if (input[j])
////				}
////				
////			
////				
////			}
////			
////			
////		}
//		
	public static final ArrayList<MaxObject> findpeaks (double[] input){
		
		Utilities.positivePart(input);
		// Calculate derivative and second derivative of the input vector
		double[] dInput = Utilities.deriv(input) ;
		double[] d2Input = Utilities.deriv(dInput) ; 
		ArrayList <MaxObject> output = new ArrayList<MaxObject>() ; 
		
		// find the max indices and heights
		for (int i = 0 ; i < input.length ; i++){ 
			if ((dInput[i] < 0.015 && dInput[i] > - 0.015) && d2Input[i] < 0){
				MaxObject max = new MaxObject(i,input[i],null) ; 
				output.add(max) ; 
			}
		}
			
		// calculate the prominences
		for (MaxObject m : output){
			
			double maxHeight = m.getHeight() ;  
			int maxIndex = m.getIndex(); 
			
			int rightIndex  = input.length ; 
			int leftIndex = 0 ; 
			
			int j = maxIndex + 1 ; 
			while (rightIndex == input.length && j < input.length){
				if (input[j] == maxHeight) rightIndex = j ; 
				j++ ; 
			}
			
			j = maxIndex - 1 ; 
			while(leftIndex == 0 && j > 0){
				if (input[j] == maxHeight) leftIndex = j ; 
				j-- ; 
			}
			
			
			
			
			double[] rightSide = Arrays.copyOfRange(input,maxIndex, rightIndex) ; 
			double[] leftSide = Arrays.copyOfRange(input,leftIndex,maxIndex) ; 
			double leftMin = Collections.min(Utilities.toList(leftSide)) ; 
			double rightMin = Collections.min(Utilities.toList(rightSide)) ; 
			double referenceLevel = Double.max(leftMin, rightMin) ; 
			m.setProminence(maxHeight - referenceLevel) ; 
				
					
		}
		
		return output ; 
		
	}
			
		
		
}


			
	
        
    