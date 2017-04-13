package accompagnement;
import java.util.*;

public class Tempo {
	public static double estfreqautoco(double[] s,double fe)
	{
		ArrayList <Double> Autoco_s = new ArrayList <Double>() ;
		
		double moy_s = 0.0 ;
		for(int i = 0 ; i< s.length ; i++) moy_s = moy_s + s[i];
		moy_s = moy_s / s.length;
		
		for(int i = 0 ; i < s.length ;i++){   
			Autoco_s.add(0.0) ;
			for(int j = 0 ; i+j < s.length; j++){
				Autoco_s.set(i, (s[j] - moy_s) * (s[j+i] - moy_s) + Autoco_s.get(i));
			}
		}
		double R0 = Autoco_s.get(0);
		for(int i = 0 ; i< s.length; i++) Autoco_s.set(i, Autoco_s.get(i)/R0);
				/*System.out.println("Autoco_s["+ i +"] ="+ Autoco_s.get(i));*/



		ArrayList <Double> D1 = new ArrayList <Double>();
		ArrayList <Double> D2 = new ArrayList <Double>();
		ArrayList <Integer> Indmaxloc = new ArrayList <Integer>();

		int K;
		K = Autoco_s.size();

		int indml;
		indml = 0;

		for(int i = 0; i < K - 1; i++){
			D1.add(0.0);
			D1.set(i, Autoco_s.get(i + 1) - Autoco_s.get(i));
			D1.set(i, Math.signum(D1.get(i)));
			/*System.out.println("D1["+ i +"] ="+ D1.get(i));*/
		}


		for(int i = 0; i < K - 2; i++){
			D2.add(0.0);
			D2.set(i, D1.get(i + 1) - D1.get(i));
			/*System.out.println("D2["+ i +"] ="+ D2.get(i));*/
		}


		int k = 0;
		ArrayList <Integer> Indcand = new ArrayList<Integer>();

		for(int i = 0; i < K-2; i++){
			if(D2.get(i) == -2)

			{ 
				Indcand.add(0);
				Indcand.set(k, i + 1);
				/*System.out.println("Indcand["+ k +"] ="+ Indcand.get(k));*/
				k = k + 1;

			}
		}

		int bsup;
		int binf;
		double m;
		int P = K/200;
		for(int i = 0; i < Indcand.size(); i++)
		{
			binf= Math.max(Indcand.get(i) - P,0 );
			bsup= Math.min(Indcand.get(i) + P,K );
			double[] Support_loc = new double[bsup - binf];
		 
			for(int j = 0; j < bsup-binf; j++)
			{
				Support_loc[j] = Autoco_s.get(binf + j);
				/*System.out.println("Support_loc["+ j +"] ="+ Support_loc[j]);*/
			}
		 
			m = 0;
		 
			for(int l = 0 ;l < bsup - binf ; l++)
			{
				if(Support_loc[l]>m)
				{
					m = Support_loc[l];
				}
				/*System.out.println(m);*/
			}
		 
		 
			if(Autoco_s.get(Indcand.get(i)) == m)
			{
				Indmaxloc.add(0);
				Indmaxloc.set(indml, Indcand.get(i));
				System.out.println("Indmaxloc["+ i +"] ="+ Indmaxloc.get(i));
				indml = indml + 1;
			}
		}


		int imax;
		imax = Indmaxloc.get(0);
		double To ;
		double fo ; 
		To =  (imax)/fe;
		fo = 1.0/To;
		return fo;
		}
	}


