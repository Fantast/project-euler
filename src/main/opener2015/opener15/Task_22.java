package opener15;

import jodd.jerry.Jerry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;
import utils.pairs.Pair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;
import static jodd.jerry.Jerry.jerry;

//Answer :
//see: http://oeis.org/A025426
public class Task_22 extends AbstractTask {

    private BigInteger B0 = ZERO;

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_22());
        Logger.close();
    }

    Pattern NUMBER = Pattern.compile("\\d+");

    long p[] = new long[]{
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103,
            107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223,
            227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347,
            349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
            467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607,
            613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743,
            751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883,
            887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019, 1021, 1031,
            1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151,
            1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223, 1229, 1231, 1237, 1249, 1259, 1277, 1279,
            1283, 1289, 1291, 1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381, 1399, 1409, 1423,
            1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499, 1511, 1523,
            1531, 1543, 1549, 1553, 1559, 1567, 1571, 1579, 1583, 1597, 1601, 1607, 1609, 1613, 1619, 1621
    };

    int integerExponent2[] = new int[]{
            100, 0, 1, 0, 2, 0, 1, 0, 3, 0, 1, 0, 2, 0, 1, 0, 4, 0, 1, 0, 2, 0,
            1, 0, 3, 0, 1, 0, 2, 0, 1, 0, 5, 0, 1, 0, 2, 0, 1, 0, 3, 0, 1, 0, 2,
            0, 1, 0, 4, 0, 1, 0, 2, 0, 1, 0, 3, 0, 1, 0, 2, 0, 1, 0, 6, 0, 1, 0,
            2, 0, 1, 0, 3, 0, 1, 0, 2, 0, 1, 0, 4, 0, 1, 0, 2, 0, 1, 0, 3, 0, 1,
            0, 2, 0, 1, 0, 5, 0, 1, 0, 2, 0, 1, 0, 3, 0, 1, 0, 2, 0, 1, 0, 4, 0,
            1, 0, 2, 0, 1, 0, 3, 0, 1, 0, 2, 0, 1, 0, 7, 0, 1, 0, 2, 0, 1, 0, 3,
            0, 1, 0, 2, 0, 1, 0, 4, 0, 1, 0, 2, 0, 1, 0, 3, 0, 1, 0, 2, 0, 1, 0,
            5, 0, 1, 0, 2, 0, 1, 0, 3, 0, 1, 0, 2, 0, 1, 0, 4, 0, 1, 0, 2, 0, 1,
            0, 3, 0, 1, 0, 2, 0, 1, 0, 6, 0, 1, 0, 2, 0, 1, 0, 3, 0, 1, 0, 2, 0,
            1, 0, 4, 0, 1, 0, 2, 0, 1, 0, 3, 0, 1, 0, 2, 0, 1, 0, 5, 0, 1, 0, 2,
            0, 1, 0, 3, 0, 1, 0, 2, 0, 1, 0, 4, 0, 1, 0, 2, 0, 1, 0, 3, 0, 1, 0,
            2, 0, 1, 0, 8
    };

    int sr[] = new int[p.length];

    CloseableHttpClient httpClient = HttpClients.createDefault();

    BigInteger B1 = valueOf(1);
    BigInteger B2 = valueOf(2);
    BigInteger B4 = valueOf(4);

    public void solving() throws IOException, URISyntaxException {
        BigInteger googol = new BigInteger("10").pow(100);
        for (int i = 0; i <= 256; ++i) {
            progress(i);

            BigInteger n = googol.add(valueOf(i));

            List<Pair<BigInteger, Integer>> r = factor(n);
            int m = 1;
            for (Pair<BigInteger, Integer> factor : r) {
                if (!factor.a.equals(B2)) {
                    if (factor.a.mod(B4).intValue() == 1) {
                        m *= 1 + factor.b;
                    } else {
                        if (factor.b % 2 != 0) {
                            m = 0;
                            break;
                        }
                    }
                }
            }

            if (m % 2 == 1) {
                m = integerExponent2[i] % 2 == 0 ? m - 1 : m + 1;
            }

            sr[i] = m / 2;
        }

        BigInteger res = B1;
        for (int i = 0; i < sr.length; ++i) {
            res = res.multiply(valueOf(p[i]).pow(sr[i]));
        }
        System.out.println(res);
    }

    private List<Pair<BigInteger, Integer>> factor(BigInteger n) throws IOException, URISyntaxException {
        List<Pair<BigInteger, Integer>> factors = new ArrayList<>();

        String query = "http://factordb.com/index.php?query=" + n.toString();
        String content = request(query);

        Jerry doc = jerry(content);
        Jerry links = doc.$("table td a");

        boolean first = true;
        for (Jerry link : links) {
            String href = link.attr("href");
            if (href != null) {
                int idIndex = href.indexOf("index.php?id=");
                if (idIndex >= 0) {
                    if (first) {
                        first = false;
                    } else {
                        String id = href.substring(idIndex + "index.php?id=".length());
                        BigInteger factor = getFactor(id);

                        int power = 0;
                        while (n.mod(factor).equals(B0)) {
                            power++;
                            n = n.divide(factor);
                        }

                        factors.add(new Pair<>(factor, power));
                    }
                }
            }
        }

        return factors;
    }

    private BigInteger getFactor(String id) throws IOException, URISyntaxException {
        String query = "http://www.factordb.com/index.php?showid=" + id;
        String content = request(query);
        Jerry doc = jerry(content);

        Jerry tds = doc.$("table td[align=center]");

        for (Jerry td : tds) {
            if (td.attr("bgcolor") == null) {
                String n = td.text().trim();
                if (NUMBER.matcher(n).matches()) {
                    return new BigInteger(n);
                }
            }
        }
        throw new IllegalStateException("SHouldn't happen");
    }

    private String request(String url) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(url);

        HttpGet get = new HttpGet(uriBuilder.build());

        CloseableHttpResponse response = httpClient.execute(get);
        HttpEntity responseEntity = response.getEntity();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        responseEntity.writeTo(outputStream);

        byte[] rawContent = outputStream.toByteArray();
        String content = new String(rawContent);

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            throw new IllegalStateException("Http request error");
        }

        return content;
    }
}
