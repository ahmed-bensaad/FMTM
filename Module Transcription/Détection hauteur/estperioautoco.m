function [To] = estperioautoco(x,Te)
  % x=signal échantillonner  à étudier %
  % Te = pas d'échantillonage de x%

N=length(x); 
Rxx=zeros(1,N);
Wxx=zeros(1,N-1);
x=x';
x=x-ones(1,N)*x/N;
for i=1:N
  Rxx(i)=x(1:N-i+1)'*x(i:N);
    
end
Rxx=Rxx/N;
for j=1:N-1
  Wxx(j)=Rxx(j+1);
end

[Max,Ind]=max(Wxx);
To= Te*(Ind);