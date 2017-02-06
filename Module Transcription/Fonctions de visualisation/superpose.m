function [ output_args ] = trace_superpose(x,y,fe)
%UNTITLED8 Summary of this function goes here
%   Detailed explanation goes here

temporel(x,fe,'-r') 
hold on 
temporel(y,fe,'-b')
hold off

end

