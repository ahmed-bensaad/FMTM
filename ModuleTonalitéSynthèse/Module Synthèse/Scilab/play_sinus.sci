function []= play_sinus(p) // N -> liste notes, R-> liste temps notes 
    
   N=['do','do','do','re','mi','re','do','mi','re','re','do'];
   N=['do','do','si','si','do','do','la','mi','mi','sol','sol','la'];
   N=['do','re','mi','fa','sol','la','si','do'];
   N1=[1:8];
   n=length(N1);
   
   for i = 1:n
       disp(i);
       N1(i)=frequence(4,N(i));
   end
   R=[0.5 0.5 0.5 0.5 1 1 0.5 0.5 0.5 0.5 1]; 
   R=[0.5 0.5 0.5 0.5 0.125 0.325 1.5 0.5 0.5 0.125 0.325 1.5]
    disp(N1);
   
   //p=3; // caractérise le timbre prendre un entier
   fmax=max(N1); 
   L=sinus(N1(1),5*fmax,R(1));
   n=length(N1);
  
   
   for k=2:n
        L=[L sinus(N1(k),5*fmax,R(k))];   
   end
 

   sound(L,5*fmax);
endfunction
