package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static utils.MyMath.isBitSet;
import static utils.MyMath.setBit;
import static utils.MyMath.unSetBit;

//Answer :
public class Task_24 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_24());
        Logger.close();
    }

    String[] all = new String[]{
            "FQFRLEDUDJYNYIIILDTYGCRPEHIHAUPXZXQIRFKQHTOBZRIRVXNARWTKZPPKSHBTEEPWIYZOBKTMGOXLKJOQDINFAQJKUSAKRIGFSVDKJSMBPVNKIRZWVXDIXDRWQQPSRNMLNZJCIDDIMGNCQSVQVAEHAVWMIGFRJDJBQTQEBLTMSDZNCPWOUGANSCOXUJHICSTAQMABQJMXNXYBDSMETRJFDHYNIW",
            "IMVSJPCLFCTDQZTLLWQSODRSJWEUWZJXMWSRYTACMPDUOJGMLZCYBLHOCZRODZPLVGGUWZFZYGFHTDOEGGYDPJCKOMWXFOYTGPPQNJNIEEPWZXYSFQGXECULFAMTMRTVTMMQNTRBECGWOWTMJUOVTSLDHWOJGKNVKPJWRYCDQRHXSUYYDTPMPYLLBBTBKIZSWVRYDIZFEUMNKGWQUZAGWVWSCOPFCNQKXDQOTAHNLOL",
            "ITPGFIFVUKVFRCQKMAMCNUKDQVCXRTBMMJHOCNGPIBINYXZMUWGNKGZFEOPOJFKCKNWJHHOEQLETAMPWAYRLNXGPCPAAOXRDTLGZNDBSFVDQRPXRQAZNLMYLRSBHJMOABVBACIOUKQRXRVFRSNBUKUXVIMRTERLYOUHTQQOUFAAIATTVOWVUCUFEVEBBHAAMPIETQKXUKUTGMKWDWNSAREVWGEWMK",
            "KBONFIEKNTZOFOVERXLAWDTTCQYOXKMOZNWPDFEVSVESWUYIWJWVIPRUBNYMMGLMZJKZFNZMJCEHXEIHAHXDHMKGOSZXJDXLQSNXWJAWKRJETIVASDMHYISOAGCGSHKAVVQGQJCBSYECIMVSJPCLFCTDQZTLLWQSODRSJWEUWZJXMWSRYTACMPDUOJGMLZCYBLHOCZRODZPLVGGUWZFZYGFHTD",
            "KKTKLQGSLGLZRNQKSMRHDFQQEBDIZHUJBBMQVLTIYIFUOUFMRUVLGVSSIIZGJOCFZYNQIGDVFCJQPBZDXCJWPAKIDCEAIBXOZYVJBORASLMYCIVKOLSZAGCMAMQMVQTFLQNFPFEEZKTDHBXZPJBWXTBUOBPSNTLRXHPIAMFCALJPIWZMMKVZPAXWABODNTILHIDCIUCJWQYUYLPAISEMYYWJXWOTKB",
            "KQRXRVFRSNBUKUXVIMRTERLYOUHTQQOUFAAIATTVOWVUCUFEVEBBHAAMPIETQKXUKUTGMKWDWNSAREVWGEWMKYYFAUKCGUOXTGXVPILHNAWKKCCJZYTHGZMGGQBSDGETAOSTMAOLOSLLKZQOZYFBZZUGMCIYXPJKJUTULNJCMKLSYGVJQOEWDYCHSPXJRXFWNOVYUFJITAPNZYQTVXRU",
            "LRXHPIAMFCALJPIWZMMKVZPAXWABODNTILHIDCIUCJWQYUYLPAISEMYYWJXWOTKBOMHOLUHISRKSPVCNFLGZMKUBITIOIREMLDRGPKEKAVSYESZTFNZHNWWRAZKGVHVIGMGZYHXJQUSXUTVUOCVIPJSYYYCWHEYYYGQWCIBPLQWMXEUUTEMABCENCGANXYZFYXRYKBCRHJIICUYTAKEHDIXXIJDJYSGNLKXANVDZAU",
            "MCIYXPJKJUTULNJCMKLSYGVJQOEWDYCHSPXJRXFWNOVYUFJITAPNZYQTVXRUYSATKSYPMTKCSCYMKBPFHVRTCIVHNMJAMEPJPXPINPECNMMKJGPPOJGLEUXHIKOMAFBGYKUYYZNFCTEGAPVINUJMNEGVTUWEYKOCPFQFRLEDUDJYNYIIILDTYGCRPEHIHAUPXZXQIRFKQHTOBZRIRV",
            "MXFZRVTPMXSIPXDYACTGCMDUHFNYGXZHTEZQMWPGRPAGYDSNLXHYHTMMSEYQMCEGOZYPMPVFJDVYKBIQIMSLKFUUGSLVMRHQUPZXLIYKSGCQWHRESMWWILNDIXULALJIEZOKQZMUCSZZKHWNYHJZAFHLNMYVOBXEXZQTCLRURVHCOBZTCPOVPHNQECTDKSMQFEBKQSLFNXAGUFTFFBBKNGIWHIWMT",
            "QTCLRURVHCOBZTCPOVPHNQECTDKSMQFEBKQSLFNXAGUFTFFBBKNGIWHIWMTMKOELVLWMOKNFIGCFMOHFTCDYUOYHTFKJFLTYOADKQXTSWUOINNVKBZPDKUDELFYKMCBPIJWJSJLDGAABYBTKBONFIEKNTZOFOVERXLAWDTTCQYOXKMOZNWPDFEVSVESWUYIWJWVIPRUBNYMMGLMZJKZFNZMJC",
            "RYCDQRHXSUYYDTPMPYLLBBTBKIZSWVRYDIZFEUMNKGWQUZAGWVWSCOPFCNQKXDQOTAHNLOLJYLKJHNYTYBHTZUXFCUIZJMAVGCWRZCIMGRMYIVWSWROWEHAQFXDRJTFRMKZTUBWHKKTKLQGSLGLZRNQKSMRHDFQQEBDIZHUJBBMQVLTIYIFUOUFMRUVLGVSSIIZGJOCFZYNQIGDVFCJ",
            "VQVAEHAVWMIGFRJDJBQTQEBLTMSDZNCPWOUGANSCOXUJHICSTAQMABQJMXNXYBDSMETRJFDHYNIWRTLKZXVZYNJIVMHHTDSABWHTNKBTWWSXEBBAFHBEYDFDOERHNXYSCRMHQZAAQFXBPZDNJHKIGLWPXABFWZMXFZRVTPMXSIPXDYACTGCMDUHFNYGXZHTEZQMWPGRPAGYDSNLXHYHTMMSEYQMC",
            "YCWHEYYYGQWCIBPLQWMXEUUTEMABCENCGANXYZFYXRYKBCRHJIICUYTAKEHDIXXIJDJYSGNLKXANVDZAUUIQARKFVODMZXUKCZNQSEFUSHYHGZUGMUZZXRZBLDFWIXVIQVOZLDPAQRXVUCAURTPCCQQGVIJCVQGLPUBLMYRGTQLHSQJBUZAWSBXIKBNTXPYZGTCWHSKTUIQDK"
    };

    int n = all.length;

    int dist[][] = new int[n][n];

    Map<Integer, Integer> path[][][] = new Map[n][n][n];

    public void solving() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               for (int k = 0; k < n; k++) {
                   if (i!= j && i!=k && j!=k && (all[i] + all[j]).contains(all[k])) {
                       System.out.println(i + " " + j  + " " + k);
                   }
                   path[i][j][k] = new HashMap();
               }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int d = distance(all[i], all[j]);
                    dist[i][j] = all[j].length() - (all[i].length() + all[j].length() - d);
                    path[1][i][j].put((1 << i) + (1 << j), d);
                }
            }
        }

        for (int distance = 2; distance < n; ++distance) {
            Map[][] prev = path[distance - 1];
            Map[][] curr = path[distance];

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    Map<Integer, Integer> pij = prev[i][j];
                    for (Map.Entry<Integer, Integer> e : pij.entrySet()) {
                        int p = e.getKey();
                        int d = e.getValue();

                        for (int k = 0; k < n; ++k) {
                            if (!isBitSet(p, k)) {
                                int npath = setBit(p, k);

                                Map<Integer, Integer> cik = curr[i][k];
                                Integer dik = cik.get(npath);
                                if (dik == null || dik > d + dist[j][k]) {
                                    cik.put(npath, d + dist[j][k]);
                                }
                            }
                        }
                    }
                }
            }
        }

        int currmask = (1<<n) - 1;
        int best = Integer.MAX_VALUE;
        int besti = 0;
        int bestk = 0;
        for (int i = 0; i < n; ++i) {
            for (int k = 0; k < n; ++k) {
                if (i == k) {
                    continue;
                }
                Map<Integer, Integer> dik = path[n-1][i][k];
                Integer d = dik.get(currmask);
                if (d == null) {
                    System.out.println(i);
                }
                if (d < best) {
                    best = d;
                    besti = i;
                    bestk = k;
                }
            }
        }

        currmask = unSetBit(currmask, bestk);

        List<Integer> ps = new ArrayList<>();
        ps.add(bestk);

        for (int distance = n-2; distance >= 1; --distance) {
            Map<Integer, Integer>[][] curr = path[distance];

            for (int j = 0; j < n; ++j) {
                if (j != besti && isBitSet(currmask, j) && (best == curr[besti][j].get(currmask) + dist[j][bestk])) {
                    best = curr[besti][j].get(currmask);
                    currmask = unSetBit(currmask, j);
                    ps.add(j);

                    bestk = j;
                    break;
                }
            }
        }

        Collections.reverse(ps);

        String res = all[besti];
        for (int si : ps) {
            res = merge(res, all[si]);
        }

        System.out.println(res);
        System.out.println(res.length());
    }

    private String merge(String s1, String s2) {
        int d = distance(s1, s2);
        int di = s1.length() + s2.length() - d;
        return s1.substring(0, s1.length() - di) + s2;
    }

    private int distance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        for (int i = 0; i < len1; ++i) {
            int je = min(len1 - i, len2);
            boolean ok = true;
            for (int j = 0; j < je; ++j) {
                if (s1.charAt(i + j) != s2.charAt(j)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return max(len1, i + len2);
            }

        }
        return len1 + len2;
    }
}
