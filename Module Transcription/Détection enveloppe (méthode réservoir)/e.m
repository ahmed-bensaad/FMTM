function [resultat] = e(x,a,fe)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

N = length(x) ; 

reservoir = [(1-a)*x(1)*x(1)] ; 

for k=(2:1:N)
    reservoir = [reservoir a*reservoir(k-1) + (1-a)*x(k)*x(k)]  ; 
    
plot((0:(N-1))*(1/fe),reservoir)

end

resultat = reservoir ; 