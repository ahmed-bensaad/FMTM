package accompagnement;
import java.io.File;
import java.util.* ;

import audio.WavFile;
import divers.Tempo;
import outils.Utilities;
import tonalité.*; 
public class Harmonize2 {
	
	public final static ArrayList<Chord> divideInChords(double[][] notes,double tempo,double fe){
		
		double[] durations = notes[0] ; 
		double[] frequencies = notes[1] ; 
//		double[] frequencies = new double[notes[1].length] ; 
//		for (int i = 0 ; i < frequencies.length ; i ++) frequencies[i] = Lecture.noteToFrequence(Lecture.note(notes[1][i])) ; 

		ArrayList<Chord> chords = new ArrayList<Chord>() ;
		
		int numNotes = notes[0].length ; 
		int numChords = (int) (numNotes/4) ; 
		int wholeLength = (int) ((60*fe)/tempo) ; 
		
		for (int i = 0 ; i < numChords*4 ; i = i+4){
			double[] notesChord = {frequencies[i],frequencies[i+1],frequencies[i+2],frequencies[i+3]} ;
			double durationChord = wholeLength/2 ; 
			Chord chord = new Chord(notesChord,durationChord) ; 
			chords.add(chord) ; 
			}
		
		if  (numNotes - numChords*4 != 0){
			double[] notesChord = new double[numNotes - numChords*4] ;
			double durationChord = wholeLength ; 
		
			for (int i = numChords*4 ; i < numNotes ; i++) notesChord[i-numChords*4] = frequencies[i] ; 
			
		
			Chord chord = new Chord(notesChord,durationChord) ;
			chords.add(chord) ; 
		}
		
		return chords ; 
		
	}
		
	public static final void harmonize(double[][] notes, double[] signal, double fe){
		
		double[] N = notes[1] ; 
//		double[] N = new double[notes[1].length] ; 
//		for (int i = 0 ; i < N.length ; i ++) N[i] = Lecture.noteToFrequence(Lecture.note(notes[1][i])) ;// liste 
		double[] R = notes[0] ; // liste 
		double tempo = EfficientTempo.tempo(signal, fe) ; 
		ArrayList<Chord> chords = divideInChords(notes,tempo,fe) ; 
		int numChords = chords.size() ; 
		
		
		double[] N1 = new double[numChords] ;  
		double[] R1 = new double[numChords] ;  
		
		double[] N2 = new double[numChords] ;  
		double[] R2 = new double[numChords] ;  
		
		double[] N3 = new double[numChords] ;  
		double[] R3 = new double[numChords] ;  
		
		double[] N4 = new double[numChords] ;  
		double[] R4 = new double[numChords] ;  
		
		double[][][] chordPack = {{N1,R1},{N2,R2},{N3,R3},{N4,R4}} ; 
		
		for (int i = 0 ; i < chords.size() ; i ++){
			for (int j = 0 ; j < chords.get(i).getNotes().length ; j++){
				chordPack[j][0][i] = chords.get(i).getNotes()[j] ; 
				chordPack[j][1][i] = chords.get(i).getDuration() ; 
			}
		}
	
	
		try
		{
			int sampleRate = 44100;		// Samples per second
			double duration = 0;		// Seconds
			
			for(int i =0 ;i<N.length;i++) // on calcule la durï¿½e du wav en secondes
			{
				duration=duration+R[i];
			}
			
			// Calculate the number of frames required for specified duration
			long numFrames = (long)(duration * sampleRate);

			// Create a wav file with the name specified as the first argument
			WavFile wavFile = WavFile.newWavFile(new File("harmonized.wav"), 2, numFrames, 16, sampleRate);

			// Create a buffer of 100 frames
			double[][] buffer = new double[2][100];
			//double[] y = Trompette.jouer(N2,D);
			 
			double[] y = Trompette.jouer(N,R); 
			double[] y1 = Basse.jouer(N1,R1,2); 
			double[] y2 = Basse.jouer(N2,R2,2); 
			double[] y3 = Basse.jouer(N3,R3,2); 
			double[] y4 = Basse.jouer(N4,R4,2); 
			
			// Initialise a local frame counter
			long frameCounter = 0;
			int k = 0;
			// Loop until all frames written
			while (frameCounter < numFrames)
			{
				// Determine how many frames to write, up to a maximum of the buffer size
				long remaining = wavFile.getFramesRemaining();
				int toWrite = (remaining > 100) ? 100 : (int) remaining;
				
				// Fill the buffer, one tone per channel
				for (int s=0 ; s<toWrite ; s++, frameCounter++)
				{
					//buffer[0][s] = Math.sin(2.0 * Math.PI * 400 * frameCounter / sampleRate);
					//buffer[1][s] = Math.sin(2.0 * Math.PI * 500 * frameCounter / sampleRate);
					//System.out.println(k);
					buffer[0][s] = y[k]/5 + y1[k]/5 +y2[k]/5 + y3[k]/5 + y4[k]/5 ;
					//System.out.println(y[k]);
					k++;
				}

				// Write the buffer
				wavFile.writeFrames(buffer, toWrite);
			}

			// Close the wavFile
			wavFile.close();
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
			

		
	}
	
	public static final ArrayList<Double> metronome(double[] signal, double tempo, double fe){
		
		ArrayList<Double> output = new ArrayList <Double>() ; 
		int wholeLength = (int) ((60*fe)/tempo) ; 
		for (int i = 0 ; i < signal.length ; i = (int) i+wholeLength/16) output.add(i/fe) ; 
		return output ;
		
	}
		
	public static final void quantize(double[] signal, double[][] notes, double tempo, double fe){
		
		ArrayList<Double> metronome = metronome(signal,tempo,fe) ; 
		double[] durations = notes[0] ; 
		double[] times = new double[durations.length] ; 
		for (int i = 0 ; i < durations.length ; i++){
			times[i] = 0 ;  
			for (int j = 0 ; j < i ; j++) times[i] += durations[j] ; 
		}
		ArrayList<Double> timesCorrected = new ArrayList<Double>() ; 
		for(int i = 0 ; i < times.length ; i++) timesCorrected.add(Utilities.minDistance(times[i], metronome)) ; 
		
		for (int i = 0 ; i < timesCorrected.size()-1 ; i ++) durations[i] = timesCorrected.get(i+1) - timesCorrected.get(i) ; 
		durations[durations.length-1] = signal.length/fe - timesCorrected.get(timesCorrected.size()-1) ; 

		
	}

	public static final ArrayList<Chord> divideInChords2(double[] signal, double[][] notes, double tempo, double fe){
		
		double[] durations = notes[0] ;
		double[] frequencies = notes[1] ; 
		ArrayList<Chord> chords = new ArrayList<Chord>() ;
		
		double[] times = new double[durations.length] ; 
		for (int i = 0 ; i < durations.length ; i++){
			times[i] = 0 ;  
			for (int j = 0 ; j < i ; j++) times[i] += durations[j] ; 
		}
		
		int numNotes = notes[0].length ; 
		int numChords = (int) (numNotes/4) ; 
		int wholeLength = (int) ((60*fe)/tempo) ; 
		
		
		return chords ; 
	}
	
}
	
		

