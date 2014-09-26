package solved;

import tasks.ITask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

//Answer : 24702
public class Task_096 implements ITask {
    BufferedReader inFile;
    boolean rows[][] = new boolean[9][10];
    boolean cols[][] = new boolean[9][10];
    boolean sqs[][][] = new boolean[3][3][10];
    long d[][] = new long[9][9];

    public void solving() {
        try {
            inFile = new BufferedReader(new FileReader("D:\\sudoku.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int sum = 0;
        while ( next() ) {
            solve(0, 0);
            sum += d[0][0]*100 + d[0][1]*10 + d[0][2];
        }
        System.out.println(sum);
    }

    private boolean solve(int i, int j) {
        if (i == 9) return true;
        if (j == 9) return solve(i+1, 0);
        if (d[i][j] != 0) {
            return solve( i, j+1 );
        }

        for (int dg = 1; dg < 10; ++dg) {
            if ( can(i, j, dg) ) {
                set(i, j, dg);
                if ( solve(i, j+1) ) {
                    unset(i, j, dg);
                    return true;
                }
                unset(i, j, dg);
            }
        }

        d[i][j] = 0;
        return false;
    }

    private void unset(int i, int j, int dg) {
        rows[i][dg] = false;
        cols[j][dg] = false;
        sqs[i / 3][j / 3][dg] = false;
    }

    private void set(int i, int j, int dg) {
        rows[i][dg] = true;
        cols[j][dg] = true;
        sqs[i / 3][j / 3][dg] = true;

        d[i][j] = dg;
    }

    private boolean can(int i, int j, int dg) {
        return ( !rows[i][dg] && !cols[j][dg] && !sqs[i/3][j/3][dg]);
    }

    private boolean next() {
        for (int i = 0; i < 9; ++i) {
            Arrays.fill(rows[i], false);
            Arrays.fill(cols[i], false);
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                Arrays.fill(sqs[i][j], false);
            }
        }

        try {
            String line = inFile.readLine();
            if (line == null) return false;
            String row;
            for (int i = 0; i < 9; ++i) {
                row = inFile.readLine();
                for (int j = 0; j < 9; ++j) {
                    int dg = row.charAt(j) - '0';
                    set(i, j, dg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
