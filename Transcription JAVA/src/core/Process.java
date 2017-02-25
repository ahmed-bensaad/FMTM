package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import biz.source_code.dsp.filter.IirFilter;
import biz.source_code.dsp.filter.IirFilterCoefficients;

public class Process {
	
	public static final double[] fft(double input[]) {

	    //fft works on data length = some power of two
	    int fftLength;
	    int length = input.length;  //initialized with input's length
	    int power = 0;
	    while (true) {
	        int powOfTwo = (int) Math.pow(2, power);  //maximum no. of values to be applied fft on

	        if (powOfTwo == length) {
	            fftLength = powOfTwo;
	            break;
	        }
	        if (powOfTwo > length) {
	            fftLength = (int) Math.pow(2, (power - 1));
	            break;
	        }
	        power++;
	    }

	    double[] tempInput = Arrays.copyOf(input, fftLength);
	    FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
	    //apply fft on input
	    Complex[] complexTransInput = fft.transform(tempInput, TransformType.FORWARD);

	    for (int i = 0; i < complexTransInput.length; i++) {
	        double real = (complexTransInput[i].getReal());
	        double img = (complexTransInput[i].getImaginary());

	        tempInput[i] = Math.sqrt((real * real) + (img * img));
	    }

	    return tempInput;
	}

	public static final double[] envelope(double input[]){

		double[] a = new double[2] ; 
		double[] b = new double[1] ; 
		b[0] = 1 ;
		a[0] = 1 ; 
		a[1] = -0.99 ; 
		ArrayList <Double> envelopeList = filter(b,a,energy(input)) ; 
		double[] envelope = new double[input.length] ; 
		for (int i = 0 ; i < envelope.length ;  i ++){
			envelope[i] = envelopeList.get(i) ; 
		}
		return envelope ; 
	}
	
	public static final ArrayList <Double> filter(double[] b, double[] a, double[] input){
		
		IirFilterCoefficients coeff = new IirFilterCoefficients() ; 
		coeff.a = a ; 
		coeff.b = b ; 
		IirFilter filter = new IirFilter(coeff) ;
		
		ArrayList <Double> output = new ArrayList <Double>(input.length) ; 
		
		for (int i = 0 ; i < input.length ; i++) output.add(filter.step(input[i])) ;
		
		return output ; 
		
	}

	public static final double[] energy(double[] input){
		
		double[] output = new double[input.length] ; 
		for (int i = 0 ; i < output.length ; i++){
			output[i] = input[i]*input[i] ;
		}
		return output ;
			
		}

	public static final double[] transients(double[] input, int N){
		
		double[] a = new double[1] ; 
		double[] b  = new double[2*N] ; 
		double[] transients = new double[input.length] ;
		
		for(int i = 0  ; i < N ; i++) b[i] = 1 ; 
		for (int i = N ; i < 2*N ; i++) b[i] = -1 ; 		
		a[0] = 1 ; 
		
		ArrayList <Double> transientsList = filter(b,a,input) ; 
		
		for (int i = 0 ; i < transientsList.size() ; i++) transientsList.set(i, Double.max(transientsList.get(i), 0)) ;
		
		Double max = Collections.max(transientsList) ; 
		for (int i = 0 ; i < transientsList.size(); i++){ 
			transientsList.set(i, transientsList.get(i)/max) ; 
			transients[i] = transientsList.get(i) ; 
		}
		return transients ; 
		
			
			
		
			
	}

	
	
}
        
    