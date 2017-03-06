package pact21;
import java.lang.Math;
import java.util.*;
public class Lecture {
	
	
	public static String note(double f)
	{	
		double x=Math.log(f/440)/Math.log(Math.pow(2,(1/12)));
		int y= (int) (Math.round(x) + 45) ;
		int oct = Math.floorDiv(y,12);
		int m = y% 12;
		String[] G={"do", "do#", "re", "re#", "mi", "fa", "fa#", "sol", "sol#", "la", "la#", "si"};
		String n = G[m];
		String d =  n;
		return d;
	}
	
	public static double frequence(String O, String note){
		double oct =Integer.parseInt(O);
		String[] G={"do", "do#", "re", "re#", "mi", "fa", "fa#", "sol", "sol#", "la", "la#", "si"};
		int i = Arrays.asList(G).indexOf(note);
		double f = Math.pow(Math.pow(2, 1/12),oct*12 + i - 45);
		return f;
	}
	public static String [] frequenceToNote(double f []){
		int n = f.length;
		String [] notes = new String [n];
		for(int i =0; i<n; i++){
			notes[i]= note(f[i]);
		}
		return notes;
		
	}
	public static String tonaliteMaj(String[] N){
		int k=0;
		int l=0;
		for(int i = 0; i< N.length; i++){
		    if (N[i].equals("si#"))
		        k=Math.max(7,k);
		    else if (N[i].equals("mi#"))
		            k =Math.max(6,k);
		        else if (N[i].equals("la#"))
		                k=Math.max(5,k);
		            else if (N[i].equals("re#"))
		                    k=Math.max(4,k);
		                else if (N[i].equals("sol#"))
		                        k= Math.max(3,k);
		                    else if (N[i].equals("do#"))
		                            k= Math.max(2,k);
		                        else if (N[i].equals("fa#"))
		                                k= Math.max(1,k);
		}
		for (int j=0; j<N.length; j++){
			if (N[j].equals("fab"))
		        l=Math.max(7,l);
		    else if (N[j].equals("dob"))
		            l =Math.max(6,l);
		        else if (N[j].equals("solb"))
		                l=Math.max(5,l);
		            else if (N[j].equals("reb"))
		                    l=Math.max(4,l);
		                else if (N[j].equals("lab"))
		                        l= Math.max(3,l);
		                    else if (N[j].equals("mib"))
		                            l= Math.max(2,l);
		                        else if (N[j].equals("sib"))
		                                l= Math.max(1,l);
		}
		if (l > 0)
		    l=12-l;

		k=Math.max(k,l);    
		String [] GM={"do", "sol", "re", "la", "mi", "si", "fa#", "reb","lab", "mib","sib","fa"};
		return GM[k];


	}

}
