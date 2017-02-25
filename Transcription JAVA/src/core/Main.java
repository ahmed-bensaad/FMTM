package core;

public class Main {
	
	public static void main(String[] args) {
		
		Audio audio = new Audio(args[0]) ; 
		double[] signal = audio.getSignal(); 
		double fe = audio.getFe() ; 
		double[] envelope = Process.envelope(signal) ; 
		double[] transients = Process.transients(envelope,2000) ; 
		Visualizer.show(transients,fe);

		
		
		
			
		
		
		
		
	}
	
}

		

	

	 

