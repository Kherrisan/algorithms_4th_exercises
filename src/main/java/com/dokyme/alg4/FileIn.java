package com.dokyme.alg4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/2-17:30
 * Description:
 */
public class FileIn {

    private String path;

    private BufferedReader reader;

    private String[] tokens;

    private int cursor;

    private static FileIn fileIn;

    public static boolean setFile(String path) {
        try {
            fileIn = new FileIn(path);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static String readString() {
        return fileIn.readStr();
    }

    public static int readInt() {
        return fileIn.readI();
    }

    private FileIn(String path) throws IOException {
        this.path = path;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
    }

    private boolean readNext() {
        if (tokens == null) {
            return false;
        }
        cursor++;
        if (cursor == tokens.length) {
            try {
                String line = reader.readLine();
                tokens = line.split(" ");
                cursor = 0;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmpty() {
        return false;
    }

    private int readI() {
        if (!readNext()) {
            return Integer.MAX_VALUE;
        }
        return Integer.valueOf(tokens[cursor]);
    }

    private String readStr() {
        if (!readNext()) {
            return null;
        }
        return tokens[cursor];
    }

    public String[] readAllStrings() {
        return null;
    }

    @Override
    protected void finalize() throws Throwable {
        reader.close();
    }

    public static void main(String[] args) {

    }
}
