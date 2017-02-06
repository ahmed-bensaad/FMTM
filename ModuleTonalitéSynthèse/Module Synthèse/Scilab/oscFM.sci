function [s]= oscFM(f,m,Fe,d) //f-> fréquence(t) , d-> durée  ,m amplitude(t), Fe -> frequence echantillonage, d-> durée son
    
    // on veut retourné s(t)=m(t)cos(phi(t)) avec phi(t)=2*pi*(intégrale entre 0 et t de f(t) + f(0)) cf poly. synthèse FM
    pi=3.14159;
    
    Te= 1/Fe; //temps échantillonage
    
    x=[0:Te:d]; // axe des temps
    
    phi=[0:Te:d]; // definission de phi (cf.poly., méthode des rectangles)
    phi(1)=0;
    n=length(phi);
    for i=2:n
        phi(i)= phi(i-1) + 2*pi*Te*f(i);
    end
   
   
   s=[0:Te:d];
   for i=1:n
       s(i)= m(i)*cos(phi(i));
   end
    
  
endfunction

