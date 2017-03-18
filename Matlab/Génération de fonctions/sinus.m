function [result] = sinus(T,fe,f)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
t = 0:1/fe:T ;
result = sin(2*pi*f*t) ;


end

