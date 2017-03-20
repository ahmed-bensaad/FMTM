package tonalité;

public class Basse {

	public static double[] jouer_note(double fréquence, double durée,int d)
	{
		double e =0;
		int Fe = 44100;
		double Te = 1.0/Fe;
		// Calculate the number of frames required for specified duration
		int numFrames = (int) (durée * Fe);
		
		double[] m = new double[numFrames];
		
		for(int i=0;i<numFrames;i++)
		{
			m[i]=d*fréquence;
		}
		
		double[] f = new double[numFrames];
		for(int i=0;i<numFrames;i++)
		{
			f[i]=fréquence+e;
		}
		double[] f2 = new double[numFrames];
		for(int i=0;i<numFrames;i++)
		{
			f2[i]=4*fréquence+e;
		}
		double[] y1 = OscFm.jouer(f, durée, m);
		double[] y2 = OscFm.jouer(f2, durée, m);
		double[] y3 = new double[numFrames];
		
		for(int i = 0 ; i< numFrames;i++)
		{
			double t=Te*i;
			y1[i]= y1[i]*Enveloppe.enveloppe_basse(durée,t,1);
			y2[i]= y2[i]*Enveloppe.enveloppe_basse(durée,t,1);
			y3[i]=fréquence+y1[i]+y2[i];
		}
		
		double[] m2 = new double[numFrames];
		
		for(int i=0;i<numFrames;i++)
		{
			m2[i]=1;
		}
		double[] y4 =OscFm.jouer(y3, durée, m2);
		for(int i=0;i<numFrames;i++)
		{
			y4[i]=y4[i]*Enveloppe.enveloppe_basse(durée, i*Te, 1);
		}
		
		return y4;
	}
	
	public static double[] jouer(double[] N, double[] R,int d)
	{
		double fe =44100;
		double duration = 0;
		for(int i = 0 ; i<R.length;i++)
		{
			duration=duration+R[i];
		}
		
		int numFrames =  (int) (duration*fe); 

		double[] s = new double[numFrames];
		int k =0;
		for(int i =0; i<N.length;i++)
		{
			double[] y=jouer_note(N[i],R[i],d);
			
			for(int j =0; j<y.length;j++)
			{
				s[k]=y[j];
				k++;
			}
			
		}
		return s;
	}
}


/*
function [y4]=basse(fp,Fe,d,m0)//même paramètre que pour la trompette
    
    //shéma du piano/guitare FM (instrument à voie libre)
    
    e=0;
    
      
    Te=1/Fe; // temps echantillonage
    
    x=[0:Te:d]; //axe temps
   
    m=[0:Te:d];   // amplitude constante au cours du temps des oscillateurs modulants
    n=length(m);
    for i=1:n
        m(i)= m0;
    end
       
    f=[0:Te:d];   
    for i=1:n
       f(i)= fp;
    end     
    y1=oscFM(f+e,m,Fe,d);      //premier oscillateur à fm=fp (environ)     
    y2=oscFM(4*f+e,m,Fe,d);     //deuxième à fm=4*fp (environ)
    
    for i = 1:n
        y1(i)=y1(i)*enveloppe_basse(d,x(i),1);   // ajout des enveloppe sur les oscillateurs (la même que sur le signal total)
        y2(i)=y2(i)*enveloppe_basse(d,x(i),1);
    end
    
    y3=fp+y1+y2;
    
    m2=[0:Te:d];   // amplitude constante au cours du temps du signal total
    n=length(m);
    for i=1:n
        m2(i)= 1;
    end
    
    y4=oscFM(y3,m2,Fe,d);
    
    for i=1:n
        y4(i)=y4(i)*enveloppe_basse(d,x(i),1);     //ajout de l'enveloppe sur le signal total
    end
    
    
    
endfunction





*/