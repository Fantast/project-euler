def true.*(value); value; end

def false.*(value); 0; end

class Fixnum
  def [](bit)
    self & 1 << bit != 0
  end
end

class F
  def initialize(k)
    @k = k
  end

  def [](a, b, c, d)
    @k[a*8 + b*4 + c*2 + d*1]
  end
end

def add(f, a, b)
  ->(x, y, z){[->(x, y, z){f[true, z, y, x]}[x, y, z], ->(x, y){f[false, true, x, y]}[->(x, y){f[false, false, y, x]}[x, y],
  ->(x, y){f[false, false, y, x]}[z, ->(x, y, z){f[true, z, y, x]}[false, x, y]]]]}[a[3], b[3], ->(x, y, z){[->(x, y, z){f[true, z, y, x]}[x, y, z],
  ->(x, y){f[false, true, x, y]}[->(x, y){f[false, false, y, x]}[x, y], ->(x, y){f[false, false, y, x]}[z, ->(x, y, z){f[true, z, y, x]}[false, x, y]]]]}[a[2], b[2],
  ->(x, y, z){[->(x, y, z){f[true, z, y, x]}[x, y, z], ->(x, y){f[false, true, x, y]}[->(x, y){f[false, false, y, x]}[x, y],
  ->(x, y){f[false, false, y, x]}[z, ->(x, y, z){f[true, z, y, x]}[false, x, y]]]]}[a[1], b[1],
  ->(x, y, z){[->(x, y, z){f[true, z, y, x]}[x, y, z], ->(x, y){f[false, true, x, y]}[->(x, y){f[false, false, y, x]}[x, y],
  ->(x, y){f[false, false, y, x]}[z, ->(x, y, z){f[true, z, y, x]}[false, x, y]]]]}[a[0], b[0], false][1]][1]][1]][0] * 8 |
  ->(x, y, z){[->(x, y, z){f[true, z, y, x]}[x, y, z], ->(x, y){f[false, true, x, y]}[->(x, y){f[false, false, y, x]}[x, y],
  ->(x, y){f[false, false, y, x]}[z, ->(x, y, z){f[true, z, y, x]}[false, x, y]]]]}[a[2], b[2],
  ->(x, y, z){[->(x, y, z){f[true, z, y, x]}[x, y, z], ->(x, y){f[false, true, x, y]}[->(x, y){f[false, false, y, x]}[x, y],
  ->(x, y){f[false, false, y, x]}[z, ->(x, y, z){f[true, z, y, x]}[false, x, y]]]]}[a[1], b[1], ->(x, y, z){[->(x, y, z){f[true, z, y, x]}[x, y, z],
  ->(x, y){f[false, true, x, y]}[->(x, y){f[false, false, y, x]}[x, y],
  ->(x, y){f[false, false, y, x]}[z, ->(x, y, z){f[true, z, y, x]}[false, x, y]]]]}[a[0], b[0], false][1]][1]][0] * 4 |
  ->(x, y, z){[->(x, y, z){f[true, z, y, x]}[x, y, z], ->(x, y){f[false, true, x, y]}[->(x, y){f[false, false, y, x]}[x, y],
  ->(x, y){f[false, false, y, x]}[z, ->(x, y, z){f[true, z, y, x]}[false, x, y]]]]}[a[1], b[1],
  ->(x, y, z){[->(x, y, z){f[true, z, y, x]}[x, y, z], ->(x, y){f[false, true, x, y]}[->(x, y){f[false, false, y, x]}[x, y],
  ->(x, y){f[false, false, y, x]}[z, ->(x, y, z){f[true, z, y, x]}[false, x, y]]]]}[a[0], b[0], false][1]][0] * 2 |
  ->(x, y, z){[->(x, y, z){f[true, z, y, x]}[x, y, z], ->(x, y){f[false, true, x, y]}[->(x, y){f[false, false, y, x]}[x, y],
  ->(x, y){f[false, false, y, x]}[z, ->(x, y, z){f[true, z, y, x]}[false, x, y]]]]}[a[0], b[0], false][0] * 1
end

def test()
  resf = 1
  (0...2**16).each do |k|
    puts k
    f = F.new(k)
    ok = true
    (0...2**4).each do |a|
       (a...2**4).each do |b|
         if ((a+b) % 16) != add(f, a, b)
           ok = false
           break
         end
       end
       break if !ok
    end
    if ok
      resf = f
      break
    end
  end
  # puts resf.k
  (0...16).map{|x| resf[x[3], x[2], x[1], x[0]] * 1 << x}.inject(&:+);
end

puts test();
