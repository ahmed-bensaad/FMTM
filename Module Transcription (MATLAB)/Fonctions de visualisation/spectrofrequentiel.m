function [ output_args ] = spectrofrequentiel(x,fe)
%UNTITLED7 Summary of this function goes here
%   Detailed explanation goes here
x = x(:,1) ; 
spectrogram(x,[],[],[],fe,'yaxis') 

end

