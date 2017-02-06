
function []= instru2(f0,t) //f0-> fréquence note, t-> durée note 
    
    a=1;
    b=1;
    c=1;
    
    fs=5*f0;
    ts=1/fs;
    x=[0:ts:t];
    epsilon=0;
    f0=f0+epsilon;
    pi=3.14159;
    y1=a*sin(2*pi*f0*x);
    y2=b*sin(2*pi*4*f0*x);
    y3=c*(y1+y2)+sin(2*pi*f0*x);
    
    y4=[0:ts:t];
    n=length(y4);
    for i=1:n
        y4(i)=enveloppe_ADSR(x(i))*sin(2*pi*y3(i)*x(i));
    end

    
    sound(y4,fs)
