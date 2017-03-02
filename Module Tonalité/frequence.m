function [ f ] = frequence( oct, n)
G={'do', 'do#', 're', 're#', 'mi', 'fa', 'fa#', 'sol', 'sol#', 'la', 'la#', 'si'};
i = find(ismember(G, n))
f=2.^((oct.*12+i-46)./12)*440

end

