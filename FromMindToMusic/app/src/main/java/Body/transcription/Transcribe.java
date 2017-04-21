package Body.transcription;

import Body.audio.* ;
import Body.tonalité.Play;

import java.util.ArrayList;

import Body.accompagnement.* ;

public class Transcribe {

	public static final Transcript transcribe(String fileName){
				
		Audio audio = new Audio(fileName) ;
		double[] signal = audio.getSignal() ; 
		double fe = audio.getFe(); 
		
		int p = (int)(0.3*fe) ;
		int n = (int)(0.008*fe) ;
		
		Transcript transcript =  Notes.notes(signal, fe, n, p) ;
		
		return transcript ; 

	}
		
//,2.959002267573696,2.959002267573696} ;
// ,123.52941176470588,119.51219512195122} ; 
}
	
