package Body.transcription;
import java.util.ArrayList;
import java.util.Arrays;

import Body.accompagnement.* ;

public class HarmonizeChords {
	
	public static final double harmonize(double[][] notes,double[] signal,ArrayList<Integer> tr, double fe){
		
		double[] durations = Arrays.copyOf(notes[0], notes[0].length) ; 
		double[] frequencies = Arrays.copyOf(notes[1], notes[1].length) ; 
		double[][] notesbis = {durations,frequencies} ; 
		double tempo = Tempo.tempo(signal.length, fe, tr) ;
		frequencies = Lecture.notesToFrequencies(frequencies) ; 
		Harmonize2.quantize(notesbis, tempo, fe);
		Harmonize2.harmonize(tempo, notesbis, signal, fe);
		return tempo ;
		
	}

}
