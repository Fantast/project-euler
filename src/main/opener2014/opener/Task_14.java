package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;
import utils.pairs.LongIntPair;

import java.math.BigInteger;
import java.util.*;

import static java.math.BigInteger.valueOf;
import static java.util.Collections.sort;

//Answer :
public class Task_14 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_14());
        Logger.close();
    }

    A a = new A();
    B b = new B();
    C c = new C();
    D d = new D();
    
    long aa, ab, ac, ad;
    long ba, bb, bc, bd;
    long ca, cb, cc, cd;
    long da, db, dc, dd;
    
    public void solving() {
//            A) Всегда возвращать false.
//            B) Если противник на предыдущем шаге вернул false, то изменить свой прошлый ответ, иначе его повторить; на первом шаге вернуть false.
//            C) Повторить предыдущий ответ противника; на первом шаге вернуть true.
//            D) Если противник хотя бы раз вернул false, возвращать false; на первом шаге вернуть true.

        aa = compete(a, a);
        ab = compete(a, b);
        ac = compete(a, c);
        ad = compete(a, d);

        ba = compete(b, a);
        bb = compete(b, b);
        bc = compete(b, c);
        bd = compete(b, d);

        ca = compete(c, a);
        cb = compete(c, b);
        cc = compete(c, c);
        cd = compete(c, d);

        da = compete(d, a);
        db = compete(d, b);
        dc = compete(d, c);
        dd = compete(d, d);

        long mx = 0;
        long mxa = 0;
        long mxb = 0;
        long mxc = 0;
        LinkedHashMap<Long, Integer> current = new LinkedHashMap<Long, Integer>();
        ArrayList<Long> path = new ArrayList<Long>();
        
        long rr = find(1, 1, 1, 0, current, path);
        System.out.println(rr);
        for (long a = 0; a <= 100; ++a) {
            long be = 100 - a;
            for (long b = 0; b <= be; ++b) {
                long ce = 100 - a - b;
                for (long c = 0; c <= ce; ++c) {
                    long r = find(a, b, c, 100-a-b-c, current, path);
                    if (r > mx) {
                        mx = r;
                        mxa = a;
                        mxb = b;
                        mxc = c;
                    } else if (r == mx) {
                        long mxd = 100 - mxa - mxb - mxc;
                        System.out.println(mx);
                        System.out.println(mxa + " " + mxb + " " + mxc + " " + mxd);
                        System.out.println(getNum(mxa, mxb, mxc, mxd));
                        System.out.println("---");
                    }
                }
            }
        }
        long mxd = 100 - mxa - mxb - mxc;
        System.out.println(mx);
        System.out.println(mxa + " " + mxb + " " + mxc + " " + mxd);
        System.out.println(getNum(mxa, mxb, mxc, mxd));
    }

    private BigInteger getNum(long mxa, long mxb, long mxc, long mxd) {
        BigInteger res = valueOf(2).pow((int) mxa);
        res = res.multiply(valueOf(3).pow((int) mxb));
        res = res.multiply(valueOf(5).pow((int) mxc));
        res = res.multiply(valueOf(7).pow((int) mxd));
        return res;
    }

    private Map<Long, Long> s = new HashMap<Long, Long>();
    private Map<Long, Boolean> cycle = new HashMap<Long, Boolean>();
    
    private long find(long a, long b, long c, long d, LinkedHashMap<Long, Integer> current, ArrayList<Long> path) {
        long state = getState(a, b, c, d);
        Long r = s.get(state);
        if (r == null) {

            Integer ind = current.get(state);
            if (ind != null) {
//                System.out.println("Cycle found");
                long cycleLength = current.size() - ind;
                for (int i = ind; i < current.size(); ++i) {
                    Long st = path.get(i);
                    s.put(st, cycleLength);
                    cycle.put(st, true);
                }
                return cycleLength;
            }
            
            if (a == 0 && b == 0 && c == 0
                    || a == 0 && b == 0 && d == 0
                    || a == 0 && c == 0 && d == 0
                    || c == 0 && b == 0 && d == 0) {
                r = 0L;
            } else {
                long as = 1 * ((a-1) * aa   + b*ab      + c*ac      + d*ad);
                long bs = 1 * (a * ba       + (b-1)*bb  + c*bc      + d*bd);
                long cs = 1 * (a * ca       + b*cb      + (c-1)*cc  + d*cd);
                long ds = 1 * (a * da       + b*db      + c*dc      + (d-1)*dd);
                if (a == 0) as = 0;
                if (b == 0) bs = 0;
                if (c == 0) cs = 0;
                if (d == 0) ds = 0;

                List<LongIntPair> all = rate(as, bs, cs, ds);
                int i = 0;
                while (getCnt(all.get(i).b, a, b, c, d) == 0) {
                    i++;
                }
                
                long mn = all.get(i).a;
                for (int j = i; j < 4; ++j) {
                    LongIntPair sm = all.get(j);
                    if (sm.a == mn) {
                        switch (sm.b) {
                            case 1: a--; break;
                            case 2: b--; break;
                            case 3: c--; break;
                            case 4: d--; break;
                        }
                    }
                }

                long mx = all.get(3).a;
                for (int j = 3; j >= 0; --j) {
                    LongIntPair sm = all.get(j);
                    if (sm.a == mx) {
                        switch (sm.b) {
                            case 1:
                                if (a == 255) {
                                    System.out.println("danger a");
                                }
                                a++;
                                break;
                            case 2:
                                if (b == 255) {
                                    System.out.println("danger b" );
                                }
                                b++;
                                break;
                            case 3:
                                if (c == 255) {
                                    System.out.println("danger c");
                                }
                                c++;
                                break;
                            case 4:
                                if (d == 255) {
                                    System.out.println("danger d");
                                }
                                d++;
                                break;
                        }
                    }
                }
                current.put(state, current.size());
                path.add(state);
                r = find(a, b, c, d, current, path);
                current.remove(state);
                path.remove(path.size() - 1);
                
                if (cycle.containsKey(state)) {
                    return s.get(state);
                }
                
                if (s.containsKey(state)) {
                    System.out.println("STRANGE");
                }
                r++;
            }
            s.put(state, r);
            return r;
        }
        return r;
    }

    private long getState(long a, long b, long c, long d) {
        return a + (b<<15L) + (c<<30L) + (d<<45L);
    }

    private long getCnt(int i, long a, long b, long c, long d) {
        if (i == 1) {
            return a;
        } else if (i == 2) {
            return b;
        } else if (i == 3) {
            return c;
        } else if (i == 4) {
            return d;
        }
        throw new IllegalStateException("ouch");
    }

    private List<LongIntPair> rate(long as, long bs, long cs, long ds) {
        List<LongIntPair> all = new ArrayList<LongIntPair>();
        all.add(new LongIntPair(as, 1));
        all.add(new LongIntPair(bs, 2));
        all.add(new LongIntPair(cs, 3));
        all.add(new LongIntPair(ds, 4));
        sort(all);
        return all;
    }

    private int compete(Alg a, Alg b) {
        boolean aprev = a.first();
        boolean bprev = b.first();
        boolean anofalses = aprev;
        boolean bnofalses = bprev;
        int ascore = score(aprev, bprev);
        for (int step = 1; step < 100; ++step) {
            boolean acurr = a.get(aprev, bprev, bnofalses);
            boolean bcurr = b.get(bprev, aprev, anofalses);
            
            ascore += score(acurr, bcurr);
            
            aprev = acurr;
            bprev = bcurr;
            anofalses &= acurr;
            bnofalses &= bcurr;
        }
        
        return ascore;
    }

    private int score(boolean my, boolean his) {
        if (my) {
            return his ? 3 : 0;
        }
        return his ? 5 : 1;
    }
    
    interface Alg {
        boolean first();
        boolean get(boolean myprev, boolean hisprev, boolean hisnofalses);
    }

    class A implements Alg {
        @Override
        public boolean first() { return false; }

        @Override
        public boolean get(boolean myprev, boolean hisprev, boolean hisnofalses) {
            return false;
        }
    }

    class B implements Alg {
        @Override
        public boolean first() { return false; }

        @Override
        public boolean get(boolean myprev, boolean hisprev, boolean hisnofalses) {
            return hisprev ? myprev : !myprev;
        }
    }
    class C implements Alg {
        @Override
        public boolean first() { return true; }

        @Override
        public boolean get(boolean myprev, boolean hisprev, boolean hisnofalses) {
            return hisprev;
        }
    }
    class D implements Alg {
        @Override
        public boolean first() { return true; }

        @Override
        public boolean get(boolean myprev, boolean hisprev, boolean hisnofalses) {
            return hisnofalses;
        }
    }
}
