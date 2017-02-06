function [s]= instru_vent(fp,fm,Fe,d,m0) // f0 -> hauteur, d-> durée,  m0-> amplitude  
    
   Te=1/Fe; // temps echantillonage
   pi=3.14159;
   x=[0:Te:d]; //axe temps
   a=0.03; //excursion du vibrato   
   fv=2;  // fréquence du vibrato  
   
   m=[0:Te:d];   // amplitude constante au cours du temps
   n=length(m);
   for i=1:n
       m(i)= m0*(1+a*sin(2*pi*fv*x(i)));   //Vibrato en amplitude
   end
    
   f=[0:Te:d];   // frequence de l'oscillateur FM constante au cours du temps
   for i=1:n
       f(i)= fm;
   end 
   
   x1 = oscFM(f,m,Fe,d);
   

   for i=1:n
       x1(i)= (x1(i)+fp)*(1 + a*sin(2*pi*fv*x(i)));         //Vibrato
   end  
    
   m2=[0:Te:d];   // amplitude constante au cours du temps de s
   n=length(m2);
   for i=1:n
       m2(i)= 1;
   end


   s=oscFM(x1,m2,Fe,d);
   

  //plot(s);
    
   //sound(s,Fe);
    
endfunction

