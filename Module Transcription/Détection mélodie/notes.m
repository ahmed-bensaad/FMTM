function [result] = notes(x)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
fr = front(x,0.99) ; 
[pks, locs] = findpeaks(fr) ;
result = locs
    



end

