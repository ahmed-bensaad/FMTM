package tonalité;


public class Enveloppe {
	
	public static double enveloppe_ADSR(double t, double d, double m)
	{
		double a = 1.0/24;
		double d2 = 1.0/24;
		double s = d - 3.0/16;
		double r = 1.0/8;

		if(0<=t && t<a)
		{
			return 1.0/a*t;
		}
		else if(a<=t && t<(a+d2))
		{
			return 1.0-(1.0-m)/d2*(t-a);
		}
		else if((a+d2)<=t && t<(a+d2+s))
		{
			return m;
		}
		else
		{
			return m-m/r*(t-(a+d2+s));
		}
		
	}
	
	public static double enveloppe_vibrato(double t,double d)
	{
		double a =1.0/24;
		double d2=1.0/24;
		double s = d -3.0/16;
		double r=1.0/8;
		double m=0.01;
		double u = 1.0/24+1.0/24+(d-3.0/16)/2.0;
		
		if(0<=t && t<a+d2)
		{
			return 0;
		}
		else if((a+d2<=t) && t<1+a+d2)
		{
			return m*t-(a+d2)*m;
		}
		else
		{
			return m;
		}
	}
	public static double enveloppe_piano(double t, double duree){
		double a = 0.02;
		double d = 0.02;
		double s = 0.75;
		double r = 0.03;

		if(0<=t && t<a)
		{
			return 1.0/a*t;
		}
		else if(a<=t && t<(a+d))
		{
			return 1.0-(1.0-s)/d*(t-a);
		}
		else if((a+d)<=t && t<(duree))
		{
			return s;
		}
		else
		{
			return s-s/r*(t-duree);
		}
	}
	
	
	public static double enveloppe_basse(double d,double t,double m)
	{
		double a = 1.0/48;
		double c = 1.0/2;
		double b = d-a-c;
		double v = 3.0;
		double v2 = 0.00000005;
		double t1 = a;
		double t2=a+b;
		double t3 = a+b+c;
		
		if(0<=t && t<t1)
		{
			return m/a*t;
		}
		else if(t1<=t && t<t2)
		{
			if(m-v*m*(t-t1)>0)
			{
				return (m-v*m*(t-t1));
			}
			else
			{
				return 0;
			}
		}
		else
		{
			if ((m-v*m*(t-t1)>0)){
				if(m*(1-v*(t-t1))-v2*m*t>0)
				{
	                    return (m*(1-v*(t-t1))-v2*m*t);
				}        
	            else
	            {
	               return 0;
	            }
			}
            else
            {
            	return 0;
            }
		}
	}
}


/*
function [y]=enveloppe_basse(d,t,m)// d = durï¿½e, m=amplitude
    a=1/48;
    c=1/2;
    b=d-a-c;
    v=3;//vitesse de chute
    v2=0.00000005;//2ï¿½me vitesse de chute
    t1=a;
    t2=a+b;
    t3=a+b+c;
    
    if (0<=t) & (t<t1)
        y=(m/a*t);
    elseif (t1<=t) & (t<t2)
        if(m-v*m*(t-t1)>0)
            y=(m-v*m*(t-t1));
        else
            y=0;
        end;
    else
        if (m-v*m*(t-t1)>0)
            if(m*(1-v*(t-t1))-v2*m*t>0)
                y=(m*(1-v*(t-t1))-v2*m*t);
            else
                y= 0
            end;
        else
            y=0;
        end;
    end;
    
 
    
    
endfunction

 
 function [y]=enveloppe_vibrato(t,d) //demi-trapï¿½ze (marche)
    a=1/24;
    d2=1/24;
    s=d-3/16;
    r=1/8;
    
    m=0.01;
    u=1/24+1/24+(d-3/16)/2;
    
    
    if (0<=t) & (t<a+d2)
        y=0;
    elseif (a+d2<=t) & (t<(1+a+d2))
        y=m*t-(a+d2)*m;
    else
        y=m;   
    end;
endfunction

 
 
 
 
 function [y]=enveloppe_ADSR(t,d,m)
    a=1/24;
    d2=1/24;
    s=d-3/16;
    r=1/8;
    
    //m=0.5;
    
    if (0<=t) & (t<a)
        y=1/a*t;
    elseif (a<=t) & (t<(a+d2))
        y=1-(1-m)/d2*(t-a);
    elseif ((a+d2)<=t) & (t<(a+d2+s))
        y=m;
    else
        y=m-m/r*(t-(a+d2+s));   
    end;
endfunction
 

 
 */