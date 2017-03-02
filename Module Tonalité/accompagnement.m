function [ acc ] = accompagnement(note1)
%associe a un numero d'une note dans une gamme le numéro de l'accord
%d'accopagnement
switch note1
    case 1
        acc=1;
    case 2
        acc=5;
    case 3
        acc=1;
    case 4
        acc=4;
    case 5
        acc=5;
    case 6
        acc=4;
    case 7
        acc=5;
end


end

