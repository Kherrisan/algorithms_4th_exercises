package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/27-21:04
 * Description:
 */
public class California implements Comparable<California> {

    private static final int[] SEQ;

    static {
        SEQ = new int[256];
        int p = 0;
        char[] chs = new char[]{'R', 'W', 'Q', 'O', 'J', 'M', 'V', 'A', 'H', 'B', 'S', 'G', 'Z', 'X', 'N', 'T', 'C', 'I', 'E', 'K', 'U', 'P', 'D', 'Y', 'F', 'L'};
        for (char ch : chs) {
            SEQ[ch] = p++;
        }
    }

    private String name;

    public California(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(California o) {
        int min = Math.min(name.length(), o.name.length());
        for (int i = 0; i < min; i++) {
            if (SEQ[name.charAt(i)] != SEQ[o.name.charAt(i)]) {
                return SEQ[name.charAt(i)] - SEQ[o.name.charAt(i)];
            }
        }
        return name.length() - o.name.length();
    }

    public static void main(String[] args) {
        California[] californias = new California[5];
        californias[0] = new California("ZouDikai");
        californias[1] = new California("Doudou");
        californias[2] = new California("Wenwen");
        californias[3] = new California("Paopao");
        californias[4] = new California("Xiaoxiao");
        new Quick().sort(californias);
        StdOut.println(Arrays.toString(californias));
    }
}
