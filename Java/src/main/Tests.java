package main;

import transcription.*; 
import outils.* ; 
import audio.* ; 
import java.util.* ;

import accompagnement.Harmonize;
import tonalité.* ; 

public class Tests {

	public static void main(String[] args){
				
		// ouverture du fichier
		Audio audio = new Audio(args[0]) ; 
		double[] signal = audio.getSignal() ; 
		double fe = audio.getFe(); 
		
		// d�but de la partie transcription
		int p = (int)(0.5*fe) ; 
		int n = (int)(0.02*fe) ; 
		double[][] notes = Notes.notes(signal, fe, n, p) ; 
		System.out.println("fin de la partie transcription");
		Play.jouer(notes);
		
		
		// d�but de la partie accompagnement
		Harmonize.harmonize(notes);
		// fin de la partie accompagnement
		
		// d�but de la partie synth�se 
		// fin de la partie synth�se

		
	}

}
	
