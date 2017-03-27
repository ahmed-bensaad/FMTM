package tonalité;


public class Piano {
	public static double[] jouer_note(double frequence, double duree, double a1, double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9)
	{
		double alpha = 0.9;
		double sigmaalpha = 0.1;
		double sigmaphi = 10.0;
		double phi = 10.0;
		double fm = 5.0;
		double e =0;
		int Fe = 44100;
		double Te = 1.0/Fe;
		double f1= frequence/2;
		double f2= frequence;
		double f3= 3*frequence/2;
		double f4= 2*frequence;
		double f5= 3*frequence;
		double f6= 4*frequence;
		double f7= 5*frequence;
		double f8= 6*frequence;
		double f9= 8*frequence;
		// Calculate the number of frames required for specified duration
		int numFrames = (int) (duree * Fe);
		
		double[] y = new double[numFrames];
		double[] phi = new double[numFrames];
		double[] alpha = new double[numFrames];
		for(int i=0;i<numFrames;i++)
		{
			alpha[i]= alpha = sigmaalpha*Math.cos(2*Math.PI*fm*i*Te);
			phi[i]= phi = sigmaphi*Math.cos(2*Math.PI*fm*i*Te);
			y[i]=alpha[i]*(a1*Math.cos(2*Math.PI*f1*i*Te+phi[i])+a2*Math.cos(phi[i]+2*Math.PI*f2*i*Te)+a3*Math.cos(phi[i]+2*Math.PI*f3*i*Te)+a4*Math.cos(phi[i]+2*Math.PI*f4*i*Te)+a5*Math.cos(phi[i]+2*Math.PI*f5*i*Te)+a6*Math.cos(phi[i]+2*Math.PI*f6*i*Te)+a7*Math.cos(phi[i]+2*Math.PI*f7*i*Te)+a8*Math.cos(phi[i]+2*Math.PI*f8*i*Te)+a9*Math.cos(phi[i]+2*Math.PI*f9*i*Te));
		}
		
//		double[] f = new double[numFrames];
//		for(int i=0;i<numFrames;i++)
//		{
//			f[i]=frequence+e;
//		}
//		double[] f2 = new double[numFrames];
//		for(int i=0;i<numFrames;i++)
//		{
//			f2[i]=4*frequence+e;
//		}
//		double[] y1 = OscFm.jouer(f, duree, m);
//		double[] y2 = OscFm.jouer(f2, duree, m);
//		double[] y3 = new double[numFrames];
//		
//		for(int i = 0 ; i< numFrames;i++)
//		{
//			double t=Te*i;
//			y1[i]= y1[i]*Enveloppe.enveloppe_basse(duree,t,1);
//			y2[i]= y2[i]*Enveloppe.enveloppe_basse(duree,t,1);
//			y3[i]=frequence+y1[i]+y2[i];
//		}
//		
//		double[] m2 = new double[numFrames];
//		
//		for(int i=0;i<numFrames;i++)
//		{
//			m2[i]=1;
//		}
//		double[] y4 =OscFm.jouer(y3, duree, m2);
		for(int i=0;i<numFrames;i++)
		{
			y[i]=y[i]*Enveloppe.enveloppe_piano(i*Te, duree);
		}
//		
//		return y4;
		return y
	}
	public static double[] jouer(double[] N, double[] R)
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
