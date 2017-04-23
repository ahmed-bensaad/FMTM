package Body.accompagnement;

import android.os.Environment;

import com.oc.rss.frommindtomusic.RecordActivity;

import java.io.File;

import Body.audio.WavFile;
import Body.outils.Utilities;
import Body.tonalité.Basse;
import Body.tonalité.Piano;
import Body.tonalité.Trompette;

public class Orchestrate {

    public static final double[] orchestrate(double[] y, double[][] newNotes, String ch){

        //exemple simple
        double[] N = newNotes[1];  // liste
        double[] R = newNotes[0]; // liste

        double[] y2 = null ;

        Utilities.normalize(y) ;

        switch(ch){
            case "Trumpet":{
                y2 = Trompette.jouer(N,R);
                break;}
            case "Bass":{
                y2 = Basse.jouer(N,R,1);
                break;}
            case "Piano":{
                y2 = Piano.jouer(N,R);
                break;}

        }

        double[][] pack = {y,y2} ;

        int M,m ;
        if (y.length > y2.length){
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
            Y[k] += pack[M][k]/2;
            if (k < l) Y[k] += pack[m][k]/2;
        }


        return Y ;

    }


}
