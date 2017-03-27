package com.oc.rss.frommindtomusic;

import java.util.ArrayList;

import Body.transcription.*;

/**
 * Created by ahmed on 05/03/17.
 */

public class TRThread extends Thread {
        private String str;
        static double[][] notes;

    public TRThread(String str) {
        super();
        this.str = str;
        }
        public void run() {
            notes=Transcribe.transcribe(str);
        }
    }

