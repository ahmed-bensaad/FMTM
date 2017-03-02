function [ output_args ] = frequentiel(x,fe)
% trace le spectre d'un échantillon audio

% Nombre d'échantillon du signal 
N = length(x) ; 

% Pour augmenter la précision de la fft qui suit, on la fait sur plus de points que le nombre d'échantillons
% Pour augmenter l'efficacité, on se débrouille pour prendre une puissance
% de 2 
L = 2^nextpow2(N*100) ; 

% On calcule la transformée de Fourier. Le zero-padding est fait
% automatiquemeent 
X = fft(x,L) ;  

% On repère le max de la TF 
[M,I]  = max(abs(X)) ; 

% On renormalise l'axe des abscisses et on supprime la partie qui
% correspond au sym�trique
f = (1:floor(L/2))*(fe/L) ; 

% On normalise les ordonn�es 
Y = abs(X)/M ;

% On supprime la partie qui correspond au sym�trique (pour les ordonn�es)
Y = Y(1:floor(L/2)) ; 

% On trace et on l�gende
plot(f,Y) ; 
title('Spectre de x(t)')
xlabel('f(Hz)')
ylabel('abs(X(f))')





end

