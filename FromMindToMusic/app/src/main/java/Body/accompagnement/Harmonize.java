package Body.accompagnement;


import android.os.Environment;

import com.oc.rss.frommindtomusic.RecordActivity;

import Body.audio.*;
import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;

import Body.tonalité.*;

public class Harmonize {
	public static void harmonize(double[][] liste){
		
		int n = liste[0].length;
		double[][] accord = new double[4][n];
		double[] f = liste[1];
		String [][] notes = Lecture.frequenceToNote(f);
		String ton = Lecture.tonaliteMaj(notes);
		System.out.println(ton) ;
		String [][]acc;
		accord[0]=liste[0];
		
		for(int i=0; i<n; i++){
			acc = Accord.accord(ton, notes[i][0], notes[i][1]);
			accord[1][i]= Lecture.frequence(acc[0]);
			accord[2][i]= Lecture.frequence(acc[1]);
			accord[3][i]= Lecture.frequence(acc[2]);
		}
		double[] N = accord[0];
		double[] a1 = accord[1];
		double[] a2 = accord[2];
		double[] a3 = accord[3];
		double[] listebis = Arrays.copyOf(liste[1],liste[1].length) ;
		for (int i = 0 ; i < liste[1].length ; i ++) listebis[i] = listebis[i]*2 ;
		double[] y0 = Trompette.jouer(listebis, N) ;
		double[] y1 = Piano.jouer(a1, N) ;
		double[] y2 = Piano.jouer(a2, N) ;
		double[] y3 = Piano.jouer(a3, N) ;
		double[] y = new double[y1.length] ;
		for (int i = 0 ; i < y.length ; i++) y[i] = y0[i]/3 + y1[i]/5 + y2[i]/5 + y3[i]/5 ;

		double[] R = liste[0] ;
		int sampleRate = 44100;		// Samples per second
		double duration = 0;		// Seconds

		for(int i =0 ;i<N.length;i++) // on calcule la dur�e du wav en secondes
		{
			duration=duration+R[i];
		}

		// Calculate the number of frames required for specified duration
		long numFrames = (long)(duration * sampleRate);


		try
		{

			// Create a wav file with the name specified as the first argument
			WavFile wavFile = WavFile.newWavFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "FMTM" + "/" + "perm" + "/"+"harmonized/" +
					"template2" + RecordActivity.i + "AudioRecording.wav"), 2, numFrames, 16, sampleRate);

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