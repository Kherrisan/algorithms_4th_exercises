package com.dokyme.alg4.sorting.basic;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/12-22:18
 * Description:
 */
public class GeometricSeries {

    public static double test(double[] array, int g) {
        Shell.hArray = Shell.geometric(g);
        Stopwatch sw = new Stopwatch();
        Shell.sort(array);
        return sw.elapsedTime();
    }

    public static void main(String[] args) {
        double[] a = new double[1000000];
        for (int j = 0; j < a.length; j++) {
            a[j] = StdRandom.uniform();
        }
        for (int i = 2; i < 30; i++) {
            double t = test(Arrays.copyOf(a, a.length), i);
            StdOut.println(i + "\t" + t);
        }
    }
}
