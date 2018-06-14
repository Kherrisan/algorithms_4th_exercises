package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/14-18:56
 * Description:
 */
public class PartialOrdered {

    public static double test95Ordered(Sorting sorting, int length, int times) {
        double t = test(sorting, new DataMocker<Integer>() {
            @Override
            public Integer mock(int i) {
                if (i < 0.95 * length) {
                    return i;
                } else {
                    return StdRandom.uniform(length);
                }
            }
        }, length, times);
        StdOut.println(String.format("95%%-Ordered\t%s\t%d\t%f", sorting.getClass().getSimpleName(), length, t));
        return t;
    }

    public static double testDistance10(Sorting sorting, int length, int times) {

    }

    public static double test5random(Sorting sorting, int length, int times) {
        double t = test(sorting, new DataMocker<Integer>() {
            @Override
            public Integer mock(int i) {
                int ch = StdRandom.uniform(100);
                if (ch <= 5) {
                    StdRandom.uniform(length);
                } else {
                    return i;
                }
            }
        }, length, times);
        StdOut.println(String.format("5%%-Scatter\t%s\t%d\t%f", sorting.getClass().getSimpleName(), length, t));
        return t;
    }

    public static void main(String[] args) {
        test(new Sorting[]{new Insertion(), new Selection(), new Shell()}, 10000, 1000000, 100, ((sorting, length, times) -> {
            test95Ordered(sorting, length, times);
            test5random(sorting, length, times);
            testDistance10(sorting, length, times);
        }));
    }
}
