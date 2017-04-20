package accompagnement;

import java.util.ArrayList;
import java.util.Arrays;

public class Chord {
	
	private ArrayList<Double> notes ; 
	private double duration ;
	
	public ArrayList<Double> getNotes() {
		return notes;
	}
	public void setNotes(ArrayList<Double> notes) {
		this.notes = notes;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public Chord(ArrayList<Double> notes, double duration) {
		super();
		this.notes = notes;
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "Chord [notes=" + notes + ", duration=" + duration + "]";
	}

	
	
	
	

}
