# e = Enumerator.new do |g|
#   (0..625).each { |r|
#     puts "r: #{r}"
#     arr = *(-r..r)
#     arr.product(arr).select { |c|
#       c.map(&:abs2).reduce(&:+)==r
#     }.map { |c|
#       m = c.map(&:abs).sort
#       t = lambda { |x; p |
#         x.times { |i|
#           # p ||= [1]
#           p = [1] + (1..i).map { |j|
#             p[j-1, 2].reduce(&:+)
#           } + [1]
#           print "p: #{p}\n"
#         }
#
#         # p ==== x-th row of pascal triangle
#         # so it's true if x-th row contains numbers that are all multiples of x
#         x>1 && (2...x).all?(&lambda { |i|
#                               p[i] % x == 0
#                             })
#       }
#
#       p = m[0]==0 ? t[m[1]] && m[1]%4==3 : t[r]
#
#       if (p)
#         g.yield(c)
#       end
#     }
#   }
# end
#
# e.each { |x| print "  point: #{x}\n" }

p = []
tt = [false] + (1..625).map { |r|
  p = [1] + (1...r).map { |j|
    p[j-1, 2].reduce(&:+)
  } + [1]

  res = (2...r).all?{|i| p[i] % r == 0}
  # print "#{res}: p: #{r}: #{p}\n"
  res
}
tt[1] = false

# print tt

e = Enumerator.new do |g|
  (0..625).each { |r|
    # puts "r: #{r}"
    arr = *(-r..r)
    arr.product(arr) { |c|
      if c.map(&:abs2).reduce(&:+)==r
        m = c.map(&:abs).sort

        p = m[0]==0 ? tt[m[1]] && m[1]%4==3 : tt[r]

        if (p)
          g.yield(c)
        end
      end
    }
  }
end

e.each { |x| print "#{x[0]} #{x[1]}\n" }
