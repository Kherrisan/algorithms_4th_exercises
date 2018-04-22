package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/22-14:24
 * Description:
 */
public class LinearBlockMerge implements Sorting {

    public static final int BLOCK_SIZE = 20;

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int i = 0; i < N / BLOCK_SIZE; i++) {
            int minb = i;
            for (int j = i + 1; j < N / BLOCK_SIZE; j++) {
                if (less(a[j * BLOCK_SIZE], a[minb * BLOCK_SIZE])) {
                    minb = j;
                }
            }
            exchBlock(a, i, minb);
        }
        for (int i = 0; i < N / BLOCK_SIZE - 1; i++) {
            merge(a, i * BLOCK_SIZE, (i + 1) * BLOCK_SIZE - 1, (i + 2) * BLOCK_SIZE - 1, aux);
        }
    }

    private static void exchBlock(Comparable[] a, int bi, int bj) {
        for (int k = 0; k < BLOCK_SIZE; k++) {
            exch(a, bi * BLOCK_SIZE + k, bj * BLOCK_SIZE + k);
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        int i = lo, j = mid;
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(a[i], a[j])) {
                a[k] = a[i++];
            } else {
                a[k] = a[j++];
            }
        }
    }

    public static void main(String[] args) {
        Comparable[] a = generateTestData(new Double(1), 100);
        new LinearBlockMerge().sort(a);
        assert isSorted(a);
    }

}
