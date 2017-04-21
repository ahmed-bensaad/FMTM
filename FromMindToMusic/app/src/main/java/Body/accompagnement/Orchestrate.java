package Body.accompagnement;

import android.os.Environment;

import com.oc.rss.frommindtomusic.RecordActivity;

import java.io.File;

import Body.audio.WavFile;
import Body.tonalité.Basse;
import Body.tonalité.Piano;
import Body.tonalité.Trompette;

public class Orchestrate {

    public static final void orchestrate(double[][] notes1, double[][] notes2,String ch1,String ch2){

        //exemple simple
        double[] N1 = notes1[1];  // liste
        double[] R1 = notes1[0]; // liste

        double[] N2 = notes2[1];
        double[] R2 = notes2[0];

        double[] y1 = null ;
        double[] y2 = null ;

        switch(ch1){
            case "Trumpet":{
                y1 = Trompette.jouer(N1,R1);
                break;}
            case "Bass":{
                y1 = Basse.jouer(N1,R1,1);
                break;}
            case "Piano":{
                y1 = Piano.jouer(N1,R1);
                break;}

        }

        switch(ch2){
            case "Trumpet":{
                y2 = Trompette.jouer(N2,R2);
                break;}
            case "Bass":{
                y2 = Basse.jouer(N2,R2,1);
                break;}
            case "Piano":{
                y2 = Piano.jouer(N2,R2);
                break;}

        }

        double[][] pack = {y1,y2} ;

        int M,m ;
        if (y1.length > y2.length){
            M = 0;
            m = 1;
        }
        else{
            M = 1 ;
            m = 0 ;
        }

        int L = pack[M].length ;
        int l = pack[m].length ;

        double[] Y = new double[L] ;

        for (int k = 0 ; k < L ; k++) {
            Y[k] += pack[M][k];
            if (k < L) Y[k] += pack[m][k];
        }


        int sampleRate = 44100;		// Samples per second
        double duration = 0;		// Seconds

        for(int i =0 ;i<L;i++) // on calcule la dur�e du wav en secondes
        {
            duration=duration + pack[M][i];
        }

        // Calculate the number of frames required for specified duration
        long numFrames = (long)(duration * sampleRate);


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
                    //System.out.println(k);
                    buffer[0][s] = Y[k];
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
