package Body.transcription;

/**
 * Created by DELL on 21/04/2017.
 */

public class NoteObject {

    private String noteAndOct ;
    private double duration ;

    public String getNoteAndOct() {
        return noteAndOct;
    }

    public void setNoteAndOct(String noteAndOct) {
        this.noteAndOct = noteAndOct;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public NoteObject(String noteAndOct, double duration) {
        this.noteAndOct = noteAndOct;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "NoteObject{" +
                "noteAndOct='" + noteAndOct + '\'' +
                ", duration=" + duration +
                '}';
    }
}
