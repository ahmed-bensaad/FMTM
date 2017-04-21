package Body.transcription;

import java.util.ArrayList;

import Body.accompagnement.Lecture;

/**
 * Created by DELL on 21/04/2017.
 */

public class NotesList {

    public static final ArrayList<NoteObject> getNotesList(double[][] list){

        ArrayList<NoteObject> notes = new ArrayList<NoteObject>() ;
        String[][] notesString = Lecture.frequenceToNote(list[1]) ;
        for (int i = 0 ; i < notesString.length ; i++){
            notes.add(new NoteObject(notesString[i][0]+  " " + notesString[i][1],list[0][i])) ;
        }

        return notes ;

    }
}
