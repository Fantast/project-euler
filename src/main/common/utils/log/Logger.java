package utils.log;

import java.io.PrintWriter;

/**
 * @author dpaulenk
 */
public class Logger {
    public static PrintWriter out;
    public static void close() {
        if (out != null) {
            try {
                out.close();
            } catch (Throwable t) {
            }
        }
    }
    
    public static void init(String fileName) {
        close();
        try {
            out = new PrintWriter(fileName);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
