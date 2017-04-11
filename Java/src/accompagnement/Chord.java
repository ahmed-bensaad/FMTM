package accompagnement;

import java.util.Arrays;

public class Chord {
	
	private double[] notes ; 
	private double duration ;
	
	public double[] getNotes() {
		return notes;
	}
	public void setNotes(double[] notes) {
		this.notes = notes;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public Chord(double[] notes, double duration) {
		super();
		this.notes = notes;
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "Chord [notes=" + Arrays.toString(notes) + ", duration=" + duration + "]";
	} 
	
	
	
	

}
