function []=test()
    t=[0:0.1:8];
    y=[0:0.1:8];
    n=length(y);
    
    for i=1:n
        y(i)=enveloppe_basse(1,t(i),0.01);
    end
    
    plot(t,y);
endfunction
