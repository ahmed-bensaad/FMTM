function [ output_args ] = frequentiel(x,fe)
% trace le spectre d'un échantillon audio

N = length(x) ;
L = 2^nextpow2(N) ; 
xprime = [x' zeros(1,L-N)]';
X = fft(xprime,L) ; 
[M I] = max(X) ; 
X = X/M ;
f = (1:floor(L/2))*(fe/L) ; 
Y = abs(X) ; 
Y = Y(1:floor(L/2)) ; 
plot(f,Y) ; 
title('Spectre de x(t)')
xlabel('f(Hz)')
ylabel('abs(X(f))')





end

