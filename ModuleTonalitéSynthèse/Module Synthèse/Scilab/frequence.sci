function [ f ] = frequence( oct, n)
    
G={'do', 'do#', 're', 're#', 'mi', 'fa', 'fa#', 'sol', 'sol#', 'la', 'la#', 'si'};
[nb, loc] = members(G,[n], "last");
i = find(loc);
disp(i)
f=2^((oct*12+i-46)/12)*440;

endfunction
