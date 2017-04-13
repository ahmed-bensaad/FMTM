package transcription;

import audio.* ;
import accompagnement.* ;

public class Transcribe {

	public static final double[][] transcribe(String fileName){
				
		Audio audio = new Audio(fileName) ;
		double[] signal = audio.getSignal() ; 
		double fe = audio.getFe(); 
		
		int p = (int)(0.3*fe) ;
		int n = (int)(0.008*fe) ;
		
		return Notes.notes(signal, fe, n, p) ;

		

		
	}

}
	
