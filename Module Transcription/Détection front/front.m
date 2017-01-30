function [result] = front(x,fe)
%FRONT Cette fonction renvoie la liste des indices des débuts de notes

[M I] = max(x) ; 
x_norm = x/M ; 
temporel(x_norm,fe,'-b')
hold on 

N = length(x) ; 

y = filter(1,[1 -0.99],2*(x.*x)) ; 
env = sqrt(0.01*y) ; 
[M I] = max(env) ; 
env_norm  = env/M ; 
temporel(env_norm(1:N),fe,'-g') ;

L = floor(N/1000) ;
fr =  conv([ones(1,L) ones(1,L)*(-1)],y) ;
fr = max(0,fr(1:N)) ;
[M I] = max(fr);
fr_norm = fr/M ; 
temporel(fr_norm,fe,'-r') 
hold off


R = [] ; 
i = 1 ; 
while i < length(fr_norm)
    if fr_norm(i) > 0.1 
        R = [R i] ;  
        while fr_norm(i) > 0 
           i = i+1 ;  
        end 
    end 
    i = i+1 ;
end 
    
result = R ; 

end

