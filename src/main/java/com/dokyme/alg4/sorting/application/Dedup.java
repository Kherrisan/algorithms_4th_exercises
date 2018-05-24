package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/27-12:19
 * Description:
 */
public class Dedup {

    public static String[] dedup(String[] a) {
        new Quick().sort(a);
        String[] res = new String[a.length];
        int size = 0;
        for (int i = 0; i < a.length; ) {
            String current = a[i++];
            while (i < a.length && current.equals(a[i])) {
                i++;
            }
            res[size++] = current;
        }
        String[] newRes = new String[size];
        System.arraycopy(res, 0, newRes, 0, size);
        return newRes;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"a", "b", "c", "a", "aa", "b", "a", "f"};
        a = dedup(a);
        StdOut.println(Arrays.toString(a));
    }
}
