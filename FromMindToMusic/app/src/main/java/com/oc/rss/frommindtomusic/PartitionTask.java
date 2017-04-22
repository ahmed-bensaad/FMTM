package com.oc.rss.frommindtomusic;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.TextView;

import java.util.ArrayList;

import Body.transcription.NoteObject;
import Body.transcription.NotesList;

import static com.oc.rss.frommindtomusic.R.id.part;

/**
 * Created by user on 22/04/2017.
 */

public class PartitionTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        NotesList ntlst = new NotesList();
        ArrayList<NoteObject> partition = ntlst.getNotesList(TRThread.notes);
        for (int i = 0; i < partition.size(); i++) {

            TextView T= (TextView)objects[0];
           // T.setText(partition.get(i).getNoteAndOct());
            SystemClock.sleep((long) (1000 * partition.get(i).getDuration()));

        }
        return null;
    }
}
