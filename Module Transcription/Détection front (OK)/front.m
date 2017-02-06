function [result] = front(x,a)
%UNTITLED6 Summary of this function goes here
%   Detailed explanation goes here 

N = length(x) ; 
f = conv([ones(1,1000) ones(1,1000)*(-1)],conv((ones(1,N)*a).^(0:N-1),2*(x.*x))) ;
[M I] = max(f) ; 
g = (f/M) ; 
result = max(0,g(1:N)) ;


end

