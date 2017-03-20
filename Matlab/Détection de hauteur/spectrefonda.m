function fo=spectrefonda(x,fe,K)
  % x signal a �tudier
  % Te periode d'�chantillonnage
  % K nombre de point de fr�quence
  N=length(x);
  

  xf=abs(fft(x,K));
  [xfmax Ixfmax]=max(xf);
  fo=(Ixfmax-1)/(fe*K);
  f=(0:K-1)/(K*fe);
  plot(f,xf);
  hold on ; plot(fo,xfmax);hold off
  