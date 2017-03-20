package tonalit�;

public class Basse {

	public static double[] jouer_note(double fr�quence, double dur�e,int d)
	{
		double e =0;
		int Fe = 44100;
		double Te = 1.0/Fe;
		// Calculate the number of frames required for specified duration
		int numFrames = (int) (dur�e * Fe);
		
		double[] m = new double[numFrames];
		
		for(int i=0;i<numFrames;i++)
		{
			m[i]=d*fr�quence;
		}
		
		double[] f = new double[numFrames];
		for(int i=0;i<numFrames;i++)
		{
			f[i]=fr�quence+e;
		}
		double[] f2 = new double[numFrames];
		for(int i=0;i<numFrames;i++)
		{
			f2[i]=4*fr�quence+e;
		}
		double[] y1 = OscFm.jouer(f, dur�e, m);
		double[] y2 = OscFm.jouer(f2, dur�e, m);
		double[] y3 = new double[numFrames];
		
		for(int i = 0 ; i< numFrames;i++)
		{
			double t=Te*i;
			y1[i]= y1[i]*Enveloppe.enveloppe_basse(dur�e,t,1);
			y2[i]= y2[i]*Enveloppe.enveloppe_basse(dur�e,t,1);
			y3[i]=fr�quence+y1[i]+y2[i];
		}
		
		double[] m2 = new double[numFrames];
		
		for(int i=0;i<numFrames;i++)
		{
			m2[i]=1;
		}
		double[] y4 =OscFm.jouer(y3, dur�e, m2);
		for(int i=0;i<numFrames;i++)
		{
			y4[i]=y4[i]*Enveloppe.enveloppe_basse(dur�e, i*Te, 1);
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
function [y4]=basse(fp,Fe,d,m0)//m�me param�tre que pour la trompette
    
    //sh�ma du piano/guitare FM (instrument � voie libre)
    
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
    y1=oscFM(f+e,m,Fe,d);      //premier oscillateur � fm=fp (environ)     
    y2=oscFM(4*f+e,m,Fe,d);     //deuxi�me � fm=4*fp (environ)
    
    for i = 1:n
        y1(i)=y1(i)*enveloppe_basse(d,x(i),1);   // ajout des enveloppe sur les oscillateurs (la m�me que sur le signal total)
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