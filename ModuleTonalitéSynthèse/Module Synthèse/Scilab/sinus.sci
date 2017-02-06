function [y]=sinus(f0,Fe,d) // f0 -> hauteur frequence d-> durée
   Te=1/Fe; // temps echantillonage
   
   x=[0:Te:d]; //axe temps
   
   y=[0:Te:d];   // amplitude constante au cours du temps
   n=length(y);
   for i=1:n
       y(i)= sin(2*3.14159*f0*x(i));
   end
    
endfunction
