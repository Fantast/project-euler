using System;
using System.Collections.Generic;
using System.Linq;

// answer: 1954418062651007701
public class Task_21
{
  public unsafe static void Main()
  {
    long x = -1;
    double d = Double.NegativeInfinity;
    long y = *((long*)&d);

    // Console.WriteLine("t: " + String.Join(", ", abba.Select(o => o.ToString()).ToArray()));
    // Console.WriteLine("y: " + y);
    // Console.WriteLine("yb: " + Convert.ToString(y, 2));
    // Console.WriteLine("yb: " + Convert.ToString((long)2309737967U, 2));
    // 
    // Console.WriteLine("yb: " + Convert.ToString(int.MaxValue, 2));
    // Console.WriteLine("yb: " + Convert.ToString((uint)16320, 2));
    // Console.WriteLine("yb: " + Convert.ToString((uint)3932220, 2));
    // Console.WriteLine("yb: " + Convert.ToString((uint)50529027, 2));
    // Console.WriteLine("yb: " + Convert.ToString((uint)143165576, 2));

    // ulong test = 123456123;
    // Console.WriteLine("test: " + Convert.ToString((long)test, 2));
    // Console.WriteLine("sest: " + Convert.ToString((long)swap(test,2, 3), 2));
    // Console.WriteLine("sest: " + Convert.ToString((long)swap(test,3, 2), 2));
    // Console.WriteLine("sest: " + Convert.ToString((long)swap(test,63,0), 2));

    // Console.WriteLine("yb: " + Convert.ToString((long)T(1), 2));
    // Console.WriteLine("yb: " + Convert.ToString((long)T(2464), 2));
    // Console.WriteLine("yb: " + Convert.ToString((long)T(315), 2));
    // Console.WriteLine("yb: " + Convert.ToString((long)T(4561), 2));
    // Console.WriteLine("yb: " + Convert.ToString((long)T(4561456145614561L), 2));
    

// sh: 1111011100011111101000001101111111100010111010110111111011010101
// r1: 1100101100001000110010111000110110011000011111100110000111101111
// sh: 0111011100011111101000001101111111100010111010110111111011010101
// r2: 1100101100001000110010111000110100011000011111100110000111101111

    // ulong a = (ulong)Convert.ToInt64("0010110001010000000100100100010001010000010100001110111001000100", 2);
    // Console.WriteLine("r1: " + Convert.ToString((long)T(a), 2));
    // ulong b = (ulong)Convert.ToInt64("1010110001010000000100100100010001010000010100001110111001000100", 2);
    // Console.WriteLine("r2: " + Convert.ToString((long)T(b), 2));
    ulong a = (ulong)Convert.ToInt64("1101100011111011111001101111100111110111010111010001011010101", 2);
    Console.WriteLine("r1: " + Convert.ToString((long)T(a), 2));
    ulong b = (ulong)Convert.ToInt64("1101100011111011111001101111110111110111010111010001011010101", 2);
    Console.WriteLine("r2: " + Convert.ToString((long)T(b), 2));
    Console.WriteLine("res: " + (a+b)/2);
  }

 public unsafe static ulong swap(ulong u, int i, int j) {
  ulong bi = (u >> i) & 1U;
  ulong bj = (u >> j) & 1U;

  return (u & ~(1U<<i) & ~(1U<<j)) | (bi<<j) | (bj<<i);
 }

 public unsafe static ulong T(ulong u)
  {
    // -Infinity       FFF0000000000000
    //     or
    // -Infinity       11111111111100000000000000000000 - msb
    //                 00000000000000000000000000000000 - lsb

    double* x= (double*)&u;

    // Console.WriteLine("u: " + u);
    
    "opener15".ToCharArray().Select(
      (i,j)=> *((byte*)x+j) += (byte)i
    ).Count();
    // Console.WriteLine("c: " + c);
    // Console.WriteLine("u: " + u);
    // Console.WriteLine("uc: " + (&u==&c));

    // ulong a = (ulong)Convert.ToInt64("0010110001010000000100100100010001010000010100001110111001000100", 2);
    // Console.WriteLine("r1: " + Convert.ToString((long)T(a), 2));
    // ulong b = (ulong)Convert.ToInt64("1010110001010000000100100100010001010000010100001110111001000100", 2);
    // Console.WriteLine("r2: " + Convert.ToString((long)T(b), 2));
    
    Console.WriteLine("sh: " + Convert.ToString((long)u, 2));

    // var some = new uint[]{16320<<2, 3932220<<2, 50529027<<2, 143165576<<2}
    var some = new uint[]{65280, 15728880, 202116108, 572662304}
      .Select(
        (a,j)=>new{a=a,j=1<<(3-j)}
      );
    // some: [
    // { a = 65280, j = 8 },
    // { a = 15728880, j = 4 },
    // { a = 202116108, j = 2 },
    // { a = 572662304, j = 1 }]
    // 8 -> 1111111100000000
    // 4 -> 111100000000000011110000
    // 2 -> 1100000011000000110000001100
    // 1 -> 100010001000100010001000100000

    u=new uint[]{0,1}.Select(
      i=>*((uint*)x+i)
    ).Select(
      b => some.Select(
            w => {
              uint ss = ( b ^ (b>>w.j) ) & w.a;
              b = ss ^ b ^ (ss<<w.j);
              // return b ^ 2309737967U;
              return b;
            })
          .Last()
    ).Aggregate(0UL, (total,element) => {
      // Console.WriteLine("element: " + Convert.ToString((long)element, 2));
      // Console.WriteLine("total  : " + Convert.ToString((long)((total<<32) + element), 2));
      return (total<<32) + element;
    });

    ulong xr = (2309737967UL<<32) + 2309737967UL;

    //here u should be one of
    // 1000100110100100001100100001000001110110010101000011001000010000
    // 0000100110100100001100100001000001110110010101000011001000010000
    Console.WriteLine("nx: " + Convert.ToString((long)u, 2));

// xr: 1000100110101011110011011110111110001001101010111100110111101111
    u = u ^ xr;

    //here u should be one of
    // 0000000000001111111111111111111111111111111111111111111111111111
    // 1000000000001111111111111111111111111111111111111111111111111111
    // u = (ulong)(Convert.ToInt64("0000000000001111111111111111111111111111111111111111111111111111", 2));
    u = ~u;


    //here u should be one of
    // 1111111111110000000000000000000000000000000000000000000000000000
    // 0111111111110000000000000000000000000000000000000000000000000000
    // 0111111111110000000000000000000000000000000000000000000000000000
    u = u | (1UL << 63);

// xr: 1000100110101011110011011110111110001001101010111100110111101111
// yb: 1100101111111100000011110011010001101001011100100010110000000101
// yb: 1100101110111101000110100110011101101001011100100010110000000101
// yb: 1100101111111101010111100111011001101001011100100010110000000101
// yb: 1001111011111101000010100011010001101001011100100010110000000101
//Inf: 1111111111110000000000000000000000000000000000000000000000000000
    return *((ulong*)x);
  }
}
