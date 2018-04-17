package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.CurveGraphDrawer;
import com.dokyme.alg4.sorting.basic.Example;
import com.dokyme.alg4.sorting.basic.SortingDrawer;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/17-14:46
 * Description:
 */
public class MergeBU {
    private static Comparable[] aux;
    private static List<Integer> accessCounts;

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz *= 2) {
            for (int lo = 0; lo < N - sz; lo += 2 * sz) {
                Merge.merge(a, lo, lo + sz - 1, Math.min(lo + 2 * sz - 1, N - 1), aux);
            }
        }
    }

    public static List<Integer> testAccessMemoryCounts() {
        accessCounts = new ArrayList<>();
        for (int N = 1; N < 512; N++) {
            Double[] a = new Double[N];
            for (int i = 0; i < a.length; i++) {
                a[i] = StdRandom.uniform();
            }
            sort(a);
        }
        return accessCounts;
    }

    public static List<Integer> nlognSequence(int n) {
        List<Integer> seq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            seq.add(new Double(6 * i * Math.log(i)).intValue());
        }
        return seq;
    }

    public static void main(String[] args) {
        CurveGraphDrawer<Integer> drawer = new CurveGraphDrawer<>();
        drawer.addDataSet("1", testAccessMemoryCounts())
                .addDataSet("2", Merge.testAccessMemoryCounts())
                .addDataSet("3", nlognSequence(512))
                .draw();
    }
}
