package Body.transcription;

import android.util.Log;

import Body.outils.* ;
import java.util.*;

public class TransientDetector {
	
	public static final double[] envelope(double[] input){

		Log.e("progfiling","début détection d'enveloppe") ;
		// set the coeffs for an envelope detector (low pass filtering)
		double[] a = new double[2] ; 
		double[] b = new double[1] ; 
		b[0] = 1 ;
		a[0] = 1 ; 
		a[1] = -0.99 ;
		Log.e("progfiling","fin détection d'enveloppe") ;

		// return the envelope
		return Utilities.filter(b,a,energy(input)) ; 

	}	

	public static final double[] energy(double[] input){
		
		// returning the squared input
		double[] output = new double[input.length] ; 
		for (int i = 0 ; i < input.length ; i++) output[i] = input[i]*input[i] ; 
		return output ;
		}

	public static final double[] transients(double[] input, int N){
		
		// get the transients vectors, keep the positive part and normalize it 
		
		double[] output = largeDeriv(envelope(input), N) ; 
		Utilities.positivePart(output) ; 
		Utilities.normalize(output) ; 
		return output ; 
		
	}

	public static final double[] largeDeriv(double[] input, int N){

		Log.e("progfiling","Début large dériv") ;
		// set the right coeffs for a large derivation (deriv on N points)
		double[] a = new double[1] ; 
		double[] b  = new double[2*N] ; 
		for(int i = 0  ; i < N ; i++) b[i] = 1 ; 
		for (int i = N ; i < 2*N ; i++) b[i] = -1 ; 		
		a[0] = 1 ;

		Log.e("progfiling","Fin large dériv") ;

		// return the large derivation 
		return Utilities.filter(b,a,input) ; 
		
	}
				
	public static final ArrayList<Integer> locations(double[] input, int N, int p){

		N = N/4 ;
		p =  4*p/5 ;
		double[] downSampled = new double[input.length/4] ;
		for (int i = 0 ; i%4 == 0 && i < input.length ; i+=4) downSampled[i/4] = input[i] ;
		ArrayList<Integer> peaks = findpeaks(TransientDetector.transients(input,N),p) ;
		for (Integer i : peaks) i = i*4 ;
		return peaks ;

	}
	
	public static final ArrayList<Integer> findpeaks(double[] input, int p){

		Log.e("progfiling","Début findpeaks") ;

		double supremeMax  = Utilities.max(input) ; 
		
		ArrayList<Integer> rawMax = new ArrayList<Integer>() ; 
		ArrayList<Integer> filteredMax = new ArrayList<Integer>() ; 
		
		double[] dInput = Utilities.deriv(input) ; 
		double[] d2Input = Utilities.deriv(dInput) ; 
		
		for (int i = 1 ; i < dInput.length  ; i++) if (dInput[i-1]*dInput[i] < 0 && d2Input[i] < 0) rawMax.add(i) ; 
		
		int L = input.length - 1 ; 
		
		for (int m : rawMax){
		
			int leftIndex = 0 ;
			int rightIndex = 0 ; 
			
			if (m >= p && m <= L-p){
				leftIndex = m-p ;
				rightIndex = m+p ;
			}
			
			else if (m < p && m <= L-p){
				leftIndex = 0 ; 
				rightIndex = 2*p ;
			}

			else if(m >= p && m > L-p){
				leftIndex = L - 2*p ; 
				rightIndex = L ; 
			
			}
			
			double localMax = Double.MIN_VALUE ;
			
			for (int i = leftIndex ; i < m ; i++){
				if (input[i] > localMax) localMax = input[i] ; 
			}
			for (int i = m ; i <= rightIndex ; i++){
				if (input[i] > localMax) localMax = input[i] ; 
			}
						
			if(input[m] == localMax && input[m] > 0.05*supremeMax) filteredMax.add(m) ; 
			
		}
		Log.e("progfiling","Fin findpeaks") ;

		return filteredMax ; 
	
	}
	
}


			
	
        
    