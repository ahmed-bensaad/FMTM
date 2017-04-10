package accompagnement;


import audio.*;
import java.io.BufferedReader;
import java.io.File;
import tonalité.*;

public class Harmonize {
	public static double[][] harmonize(double[][] liste){
		int n = liste.length;
		double[][] accord = new double[4][n];
		double[] f = liste[1];
		String [][] notes = Lecture.frequenceToNote(f);
		String ton = Lecture.tonaliteMaj(notes);
		String [][]acc;
		accord[0]=liste[0];
		for(int i=0; i<n; i++){
			acc = Accord.accord(ton, notes[i][0], notes[i][1]);
			accord[1][i]= Lecture.noteToFrequence(acc[0][0], acc[0][1]);
			accord[2][i]= Lecture.noteToFrequence(acc[1][0], acc[1][1]);
			accord[3][i]=Lecture.noteToFrequence(acc[2][0], acc[2][1]);
		}
		double[] N = accord[0];
		double[] a1 = accord[1];
		double[] a2 = accord[2];
		double[] a3 = accord[3];
		try
		{
			int sampleRate = 44100;		// Samples per second
			double duration = 0;		// Seconds
			
			for(int i =0 ;i<n;i++) // on calcule la durï¿½e du wav en secondes
			{
				duration=duration+N[i];
			}
			
			// Calculate the number of frames required for specified duration
			long numFrames = (long)(duration * sampleRate);

			// Create a wav file with the name specified as the first argument
			WavFile wavFile = WavFile.newWavFile(new File("monWav2.wav"), 2, numFrames, 16, sampleRate);

			// Create a buffer of 100 frames
			double[][] buffer = new double[2][100];
			//double[] y = Trompette.jouer(N2,D);
			double[] y1 = Trompette.jouer(a1,N);
			double[] y2 =Piano.jouer(a1,N);
			double[] y3 = Piano.jouer(a2,N);
			double[] y4= Piano.jouer(a3,N);
			double[] y= new double[n];
			for(int i=0; i<n; i++){
				y[i]=y1[i]+y2[i]+y3[i]+y4[i];
			}
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
		return (accord);
	}

}