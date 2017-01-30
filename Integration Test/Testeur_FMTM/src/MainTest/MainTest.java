package MainTest;

import java.util.ArrayList;

public class MainTest {
	
	public static void main(String[] args) {
			
			ArrayList<float[]> trans=Transcription();
			Synthèse(trans);
	}
	public static ArrayList<float[]> Transcription() {
		ArrayList<float[]> trans = new ArrayList<float[]>();
		float[] note1 = {(float) 3.5,(float) 2.5};
		float[] note2 ={(float) 2.5,(float) 5.5};
		trans.add(0,note1);
		trans.add(0,note2);

		return trans;
	}
	public static void Synthèse(ArrayList<float[]> A) {
		for (int i=0;i<A.size();i++){
			for (float j:A.get(i)){
				System.out.print(j + " ");
			}
			System.out.println("");
		}
	}

}
