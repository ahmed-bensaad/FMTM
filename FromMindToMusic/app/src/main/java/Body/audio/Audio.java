package Body.audio;

import java.io.* ; 

public class Audio{
	
	double[] signal ; 
	double fe ; 
	
	public Audio(String fileName) {
		
		try {
			
			// Open the wav file specified as the first argument
			WavFile wavFile = WavFile.openWavFile(new File(fileName));
			
			// Give the right size to the array of frames
			signal = new double[(int)wavFile.getNumFrames()] ; 
			
			double[] buffer = new double[1000] ; 
	        int framesRead;
	        int k = 0  ; 
	        
	        do{
	        	
	           // Read frames into buffer
	           framesRead = wavFile.readFrames(buffer, 1000);

	           // 
	           for (int s=0 ; s<framesRead ; s++){
	        	   signal[k+s] = buffer[s] ; 
	           }
	           
	           k+=1000 ;
	        }
	        while (framesRead != 0);
	        
	        // store the sampling frequency 
	        fe = wavFile.getSampleRate() ; 
	        
			// Close the wavFile
			wavFile.close();
			
		}
		
		catch (Exception e){
			
			System.err.println(e);
		}
	
	}
	
	public double[] getSignal() {
		return signal;
	}

	public double getFe() {
		return fe;
	}


	
}
