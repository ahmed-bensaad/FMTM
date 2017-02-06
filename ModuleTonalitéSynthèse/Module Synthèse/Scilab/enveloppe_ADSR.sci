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
