--function R()
--    return(function(...)_=0;repeat;_=_+1;until#(function(...)for _=1,(...)[(...)[1]+1]{(...)[2]}do(function(...)for _=(...)[2],(...)[1][(...)[1][1]+1]{(...)[1][2]}do;if(...)[1][2]==_^(...)[1][1]+(...)[2]^(...)[1][1]then;(function(...);(...)[1][#(...)[1]+1]=(...)[2];end){(...)[1][3],(...)[2]};end;end;end){...,_}end;return(...)[3];end){(...)._-1,_,{},function(...);return(...)[1]^0.35;end}==(...)._;return _;end){_=4}
--end
function R()
    return (
        function(...)
            print ((...)._);
            count = 0;
            repeat
                count = count + 1;
            until #(
                function(const3,cnt,set,func, ...)
                    for i = 1, count ^ 0.35 do
                        (function(...)
                            for j = i, count ^ 0.35 do
                                if count == j ^ 3 + i ^ 3
                                    then
                                        (function(...)
                                            (...)[1][#(...)[1] + 1] = i;
                                        end) { (...)[1][3]};
                                end;
                            end;
                        end) { ... } end;
                    return (...)[3];
                end) (
                        3,  --1
                        count, --2
                        {}, -- 3
                        function(...) --4
                            return (...)[1] ^ 0.35;
                        end
                    ) == 4;
            return count;
        end) { _ = 4 }
end

(print)(({
    a=33, 5, {23},
    function(...)
        return (...)[1] ^ 0.35;
    end
}).a);

print (4.3 ^ 0.35)
R()