import java.io.File;

public class Play {

		public static void main(String[] args)
		{
			
			double[] N = {440,220,440};
			double[] R = {0.5,1,0.5};
			String[] N1={"do","do","si","si","do","do","la","mi","mi","sol#","sol#","la"};
			String[] U={"c","c","c","c","dc","c","n+dc","c","c","dc","c","n"};
			int[] O = {4,4,3,3,4,4,3,3,3,3,3,3};
			double[] D = new double[N1.length];
			double[] N2= new double[N1.length];
			for(int i =0;i<N1.length;i++)
			{
				Note u = new Note(N1[i],O[i],U[i],60);
				N2[i]=u.getfréquence();
				D[i]=u.getdurée();
			}
		
			try
			{
				int sampleRate = 44100;		// Samples per second
				double duration = 0;		// Seconds
				
				for(int i =0 ;i<N1.length;i++)
				{
					duration=duration+D[i];
				}
				
				// Calculate the number of frames required for specified duration
				long numFrames = (long)(duration * sampleRate);

				// Create a wav file with the name specified as the first argument
				WavFile wavFile = WavFile.newWavFile(new File("monWav.wav"), 2, numFrames, 16, sampleRate);

				// Create a buffer of 100 frames
				double[][] buffer = new double[2][100];
				//double[] y = Trompette.jouer(N2,D);
				double[] y = Basse.jouer(N2, D, 1);
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
