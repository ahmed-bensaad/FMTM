package Body.accompagnement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Harmonize {
	public static ArrayList<ArrayList<Double>> harmonize(ArrayList<ArrayList<Double>> L){
		int n = L.size();
		ArrayList<ArrayList<Double>> accord = new ArrayList<ArrayList<Double>>();
		double[] f = new double[n];
		for(int row = 0; row < n; row++)
		{
			f[row]=L.get(row).get(1);
		}
		String [][] notes = Lecture.frequenceToNote(f);
		String ton = Lecture.tonaliteMaj(notes);
		String [][]acc;
		for(int i=0; i<n; i++){
			accord.get(i).set(0, L.get(i).get(0));

			acc = Accord.accord(ton, notes[i][0], notes[i][1]);
			accord.get(i).set(1, Lecture.noteToFrequence(acc[0][0], acc[0][1]));
			accord.get(i).set(2, Lecture.noteToFrequence(acc[1][0], acc[1][1]));
			accord.get(i).set(3, Lecture.noteToFrequence(acc[2][0], acc[2][1]));
		}
		return (accord);
	}

}