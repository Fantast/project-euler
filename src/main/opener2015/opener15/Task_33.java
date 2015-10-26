package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Answer : 56315
public class Task_33 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_33());
        Logger.close();
    }

    Random random = new Random();

    int[] need_i = new int[] {151, 221, 248, 176,164,129,126,108,84,192,243,22,207,22,83,175,23,150,40,93,250,5,155,232,207,48,120,200,78,165,27,131,236,46,122,60,164,145,96,56,66,34,129,75,174,40,126,177,164,158,59,192,44,110,128,115,114,237,76,4,5,146,131,35,57,117,147,77,94,23,51,173,46,184,76,150,15,13,111,253,36,2,5,189,82,146,79,0,152,78,85,211,118,193,205,143,58,51,218,187,133,33,159,3,83,27,7,85,46,26,236,16,160,100,32,39,19,206,100,66,34,99,122,67,214,113,159,83,33,172,209,116,95,151,212,179,132,84,216,138,100,142,195,106,138,219,63,147,173,142,165,38,38,175,83,17,106,21,249,87,180,36,9,141,165,169,50,96,157,7,51,16,22,219,212,212,133,92,95,171,85,199,153,63,241,67,225};
    byte[] need = new byte[need_i.length];
    int mask = Integer.parseUnsignedInt("4278190080");

    String found = "0110100000100100100110100000101100000110100010100000010001000000110100010100000110110100000101101100110100000101101010001000000110100000101111100110100010100000100110100010100000000110100000101100000110100000101100100110100000101110000110100000101110110110100000101100000001000000110100000101111100110100010100000010110100000101101010110100000101111010110100000101111010110100010100011110110100010100011110001000000110100010100000010110100000101110110110100010100011110110100000101110100110100000101111100110100010100000100110100010100011000001000000110100000101100010110100010100000110110100000101110110110100010100011000110100000101100100110100000101100000110100010100000000110100000101111010110100000101100000110100010100011110000010100110100000100110000001000000110100010100011110001000000110100000101101110110100000101111010110100000101100000110100010100011100001011000001000000110100010100001110110100010100000100110100000101111100001000000110100000101110100110100010100000000110100000101110000110100000101110100110100000101111010110100010100000110110100000101100100001011000001000000110100000101100100110100010100010110001000000110100000101111000110100000101111100110100000101101100110100000101101010110100010100000100110100000101101010001000000110100010100000010110100000101111110110100010100000000110100010100010110110100000101100110110100000101111010110100010100000110110100010100000100110100010100011000001000000110100010100000010001000000110100010100000110110100000101111000110100000101100000001011100000010100110100000100110000001000000110100000101110100110100000101111100110100000101100110110100000101101000110100000101100000001000000110100000101100100110100010100010110001000000110100010100000110110100000101111000110100010100000000110100000101101010110100010100000100110100000101101010001000000110100000101111010110100000101100000001000000110100010100011010110100010100000100110100000101111100110100000101110010001000000110100010100000010110100000101110100110100000101100000110100000101111000110100000101101010110100000101110010110100000101110100110100000101101010001011000001000000110100000101110100110100000101111100110100010100010000110100000101111000110100000101100000110100010100000000110100000101111010110100000101100000110100010100011110000010100110100000100100100110100000101100000110100010100010000001000000110100010100000010110100000101110000110100010100000000110100000101101010110100000101111010110100000101101010110100000101100100110100010100010110110100000101110010001000000110100010100000100110100010100000000110100010100000110110100000101111110110100000101110000110100000101110100001000000110100000101111100110100000101110100110100010100000110110100010100000100110100000101100000110100000101101010110100010100000100001000000110100010100000010110100000101100000110100000101100100110100000101100000110100000101111010110100000101111100110100000101111000001000000110100010100000100110100010100011000110100000101111000110100000101100000001011100001011100001011101";
    byte[] res = new byte[9000];

    public void solving() {
        for (int i = 0; i < need.length; ++i) {
            need[i] = (byte) need_i[i];
        }

        //1st round
//        int a = 0;
//        int b = Integer.parseUnsignedInt("4294967295");
//        int c = 1;
//        int p[][] = new int[512][2];
//
//        find(0, 0, a, b, c, p, (byte)0, 1);

        //2nd round
//        String ss = found;
//        int volume = 10;
//
//        int ind = ss.length();
//
//        for (int v = 10; v < 20; ++v) {
//            volume = v;
//            byte[] some = new byte[ss.length() + volume];
//            for (int i = 0; i < ss.length(); ++i) {
//                some[i] = (byte)(ss.charAt(i) - '0');
//            }
//            for (int i = 0; i < (1<<volume); ++i) {
//                for (int j = 0; j < volume; ++j) {
//                    some[ind+j] = (byte)((i >> (volume-1-j)) & 1);
//                }
//                List<Byte> res = compress(some);
//                boolean fit = res.size() == need.length;
//                for (int j = 180; fit && j < need.length; ++j) {
//                    if (res.get(j) != need[j]) {
//                        fit = false;
//                    }
//                }
//                if (fit) {
//                    System.out.println(join(some, some.length));
//                    break;
//                }
//            }
//        }

        //3rd round
        int sum = 0;
        for (int i = 0; i < found.length() / 9; ++i) {
            String ns = found.substring(i*9+1, i*9+9);
            sum += Integer.parseInt(ns, 2);
        }
        System.out.println(sum);
    }

    public static String join(byte[] a, int len) {
        String res = "";
        for (int i = 0; i < len; i++) {
            res += a[i];
        }
        return res;
    }

    public static String common(String s1, String s2) {
        int len = Math.min(s1.length(), s2.length());
        for (int i = 0; i < len; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return s1.substring(0, i);
            }
        }
        return s1.substring(0, len);
    }

    public List<Byte> compress(byte[] data) {
        int mask = Integer.parseUnsignedInt("4278190080");
        final int[] a = {0};
        final int[] b = {Integer.parseUnsignedInt("4294967295")};
        int c = 1;
        int p[][] = new int[512][2];
        final List<Byte> result = new ArrayList<>();

        Runnable w = (() -> {
            while (((a[0] ^ b[0]) & mask) == 0) {
                result.add((byte)(b[0] >> 24));
                a[0] <<= 8;
                b[0] = (b[0] << 8) + 255;
            }
        });

        for (byte x : data) {
            int m = (a[0] + ((b[0] - a[0]) >>> 12) * (4096 * (p[c][1] + 1) / (p[c][0] + p[c][1] + 2)));

            if (x == 0) {
                a[0] = m + 1;
            } else {
                b[0] = m;
            }

            if (65534 < ++p[c][x]) {
                p[c][0] >>= 1;
                p[c][1] >>= 1;
            }
            if ((c += c + x) >= 512) c = 1;
            w.run();
        }
        w.run();
        result.add((byte) (b[0] >>> 24));
        return result;
    }

    public boolean find(int needInd, int resInd, int a, int b, int c, int[][] p, byte x, int zeroes) {
        if (resInd >= res.length) {
            return false;
        }

        if (zeroes > 10) {
            return false;
        }

        int maxSearch = found.length() + (needInd-100 + 1) * 80;
        if (resInd > found.length() && resInd > maxSearch) {
            return false;
        }
        res[resInd] = x;
        if (needInd == 20) {
            System.out.println("Almost there 20: " + join(res, resInd));
        } else if (needInd == 40) {
            System.out.println("Almost there 40: " + join(res, resInd));
        } else if (needInd == 60) {
            System.out.println("Almost there 60: " + join(res, resInd));
        } else if (needInd == 80) {
            System.out.println("Almost there 80: " + join(res, resInd));
        } else if (needInd == 100) {
            System.out.println("Almost there 100: " + join(res, resInd));
        } else if (needInd == 120) {
            System.out.println("Almost there 120: " + join(res, resInd));
        } else if (needInd == 140) {
            System.out.println("Almost there 140: " + join(res, resInd));
        } else if (needInd == 160) {
            System.out.println("Almost there 160: " + join(res, resInd));
        } else if (needInd == 185) {
            System.out.println("Almost there 185: " + join(res, resInd));
        }

        int m = (a + ((b - a) >>> 12) * (4096 * (p[c][1] + 1) / (p[c][0] + p[c][1] + 2)));
        if (x == 0) {
            a = m + 1;
        } else {
            b = m;
        }

        if (65534 < ++p[c][x]) {
            p[c][0] >>= 1;
            p[c][1] >>= 1;
        }
        if ((c += c + x) >= 512) c = 1;
        while (((a ^ b) & mask) == 0) {
            byte next = (byte)(b >> 24);
            if (needInd == need.length - 2) {

            }
            if (next != need[needInd++]) {
                return false;
            }
            a <<= 8;
            b = (b << 8) + 255;
        }

        if (needInd == need.length - 1) {
            return false;
        }

        if (resInd < found.length() - 1) {
            byte foundRes = (byte) (found.charAt(resInd+1) - '0');
            return find(needInd, resInd+1, a, b, c, pcopy(p), foundRes, foundRes == 0 ? zeroes + 1 : 0);
        } else {
            byte nextx = (byte) random.nextInt(2);
            return find(needInd, resInd+1, a, b, c, pcopy(p), nextx, nextx == 0 ? zeroes + 1 : 0) ||
                    find(needInd, resInd+1, a, b, c, pcopy(p), (byte) (1-nextx), nextx == 1 ? zeroes + 1 : 0);
        }
    }

    private int[][] pcopy(int[][] p) {
        int newp[][] = new int[512][2];
        for (int i = 0; i < 512; ++i) {
            newp[i][0] = p[i][0];
            newp[i][1] = p[i][1];
        }
        return newp;
    }
}
