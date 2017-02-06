function [ output_args ] = frequentiel(x,fe)
//trace le spectre d'un échantillon audio

N = length(x) ;
L = 2^nextpow2(N) ; 
xprime = [x zeros(1,L-N)]';
X = fftw(xprime,-1)/L ; 
f = (0:L-1)*(fe/L) ; 
plot(f,abs(X)) ; 
title('Spectre de x(t)')
xlabel('f(Hz)')
ylabel('abs(X(f))')

endfunction

