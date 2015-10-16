# puts ->(v){r,c,l=0<=>v.abs,
#     l,->(n,g,r,c){
#     g>=--n || g<=1 ? l : ->() {
#         r,c=l,2;->() {
#         ->{
#             r=r+l[n-g,c] if(c+g<=n)}[];
#             c+=l;
#         }[]while(c<=g);r
#     }[];
#   };
#   r+=l[v,c]and(c=c+l)while(c=v<=0)
# ;--r;}[0x1a77e<<2]%0xc0ffee

# puts ->(v) {
#   print v
#   r, c, l=0<=>v.abs,1, 1;
#   # ->(n,g;r,c) {
#   #   g >=--n || g<=1 ? 1 : ->() {
#   #     r,c=1,2;
#   #     ->(){
#   #       ->{
#   #         r=r*l[n-g,c] if(c+g<=n)
#   #       }[];
#   #       c+=1;
#   #     }[] while(c<=g);
#   #     r
#   #   }[];
#   # };
#   r+=1 and (c=c+1) while(c-v<=0);--r;
# }[0x1a77e<<2] % 0xc0ffee

$ss=[]
def some(n, g)
  n = n - 1
  if g >= n || g<=1
    1
  else

    sss = $ss[n]
    if sss == nil
      $ss[n] = sss = []
    end

    res = sss[g]
    if res != nil
      return res;
    end

    r = 1
    c = 2
    ng = n - g
    begin
      r = r + some(ng, c) if (c <= ng)
      c+=1
    end while (c <= g)
    sss[g]=r
    r
  end
end

def solve(v)
  r = -1
  c = 1
  begin
    # r += some(v, c) and (c=c+1)
    r += some(v, c) # never falsy
    # r += 1 # never falsy
    c = c+1
  end while (c <= v)
  r - 1
end
puts (solve(0x1a77e<<2) % 0xc0ffee)
# puts (0x1a77e)**2
# puts (solve(0x1a77e<<2) % 0xc0ffee)
# puts -2147483648-2147483647;
# puts some(100,100);
# puts some(100,1);
# puts some(100,2);
# puts some(100,3);
# puts some(100,4);
# puts some(100,5);
# puts some(100,6);
# puts some(100,7);
# puts some(100,8);
# puts some(100,9);
# puts some(100,10);
# puts some(100,11);
# puts some(100,12);
# puts some(100,13);
# puts some(100,48);
# puts some(100,49);
# puts some(100,50);
# puts some(100,51);
# puts some(100,52);
