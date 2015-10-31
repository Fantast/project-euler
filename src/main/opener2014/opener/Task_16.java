package opener;

import tasks.ITask;
import tasks.Tester;
import utils.FileUtils;
import utils.log.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Answer :
public class Task_16 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_16());
        Logger.close();
    }

    static class Word {
        final char[] word;
        final long score;

        Word(char[] word, long score) {
            this.word = word;
            this.score = score;
        }
    }

    public void solving() throws IOException {
        //creates the single metric to use - in this case the simple
        // Levenshtein is used, this is far from recomended as much better
        // metrics can be employed in most cases, please see the sourceforge
        // SimMetric forums for advice on the best metric to employ in
        // differing situations.

        char[] t1 = "abcee".toCharArray();
        char[] t2 = "abcee".toCharArray();

        System.out.println(levenshteinDistance(t1, t2));
        System.out.println(levenshteinDistance(t2, t1));

        List<String> wordStings = FileUtils.readLines("D:\\Downloads\\task.txt");
//        wordStings = wordStings.subList(0, 300);

        char[][] wordChars = new char[wordStings.size()][];
        for (int i = 0; i < wordChars.length; ++i) {
            wordChars[i] = wordStings.get(i).toCharArray();
        }

        List<Word> words = new ArrayList<Word>();

        char[] w = null;
        long mx = 0;
        long scores[] = new long[wordStings.size()];
        for (int i = 0; i < wordChars.length; i++) {
            if (i % 500 == 0) {
                System.out.println("Progress: " + i);
            }

            char[] w1 = wordChars[i];
            long score = scores[i];
            for (int j = i+1; j < wordChars.length; j++) {
                char[] w2 = wordChars[j];

                long similarity = levenshteinDistance(w1, w2);

                score += similarity;
                scores[j] += similarity;
            }

            words.add(new Word(w1, score));

            if (score > mx) {
                System.out.println("Better: " + new String(w1) + ": " + score);
                mx = score;
                w = w1;
            }
        }

        System.out.println(mx);
        System.out.println(w);

        Collections.sort(words, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return -Double.compare(o1.score, o2.score);
            }
        });

        List<String> outLines = new ArrayList<String>();
        for (Word a : words) {
            outLines.add(new String(a.word) + " : " + a.score);
        }
        FileUtils.writeFileLines("D:\\Downloads\\task3.out.txt", outLines);

    }

    int[] v0 = new int[50];
    int[] v1 = new int[50];

    public int levenshteinDistance(char[] s, char[] t) {
        // degenerate cases
//        if (s == t) return 0;

        int len1 = s.length;
        int len2 = t.length;

        if (len1 == 0) return len2;
        if (len2 == 0) return len1;

        // create two work vectors of integer distances

        // initialize v0 (the previous row of distances)
        // this row is A[0][i]: edit distance for an empty s
        // the distance is just the number of characters to delete from t
        for (int i = 0; i <= len2; i++)
            v0[i] = i;

        for (int i = 0; i < len1; i++) {
            // calculate v1 (current row distances) from the previous row v0

            // first element of v1 is A[i+1][0]
            //   edit distance is delete (i+1) chars from s to match empty t
            v1[0] = i + 1;

            // use formula to fill in the rest of the row
            for (int j = 0; j < len2; j++) {
                int cost = (s[i] == t[j]) ? 0 : 1;
                v1[j + 1] = min(v1[j] + 1, v0[j + 1] + 1, v0[j] + cost);
            }

            // copy v1 (current row) to v0 (previous row) for next iteration
            for (int j = 0; j <= len2; j++)
                v0[j] = v1[j];
        }

        return v1[len2];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
