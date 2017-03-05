package Body.Test_audio.src;

public class Note {

	private double frequence;
	private double duree;
	private String note;
	private int octave;
	private String rythme;
	
	public Note(double frequence,double duree)
	{
		
	}
	
	public Note(String note, int octave, String rythme, double Tempo)
	{	
		this.note = note;
		this.rythme = rythme;
		this.octave=octave;
		
		double t0 = 0.25 * 60 /Tempo;					// conversion rythme -> dur?e , ex : noire -> 1s
		String[] G ={"dc", "c", "cp","n","n+dc","np","vide","b"};
		
		int index=0;
		for(int j =0 ; j<8;j++)
		{
			if(G[j].equals(rythme))
			{
				index=j;
			}
		}
		this.duree=t0*(index+1);
		
		//conversion note -> fr?quence
		
		String[] H =  {"do", "do#", "re", "re#", "mi", "fa", "fa#", "sol", "sol#", "la", "la#", "si"};
		
		index =0;
		for(int j =0;j<H.length;j++)
		{
			if(H[j].equals(note))
			{
				index = j+1;
			}
		}

		this.frequence= Math.pow(2,((octave-3)+((double)(index-10))/12))*440;
		
/*G={'do', 'do#', 're', 're#', 'mi', 'fa', 'fa#', 'sol', 'sol#', 'la', 'la#', 'si'};
[nb, loc] = members(G,[n], "last");
i = find(loc);
disp(i)
f=2^((oct*12+i-46)/12)*440;
*/
		
	}
	
	public double getfrequence()
	{
		return frequence;
	}
	public double getduree()
	{
		return duree;
	}
	public String getnote()
	{
		return note;
	}
	public int getoctave()
	{
		return octave;
	}
	public String getrythme()
	{
		return rythme;
	}
	
}
