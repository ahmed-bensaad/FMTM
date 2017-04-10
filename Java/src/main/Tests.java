package main;

import transcription.*; 
import outils.* ; 
import audio.* ; 
import java.util.* ;
import accompagnement.*;
import tonalité.* ; 

public class Tests {

	public static void main(String[] args){
				
		// ouverture du fichier
		
//		Audio audio = new Audio(args[0]) ;
//		double[] signal = audio.getSignal() ; 
//		double fe = audio.getFe(); 
//		
//		int p = (int)(0.3*fe) ;
//		int n = (int)(0.009*fe) ;
//		ArrayList<Integer> locations = TransientDetector.locations(signal, n, p) ; 
//		double[] transients = new double[signal.length] ; 
//		for (int i : locations) transients[i] = 1 ; 
//		Visualizer.superpoze(signal,transients, fe);
		
		double[][] notes = Transcribe.transcribe(args[0]) ; 
		Harmonize.harmonize(notes);
		

		

		
	}

}
	
