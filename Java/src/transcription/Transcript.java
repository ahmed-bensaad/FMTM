package transcription;

import java.util.ArrayList;

public class Transcript {
	
	private double[][] notes ; 
	private ArrayList<Integer> tr ;
	public Transcript(double[][] notes, ArrayList<Integer> tr) {
		super();
		this.notes = notes;
		this.tr = tr;
	}
	public double[][] getNotes() {
		return notes;
	}
	public void setNotes(double[][] notes) {
		this.notes = notes;
	}
	public ArrayList<Integer> getTr() {
		return tr;
	}
	public void setTr(ArrayList<Integer> tr) {
		this.tr = tr;
	} 
	
	

}
