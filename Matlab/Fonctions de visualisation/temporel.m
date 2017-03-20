function [ output_args ] = temporel(x,fe,color)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

N = length(x) ; 
Te = 1/fe ;
t = (0:N-1)*Te ;
plot(t,x,color)
xlabel('t en secondes')
ylabel('x(t)')
title('Tracé temporel du signal x(t)')


end

