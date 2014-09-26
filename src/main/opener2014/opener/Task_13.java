package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

//Answer :
public class Task_13 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_13());
        Logger.close();
    }


    String s = "1220021001202010211110000122220012222111";
    char ch[] = s.substring(0, 40).toCharArray();
    int n = ch.length;
    int ds[] = new int[n];
    PrintWriter out;

    public void solving() throws FileNotFoundException {
        System.out.println(s);
        for (int i = 0 ; i < n; ++i) {
            ds[n-1-i] = ch[i] - '0';
        }
        
        int[] fr = fromgray(3, ds);
        System.out.println(Arrays.toString(fr));
        
        String s = "";
        for (int d : fr) {
            s = d + s;
        }
        System.out.println(s);
        System.out.println(new BigInteger(s, 3));
        
        
    }

//// inputs: base, digits, value
//// output: gray
//// Convert a value to a graycode with the given base and digits.
//// Iterating through a sequence of values would result in a sequence
//// of Gray codes in which only one digit changes at a time.
//        void to_gray(unsigned base, unsigned digits, unsigned value, unsigned gray[digits])
//        {
//            unsigned baseN[digits]; // Stores the ordinary base-N number, one digit per entry
//            unsigned i;             // The loop variable
//
//            // Put the normal baseN number into the baseN array. For base 10, 109
//            // would be stored as [9,0,1]
//            for (i = 0; i < digits; i++) {
//                baseN[i] = value % base;
//                value    = value / base;
//            }
//
//            // Convert the normal baseN number into the graycode equivalent. Note that
//            // the loop starts at the most significant digit and goes down.
//            unsigned shift = 0;
//            while (i--) {
//                // The gray digit gets shifted down by the sum of the higher
//                // digits.
//                gray[i] = (baseN[i] + shift) % base;
//                shift = shift + base - gray[i]; // Subtract from base so shift is positive
//            }
//        }
//// EXAMPLES
//// input: value = 1899, base = 10, digits = 4
//// output: baseN[] = [9,9,8,1], gray[] = [0,1,7,1]
//// input: value = 1900, base = 10, digits = 4
//// output: baseN[] = [0,0,9,1], gray[] = [0,1,8,1]
    
    
    public int[] togray(int base, int[] digits) {
        int[] gray = new int[digits.length];
        
        int shift = 0;
        for (int i = digits.length-1; i >= 0; i--) {
            gray[i] = (digits[i] + shift) % base;
            shift = shift + base - gray[i];
        }
        
        return gray;
    }
    
    public int[] fromgray(int base, int[] gray) {
        int[] digits = new int[gray.length];
        
        int shift = 0;
        for (int i = digits.length-1; i >=0; i--) {
            digits[i] = (gray[i] + shift) % base;
//            shift = shift + base - gray[i];
            shift = digits[i];
        }
        
        return digits;
    }
    
    
        
}
