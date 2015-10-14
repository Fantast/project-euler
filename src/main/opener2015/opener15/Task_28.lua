--function R()
--    return(function(...)_=0;repeat;_=_+1;until#(function(...)for _=1,(...)[(...)[1]+1]{(...)[2]}do(function(...)for _=(...)[2],(...)[1][(...)[1][1]+1]{(...)[1][2]}do;if(...)[1][2]==_^(...)[1][1]+(...)[2]^(...)[1][1]then;(function(...);(...)[1][#(...)[1]+1]=(...)[2];end){(...)[1][3],(...)[2]};end;end;end){...,_}end;return(...)[3];end){(...)._-1,_,{},function(...);return(...)[1]^0.35;end}==(...)._;return _;end){_=4}
--end

function some(set, count)
    for i = 1, count ^ 0.35 do
        for j = i, count ^ 0.35 do
            if count == j ^ 3 + i ^ 3 then
                set[#set + 1] = i; -- add to the end
            end;
        end;
    end;
    return set;
end

function R()
    count = 0;
    repeat
        count = count + 1;
    until #some({}, count) == 4; -- ?? until length == 4
    return count;
end

--print ((1)[1])

(print)(#({
    33, 5, {23},
    function(...)
        return (...)[1] ^ 0.35;
    end
}));

print (4.3 ^ 0.35)
R()