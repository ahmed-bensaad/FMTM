package Body.transcription;

import java.util.ArrayList;

public class Transcript {
	
	private double[][] notes ; 
	private ArrayList<Integer> tr ;
    private double[] signal ;
	public Transcript(double[][] notes, ArrayList<Integer> tr, double[] signal) {
		super();
		this.notes = notes;
		this.tr = tr;
        this.signal = signal ;
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

    public double[] getSignal() {
        return signal;
    }

    public void setSignal(double[] signal) {
        this.signal = signal;
    }
}
