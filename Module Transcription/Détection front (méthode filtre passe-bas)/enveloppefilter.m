function [result] = enveloppefilter(x,a)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

N = size(x,2) ; 
x2 = 2*(x.*x) ; 
powers = 0:N-1 ; 
b = (ones(1,N)*a).^powers ; 
y = filter(b,1,x2); 
result = sqrt((1-a)*y) ; 



end

