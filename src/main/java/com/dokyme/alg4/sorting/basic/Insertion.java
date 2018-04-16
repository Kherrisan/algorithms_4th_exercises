package com.dokyme.alg4.sorting.basic;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/3/10-13:45
 * Description:
 */
public class Insertion {

    /**
     * 对于有序或者部分有序的数组很有效，因为能够迅速发现一个元素已经就位。
     * 平均情况下需要平方的时间。
     * 插入排序需要的交换操作和数组中倒置的数量相同（每次交换都减少一对倒置）。
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            //将a[i]插入到a[0],a[1],...,a[i-1]中去
            for (int j = i; j > 0 && Example.less(a[j], a[j - 1]); j--) {
                //如果a[j]比有序的子序列中比前一个小，就和前一个交换次序，否则内循环结束。
                Example.exch(a, j, j - 1);
            }
        }
    }

    public static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                Example.exch(a, j, j - 1);
            }
        }
    }

    public static void sortWithAutoBoxing(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && Example.less(a[j], a[j - 1]); j--) {
                Example.exch(a, j, j - 1);
            }
        }
    }

    public static void sortWithoutExch(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            Comparable t = a[i];
            int j = i;
            for (; j > 0 && t.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = t;
        }
    }

    public static void testSortWithoutExch() {
        Double[] array = new Double[10];
        for (int j = 0; j < array.length; j++) {
            array[j] = StdRandom.uniform();
        }
        sortWithoutExch(array);
        StdOut.print("Is sorted:" + Example.isSorted(array));

    }

    public static void sort(Comparable[] a, boolean useGuardian) {
        int guardian = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[guardian].compareTo(a[i]) > 0) {
                guardian = i;
            }
        }
        Example.exch(a, 0, guardian);
        //第一次找出最小的元素放到数组的最左边，这样在内循环中就不需要判断j>0的数组越界的问题。
        for (int i = 2; i < a.length; i++) {
            for (int j = i; a[j].compareTo(a[j - 1]) < 0; j--) {
                Example.exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        testSortWithoutExch();
    }
}
