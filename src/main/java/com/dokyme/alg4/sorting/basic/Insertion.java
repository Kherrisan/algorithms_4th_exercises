package com.dokyme.alg4.sorting.basic;

import edu.princeton.cs.algs4.In;

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

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a);
    }
}
