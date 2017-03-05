package Body.Transcription.src.core;

public class MaxObject {
	
	private int index ; 
	private Double height ; 
	private Double prominence ;
	
	public MaxObject(int index, Double height, Double prominence) {
		this.index = index;
		this.height = height;
		this.prominence = prominence;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getProminence() {
		return prominence;
	}

	public void setProminence(Double prominence) {
		this.prominence = prominence;
	} 
	
	public final String toString(){
		return "Index: " + this.index + ", Height: " + this.height + ", Prominence: " + this.prominence ; 
	}
	
	

}
