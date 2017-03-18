package tonalit�;




public class Trompette {

	public static double[] jouer_note(double fr�quence, double dur�e)
	{
		double fv = 4; // fr�quence vibrato
		double a =0.01; // excursion du vibrato
		int Fe = 8000;
		double Te = 1.0/Fe;
		// Calculate the number of frames required for specified duration
		int numFrames = (int) (dur�e * Fe);
		
		double[] m = new double[numFrames];
		
		for(int i=0;i<numFrames;i++)
		{
			m[i]=5*fr�quence;
		}
		
		double[] f = new double[numFrames]; 
		
		for(int i=0;i<numFrames;i++)
		{
			f[i]=fr�quence;
		}
		
		double[] x1 = OscFm.jouer(f, dur�e, m);
		
		
		for(int i=0;i<numFrames;i++)
		{
			double t= i*Te;
			x1[i]=x1[i]* Enveloppe.enveloppe_ADSR(t, dur�e,0.5)+fr�quence;
		}
		
		for(int i=0;i<numFrames;i++)
		{
			double t= i*Te;
			x1[i]=x1[i]*(1+Enveloppe.enveloppe_vibrato(t, dur�e)*Math.sin(2*Math.PI*fv*t));
		}
		
		double[] m2 = new double[numFrames]; 
		
		for(int i=0;i<numFrames;i++)
		{
			m2[i]=1;
		}
		for(int i=0;i<numFrames;i++)
		{
			double t= i*Te;
			m2[i]=m2[i]*(1+Enveloppe.enveloppe_vibrato(t, dur�e)*Math.sin(2*Math.PI*fv*t));
		}
		
		double[] s = OscFm.jouer(x1, dur�e, m2);
		
		for(int i=0;i<numFrames;i++)
		{
			double t = i*Te;
			s[i]=s[i]*Enveloppe.enveloppe_ADSR(t, dur�e,0.5);
		}
		
		return s;
	}
	
	public static double[] jouer(double[] N, double[] R)
	{
		double fe =8000;
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
			double[] y=jouer_note(N[i],R[i]);
			
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

function [s]= trompette(fp,fm,Fe,d,m0) // fp -> hauteur, d-> dur�e,  m0-> amplitude du sinus modulant 
    
   Te=1/Fe; // temps echantillonage
   
   x=[0:Te:d]; //axe temps
   
   m=[0:Te:d];   // amplitude constante au cours du temps
   n=length(m);
   for i=1:n
       m(i)= m0;
   end
    
   f=[0:Te:d];   // frequence de l'oscillateur FM constante au cours du temps
   for i=1:n
       f(i)= fm;
   end 
   
   x1 = oscFM(f,m,Fe,d);

   for i=1:n
       x1(i)= x1(i)+fp;
   end  
   
   m2=[0:Te:d];   // amplitude constante au cours du temps de s
   n=length(m2);
   for i=1:n
       m2(i)= 1;
   end

   s=oscFM(x1,m2,Fe,d);
  
  for i=1:n
      s(i)=s(i)*enveloppe_ADSR(x(i)); 
  end
  B=0;
  //s=cuivrage(s,B,1/Fe);
  //plot(s);
    
   //sound(s,Fe);
    
   // amelioration possible => Jitter, cuivrage, sh�ma plus complexe de l'instrument � vent
endfunction




 */