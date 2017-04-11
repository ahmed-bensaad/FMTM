package accompagnement;
import java.io.File;
import java.util.* ;

import audio.WavFile;
import tonalité.*; 
public class Harmonize2 {
	
	public final static ArrayList<Chord> divideInChords(double[][] notes){
		
		double[] durations = notes[0] ; 
		double[] frequencies = notes[1] ; 

		ArrayList<Chord> chords = new ArrayList<Chord>() ;
		
		int numNotes = notes[0].length ; 
		int numChords = (int) numNotes/4 ; 
		
		for (int i = 0 ; i < numChords*4 ; i = i+4){
			double[] notesChord = {frequencies[i],frequencies[i+1],frequencies[i+2],frequencies[i+3]} ;
			double durationChord = notes[0][i]+notes[0][i+1]+notes[0][i+2]+notes[0][i+3] ; 
			Chord chord = new Chord(notesChord,durationChord) ; 
			chords.add(chord) ; 
			}
		
		if  (numNotes - numChords*4 != 0){
			double[] notesChord = new double[numNotes - numChords*4] ;
			double durationChord = 0 ; 
		
			for (int i = numChords*4 ; i < numNotes ; i++){ 
				notesChord[i-numChords*4] = frequencies[i] ; 
				durationChord += durations[i] ; 
			}
		
			Chord chord = new Chord(notesChord,durationChord) ;
			chords.add(chord) ; 
		}
		
		return chords ; 
		
	}
	
	public static final void harmonize2(double[][] notes){
		
		double[] N = notes[1] ;  // liste 
		double[] R = notes[0] ; // liste 
		
		ArrayList<Chord> chords = divideInChords(notes) ; 
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
			double[] y1 = Basse.jouer(N1,R1,1); 
			double[] y2 = Basse.jouer(N2,R2,1); 
			double[] y3 = Basse.jouer(N3,R3,1); 
			double[] y4 = Basse.jouer(N4,R4,1); 
			
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
					buffer[0][s] = y[k]/4 + y1[k]/5 +y2[k]/5 + y3[k]/5 + y4[k]/5;
					// 
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
		
}
	
		

