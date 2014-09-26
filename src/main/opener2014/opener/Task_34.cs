using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;


namespace ConsoleApplication2
{
    public static class Program
    {
        static Dictionary<byte, byte> bmap = new Dictionary<byte, byte>();

        static void Main(string[] args)
        {
            for (int bi = 0; bi <= 255; ++bi)
            {
                byte b = (byte)bi;
                byte nb = (byte)(
                                        (
                                            (
                                                (
                                                    (
                                                        b * 2.ToConstant()
                                                    ) & 3.ToConstant()
                                                )
                                            )
                                            +
                                            (
                                                (
                                                    b * 4.ToConstant()
                                                ) & 5.ToConstant()
                                            )
                                        ) * 6.ToConstant() >> 24
                                    );
                bmap[b] = nb;
            }

            System.Console.WriteLine((11.ToConstant() - 8.ToConstant()).GetBytes().Select(e => bmap[e]).Shuffle());

            //9430485476773810
            //40744039757350745
            //31983384509909991
            //56542102002680620
            //7195457497419137
            //52021039029732723
            //19616219460717016
            //64440585508626710
            //15068368776474285
            //39627073384392256

            System.Console.WriteLine(Next());
            System.Console.WriteLine(Next());
            System.Console.WriteLine(Next());
            System.Console.WriteLine(Next());
            System.Console.WriteLine(Next());
            System.Console.WriteLine(Next());
            System.Console.WriteLine(Next());
            System.Console.WriteLine(Next());
            System.Console.WriteLine(Next());
            System.Console.WriteLine(Next());

            Console.Read();
        }

        private static void outputToConstant(int a)
        {
            long ac = a.ToConstant();
            System.Console.Write(a + " : " + ac + "\n   ");
            System.Console.Write(Convert.ToString(ac, 2) + "\n   ");
            outputBytes(ac.GetBytes());
        }

        static void outputBytes(byte[] bs)
        {
            foreach(var b in bs) {
                System.Console.Write(b + " ");
            }
            System.Console.WriteLine();

        }

        public static byte[] GetBytes(this long value)
        {
            return BitConverter.GetBytes(value);
        }

        public static long Shuffle(this IEnumerable<byte> value)
        {
            byte[] bytes = value.ToArray();
            var t = bytes[3];
            bytes[3] = bytes[6];
            bytes[6] = t;

            t = bytes[1];
            bytes[1] = bytes[4];
            bytes[4] = t;

            return BitConverter.ToInt64(bytes, 0);
        }

        private static readonly BigInteger[] constants = new String[]{
        "-197874039147647729473", "11476694254779498256719","-289093970435778669313140",
        "4151793073863561949107558", "-37510387802012243086813173", "221821149878260300916385327 ",
        "-864978456455427990279380030", "2181533168462235599461258812", "-3375575158216765638303229464",
        "2858148004005076567403906784", "-987312297795010011057381120"}.Select(s => BigInteger.Parse(s)).ToArray();

        public static long ToConstant(this int x)
        {
            return (long)(constants.Aggregate(BigInteger.Zero, (r, c) => r * x + c) / 725760);
        }

        private static long current = 0;
        public static long Next()
        {
            //517703989306126725 = 2132533534541^-1 (mod 2^64)
            //shuf = shuf + 11.ToConstant();
            current = current + 2132533534541;

            //current = k * 11.toc().map().shuffle()

      //      System.Console.WriteLine("New: " + current);
        //    outputBytes(current.GetBytes());

            return current.GetBytes().Select(e => bmap[e]).Shuffle();
        }

        public static long Next1()
        {
            return current = (((current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle() + 8.ToConstant()) >> (int)9.ToConstant()) & (((((~(-8.ToConstant() - current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle()) ^ (current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle() + 8.ToConstant())) & ((current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle() + 8.ToConstant()) ^ 1)) ^ ~(-8.ToConstant() - current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle())) >> (int)9.ToConstant()) & ((current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle() + 10.ToConstant()) ^ (current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle() + 11.ToConstant())))) ^ (((~(-8.ToConstant() - current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle()) ^ (((current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle() + 8.ToConstant()) ^ 1) & (~(-8.ToConstant() - current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle()) ^ (current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle() + 8.ToConstant())))) >> (int)9.ToConstant()) & ((current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle() + 10.ToConstant()) ^ (current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle() + 11.ToConstant()))) ^ (current.GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle() + 11.ToConstant()).GetBytes().Select(_ => (byte)((((((_ & 1.ToConstant()) * 2.ToConstant()) & 3.ToConstant())) + (((_ & 1.ToConstant()) * 4.ToConstant()) & 5.ToConstant())) * 6.ToConstant() >> (int)7.ToConstant())).Shuffle();
        }
    }
}
