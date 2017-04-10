package Body.transcription;

import Body.audio.* ;
import Body.accompagnement.* ;

public class Transcribe {

	public static final double[][] transcribe(String fileName){
				
		Audio audio = new Audio(fileName) ;
		double[] signal = audio.getSignal() ; 
		double fe = audio.getFe(); 
		
		int p = (int)(0.3*fe) ;
		int n = (int)(0.02*fe) ;
		double[][] notes = Notes.notes(signal, fe, n, p) ;
        return notes;

		

		
	}

}
	
