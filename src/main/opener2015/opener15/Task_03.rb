def fusc(p)
  case
    when p == 0 then 0
    when p == 1 then 1
    when p.even? then fusc(p / 2)
    else fusc(p / 2) + fusc(p / 2 + 1)
  end
end

puts fusc(2015)
