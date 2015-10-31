package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static ArrayList<String> readLines(String fileName) throws IOException {
        ArrayList<String> res = new ArrayList<String>();
        BufferedReader inFile = new BufferedReader(new FileReader(fileName));

        String line = inFile.readLine();
        while (line != null) {
            res.add(line);
            line = inFile.readLine();
        }
        inFile.close();
        return res;
    }

    public static void writeFileLines(String fileName, List<String> lines) {
//        PrintWriter outFile = new PrintWriter(fileName);
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            for (String line : lines) {
                outFile.println(line);
            }
            outFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copy(File oldFile, File newFile) throws IOException {
        byte buf[] = new byte[16384];

        FileInputStream inFile = new FileInputStream(oldFile);
        FileOutputStream outFile = new FileOutputStream(newFile);
        int len = 0;
        while ((len = inFile.read(buf)) > 0) {
            outFile.write(buf, 0, len);
        }
        inFile.close();
        outFile.close();
    }

    public static boolean move(File oldFile, File newFile) throws IOException {
        return oldFile.renameTo(newFile);
    }

    public static void cleanDirectory(String s) throws IOException {
        cleanDirectory(new File(s));
    }

    private static void cleanDirectory(File dir) throws IOException {
        if (dir.exists() && dir.isDirectory()) {
            for (File child : dir.listFiles()) {
                if (child.isDirectory()) {
                    cleanDirectory(child);
                }
                if (!child.delete()) {
                    throw new IOException("can't delete: " + child);
                }
            }
        }
    }
}
