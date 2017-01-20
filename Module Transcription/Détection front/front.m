function [result] = front(x)
%FRONT Cette fonction renvoie la liste des indices des débuts de notes
N = length(x) ; 

M = floor(N/1000) ;


y =  conv([ones(1,M) ones(1,M)*(-1)],conv((ones(1,N)*0.99).^(0:N-1),2*(x.*x))) ;
z = max(0,y(1:N)) ;
[M I] = max(z);
h = z/M ; 
[pks,locs] = findpeaks(h) ; 
K = length(locs); 
R = [] ;
for i = 1:K
    if pks(i) > 0.1
        R = [R locs(i)] ; 
    end 
end 
result = R ;          
end

