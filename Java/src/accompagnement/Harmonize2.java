package accompagnement;
import java.io.File;
import java.util.* ;

import audio.WavFile;
import divers.Tempo;
import outils.Utilities;
import tonalité.*;
import transcription.Notes; 
public class Harmonize2 {
	
	public final static ArrayList<Chord> divideInChords(double[][] notes,double tempo,double fe){
		
		double[] durations = notes[0] ; 
		double[] frequencies = notes[1] ; 
		
		double length = totalLength(durations) ; 
		double[] times = convertInTimes(durations) ; 
		
		double numNotes = frequencies.length ; 
		ArrayList<Chord> chords = new ArrayList<Chord>() ; 
		double barLength = (2*60)/tempo ; 
		
		double T = barLength;
		int i = 0 ; 
		while (i < numNotes && T < length){
			ArrayList<Double> notesChord = new ArrayList<Double>() ;
			while(i < numNotes && notesChord.size() < 4 && times[i] < T){
				notesChord.add(frequencies[i]) ; 
				i++ ;
				
			}
			chords.add(new Chord(notesChord,barLength)) ; 
			T += barLength ; 
		}
		
		
		return chords ; 
		
		}
			
			
		 
		
	public static final boolean allNotes(double[] durations, ArrayList<Chord> chords){
		
		int i = 0 ; 
		int size = durations.length ; 
		for (Chord c : chords) i += c.getNotes().size(); 
		System.out.println(i);
		if (i==size) return true ; 
		return false ; 
	}
		
	
		
	public static final void harmonize(double tempo, double[][] notes, double[] signal, double fe){
		
		double[] N = notes[1] ; 
		double[] R = notes[0] ; 
		
		ArrayList<Chord> chords = divideInChords(notes,tempo,fe) ; 
		System.out.println(chords);
		int numChords = chords.size() ; 
		
		
		double[] N1 = new double[numChords] ;  
		double[] R1 = new double[numChords] ;  
		
		double[] N2 = new double[numChords] ;  
		double[] R2 = new double[numChords] ;  
		
		double[] N3 = new double[numChords] ;  
		double[] R3 = new double[numChords] ;  
		
		double[] N4 = new double[numChords] ;  
		double[] R4 = new double[numChords] ;  
		
		double[][][] chordPack = {{R1,N1},{R2,N2},{R3,N3},{R4,N4}} ; 
		
		for (int i = 0 ; i < chords.size() ; i++){
			for (int j = 0 ; j < chords.get(i).getNotes().size() ; j++){
				chordPack[j][1][i] = chords.get(i).getNotes().get(j) ; 
				chordPack[j][0][i] = chords.get(i).getDuration() ; 
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
			System.out.println(numFrames);

			// Create a wav file with the name specified as the first argument
			WavFile wavFile = WavFile.newWavFile(new File("harmonized2.wav"), 2, numFrames, 16, sampleRate);

			// Create a buffer of 100 frames
			double[][] buffer = new double[2][100];
			//double[] y = Trompette.jouer(N2,D);
			 
			double[] y = Trompette.jouer(N,R); 
			double[] y1 = Arrays.copyOf(Trompette.jouer(N1,R1), y.length) ; 
			double[] y2 = Arrays.copyOf(Piano.jouer(N2,R2), y.length) ; 
			double[] y3 = Arrays.copyOf(Piano.jouer(N3,R3), y.length) ; 
			double[] y4 = Arrays.copyOf(Piano.jouer(N4,R4), y.length) ; 
			
			double[] Y = new double[y.length] ; 
			for (int i = 0 ; i < Y.length ; i++) Y[i] = y[i]/3 + y1[i]/6 + y2[i]/6 + y3[i]/6 + y4[i]/6 ; 
			

			
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
					
					buffer[0][s] = Y[k];
					
					

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
	
	public static final ArrayList<Double> comb(double length, double tempo, double fe,double beat){
		
		ArrayList<Double> output = new ArrayList <Double>() ; 
		int beatLength = (int) ((60*fe)/tempo) ; 
		for (int i = 0 ; i/fe < length ; i = (int) (i+beatLength/beat)) output.add(i/fe) ; 
		return output ;
		
	}
		
	public static final void quantize(double[][] notes, double tempo, double fe){
		
			double[] durations = Arrays.copyOf(notes[0], notes[0].length) ; 
			int n = 2 ; 
			do{
			double length = totalLength(notes[0]) ; 
			ArrayList<Double> comb = comb(length,tempo,fe,n) ; 
			double[] times = convertInTimes(notes[0]) ; 
			ArrayList<Double> timesCorrected = new ArrayList<Double>() ; 
			for(int i = 0 ; i < times.length ; i++) timesCorrected.add(Utilities.minDistance(times[i], comb)) ; 
		
			for (int i = 0 ; i < timesCorrected.size()-1 ; i ++) durations[i] = timesCorrected.get(i+1) - timesCorrected.get(i) ; 
			durations[durations.length-1] = length - timesCorrected.get(timesCorrected.size()-1) ; 
			n *= 2 ; 
			}while(isZero(durations)) ;
			
			notes[0] = durations ; 
			
		
	}

	
	public static final double[][] metronome(double length, double tempo, double fe, int beat){
		
		ArrayList<Double> comb = comb(length,tempo,fe,beat) ; 
		double[][] notes = new double[2][comb.size()] ; 
		for (int i = 0 ; i < comb.size() - 1 ; i++){
			notes[0][i] = comb.get(i+1) - comb.get(i) ; 
			notes[1][i] = 444.0 ;
		}
		notes[0][comb.size()-1] = length/fe - comb.get(comb.size() - 1) ;
		notes[1][comb.size()-1] = 444.0 ; 
		
		return notes ; 
		
	}
	
	public static final double[] convertInTimes(double[] durations){
		
		double length = totalLength(durations) ; 
		int numNotes = durations.length ; 
		double[] times  = new double[numNotes] ; 
		for (int i = 0 ; i < numNotes ; i++){
			times[i] = 0 ; 
			for (int j = 0  ; j < i ; j++) times[i] += durations[j] ; 
		}
		return times ; 
	}
	
	public static final double totalLength (double[] durations){
		double length = 0 ; 
		int numNotes = durations.length ; 
		for (int i = 0 ; i < numNotes ; i++) length += durations[i] ;
		return length ;
	}
	
	public static final double[][][] test(double tempo, double[][] notes, double[] signal, double fe){
		
		double[] N = notes[1] ; 
		double[] R = notes[0] ; 
		
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
		
		double[][][] chordPack = {{R1,N1},{R2,N2},{R3,N3},{R4,N4}} ; 
		
		for (int i = 0 ; i < chords.size() ; i++){
			for (int j = 0 ; j < chords.get(i).getNotes().size() ; j++){
				chordPack[j][1][i] = chords.get(i).getNotes().get(j) ; 
				chordPack[j][0][i] = chords.get(i).getDuration() ; 
			}
		}
		
		return chordPack ; 
	}
	
	public static boolean isZero(double[] durations){
	
		for (double d : durations) if (d == 0.0) return true ; 
		return false ; 
	}
}
	
		

