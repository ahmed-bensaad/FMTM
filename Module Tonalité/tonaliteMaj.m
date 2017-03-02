function [ ton ] = tonaliteMaj( N )
%N liste de note
%renvoie la tonnalité (Majeur) qui semble la plus appropriée
k=0;
l=0;
for i = N
    if strcmp(i,'si#')
        k=max(7,k);
    else if strcmp(i,'mi#')
            k =max(6,k);
        else if strcmp(i,'la#')
                k=max(5,k);
            else if strcmp(i,'re#')
                    k=max(4,k);
                else if strcmp(i,'sol#')
                        k= max(3,k);
                    else if strcmp(i,'do#')
                            k= max(2,k);
                        else if strcmp(i,'fa#')
                                k= max(1,k);
                            end
                        end
                    end
                end
            end
        end
    end

end
for i = N
    if strcmp(i,'fab')
        l=max(7,l);
    else if strcmp(i,'dob')
            l =max(6,l);
        else if strcmp(i,'solb')
                l=max(5,l);
            else if strcmp(i,'reb')
                    l=max(4,l);
                else if strcmp(i,'lab')
                        l= max(3,l);
                    else if strcmp(i,'mib')
                            l= max(2,l);
                        else if strcmp(i,'sib')
                                l= max(1,l);
                            end
                        end
                    end
                end
            end
        end
    end

end
if l > 0
    l=12-l;
end

k=max(k,l);    
GM={'do', 'sol', 're', 'la', 'mi', 'si', 'fa#', 'reb','lab', 'mib','sib','fa'};
ton=GM{k+1};


end

