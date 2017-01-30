function To = estperioautoco(x,Te,u)
  % x=signal échantillonner  à étudier
  % Te = pas d'échantillonage de x%
  % u intervalle temps d'observation
  N = length(x);
  Rxx=xcov(x);
  M=length(Rxx);
  [Ro,I]=max(Rxx);
  Wxx=Rxx(I:M-1);
  K=length(Wxx);
  D=zeros(1,K-1);
  for i=1:K-1
     D(i)=Wxx(i)-Wxx(i+1);
  end
  L=1;
  k=1;
  
  while(L>=0 |(L<=0&& D(k)<0))
      L=D(k)*D(k+1);
      
      k=k+1;
  end
  
  plot([0:K-1],Wxx);
  To= (k-1);