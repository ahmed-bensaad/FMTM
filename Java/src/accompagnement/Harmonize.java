package accompagnement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Harmonize {
	
	public static void harmonize(String[] args){
		double L[][];
		int n = L.length;
		String [][] accord = new String[n][4];
		double f [];
		for(int row = 0; row < n; row++)
		{
		    f[row] = L[row][1];
		}
		String [] notes = Lecture.frequenceToNote(f);
		String ton = Lecture.tonaliteMaj(notes);
		String []acc;
		for(int i=0; i<n; i++){
			accord[i][0]=Double.toString(L[i][0]);
			acc = Accord.accord(ton, notes[i]);
			accord[i][1]=acc[0];
			accord[i][2]=acc[1];
			accord[i][3]=acc[2];
		}
		
	}

}
