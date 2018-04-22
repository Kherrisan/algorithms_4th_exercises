package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.2.16
 * @author dokym
 * @date 2018/4/23-2:08
 * Description:
 */
public class NaturalMerge implements Sorting {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        int[] p = new int[N];
        int psize = pass(a, p);
        while (psize != 2) {
            for (int i = 0; i < psize; i += 2) {
                Merge.merge(a, p[i], p[i + 1] - 1, p[i + 2] - 1, aux);
            }
            psize = pass(a, p);
        }
    }

    private int pass(Comparable[] a, int[] r) {
        int ri = 0;
        r[ri++] = 0;
        for (int i = 1; i < a.length; i++) {
            if (!Example.less(a[i - 1], a[i])) {
                r[ri++] = i;
            }
        }
        r[ri++] = a.length;
        return ri;
    }

    public static void main(String[] args) {
        Example.testSorting(new NaturalMerge());
    }
}
