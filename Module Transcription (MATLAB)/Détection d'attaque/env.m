function [result] = env(x,fe,a)

% x : signal �analyser 
% fe : fr�quence d'�chantillonage de ce signal 


% On se r�duit �un canal si le signal est st�r�o
x = x(:,1) ; 

% On trace le signal renormalis�
[M I] = max(x) ; 
x_norm = x/M ; 
temporel(x_norm,fe,'-b')
hold on 


% Filtrage du signal par la formule y(n) = a*y(n-1) + x(n)^2 
y = filter(1,[1 -a],x.*x) ; 

% Trac� de l'enveloppe �nerg�tique renormalis�e
env = sqrt((1-a)*y) ; 
[M I] = max(env) ; 
env_norm  = env/M ; 
temporel(env_norm,fe,'-g') ;
result = env_norm ; 


