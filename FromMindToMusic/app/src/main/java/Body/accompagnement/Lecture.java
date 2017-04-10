package Body.accompagnement;
import java.lang.Math;
public class Lecture {


	public static String[] note(double f)
	{
		double x=Math.log(f/440)/Math.log(Math.pow(2,(1/12)));
		int y= (int) (Math.round(x) + 45) ;
		int oct = Math.floorDiv(y,12);
		int m = y% 12;
		String[] G={"do", "do#", "re", "re#", "mi", "fa", "fa#", "sol", "sol#", "la", "la#", "si"};
		String n = G[m];
		String[] d =  {n, Integer.toString(oct)};
		return d;
	}
	public static String [][] frequenceToNote(double f []){
		int n = f.length;
		String [][] notes = new String [n][2];
		for(int i =0; i<n; i++){
			notes[i]= note(f[i]);
		}
		return notes;

	}
	public static double noteToFrequence(String note, String oct){
		int o = Integer.parseInt(oct);
		int l=0;
		String[] G={"do", "do#", "re", "re#", "mi", "fa", "fa#", "sol", "sol#", "la", "la#", "si"};
		for(int j=0 ; j<14 ; j++)
		{
			if(G[j].equals(note)){
				l=j;
			}
		}
		double r = Math.pow(2, 1/12);
		double f = 440*Math.pow(r, o*12+l-45);
		return f;

	}
	public static String concateneNnT(String note, String ton){
		String[] n={"do", "do#", "re", "re#", "mi", "mi#", "fa", "fa#", "sol", "sol#", "la", "la#", "si"};

		String[] GM ={"do", "sol", "re", "la", "mi", "si", "fa#", "reb", "lab", "mib", "sib", "fa"};
		String[] arm={"fa", "do", "sol", "re", "la", "mi", "si"};
		int i=-1;
		for(int j=0 ; j<14 ; j++)
		{
			if(GM[j].equals(ton)){
				i=j-1;
			}
		}

		String notef="";
		if (i<=6){
		    String []armf= new String[i];
		    for(int m=0; m<i; m++){
		    	armf[m] = arm[m];
		    }
		    int h = 0;
		    for(int k=0 ; k<i+1 ; k++)
			{
				if(armf[k].equals(note)){
					h=1;
				}
			}
		    if (h==1){
		        notef=note+"#";
		    } else {
		    	notef=note;
		    }

		}


		if (i>6){
		    i=12-i;
		    String[] armf= new String[i];
		    for(int m=0; m<i; m++){
		    	armf[m]=arm[7-m];
		    }
		    int h = 0;
		    for(int k=0 ; k<i+1 ; k++)
			{
				if(armf[k].equals(note)){
					h=1;
				}
			}
		    if (h==1){
		        notef=note+"b";
		    } else {
		    	notef=note;
		    }
		}
		return notef;
	}
	public static String tonaliteMaj(String[][] N){
		int k=0;
		int l=0;
		for(int i = 0; i< N.length; i++){
		    if (N[i][0].equals("si#"))
		        k=Math.max(7,k);
		    else if (N[i][0].equals("mi#"))
		            k =Math.max(6,k);
		        else if (N[i][0].equals("la#"))
		                k=Math.max(5,k);
		            else if (N[i][0].equals("re#"))
		                    k=Math.max(4,k);
		                else if (N[i][0].equals("sol#"))
		                        k= Math.max(3,k);
		                    else if (N[i][0].equals("do#"))
		                            k= Math.max(2,k);
		                        else if (N[i][0].equals("fa#"))
		                                k= Math.max(1,k);
		}
		for (int j=0; j<N.length; j++){
			if (N[j][0].equals("fab"))
		        l=Math.max(7,l);
		    else if (N[j][0].equals("dob"))
		            l =Math.max(6,l);
		        else if (N[j][0].equals("solb"))
		                l=Math.max(5,l);
		            else if (N[j][0].equals("reb"))
		                    l=Math.max(4,l);
		                else if (N[j][0].equals("lab"))
		                        l= Math.max(3,l);
		                    else if (N[j][0].equals("mib"))
		                            l= Math.max(2,l);
		                        else if (N[j][0].equals("sib"))
		                                l= Math.max(1,l);
		}
		if (l > 0)
		    l=12-l;

		k=Math.max(k,l);
		String [] GM={"do", "sol", "re", "la", "mi", "si", "fa#", "reb","lab", "mib","sib","fa"};
		return GM[k];


	}

}
