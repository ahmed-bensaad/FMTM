function [result] = front(x)

N = length(x) ; 
M = floor(N/1000) ;
y =  conv([ones(1,M) ones(1,M)*(-1)],conv((ones(1,N)*0.99).^(0:N-1),2*(x.*x))) ;
z = max(0,y(1:N)) ;
[M I] = max(z);
result = z/M ; 




end

