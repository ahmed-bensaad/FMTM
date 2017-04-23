package Body.accompagnement;

import android.os.Environment;

import com.oc.rss.frommindtomusic.RecordActivity;
import com.oc.rss.frommindtomusic.SingInstrumentActivity;

import java.io.File;

import Body.audio.WavFile;

/**
 * Created by DELL on 23/04/2017.
 */

public class PlayArray {

    public static final void Play(double[] array){

        int sampleRate = 44100;		// Samples per second
        // Seconds

        int numFrames = array.length ;

        try
        {

            // Create a wav file with the name specified as the first argument
            WavFile wavFile = WavFile.newWavFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/FMTM/perm/orchestrated/template"+ SingInstrumentActivity.j+".wav"), 2, numFrames, 16, sampleRate);

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
                    buffer[0][s] = array[k];
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

    }
}
