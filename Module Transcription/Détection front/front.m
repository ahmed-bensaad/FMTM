function [result] = front(x,fe,Npoints,prominence)

% Cette fonction renvoie la liste des indices des débuts de notes, et
%fait un tracé superposé du signal, de son enveloppe et de ses fronts. 
% x : signal à analyser 
% fe : fréquence d'échantillonage de ce signal 
% Npoints : nombre de points à utiliser pour la dérivée sur plusieurs points
% prominence : seuil de proéminence des pics à utiliser pour la fonction de
%détection


% On se réduit à un canal si le signal est stéréo
x = x(:,1) ; 

% On trace le signal renormalisé
[M I] = max(x) ; 
x_norm = x/M ; 
temporel(x_norm,fe,'-b')
hold on 

% Nombre de points du signal
N = length(x) ; 

% Filtrage du signal par la formule y(n) = a*y(n-1) + x(n)^2 
y = filter(1,[1 -0.99],x.*x) ; 

% Tracé de l'enveloppe énergétique renormalisée
env = sqrt(0.01*y) ; 
[M I] = max(env) ; 
env_norm  = env/M ; 
temporel(env_norm(1:N),fe,'-g') ;

% On dérive le signal avec Npoints points autour de chaque valeur : on obtient
%les fronts montants
fr =  filter([ones(1,Npoints) ones(1,Npoints)*(-1)],1,y) ;

% Tracé de la partie positive des fronts montants 
fr = max(0,fr) ; 
[M I] = max(fr);
fr_norm = fr/M ; 
temporel(fr_norm,fe,'-r') 
hold off

% on cherche les maximums locaux dans le vecteur de l'enveloppe dérivée
[peaks,locs,w,p] = findpeaks(fr_norm) ; 

% on renvoie la liste des positions des fronts dont la proéminence est
%supérieure à prominence.
result = locs(p>prominence)' ; 



end

