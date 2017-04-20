package transcription;

import java.util.ArrayList;
import java.util.Arrays;
import divers.* ; 

public class Notes {
	
	public static Transcript notes(double[] signal, double fe, int n, int p){


		// Gets the list of musical events and the number of these events 
		ArrayList <Integer> tr = TransientDetector.locations(signal,n,p) ; 
		int N = tr.size() ; 
		ArrayList<ArrayList<Double>> output = new ArrayList<ArrayList<Double>>() ; 
		
		// For each of these events, determines  the note they correspond to and add it to the text file with the the date of the event 
					
			// adding dates and frequencies into the array
			if (tr.size() > 1) {
				for (int i = 0 ; i < N-1 ; i++) addNote(signal,tr,output,fe,i,i+1) ;
				addNote(signal,tr,output,fe,N-1,signal.length) ; 
			}
			else if (tr.size() == 1) addNote(signal,tr,output,fe,0,signal.length) ; 
			else output = null ; 
								
			ArrayList<ArrayList<Double>> output2 = convertInDurations(output,signal,fe) ; 
			
			double[][] arrayOutput = new double[2][output2.size()] ; 
			for (int i = 0 ; i < output2.size() ; i++){
				arrayOutput[0][i] = output2.get(i).get(0) ; 
				arrayOutput[1][i] = output2.get(i).get(1) ; 
			}
			return new Transcript(arrayOutput,tr) ;
			
		

	}
	
	private static void addNote(double[] signal,ArrayList<Integer> tr,ArrayList<ArrayList<Double>> output,double fe,int i,int j){
		
		double[] noteSignal ;
		if (j == signal.length){
			noteSignal = Arrays.copyOfRange(signal, tr.get(i), signal.length) ; 
		}
		else{
			noteSignal = Arrays.copyOfRange(signal, tr.get(i), tr.get(j)) ; 
		}
		ArrayList<Double> note = new ArrayList <Double>(2); 
		note.add(tr.get(i)/fe) ; 
		note.add(NoteDetector.note(noteSignal, fe)) ; 
		if(note.get(1) > 50) output.add(note) ; 
		
	}

	public static final ArrayList<ArrayList<Double>> convertInDurations(ArrayList<ArrayList<Double>> input, double[] signal, double fe){

		ArrayList<ArrayList<Double>> output = new ArrayList<ArrayList<Double>>(input.size()) ;
		double end = signal.length/fe ; 
		for (int i = 0 ; i < input.size() - 1 ; i++) {
			double duration = input.get(i+1).get(0) - input.get(i).get(0) ; 
			double frequency = input.get(i).get(1) ; 
			ArrayList<Double> outputI = new ArrayList<Double>(2) ; 
			outputI.add(0, duration) ; 
			outputI.add(1, frequency) ; 
			output.add(outputI) ; 
		}
		double duration = end - input.get(input.size() - 1).get(0) ; 
		double frequency = input.get(input.size() - 1).get(1) ; 
		ArrayList<Double> outputI = new ArrayList<Double>(2) ; 
		outputI.add(0, duration) ; 
		outputI.add(1, frequency) ; 
		output.add(outputI) ; 
		
		return output ; 
	}

}
