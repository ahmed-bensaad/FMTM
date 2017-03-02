function [ cinquieme ] = accompagnementMaj( ton, note )
%ne pas mettre # ou b avec la note
%associe un accord d'accompagnement à une note donné pour une tonnalité
%donné
note1= conversionMaj(ton, note);
acc= accompagnement(note1);
cinquieme= accordsMaj(acc, ton);

end

