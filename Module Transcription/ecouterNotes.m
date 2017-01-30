function audio = ecouterNotes(notes)
%prend en entrée un tableau notes et nous permet de l'écouter
audio=[];
n=length(notes);
for i=1:n-1
    l=notes(i+1,1)-notes(i,1);
    for j=1:l
        audio=[audio,sin(j*2*pi*notes(i,2)/44100)];
    end
end
for j=1:44100
    audio=[audio,sin(j*2*pi*notes(n,2)/44100)];
end

    
        
    