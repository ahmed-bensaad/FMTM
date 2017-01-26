function [result] = notes(x,fe)
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
fr = front(x,fe) ; 
N = length(fr) ; 
F = [] ;
for i=1:N-1
    note = fondamental(x(fr(i):fr(i+1)),fe) ;
    F = [F note] ; 
end
F = [F fondamental(x(fr(N):end),fe)] ; 
result = [fr*(1/fe) ; F]'
end

