function [fondamental] = fondamental_Fourier(x,fe)
% Cette fonction renvoie la fréquence fondamentale d'un son donné 

% Nombre d'échantillon du signal 
N = length(x) ; 

% Pour augmenter la précision de la fft qui suit, on la fait sur plus de points que le nombre d'échantillons
% Pour augmenter l'efficacité, on se débrouille pour prendre une puissance
% de 2 
L = 2^nextpow2(N*100) ; 

% On calcule la transformée de Fourier. Le zero-padding est fait
% automatiquement 
X = fft(x,L) ;  

% On repère le max de la TF 
[M,I]  = max(abs(X)) ; 

% On renvoie la fréquence fondamentale à partir de la relation de
% proportionnalité indices -> fréquences (f = k*(fe/L))
fondamental = I*(fe/L) ; 
end

