package tonalité;


public class OscFm {
	
	public static double[] jouer(double[] f, double d, double[] m) // f -> fr�quence(t) en Hz, m -> amplitude(t), d -> dur�e en secondes
	{
		int sampleRate = 44100;		// Samples per second
		double Te = 1.0/sampleRate;
		
		// Calculate the number of frames required for specified duration
		int numFrames = (int) (d * sampleRate);
		
		double[] s = new double[numFrames];
		double[] phi = new double[numFrames];
		
		phi[0]=0;
		for(int i = 1; i<numFrames;i++)
		{
			phi[i]=phi[i-1]+2*Math.PI*Te*f[i];
		}
		
		for(int i = 0; i<numFrames;i++)
		{
			s[i]=m[i]*Math.cos(phi[i]);
		}
		
		return s;
	} 
	
}

/*  code scilab
//on veut retourner s(t)=m(t)cos(phi(t)) avec phi(t)=2*pi*(int�grale entre 0 et t de f(t) + f(0)) cf poly. synth�se FM
pi=3.14159;

Te= 1/Fe; //temps �chantillonage

x=[0:Te:d]; // axe des temps

phi=[0:Te:d]; // definission de phi (cf.poly., m�thode des rectangles)
phi(1)=0;
n=length(phi);
for i=2:n
    phi(i)= phi(i-1) + 2*pi*Te*f(i);
end


s=[0:Te:d];
for i=1:n
   s(i)= m(i)*cos(phi(i));
end

*/