function [ notef] = noteF( ton,note )
%UNTITLED Summary of this function goes here
n={'do', 'do#', 're', 're#', 'mi', 'mi#', 'fa', 'fa#', 'sol', 'sol#', 'la', 'la#', 'si'};

GM={'do', 'sol', 're', 'la', 'mi', 'si', 'fa#', 'reb', 'lab', 'mib', 'sib' 'fa'};
arm={'fa', 'do', 'sol', 're', 'la', 'mi', 'si'};

i = find(ismember(GM, ton))-1;
if i<=6
    armf=arm(1:i);
    if any(ismember(armf, note))
        notef=strcat(note,'#');
    else
        notef=note;
    end
end
if i>6
    i=12-i
    armf=arm(8-i:7)
    ismember(armf,note)
    if any(ismember(armf, note))
        a=8
        notef=strcat(note,'b');
    else
        notef=note;
    end
end    

end

