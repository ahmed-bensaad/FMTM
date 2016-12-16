function [ output_args ] = enveloppe2(t,x,f0)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

e = 2*(x.*x) ; 
a = [1 1/(2*pi*f0)] ;
y = filter(1,a,e) ;
z = sqrt(abs(y)) ;
plot(t,z)

end

