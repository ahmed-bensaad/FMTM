function [ oct , n ] = note(f)

x=log(f./440)./log(2.^(1./12));
y=round(x)+45;
oct = fix(y/12);
m = modulo(y, 12);
G={'do', 'do#', 're', 're#', 'mi', 'fa', 'fa#', 'sol', 'sol#', 'la', 'la#', 'si'};
n=G(m+1);

endfunction
