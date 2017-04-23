package com.oc.rss.frommindtomusic;

import java.util.ArrayList;

import Body.transcription.*;

/**
 * Created by ahmed on 05/03/17.
 */

public class TRThread extends Thread {
        private String str;
        static double[][] notes;
        static ArrayList<Integer> tr ;
        static double[] signal ;
        static double[] array ;

    public TRThread(String str) {
        super();
        this.str = str;
        }
        public void run() {

            Transcript transcript = Transcribe.transcribe(str) ;
            notes = transcript.getNotes();
            tr = transcript.getTr();
            signal = transcript.getSignal() ;
        }

    public static double[] getArray() {
        return array;
    }

    public static void setArray(double[] array) {
        TRThread.array = array;
    }
}

