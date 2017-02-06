function [result] = front(x,fe,Npoints,prominence)

% Cette fonction renvoie la liste des indices des d�buts de notes, et
%fait un trac� superpos� du signal, de son enveloppe et de ses fronts. 
% x : signal �analyser 
% fe : fr�quence d'�chantillonage de ce signal 
% Npoints : nombre de points � utiliser pour la d�riv�e sur plusieurs points
% prominence : seuil de pro�minence des pics � utiliser pour la fonction de
%d�tection


% On se r�duit �un canal si le signal est st�r�o
x = x(:,1) ; 

% On trace le signal renormalis�
[M I] = max(x) ; 
x_norm = x/M ; 
temporel(x_norm,fe,'-b')
hold on 

% Nombre de points du signal
N = length(x) ; 

% Filtrage du signal par la formule y(n) = a*y(n-1) + x(n)^2 
y = filter(1,[1 -0.99],x.*x) ; 

% Trac� de l'enveloppe �nerg�tique renormalis�e
env = sqrt(0.01*y) ; 
[M I] = max(env) ; 
env_norm  = env/M ; 
temporel(env_norm(1:N),fe,'-g') ;

% On d�rive le signal avec Npoints points autour de chaque valeur : on obtient
%les fronts montants
fr =  filter([ones(1,Npoints) ones(1,Npoints)*(-1)],1,y) ;

% Trac� de la partie positive des fronts montants 
fr = max(0,fr) ; 
[M I] = max(fr);
fr_norm = fr/M ; 
temporel(fr_norm,fe,'-r') 
hold off

% on cherche les maximums locaux dans le vecteur de l'enveloppe d�riv�e
[peaks,locs,w,p] = findpeaks(fr_norm) ; 

% on renvoie la liste des positions des fronts dont la pro�minence est
%sup�rieure � prominence.
result = locs(p>prominence)' ; 



end

