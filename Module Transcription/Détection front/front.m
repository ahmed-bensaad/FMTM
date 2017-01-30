function [result] = front(x,fe,L)
%FRONT Cette fonction renvoie la liste des indices des débuts de notes, et
%fait un tracé superposé du signal, de son enveloppe, de ses fronts 
% x : signal à analyser 
% fe : fréquence d'échantillonage de ce signal 
% L : nombre de points à utiliser pour la dérivée sur plusieurs points

% On se réduit à un canal si le signal est stéréo
x = x(:,1) ; 

% On trace le signal renormalisé
[M I] = max(x) ; 
x_norm = x/M ; 
temporel(x_norm,fe,'-b')
hold on 

% Nombre de points du signal
N = length(x) ; 

% Filtrage du signal par la formule y(n) = a*y(n-1) + x²(n) 
y = filter(1,[1 -0.99],2*(x.*x)) ; 

% Tracé de l'enveloppe énergétique renormalisée
env = sqrt(0.01*y) ; 
[M I] = max(env) ; 
env_norm  = env/M ; 
temporel(env_norm(1:N),fe,'-g') ;

% On dérive le signal avec L points autour de chaque valeur : on obtient
% les fronts montants
fr =  filter([ones(1,L) ones(1,L)*(-1)],1,y) ;

% Tracé de la partie positive des fronts montants 
fr = max(0,fr) ; 
[M I] = max(fr);
fr_norm = fr/M ; 
temporel(fr_norm,fe,'-r') 
hold off


% Fonction de détection : on note les indices pour lesquels on dépasse le seuil
% 0.1 en montant, et lorsque cela se produit, on incrémente jusqu'à
% repasser par 0 en descendant. 
% Remarque : on fait cela sur la courbe de fronts normalisée, Donc en
% réalité le 0.1 correspond à 0.1*max(fr) ie. c'est bien un seuil qui
% dépend de l'entrée donnée.
R = [] ; 
i = 1 ; 
while i < length(fr_norm)
    if fr_norm(i) > 0.1 
        R = [R i] ;  
        while fr_norm(i) > 0 
           i = i+1 ;  
        end 
    end 
    i = i+1 ;
end 
    
result = R ; 

end

