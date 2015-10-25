using System;
using System.Collections.Generic;
using System.Linq;

// Answer:
public class Task_33
{
    static byte[] need = new byte[] {151,221,248,176,164,129,126,108,84,192,243,22,207,22,83,175,23,150,40,93,250,5,155,232,207,48,120,200,78,165,27,131,236,46,122,60,164,145,96,56,66,34,129,75,174,40,126,177,164,158,59,192,44,110,128,115,114,237,76,4,5,146,131,35,57,117,147,77,94,23,51,173,46,184,76,150,15,13,111,253,36,2,5,189,82,146,79,0,152,78,85,211,118,193,205,143,58,51,218,187,133,33,159,3,83,27,7,85,46,26,236,16,160,100,32,39,19,206,100,66,34,99,122,67,214,113,159,83,33,172,209,116,95,151,212,179,132,84,216,138,100,142,195,106,138,219,63,147,173,142,165,38,38,175,83,17,106,21,249,87,180,36,9,141,165,169,50,96,157,7,51,16,22,219,212,212,133,92,95,171,85,199,153,63,241,67,225};

    public unsafe static void Main()
    {
//        for (int i = 0; i <= 256; ++i) {
//
//            byte x = (byte)i;
////            int x = i;
//
//            var some = Enumerable
//                .Range(0, 9)
//                .Select(n => 1 << n)
//                .Reverse()
//                .Select(m => {
//                    return Math.Sign(m % 256 & x);
//                });
//
//            Console.WriteLine("x: " + x + "; some: " + String.Join(", ", some));
//        }

//        var ss = "01101000001001001001101000001011000001101000101000000100010000001101000101000001101101000001011011";
//        var ss = "0110100000000";
//        int check = 11;
//        int volume = 20;

          var ss = "01101000001001001001101000001011000001101000101000000100010000001101000101000001101101000001011011";
          byte[] some = new byte[ss.Length];
          for (int i = 0; i < ss.Length; ++i) {
              some[i] = (byte)(ss[i] - '0');
          }
          byte[] res = Compress(some);
          Console.WriteLine(String.Join(", ", res));

//        var ss = "";
//        int volume = 16;

//        Console.WriteLine(common("01101000000000", "0110100000000011"));

//        for (int check = 1; check < 20; ++check) {
//            if (check == 10) {
//                volume = 20;
//            } else if (check == 11) {
//                volume = 23;
//            } else if (check == 12) {
//                volume = 20;
//            } else {
//                volume = 16;
//            }
//
//            int ind = ss.Length;
//            string newstring = null;
//            int fitcount = 0;
//
//            var some = new byte[ss.Length + volume];
//            for (int i = 0; i < ss.Length; ++i) {
//                some[i] = (byte)(ss[i] - '0');
//            }
//
//            for (int i = 0; i < (1<<volume); ++i) {
//                for (int j = 0; j < volume; ++j) {
//                    some[ind+j] = (byte)((i >> (volume-1-j)) & 1);
//                }
//                bool fit = true;
//                byte[] res = new byte[0];
//                try {
//                    res = Compress(some);
//                    for (int j = 0; j < check; ++j) {
//                        if (res[j] != need[j]) {
//                            fit = false;
//                            break;
//                        }
//                    }
//                } catch (Exception) {
//                    fit = false;
//                }
//                if (fit) {
//                    fitcount++;
//                    if (newstring == null) {
//                        newstring = String.Join("", some);
//                    } else {
//                        newstring = common(newstring, String.Join("", some));
//                    }
////                    if (fitcount > 5) {
////                        break;
////                    }
////                    Console.WriteLine(String.Join("", some) + ": " + String.Join(", ", res));
//                }
//            }
//            if (newstring == null) {
//                Console.WriteLine("Ouch");
//                break;
//            }
//
//            ss = newstring;
//
//            string chs = "" + check;
//            if (check < 100) { chs = "0" + chs;}
//            if (check < 10) { chs = "0" + chs;}
//
//            Console.WriteLine("Check: " + chs + ": " + ss);
//        }

        // Console.WriteLine("yb: " + Convert.ToString((long)T(4561456145614561L), 2));
//        ulong a = (ulong)Convert.ToInt64("1101100011111011111001101111100111110111010111010001011010101", 2);
//        Console.WriteLine("r2: " + Convert.ToString((long)255, 2));
    }

    public static string common(string s1, string s2) {
        int len = Math.Min(s1.Length, s2.Length);
        for (int i = 0; i < len; ++i) {
            if (s1[i] != s2[i]) {
//                Console.WriteLine("  Common for: " + s1 + " and " + s2 + ": " + s1.Substring(0, i));
                return s1.Substring(0, i);
            }
        }
//        Console.WriteLine("  Common for: " + s1 + " and " + s2 + ": " + s1.Substring(0, len));
        return s1.Substring(0, len);
    }

    public static byte[] Compress(byte[] data) {
        var a = uint.MinValue;
        var b = uint.MaxValue;
        var c = 1;
        var p = new int[512, 2];
        var result = new List < byte > ();
        var w = (Action)(() => {
            //4278190080  ===  11111111 00000000 00000000 00000000
            while (((a ^ b) & 4278190080) == 0) {
                result.Add((byte)(b >> 24));
                a <<= 8;
                b = (b << 8) + 255;
            }
        });

//        var some = data
//            .SelectMany(
//                e => Enumerable
//                    .Range(0, 9)
//                    .Select(n => 1 << n)
//                    .Reverse()
//                    .Select(m => {
//                        return Math.Sign(m % 256 & e);
//                    })
//            )
//            .Concat(new int[] { 1 });
//
//        Console.WriteLine(String.Join(", ", some));

        //some is seq of bits of every el in data
        // .. and additional 1 at the end

        var some = data;

        foreach(var x in some) {
            var m = (uint)(a + ((b - a) >> 12) * (4096 * (p[c, 1] + 1) / (p[c, 0] + p[c, 1] + 2)));
            Console.WriteLine("m: " + m);

//            var u = (~-x ^ ((x ^ 1) & (~-x ^ x))) >> 31;
//            var v = (x >> 31) & ((((~-x ^ x) & (x ^ 1)) ^ ~-x) >> 31);
//            b = (uint)((v & (b ^ m)) ^ (u & (b ^ m)) ^ m);
//            a = (uint)((v & ((m + 1) ^ a)) ^ (u & ((m + 1) ^ a)) ^ a);

            //x is 0 or 1
            if (x == 0) {
                a = m + 1;
            } else {
                b = m;
            }

            // 65534 === 1111111111111110
            if (65534 < ++p[c, x]) {
                p[c, 0] >>= 1;
                p[c, 1] >>= 1;
            }
            if ((c += c + x) >= 512) c = 1;
            w();
        }
        w();
        result.Add((byte)(b >> 24));
        return result.ToArray();
    }
}
