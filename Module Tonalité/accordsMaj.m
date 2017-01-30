function [ notes ] = accordsMaj( acc, ton )
% acc et le résultat de la fonction accompagnement
G={'do', 'ré', 'mi', 'fa', 'sol', 'la', 'si'};
GM={'do', 'ré', 'mi', 'fa', 'sol', 'la', 'si', ' ', 'réb','mib', 'fa#', ' ', ' ', 'sib'};
j= mod(find(ismember(GM,ton)),7)
i= mod(acc+j-2,7)+1
notes={G{i},G{mod(i+1,7)+1},G{mod(i+3,7)+1}};



end

