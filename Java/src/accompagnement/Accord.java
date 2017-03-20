package accompagnement;

public class Accord {

	public static String[][] accord(String ton, String note, String oct){
		int n = conversionMaj(ton, note);
		int acc = accompagnement(n);
		String[][] accord = accordMaj(acc,ton,oct);
		accord[0][0]=Lecture.concateneNnT(accord[0][0], ton);
		accord[1][0]=Lecture.concateneNnT(accord[1][0], ton);
		accord[2][0]=Lecture.concateneNnT(accord[2][0], ton);
		return accord;
	}
	public static int conversionMaj(String note, String ton){
		String[] G={"do", "re", "mi", "fa", "sol", "la", "si"};
		String[] GM={"do", "re", "mi", "fa", "sol", "la", "si", " ", "reb","mib", "fa#", " ", " ", "sib"};
		int k =0;
		int l =0;
		for(int i =0 ; i<7;i++)
		{
			if(G[i].equals(note))
			{
				k=i;
			}
		}
		for(int j=0 ; j<14 ; j++)
		{
			if(GM[j].equals(ton)){
				l=j;
			}
		}
		int m=((k-l) % 7);
		return m;

	}

	public static int accompagnement(int n)
	{
		int acc =0;
		switch (n) {
		case 1: acc=0;
		break;
		case 2: acc=4;
		break;
		case 3: acc=0;
		break;
		case 4: acc=3;
		break;
		case 5: acc=4;
		break;
		case 6: acc=3;
		break;
		case 7: acc=4;
		break;

		}
		return acc;
	}

	public static String[][] accordMaj(int acc, String ton, String oct)
	{
		String[] G={"do", "ré", "mi", "fa", "sol", "la", "si"};
		String[] GM={"do", "ré", "mi", "fa", "sol", "la", "si", " ", "réb","mib", "fa#", " ", " ", "sib"};
		int l =0;

		for(int j=0 ; j<14 ; j++)
		{
			if(GM[j].equals(ton)){
				l=j%7;
			}
		}
		int o1 = (acc+l)/7;
		int o2 = (acc+l+2)/7;
		int o3 = (acc+l+4)/7;
		int k = (acc+l)%7;
		String[][] notes = {{G[k], Integer.toString(Integer.parseInt(oct)+o1)}, {G[(k+2)%7], Integer.toString(Integer.parseInt(oct)+o2)}, {G[(k+4)%7], Integer.toString(Integer.parseInt(oct)+o3)}};
		return notes;
	}

}
