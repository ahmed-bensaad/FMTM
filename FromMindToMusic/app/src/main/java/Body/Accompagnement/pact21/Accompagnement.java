package Body.Accompagnement.pact21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Accompagnement {
	public static String[][] accord(ArrayList<ArrayList<Double>> L){
		int n = L.size();
		String [][] accord = new String[n][4];
		double[] f = new double[n];
		for(int row = 0; row < n; row++)
		{
			L.get(row).set(1,f[row]) ;
		}
		String [] notes = Lecture.frequenceToNote(f);
		String ton = Lecture.tonaliteMaj(notes);
		String []acc;
		for(int i=0; i<n; i++){
			accord[i][0]=Double.toString(L.get(i).get(0));
			acc = Accord.accord(ton, notes[i]);
			accord[i][1]=acc[0];
			accord[i][2]=acc[1];
			accord[i][3]=acc[2];
		}
		return (accord);
	}

}
