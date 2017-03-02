package core;

import java.util.ArrayList;
import java.util.Arrays;

public class Tests {

	public static void main(String[] args) {
		
		Audio audio = new Audio(args[0]) ; 
		double[] signal = audio.getSignal() ; 
		ArrayList <Double> signalList = Utilities.toList(signal) ; 
		double fe = audio.getFe() ; 
		ArrayList<MaxObject> max = TransientDetector.findpeaks(signal) ; 
		for(MaxObject m : max) System.out.println(m) ; 
		System.out.println(max.size());
		Visualizer.temporalShow(signal, 1);
	
	}

}
