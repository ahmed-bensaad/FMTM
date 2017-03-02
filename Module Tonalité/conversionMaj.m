function [ note1 ] = conversionMaj(ton, note)
G={'do', 're', 'mi', 'fa', 'sol', 'la', 'si'}
GM={'do', 're', 'mi', 'fa', 'sol', 'la', 'si', ' ', 'reb','mib', 'fa#', ' ', ' ', 'sib'};
i = find(ismember(GM, ton));
j = find(ismember(G, note));
note1=mod(j-i,7)+1;
end

