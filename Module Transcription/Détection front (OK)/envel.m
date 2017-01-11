function [result] = envel(x,a)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

N = length(x) ; 
y = conv((ones(1,N)*a).^(0:N-1),2*(x.*x)); 
e = sqrt((1-a)*y) ;
[M I] = max(e) ; 
result = e/M ; 

end

