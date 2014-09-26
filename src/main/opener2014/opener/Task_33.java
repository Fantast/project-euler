package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

import static java.math.BigInteger.*;

//Answer :
public class Task_33 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_33());
        Logger.close();
    }

    Map<Long, Command> all = new TreeMap<>();

    char ar = 'A';
    char br = 'B';
    char cr = 'C';
    char pr = 'P';
    char rr = 'R';
    char xr = 'X';
    char dr = 'D';
    char er = 'E';
    char fr = 'F';

    String regString = "ABCPRXDEF";
    String modString = "T01";
    int rc = regString.length();
    int mc = modString.length();

    BigInteger registers[] = new BigInteger[rc];

    long program[] = new long[]{
            6884, 6778, -8531, -2187, 3, 6962, -2187, 2, 7043, -2187, 2, 7124, -2187, 102, 7448, -2187, 104, 7529, 6003, 6807, -162, 6451, 8676, -2187, 100,
            7205, -243, 6451, 8676, -2187, 101, 7205, 6147, 6896, 6150, 6896, -2187, 102, 7448, 7458, 6162, 6896, 6308, -2187, 102, 7448, -2187, 54, 7529, 6003,
            6807, -2187, 20, 7205, 6898, 7464, -2187, 102, 7448, 5997, 6898, 7140, 6898, 7059, 6147, 6896, 6150, 6896, -2187, 102, 7448, 7458, 6162, 6896, 8493,
            6392, -2187, 102, 7448, -2187, 87, 7529, 6003, 6807, -2187, 20, 7205, 6898, 7464, -2187, 102, 7448, 5997, 6898, 7140, 6898, 7059, -2187, 101, 7205,
            8415, -2187, 0, 7205, 6711
    };

    public void solving() {
        fillAll(-9105, "SHFT", "AT", "C0");
        fillAll(-4182, "TMUL", "D1", "X0");
        fillAll(18, "IFEQ", "R1", "B0");
        fillAll(1824, "IFLT", "AT", "A0");
        fillAll(-2108, "IFGT", "X0", "P1");
        fillAll(4069, "MNUS", "A1", "B1");
        fillAll(6197, "LOAD", "AT", "AT");
        fillAll(9112, "PLUS", "F1", "F1");
        fillAll(-6885, "WEBB", "A0", "R0");

//        System.out.println(all.get(-9835)); //SHFT [AT] CT
//        System.out.println(all.get(-9094)); //SHFT AT DT
//        System.out.println(all.get(-2837)); //IFGT [X0] P1
//        System.out.println(all.get(28));    //IFEQ R1, R1
//        System.out.println(all.get(6205)); //LOAD AT, C1
//        System.out.println(all.get(9127));  //PLUS AT [R1]
//        System.out.println();

//        program = new long[] {-2187, -42, 6962};

//        for (long i = 0; i < program.length; i++) {
//            long st = program[i];
//            System.out.printf("%3d : %5d : %s\n", i, st, all.get(st));
//        }

//        setRegister(ar, 100);

        runProgram();

        System.out.println(getRegister(dr));
    }

    private void runProgram() {
        for (int i = 0; i < program.length; i++) {
            setMemory(i, valueOf(program[i]));
        }
        checkMemory = true;

        long curr = 0;
        while (curr < program.length) {
            Command c = all.get(getMemory(curr).longValue());
//            System.out.println("-------------------------------");
//            System.out.println("CURRENT COMMAND: " + curr + ": " + c);
            switch (c.comm) {
                case "SHFT" : shift(c); break;
                case "IFGT" : ifgt(c); break;
                case "IFEQ" : ifeq(c); break;
                case "LOAD" : load(c); break;
                case "MNUS" : mnus(c); break;
                case "PLUS" : plus(c); break;
                default:
                    throw new IllegalStateException();
            }

            curr = getRegister(pr).longValue();
            if (curr > program.length || curr < 0) {
                throw new IllegalStateException("incorrect P value: " + curr);
            }
        }
    }

    private void ifeq(Command c) {
        BigInteger v1 = getValue(c.r1, c.m1, c.is1);
        BigInteger v2 = getValue(c.r2, c.m2, c.is2);

        if (v1.equals(v2)) {
            incrementPr();
        } else {
            incrementPr();
            incrementPr();
        }
    }

    private void ifgt(Command c) {
        BigInteger v1 = getValue(c.r1, c.m1, c.is1);
        BigInteger v2 = getValue(c.r2, c.m2, c.is2);

        if (v1.compareTo(v2) > 0) {
            incrementPr();
        } else {
            incrementPr();
            incrementPr();
        }
    }

    private void plus(Command c) {
        BigInteger v1 = getValue(c.r1, c.m1, c.is1);
        BigInteger v2 = getValue(c.r2, c.m2, c.is2);

        BigInteger r = v1.add(v2);

//        System.out.println(v1 + " + " + v2 + " = " + r);

        checkOverflow(r);

        setValue(c.r1, c.m1, c.is1, r);

        incrementPr(c.r1, c.is1);
    }

    private void mnus(Command c) {
        BigInteger v1 = getValue(c.r1, c.m1, c.is1);
        BigInteger v2 = getValue(c.r2, c.m2, c.is2);

        BigInteger r = v2.subtract(v1);

//        System.out.println(v1 + " + " + v2 + " = " + r);

        checkOverflow(r);

        setValue(c.r1, c.m1, c.is1, r);

        incrementPr(c.r1, c.is1);
    }

    private void shift(Command c) {
        BigInteger v1 = getValue(c.r1, c.m1, c.is1);
        BigInteger v2 = getValue(c.r2, c.m2, c.is2);

        BigInteger r;
        int base = 3; //~ 5, 10, 7, 8, 9, 10, 11, 12, 13
        String nstr = v1.toString(base);
        if (v2.longValue() > 0) {
            for (int i = 0; i < v2.longValue(); ++i) {
                nstr += "0";
            }
            r = new BigInteger(nstr, base);
        } else {
            throw new IllegalStateException("not implemented");
        }

        checkOverflow(r);

//        System.out.println(v1 + " >> " + v2 + " = " + r);

        setValue(c.r1, c.m1, c.is1, r);

        incrementPr(c.r1, c.is1);
    }

    private void load(Command c) {
        BigInteger v2 = getValue(c.r2, c.m2, c.is2);

        setValue(c.r1, c.m1, c.is1, v2);

        incrementPr(c.r1, c.is1);
    }

    private void checkOverflow(BigInteger r) {
//        if (r < Integer.MIN_VALUE || r > Integer.MAX_VALUE) {
////            throw new IllegalStateException("ArithmeticOverflow");
//            System.out.print("");
//        }
    }

    private BigInteger getValue(char reg, char mod, boolean isaddr) {
        BigInteger value = getRegister(reg, mod);
        return isaddr ? getMemory(value.longValue()) : value;
    }

    private void setValue(char reg, char mod, boolean isaddr, BigInteger value) {
        if (isaddr) {
            setMemory(getRegister(reg, mod).longValue(), value);
        } else {
            setRegister(reg, mod, value);
        }
    }

    private void incrementPr(char modifiedReg, boolean isaddr) {
        if (modifiedReg != pr || isaddr) {
            incrementPr();
        }
    }

    private void incrementPr() {
        setRegister(pr, '0', getRegister(pr).add(ONE), true);
    }

//    Map<Integer, Integer> memory = new HashMap<>();
    BigInteger memory[] = new BigInteger[100000000];
    int zeroMemory = memory.length / 3;
    boolean checkMemory = false;
    private void setMemory(long addr, BigInteger value) {
        if (checkMemory) {
            System.out.println("Put in " + addr + " : " + value);
            if (addr >=0 && addr < program.length) {
                System.out.print("");
            }
        }
        int iaddr = checkMemoryOverflow(addr);
        memory[iaddr] = value;
//        memory.put(addr, value);
    }

    private BigInteger getMemory(long addr) {
        int iaddr = checkMemoryOverflow(addr);
        BigInteger value = memory[iaddr];
        return value == null ? ZERO : value;
//        Integer value = memory.get(addr);
//        return value == null ? 0 : value;
    }

    private int checkMemoryOverflow(long addr) {
        addr = zeroMemory + addr;
        if (addr >= memory.length || addr < 0) {
            throw new IllegalStateException("out of memory range: " + (addr - zeroMemory));
        }
        return (int) addr;
    }

    private BigInteger getRegister(char reg) {
        return getRegister(reg, '0');
    }

    private BigInteger getRegister(char reg, char mod) {
        BigInteger val = reg == rr ? ZERO : registers[regString.indexOf(reg)];
        if (val == null) {
            val = ZERO;
        }
        if (mod == 'T') {
            val = val.subtract(ONE);
        } else if (mod == '1') {
            val = val.add(ONE);
        }

        return val;
    }

    private void setRegister(char reg, BigInteger value) {
        setRegister(reg, '0', value);
    }

    private void setRegister(char reg, char mod, BigInteger value) {
        setRegister(reg, mod, value, false);
    }

    private void setRegister(char reg, char mod, BigInteger value, boolean skipX) {
        if (reg == rr) {
            return;
        }

        if (mod == 'T') {
            value = value.add(ONE);
        } else if (mod == '1') {
            value = value.subtract(ONE);
        }

        BigInteger prev = getRegister(reg, mod);

        int ind = regString.indexOf(reg);
        registers[ind] = value;

        if (!skipX && reg != xr) {
            setRegister(xr, prev);
        }
    }

    private void fillAll(int code, String command, String a1, String a2) {
        //AT - 9*3 = 27, A0 - 27, A1-27, A* - 81
        char r1 = a1.charAt(0);
        char m1 = a1.charAt(1);
        char r2 = a2.charAt(0);
        char m2 = a2.charAt(1);

        int ri1 = regString.indexOf(r1);
        int mi1 = modString.indexOf(m1);
        int ri2 = regString.indexOf(r2);
        int mi2 = modString.indexOf(m2);

        int before = ri1 * 81 + mi1 * 27 + ri2 * 3 + mi2 + 729;
        code = code - before;

        for (ri1 = 0; ri1 < rc; ++ri1) {
            r1 = regString.charAt(ri1);
            for (mi1 = 0; mi1 < mc; ++mi1) {
                m1 = modString.charAt(mi1);
                for (ri2 = 0; ri2 < rc; ++ri2) {
                    r2 = regString.charAt(ri2);
                    for (mi2 = 0; mi2 < mc; ++mi2) {
                        m2 = modString.charAt(mi2);
                        all.put((long) code, new Command(command, r1, m1, r2, m2, true, false));
                        ++code;
                    }
                }
            }
        }

        for (ri1 = 0; ri1 < rc; ++ri1) {
            r1 = regString.charAt(ri1);
            for (mi1 = 0; mi1 < mc; ++mi1) {
                m1 = modString.charAt(mi1);
                for (ri2 = 0; ri2 < rc; ++ri2) {
                    r2 = regString.charAt(ri2);
                    for (mi2 = 0; mi2 < mc; ++mi2) {
                        m2 = modString.charAt(mi2);
                        all.put((long) code, new Command(command, r1, m1, r2, m2, false, false));
                        ++code;
                    }
                }
            }
        }

        for (ri1 = 0; ri1 < rc; ++ri1) {
            r1 = regString.charAt(ri1);
            for (mi1 = 0; mi1 < mc; ++mi1) {
                m1 = modString.charAt(mi1);
                for (ri2 = 0; ri2 < rc; ++ri2) {
                    r2 = regString.charAt(ri2);
                    for (mi2 = 0; mi2 < mc; ++mi2) {
                        m2 = modString.charAt(mi2);
                        all.put((long) code, new Command(command, r1, m1, r2, m2, false, true));
                        ++code;
                    }
                }
            }
        }
    }

    public class Command {
        public String comm;
        public char r1;
        public char m1;
        public char r2;
        public char m2;
        public final boolean is1;
        public final boolean is2;

        public Command(String comm, char r1, char m1, char r2, char m2, boolean is1, boolean is2) {
            this.comm = comm;
            this.r1 = r1;
            this.m1 = m1;
            this.r2 = r2;
            this.m2 = m2;
            this.is1 = is1;
            this.is2 = is2;
        }

        @Override
        public String toString() {
            String R1 = is1 ? "[" + r1 + m1 + "]" : r1 + "" + m1;
            String R2 = is2 ? "[" + r2 + m2 + "]" : r2 + "" + m2;
            return comm + " " + R1 + " " + R2;
        }
    }
}
