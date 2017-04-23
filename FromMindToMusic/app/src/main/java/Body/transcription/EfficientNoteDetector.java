package Body.transcription;

import java.util.Arrays;

import Body.outils.Utilities;

/**
 * Created by DELL on 23/04/2017.
 */

public class EfficientNoteDetector {

    public static final double note(double[] signal, double fe){


        int ms2 = (int) fe/500 ;
        int ms20 = (int) fe/50 ;

        double[] xcorr = Utilities.xcorr(signal) ;

        double argMax = Utilities.argMax(Arrays.copyOfRange(xcorr, ms2, ms20)) ;
        return fe/(ms2 + argMax - 1) ;
    }
}