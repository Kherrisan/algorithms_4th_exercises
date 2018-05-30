package com.dokyme.alg4.sorting.application;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.5.21
 * 假设以行为向量，列为维度
 *
 * @author dokym
 * @date 2018/5/29-21:05
 * Description:
 */
public class Vector {

    private Comparable[][] v;

    public Vector(Comparable[][] v) {
        this.v = v;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                builder.append(v[i][j]).append(",");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * 可以只从第二维开始使用稳定排序，也可以全都采用稳定排序
     */
    public void sort() {
        Comparable[][] aux = new Comparable[v.length][];
        sort(0, v.length - 1, aux);
    }

    private void sort(int lo, int hi, Comparable[][] a) {
        if (hi - lo <= 16) {
            //切换到插入排序
            for (int i = lo + 1; i <= hi; i++) {
                Comparable[] t = v[i];
                int j = i;
                for (; j > lo && less(t, v[j - 1]); j--) {
                    v[j] = v[j - 1];
                }
                v[j] = t;
            }
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(lo, mid, a);
        sort(mid + 1, hi, a);
        merge(lo, mid, hi, a);
    }

    private void merge(int lo, int mid, int hi, Comparable[][] a) {
        System.arraycopy(v, lo, a, lo, hi - lo + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) {
                v[k] = a[j++];
            } else if (j == hi + 1) {
                v[k] = a[i++];
            } else if (less(i, j)) {
                v[k] = a[i++];
            } else {
                v[k] = a[j++];
            }
        }
    }

    /**
     * 比较第i行和第j行的第d个元素大小
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return less(v[i], v[j]);
    }

    private boolean less(Comparable[] i, Comparable[] j) {
        for (int d = 0; d < v[0].length; d++) {
            if (i[d].compareTo(j[d]) != 0) {
                return i[d].compareTo(j[d]) < 0;
            }
        }
        return false;
    }

    private void exch(int i, int j) {
        Comparable[] temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }

    public static void main(String[] args) {
        /**
         * [[1,2,3],
         * [4,5,6],
         * [7,8,9],
         * [2,0,2],
         * [1,0,1]]
         *
         * d=3
         * n=5;
         */
        Double[][] array = new Double[][]{{1d, 2d, 3d}, {4d, 5d, 6d}, {7d, 8d, 9d}, {2d, 0d, 2d}, {1d, 0d, 1d}};
        Vector v = new Vector(array);
        v.sort();
        StdOut.print(v);
    }
}
