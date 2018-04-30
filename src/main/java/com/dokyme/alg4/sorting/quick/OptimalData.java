package com.dokyme.alg4.sorting.quick;

import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/26-15:36
 * Description:
 */
public class OptimalData {

    public static int[] generate(int length) {
        int[] a = new int[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(length);
        }
        generate(a, 0, a.length - 1);
        return a;
    }

    public static int generate(int[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        exch(a, 0, mid);
        int n = generate(a, lo, mid - 1);
        generate(a, mid + 1, hi);
        exch(a, n, mid);
        return 0;
    }

    public static void main(String[] args) {

    }
}
