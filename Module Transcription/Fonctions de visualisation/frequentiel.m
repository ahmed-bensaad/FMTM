function [ output_args ] = frequentiel(x,fe)
% trace le spectre d'un Ã©chantillon audio

% Nombre d'Ã©chantillon du signal 
N = length(x) ; 

% Pour augmenter la prÃ©cision de la fft qui suit, on la fait sur plus de points que le nombre d'Ã©chantillons
% Pour augmenter l'efficacitÃ©, on se dÃ©brouille pour prendre une puissance
% de 2 
L = 2^nextpow2(N*100) ; 

% On calcule la transformÃ©e de Fourier. Le zero-padding est fait
% automatiquemeent 
X = fft(x,L) ;  

% On repÃ¨re le max de la TF 
[M,I]  = max(abs(X)) ; 

% On renormalise l'axe des abscisses et on supprime la partie qui
% correspond au symétrique
f = (1:floor(L/2))*(fe/L) ; 

% On normalise les ordonnées 
Y = abs(X)/M ;

% On supprime la partie qui correspond au symétrique (pour les ordonnées)
Y = Y(1:floor(L/2)) ; 

% On trace et on légende
plot(f,Y) ; 
title('Spectre de x(t)')
xlabel('f(Hz)')
ylabel('abs(X(f))')





end

