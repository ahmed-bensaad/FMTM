package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

public class Utilities {
	
	public static final double[] toPrimitive(ArrayList<Double> input){
		double[] output = new double[input.size()] ; 
		for (int i = 0 ; i < input.size() ; i++) output[i] = (double)input.get(i) ; 
		return output ; 
	}
	
	public static final ArrayList<Double> toList(double[] input){
		ArrayList <Double> output = new ArrayList <Double>(input.length) ; 
		for (double d : input) output.add((Double)d) ; 
		return output ; 
	}
	
	public static final double[] deriv(double[] input){
		
		double[] output = new double[input.length]; 
		for (int i = 1 ; i < input.length ; i++) output[i] = input[i] - input[i-1] ; 
		return output ; 
		
//		UnivariateFunction basicF = new Function(input) ;
//		FiniteDifferencesDifferentiator differentiator = new FiniteDifferencesDifferentiator(5,1) ; 
//		UnivariateDifferentiableFunction f = differentiator.differentiate(basicF) ; 
//		for (int i = 0 ; i < input.length ; i++){
//			DerivativeStructure x = new DerivativeStructure(1,2,0,i) ; 
//			DerivativeStructure y = f.value(x) ; 
//			output[i] = y.getPartialDerivative(2) ;		
//		}
//		return output ; 
	}
	
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

	    for (int i = 0; i < Math.floor(complexTransInput.length/2); i++) {
	        double real = (complexTransInput[i].getReal());
	        double img = (complexTransInput[i].getImaginary());

	        tempInput[i] = Math.sqrt((real * real) + (img * img));
	    }

	    return tempInput;
	}
		
	public static final void positivePart(double[] input){
		for(int i = 0 ; i < input.length ; i++) input[i] = Double.max(input[i], 0) ; 
	}
	
	public static final void normalize (double[] input){
		Double max = Collections.max(toList(input)) ; 
		for (int i = 0 ; i < input.length; i++) input[i] = input[i]/max ; 
	}
	
}
