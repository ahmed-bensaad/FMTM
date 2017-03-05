package Body.Test_audio.src;
import android.os.Environment;

import com.oc.rss.frommindtomusic.RecordActivity;

import java.io.File;
import java.util.ArrayList;

import Body.WavFile.*;
public class Play {

		public static void play(ArrayList<ArrayList<Double>> notes , String instrument)
		{
			double[]D= new double[notes.size()];
			double[]N2= new double[notes.size()];
			for (int i=0; i<notes.size();i++){
				N2[i]=notes.get(i).get(0);
				D[i]=notes.get(i).get(1);
			}

			try
			{
				int sampleRate = 44100;		// Samples per second
				double duration = 0;		// Seconds
				
				for(int i =0 ;i<N2.length;i++)
				{
					duration=duration+D[i];
				}
				
				// Calculate the number of frames required for specified duration
				long numFrames = (long)(duration * sampleRate);

				// Create a wav file with the name specified as the first argument
				WavFile wavFile = WavFile.newWavFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FMTM/perm/testwave"+ RecordActivity.i+".wav"), 2, numFrames, 16, sampleRate);

				// Create a buffer of 100 frames
				double[][] buffer = new double[2][100];
				/*double[] y=new double[(int)numFrames] ;
				switch (instrument) {
					case "Basse":{
					 y = Basse.jouer(N2, D, 1);

				}
					case "Trompette":{
					 y = Trompette.jouer(N2, D);

				}
				}*/

				double[]y = Trompette.jouer(N2, D);
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
