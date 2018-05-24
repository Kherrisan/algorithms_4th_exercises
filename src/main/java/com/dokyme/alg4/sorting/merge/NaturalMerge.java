package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.2.16
 *
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
                //最后一次迭代的情况
                //  i = psize-1
                //  i = psize-2:merge(a,psize-2,psize-1,psize)
                Merge.merge(a, p[i], p[Math.min(i + 1, psize - 1)], p[Math.min(i + 2, psize - 1)], aux);
            }
            psize = pass(a, p);
        }
    }

    private int pass(Comparable[] a, int[] r) {
        int ri = 0;
        r[ri++] = 0;
        for (int i = 1; i < a.length; i++) {
            if (Example.less(a[i], a[i - 1])) {
                //r记录有序段的最后一个元素的索引
                r[ri++] = i - 1;
            }
        }
        r[ri++] = a.length - 1;
        return ri;
    }

    public static void main(String[] args) {
        Example.testSorting(new NaturalMerge());
    }
}
