def fusc(p)
  case
    when p == 0 || p == 1 then p
    when p.even? then fusc(p / 2)
    else fusc(p / 2) + fusc(p /2 + 1)
  end
end

puts fusc(2015)
# puts fusc(2015!)