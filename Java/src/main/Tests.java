package main;

import transcription.*; 
import outils.* ; 
import audio.* ; 
import java.util.* ; 
import tonalité.* ; 

public class Tests {

	public static void main(String[] args){
				
		// ouverture du fichier
		Audio audio = new Audio(args[0]) ; 
		double[] signal = audio.getSignal() ; 
		double fe = audio.getFe(); 
		
		// début de la partie transcription
		int p = (int)(0.5*fe) ; 
		int n = (int)(0.02*fe) ; 
		double[][] notes = Notes.notes(signal, fe, n, p) ; 
		System.out.println("fin de la partie transcription");
		Play.jouer(notes);
		
		
		// début de la partie accompagnement
		// fin de la partie accompagnement
		
		// début de la partie synthèse 
		// fin de la partie synthèse

		
	}

}
	
