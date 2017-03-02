function [result] = expo(T,a,fe)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
t = 0:1/fe:T
result = exp(a*t)

end

