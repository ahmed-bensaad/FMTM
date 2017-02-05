function fo=spectrefonda(x,fe,K)
  % x signal a étudier
  % Te periode d'échantillonnage
  % K nombre de point de fréquence
  N=length(x);
  

  xf=abs(fft(x,K));
  [xfmax Ixfmax]=max(xf);
  fo=(Ixfmax-1)/(fe*K);
  f=(0:K-1)/(K*fe);
  plot(f,xf);
  hold on ; plot(fo,xfmax);hold off
  