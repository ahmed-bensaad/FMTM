package core;
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		
		// Opens the file and reads frames into a double[], gets the sampling rate 
		Audio audio = new Audio(args[0]) ; 
		double[] signal = audio.getSignal(); 
		double fe = audio.getFe() ; 
		
		// Gets the list of musical events and the number of these events 
		ArrayList <Integer> tr = TransientDetector.transientLocations(signal, 100, fe) ; 
		int N = tr.size() ; 
		
		// For each of these events, determines the note they correspond to and add it to the text file with the the date of the event 
		PrintWriter notes = null ; 
		try{
			notes = new PrintWriter("notes.txt") ; 
			for (int i = 0 ; i < N-1 ; i++){ 
				double[] noteSignal = Arrays.copyOfRange(signal, tr.get(i), tr.get(i+1)) ; 
				notes.println("(" + tr.get(i)/fe  + ","  + NoteDetector.note(noteSignal, fe)  +")")  ;
			}
			double[] noteSignal = Arrays.copyOfRange(signal, tr.get(N-2), tr.get(N-1)) ;
			notes.println("(" + tr.get(N-1)/fe  + ","  + NoteDetector.note(noteSignal, fe) +")") ; 
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				notes.close() ; 
			}
			catch(Exception e) {}
		}
		
	}
	
}

		

	

	 

