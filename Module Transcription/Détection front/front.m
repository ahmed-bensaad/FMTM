function [result] = front(x,fe)
%FRONT Cette fonction renvoie la liste des indices des débuts de notes
[M I] = max(x) ; 
x_norm = x/M ; 
temporel(x_norm,fe,'-b')
hold on 

x = downsample(x,10) ;
N = length(x) ; 

y = conv((ones(1,N)*0.99).^(0:N-1),2*(x.*x)) ; 
env = sqrt(0.01*y) ; 
[M I] = max(env) ; 
env_norm  = env/M ; 
temporel(env_norm(1:N),fe/10,'-g') ;

L = floor(N/1000) ;
fr =  conv([ones(1,L) ones(1,L)*(-1)],y) ;
fr = max(0,fr(1:N)) ;
[M I] = max(fr);
fr_norm = fr/M ; 
temporel(fr_norm,fe/10,'-r') 
hold off

[pks,locs] = findpeaks(fr_norm) ; 
K = length(locs); 
R = [] ;
for i = 1:K
    if pks(i) > 0.1
        R = [R locs(i)] ; 
    end 
end 
result = R*10 ;  



end

