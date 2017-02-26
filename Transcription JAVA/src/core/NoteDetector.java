package core;

import java.util.*;
import org.apache.commons.math3.complex.*;
import org.apache.commons.math3.transform.*;

public class NoteDetector {
	
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

	public static final double note(double[] input, double fe){ 
		
		double[] fft = NoteDetector.fft(input) ; 
		ArrayList <Double> fftList = new ArrayList<Double>(fft.length) ; 
		for (int i = 0 ; i < fft.length ; i++){ 
			fftList.add(fft[i]) ; 
		}
		int maxIndex = fftList.indexOf(Collections.max(fftList)) ; 
		return (maxIndex*fe)/fft.length ; 
		
	}

	
}
