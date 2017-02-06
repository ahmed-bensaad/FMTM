function [L,fe]= play(p,d) // N -> liste notes, R-> liste temps notes 
    
   N=['do','do','do','re','mi','re','do','mi','re','re','do'];
   N=['do','do','si','si','do','do','la','mi','mi','sol#','sol#','la'];
   //N=['do','re','mi','fa','sol','la','si'];
   //N=['do','do','do','do','do','do','re'];
   N1=[1:12];
   n=length(N1);
   
   for i = 1:2
       N1(i)=frequence(4,N(i));
   end
   for i = 3:4
       N1(i)=frequence(3,N(i));
   end
   for i= 5:6
       N1(i)=frequence(4,N(i));
   end
   for i = 7:n
       N1(i)=frequence(3,N(i));
   end
   //N1(8)=frequence(5,'do');
   R=[0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 1];
   U=['c','c','c','c','dc','c','n+dc','c','c','dc','c','n'] 
   R=rythme(U,50);

   
   //p=3; // caractérise le timbre prendre un entier
   fmax=max(N1); 
   L=trompette3(N1(1),p*N1(1),50*fmax,R(1),d*N1(1));
   n=length(N1);
  
   
   for k=2:n
        L=[L trompette3(N1(k),p*N1(k),50*fmax,R(k),d*N1(k))];   
   end
    
   fe=50*fmax;
   
   sound(L,50*fmax);
endfunction


//1 5 pas mal

