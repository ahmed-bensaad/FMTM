package Body.outils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

public class Utilities {
	
	public static final double[] deriv(double[] input){
		
		double[] output = new double[input.length-1]; 
		for (int i = 0 ; i < input.length -1  ; i++) output[i] = input[i+1] - input[i] ; 
		return output ; 

	}
	
	public static final double[] absFft(double input[]) {

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

	    return abs(complexTransInput,tempInput) ; 
	}
	
	public static final Complex[] fft(double input[]) {

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

	    return complexTransInput ;
	    
	}
	
	public static final Complex[] ifft(Complex input[]) {

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

	    Complex[] tempInput = Arrays.copyOf(input, fftLength);
	    FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
	    //apply fft on input
	    Complex[] complexTransInput = fft.transform(tempInput, TransformType.INVERSE);

	    return complexTransInput ; 
	}
	
	public static final double[] xcorr(double[] input){
		
		return getReal(ifft(multiply(fft(input),conjugate(fft(input))))) ; 
	}

	public static final double[] abs(Complex[] complexTransInput, double[] tempInput){
		
		for (int i = 0; i < Math.floor(complexTransInput.length/2); i++) {
	        double real = (complexTransInput[i].getReal());
	        double img = (complexTransInput[i].getImaginary());

	        tempInput[i] = Math.sqrt((real * real) + (img * img));
	    }

	    return tempInput;
	}
		
	public static final double[] abs(Complex[] input){
		double output[] = new double[input.length];
		for (int i = 0 ; i < input.length ; i++) output[i] = input[i].abs() ; 
		return output ; 
		
		
	}
	
	public static final void positivePart(double[] input){
		for(int i = 0 ; i < input.length ; i++) input[i] = max(input[i], 0) ; 
	}
	
	public static final double[] filter(double[] b, double[] a, double[] x){
		
		int N = x.length ; 
		int Na = a.length ; 
		int Nb = b.length ; 
		double[] y = new double[N] ; 
		
		
		for (int n = 0 ; n < N ; n++){
			
			for(int k = 0 ; n-k >=0 && k < Nb ; k++) y[n] += b[k]*x[n-k] ;
			for(int k = 1 ; n-k >=0 && k < Na ; k++) y[n] -= a[k]*y[n-k] ;
			y[n] = y[n]/a[0] ; 
			
		}
		
		return y ; 
	}
	
	public static final double max(double a, double b){
		
		if (a >= b) return a ; 
		else return b ; 
		
	}
	
	public static final void normalize(double[] input){
		
		double M = Double.MIN_VALUE ; 
		for (int i = 0 ; i < input.length ; i++){
			if (input[i] > M) M = input[i] ; 
		}
		for (int i = 0 ; i < input.length ; i++){
			input[i] = input[i]/M ; 
		}
		
	}
	
	public static final int argMax(double[] input){
		
		int arg = 0  ; 
		double M = Double.MIN_VALUE ; 
		for (int i = 0 ; i < input.length ; i++){
			if (input[i] > M){
				M = input[i] ; 
				arg = i ; 
			}
		}
		return arg ; 
		
	}
	
	public static final int argMin(double[] input){
		
		int arg = 0  ; 
		double M = Double.MAX_VALUE ; 
		for (int i = 0 ; i < input.length ; i++){
			if (input[i] < M){
				M = input[i] ; 
				arg = i ; 
			}
		}
		return arg ; 
		
	}
	
	public static final double max(double[] input){
		
		double M = Double.MIN_VALUE ; 
		for (int i = 0 ; i < input.length ; i++) if (input[i] > M) M = input[i] ; 
		return M ; 
		
	}
	
	public static final Complex[] multiply(Complex[] input1,Complex[] input2){
		
		Complex[] result = new Complex[input1.length] ; 
		for (int i = 0 ; i < result.length ; i++) result[i] = input1[i].multiply(input2[i]) ; 
		return result ; 
	}
	
	public static final Complex[] conjugate(Complex[] input){
		
		Complex[] result = new Complex[input.length] ; 
		for (int i = 0 ; i < result.length ; i++) result[i] = input[i].conjugate(); 
		return result ; 
		}
		
	public static final double[] getReal(Complex[] input){
	
		double[] result = new double[input.length] ; 
		for (int i = 0 ; i < result.length ; i++) result[i] = input[i].getReal() ; 
		return result ; 
	
	}
	
	public static final int argMax(ArrayList<Double> input){
		
		int arg = 0  ; 
		double M = Double.MIN_VALUE ; 
		for (int i = 0 ; i < input.size() ; i++){
			if (input.get(i) > M){
				M = input.get(i) ; 
				arg = i ; 
			}
		}
		return arg ; 
		
	}
	
	public static final double minDistance(double from, ArrayList<Double> to){
		
		double[] distances = new double[to.size()] ; 
		for (int i = 0 ; i < to.size() ; i++) distances[i] = abs(to.get(i)-from) ; 
		
		int argMin = argMin(distances) ; 
		return to.get(argMin) ; 
	
		
		
		
	}
	
	public static final double abs(double a){
		if (a<0) return -a ; 
		return a ; 
	}
	
}

