function [ tonMin ] = majToMin( tonMaj )
%
GM={'do', 're', 'mi', 'fa', 'sol', 'la', 'si', 'reb','mib', 'fa#', 'sib'};
i = find(ismember(GM, tonMaj));
Gm={'la', 'si', 'do#', 're', 'mi', 'fa#', 'sol#', 'sib', 'do', 're#', 'sol'} 
tonMin=Gm{i}

end

