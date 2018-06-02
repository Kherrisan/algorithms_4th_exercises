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

    public FileIn(String path) {
        this.path = path;
    }

    public String[] readAllStrings() {
        List<String> list = new ArrayList<>();
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            while ((line = reader.readLine()) != null) {
                for (String word : line.split(" ")) {
                    list.add(word);
                }
            }
            return list.toArray(new String[list.size()]);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String[0];
    }

    public static void main(String[] args) {

    }
}
