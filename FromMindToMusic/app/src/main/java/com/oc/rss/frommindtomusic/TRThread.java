package com.oc.rss.frommindtomusic;

import java.util.ArrayList;

import Body.Transcription.src.core.Notes;

/**
 * Created by ahmed on 05/03/17.
 */

public class TRThread extends Thread {
        private String str;
        static ArrayList<ArrayList<Double>> notes;

    public TRThread(String str) {
            this.str = str;
        }
        public void run() {
            notes= Notes.notes(str);

        }
    }

