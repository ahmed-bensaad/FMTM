package Body.Transcription.src.core;

import java.util.ArrayList;
import java.util.Arrays;

public class Notes {

	public static double[][] notes(String fileName){

		// Opens the file and reads frames into a double[], gets the sampling rate
		Audio audio = new Audio(fileName) ;
		double[] signal = audio.getSignal();
		double fe = audio.getFe() ;
	

		// Gets the list of musical events and the number of these events 
		ArrayList <Integer> tr = TransientDetector.transientLocations(signal, 3000, fe) ; 
		int N = tr.size() ; 
		
		ArrayList<ArrayList<Double>> output = new ArrayList<ArrayList<Double>>() ; 
		
		// For each of these events, determines the note they correspond to and add it to the text file with the the date of the event 
					
			// adding dates and frequencies into the array
			if (tr.size() > 1) {
				for (int i = 0 ; i < N-1 ; i++){ 
					double[] noteSignal = Arrays.copyOfRange(signal, tr.get(i), tr.get(i+1)) ; 
					ArrayList<Double> note = new ArrayList <Double>(2); 
					note.add(tr.get(i)/fe) ; 
					note.add(NoteDetector.note(noteSignal, fe)) ; 
					output.add(note) ; 
					
				}
				double[] noteSignal = Arrays.copyOfRange(signal, tr.get(N-1), signal.length) ;
				ArrayList<Double> note = new ArrayList <Double>(2); 
				note.add(tr.get(N-1)/fe) ; 
				note.add(NoteDetector.note(noteSignal, fe)) ; 
				output.add(note) ; 
			}
			
			else if (tr.size() == 1){ 
				double[] noteSignal = Arrays.copyOfRange(signal, tr.get(0), signal.length) ; 
				ArrayList<Double> note = new ArrayList <Double>(2); 
				note.add(tr.get(0)/fe) ; 
				note.add(NoteDetector.note(noteSignal, fe)) ; 
				output.add(note) ; 
			}
			
			else output = null ; 
			
			ArrayList<ArrayList<Double>> output2 = Utilities.convertInDurations(output, signal, fe) ; 
						
			double[][] arrayOutput = new double[2][output2.size()] ; 
			for (int i = 0 ; i < output2.size() ; i++){
				arrayOutput[0][i] = output2.get(i).get(0) ; 
				arrayOutput[1][i] = output2.get(i).get(1) ; 
			}
			
			return arrayOutput ; 

	}

}
