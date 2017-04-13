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
		
		Audio audio = new Audio(args[0]) ;
		double[] signal = audio.getSignal() ; 
		double fe = audio.getFe(); 

	
		int p = (int)(0.3*fe) ;
		int n = (int)(0.008*fe) ;
		ArrayList<Integer> locations = TransientDetector.locations(signal, n, p) ; 
		double[] transients = new double[signal.length] ; 
		for (int i : locations) transients[i] = 1 ; 
		System.out.println(Tempo2.tempo(signal, fe));
//		Visualizer.superpoze(signal,transients, fe);
		
//		double[][] notes = Transcribe.transcribe(args[0]) ; 
//		for (int i = 0 ; i < notes[1].length ; i ++) notes[1][i] = Lecture.noteToFrequence(Lecture.note(notes[1][i])) ;// liste 

//		Play.jouer(notes);
//		Harmonize2.harmonize2(notes);
		
		
//		for (int i = 0 ; i < chordPack.length ; i ++){
//				for(int k = 0 ; k < chordPack[i][0].length ; k++){
//					System.out.println("Instrument: " + i + ", Note: " + chordPack[i][0][k] + " Durée: " + chordPack[i][1][k]);
//					
//				}
//			}
		
		
		
		
		
		
		

		

		
	}

}
	
