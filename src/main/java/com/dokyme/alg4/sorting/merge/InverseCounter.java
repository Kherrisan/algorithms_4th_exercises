package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.basic.Example;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/23-18:55
 * Description:
 */
public class InverseCounter {

    public int count(Comparable[] a) {
        Comparable[] copy = new Comparable[a.length];
        return count(a, 0, a.length - 1, copy);
    }

    public int count(Comparable[] a, int lo, int hi, Comparable[] copy) {
        if (lo >= hi) {
            return 0;
        }
        int mid = lo + (hi - lo) / 2;
        int leftCount = count(a, lo, mid, copy);
        int rightCount = count(a, mid + 1, hi, copy);
        int mergeCount = merge(a, lo, mid, hi, copy);
        return leftCount + rightCount + mergeCount;
    }

    private int merge(Comparable[] a, int lo, int mid, int hi, Comparable[] copy) {
        int count = 0;
        int i = lo, j = mid + 1;
        System.arraycopy(a, lo, copy, lo, hi - lo + 1);
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) {
                a[k] = copy[j++];
            } else if (j == hi + 1) {
                a[k] = copy[i++];
            } else if (Example.less(copy[j], copy[i])) {
                count += (mid - i + 1);
                a[k] = copy[j++];
            } else {
                a[k] = copy[i++];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Comparable[] array = Example.generateTestData(new Integer(1), 10);
        for (int i = 0; i < array.length; i++) {
            StdOut.print(array[i].toString() + "\t");
        }
        StdOut.print(new InverseCounter().count(array) + "\n");
    }
}
