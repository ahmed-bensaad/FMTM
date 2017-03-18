package main;

import transcription.*; 
import outils.* ; 
import audio.* ; 
import java.util.* ; 
import tonalité.* ; 

public class Tests {

	public static void main(String[] args){
				
		Audio audio = new Audio(args[0]) ; 
		double[] signal = audio.getSignal() ; 
		double fe = audio.getFe(); 
		
		int p = (int)(0.5*fe) ; 
		int n = (int)(0.02*fe) ; 
		
		//double[] transients = TransientDetector.transients(signal, n) ; 
		//Visualizer.temporalShow(signal, fe);
		//Visualizer.temporalShow(transients, fe);
		double[][] notes = Notes.notes(signal, fe, n, p) ; 
		System.out.println("Fin transription");
		Play.play(notes, "Bass") ;
		System.out.println("Fin synthèse");
		
	}

}
	
