function [y4]=test(x)
    fs=5*440;
    ts=1/fs;
    x=[0:ts:2];
    
    y4=[0:ts:2];
    n=length(y4);
    for i=1:n
        y4(i)=enveloppe_ADSR(x(i));
    end
endfunction
