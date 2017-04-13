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
//
//		int p = (int)(0.3*fe) ;
//		int n = (int)(0.008*fe) ;
//		ArrayList<Integer> locations = TransientDetector.locations(signal, n, p) ; 
//		double[] transients = new double[signal.length] ; 
//		for (int loc : locations) transients[loc] = 1 ; 
//		Visualizer.superpoze(signal, transients, fe);

//		double tempo = EfficientTempo.tempo(transients, fe);
//		Visualizer.temporalShow(Harmonize2.metronome(signal, tempo, fe),fe) ; 
//		System.out.println(EfficientTempo.tempo(transients, fe));
		double[][] notes = Transcribe.transcribe(args[0]) ; 
		Harmonize.harmonize(notes) ; 
		
//		Harmonize2.quantize(signal, notes, tempo, fe);

//		for (int i = 0 ; i < notes[1].length ; i ++) notes[1][i] = Lecture.noteToFrequence(Lecture.note(notes[1][i])) ;// liste 

//		for (int i = 0 ; i < notes[0].length ; i++) System.out.println("Durée: " + notes[0][i] + ", Fréquence: " + notes[1][i]) ; 
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
	
