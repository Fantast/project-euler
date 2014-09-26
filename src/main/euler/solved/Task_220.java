package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 139776,963904
public class Task_220 implements ITask {
    long n = 1000000000000L;

    String sRL = "FRFRRLFLFR";
    String sLL = "FRFRLLFLFR";

    Place pRL[] = new Place[51];
    Place pLL[] = new Place[51];
    Place LL = new Place(0, 0, 2);

    public void solving() {
        //just to look and notice pattern..
//        generate();
        
        pRL[2] = fromString(sRL, 4);
        pLL[2] = fromString(sLL, 4);

        for (int i = 3; i <= 50; ++i) {
            pRL[i] = pRL[i-1].add(pLL[i-1]);
            pLL[i] = pRL[i-1].add(LL).add(pLL[i-1]);
        }

        Place r = move(50, n, false);
        System.out.println(r.x + "," + r.y);
    }

    private Place move(int i, long n, boolean isLL) {
        if (i == 2) {
            return isLL ? fromString(sLL, n) : fromString(sRL, n);
        }
        
        long dn = 1L<<(i-1L);
        if (n <= dn) {
            return move(i-1, n, false);
        }

        Place r = pRL[i-1];
        if (isLL) {
            return r.add(LL).add( move(i-1, n - dn, true) );
        } else {
            return r.add( move(i-1, n - dn, true) );
       }
    }

    private Place fromString(String s, long lim) {
        long x = 0;
        long y = 0;
        int dir = 0;

        long cn = 0;
        for (int i = 0; i < s.length() && cn <= lim; ++i) {
            switch (s.charAt(i)) {
                case 'R': dir = (dir+1)%4; break;
                case 'L': dir = (dir+3)%4; break;
                case 'F':
                    ++cn;
                    switch (dir) {
                        case 0: y++; break;
                        case 1: x++; break;
                        case 2: y--; break;
                        case 3: x--; break;
                    }
            }
        }

        return new Place(x, y, dir);
    }

    class Place {
        long x;
        long y;
        int dir;

        public Place(long x, long y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public Place add(Place oth) {
            int ndir = (dir + oth.dir)%4;
            long dx = oth.x;
            long dy = oth.y;
            long nx = 0, ny = 0;
            switch (dir) {
                case 0:
                    nx = x + dx;
                    ny = y + dy;
                    break;
                case 1:
                    nx = x + dy;
                    ny = y - dx;
                    break;
                case 2:
                    nx = x - dx;
                    ny = y - dy;
                    break;
                case 3:
                    nx = x - dy;
                    ny = y + dx;
                    break;
            }
            return new Place(nx, ny, ndir);
        }
    }

    private void generate() {
        StringBuffer s = new StringBuffer("Fa");

        for (int i = 1; i <= 10; ++i) {
            StringBuffer s1 = new StringBuffer();
            for (int j = 0; j < s.length(); ++j) {
                char ch = s.charAt(j);
                if (ch == 'a') {
                    s1 = s1.append("aRbFR");
                } else if (ch == 'b') {
                    s1 = s1.append("LFaLb");
                } else {
                    s1.append(ch);
                }
            }
            s = s1;
            System.out.println(s.toString());
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_220());
    }
}
