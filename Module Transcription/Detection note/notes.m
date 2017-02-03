function [result] = notes(x,fe,Npoints,prominence)
% Fonction qui renvoie un vecteur à deux colonnes : une pour les instants
% de début des notes, l'autre pour la fréquence de la note jouée correspondante

% On appelle la fonction front sur le signal
fr = front(x,fe,L) ; 

% Nombre d'attaques détectées dans le signal
N = length(fr) ; 

% Liste contenant les fréquences de chaque note détectée
F = [] ;

% On applique la fonction de détection de fréquence fondamentale à la
% partie du signal contenue entre deux indices successifs
for i=1:N-1
    note = fondamental(x(fr(i):fr(i+1)),fe) ;
    F = [F note] ; 
end
F = [F fondamental(x(fr(N):end),fe)] ; 
result = [fr*(1/fe) ; F]'
end

