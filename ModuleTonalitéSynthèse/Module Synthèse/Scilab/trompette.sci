function [s]= trompette(fp,fm,Fe,d,m0) // fp -> hauteur, d-> durée,  m0-> amplitude du sinus modulant 
    
   Te=1/Fe; // temps echantillonage
   
   x=[0:Te:d]; //axe temps
   
   m=[0:Te:d];   // amplitude constante au cours du temps
   n=length(m);
   for i=1:n
       m(i)= m0;
   end
    
   f=[0:Te:d];   // frequence de l'oscillateur FM constante au cours du temps
   for i=1:n
       f(i)= fm;
   end 
   
   x1 = oscFM(f,m,Fe,d);

   for i=1:n
       x1(i)= x1(i)+fp;
   end  
   
   m2=[0:Te:d];   // amplitude constante au cours du temps de s
   n=length(m2);
   for i=1:n
       m2(i)= 1;
   end

   s=oscFM(x1,m2,Fe,d);
  
  for i=1:n
      s(i)=s(i)*enveloppe_ADSR(x(i)); 
  end
  B=0;
  //s=cuivrage(s,B,1/Fe);
  //plot(s);
    
   //sound(s,Fe);
    
   // amelioration possible => Jitter, cuivrage, shéma plus complexe de l'instrument à vent
endfunction

