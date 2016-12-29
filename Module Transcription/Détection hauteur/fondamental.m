function [fondamental] = fondamental(x,fe)
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here

N = length(x) ; 
L = 2^nextpow2(N*100) ; 
X = fft(x,L)/L ;  
[M,I]  = max(abs(X)) ; 
fondamental = I*(fe/L) ; 
end

