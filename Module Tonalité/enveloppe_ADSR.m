function [ f ] = enveloppe_ADSR( x )

D=1
u=0.02
d=0.02
s=0.8
r=0.1
if x<u
    f=x./u;
end

if x>=u & x<u+d
    f=1-(x-u)./d.*(1-s);
end

if x>=u+d & x<D
    f=s;

end

if x>=D & x< D+r
    f=s-(x-D)./r.*s;
end

if x>= D+r
    f=0;
end
end