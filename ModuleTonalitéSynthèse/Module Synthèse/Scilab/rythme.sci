function [R]= rythme(L,T) // L-> liste rythmes, // T->tempo en bpm0/T
   t0=0.25* 60/T //temps d'une double croche
   [n,m]=size(L); 
   R=[1:m];   
   G={'dc', 'c', 'cp','n','n+dc','np','vide','b'};
   
   for i=1:m

       [nb, loc] = members(G,[L(i)], "last");
       j = find(loc);
       disp(i);
       R(i)= j*t0;
   end
   
    
endfunction
