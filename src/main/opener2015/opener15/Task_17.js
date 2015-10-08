function random(a) {
  return Math.floor(Math.random() * ++a);
}

function go() {
  var rr = random(12) + 1; //1..13
  var rn = rr << 1;
  var ws = new Array(rn + 1).join(' ');

  console.log(rr);
  console.log('----');

  var a = [];
  var b = [];
  for (var i = 0; i < rr; i++) {
    var ind = random(a.length);
    a.splice(ind, 0, 1, 0);
    b.push(ws);
  }

  console.log(a);
  console.log(b.length);
  var y = -1;
  for (var x = 0; x < rn; x++) {
    var k = rr - 1 - y - a[x];

    //b[k][x] = a[x] === 0 ? '\\' : '/';

    var p = b[k].split('');
    p[x] = (a[x] === 0 ? '\\' : '/');
    b[k] = p.join('');

    y += a[x] * 2 - 1;
  }

  var res = b.filter(function (s) {
    return s.trim() != '';
  }).join('');

  console.log(res);
}

go();
