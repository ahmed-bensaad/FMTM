package Body.tonalité;
import android.os.Environment;

import com.oc.rss.frommindtomusic.RecordActivity;

import Body.audio.* ;

import java.io.*;

public class Play {

		public static double[] jouer(double[][] notes,String ch){

			
			//exemple simple
			double[] N = notes[1];  // liste 
			double[] R = notes[0]; // liste 
			double[] y=null;

			int sampleRate = 44100;		// Samples per second
			double duration = 0;		// Seconds

			for(int i =0 ;i<N.length;i++) // on calcule la dur�e du wav en secondes
			{
				duration=duration+R[i];
			}

			// Calculate the number of frames required for specified duration
			long numFrames = (long)(duration * sampleRate);

			switch(ch){
				case "Trumpet":{
					y = Trompette.jouer(N,R);
					break;}
				case "Bass":{
					y = Basse.jouer(N,R,1);
					break;}
				case "Piano":{
					y = Piano.jouer(N,R);
					break;}

			}

			try
			{


				// Create a wav file with the name specified as the first argument
				WavFile wavFile = WavFile.newWavFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FMTM/perm/template"+ RecordActivity.i+".wav"), 2, numFrames, 16, sampleRate);

				// Create a buffer of 100 frames
				double[][] buffer = new double[2][100];
				//double[] y = Trompette.jouer(N2,D);

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
						//Log.e("progfiling",k);
						buffer[0][s] = y[k];
						//Log.e("progfiling",y[k]);
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
				
		return y ;
			
		}
	

}
