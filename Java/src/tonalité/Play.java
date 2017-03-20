package src;

import java.io.File;

public class Play {

		public static void jouer()
		{
			
			// dans les lignes avant le try (ie les 11 prochaines) j'instancie juste un exemple
			/* String[] N1={"do","do","si","si","do","do","la","mi","mi","sol#","sol#","la"};//note
			String[] U={"c","c","c","c","dc","c","n+dc","c","c","dc","c","n"};//rythme
			int[] O = {4,4,3,3,4,4,3,3,3,3,3,3};//octave
			double[] N = new double[N1.length]; // contiendra la liste des note en fréquence
			double[] T= new double[N1.length]; // contiendra la liste des rythmes 
			
			for(int i =0;i<N1.length;i++)
			{
				Note u = new Note(N1[i],O[i],U[i],60);
				N2[i]=u.getfréquence();
				D[i]=u.getdurée();
			}*/
			
			//exemple simple
			double[] N = {440,220,440};  // liste de 3 notes en fréquence
			double[] R = {0.25,0.5,0.25}; // liste de la durée des notes en secondes
			
			try
			{
				int sampleRate = 44100;		// Samples per second
				double duration = 0;		// Seconds
				
				for(int i =0 ;i<N.length;i++) // on calcule la durée du wav en secondes
				{
					duration=duration+R[i];
				}
				
				// Calculate the number of frames required for specified duration
				long numFrames = (long)(duration * sampleRate);

				// Create a wav file with the name specified as the first argument
				WavFile wavFile = WavFile.newWavFile(new File("monWav2.wav"), 2, numFrames, 16, sampleRate);

				// Create a buffer of 100 frames
				double[][] buffer = new double[2][100];
				//double[] y = Trompette.jouer(N2,D);
				double[] y = Trompette.jouer(N,R); 
				// Initialise a local frame counter
				long frameCounter = 0;
				int k = 0;
				// Loop until all frames written
				while (frameCounter < numFrames)
				{
					// Determine how many frames to write, up to a maximum of the buffer size
					long remaining = wavFile.getFramesRemaining();
					int toWrite = (remaining > 100) ? 100 : (int) remaining;
					
					// Fill the buffer, one tone per channel
					for (int s=0 ; s<toWrite ; s++, frameCounter++)
					{
						//buffer[0][s] = Math.sin(2.0 * Math.PI * 400 * frameCounter / sampleRate);
						//buffer[1][s] = Math.sin(2.0 * Math.PI * 500 * frameCounter / sampleRate);
						//System.out.println(k);
						buffer[0][s] = y[k];
						//System.out.println(y[k]);
						k++;
					}

					// Write the buffer
					wavFile.writeFrames(buffer, toWrite);
				}

				// Close the wavFile
				wavFile.close();
			}
			catch (Exception e)
			{
				System.err.println(e);
			}
				
	
			
		}
	

}
