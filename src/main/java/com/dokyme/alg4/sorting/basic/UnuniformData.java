package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/14-17:51
 * Description:
 */
public class UnuniformData {

    private static double halfQuaQua(Sorting sorting, int length, int times) {
        double t = test(sorting, new DataMocker<Integer>() {
            @Override
            public Integer mock(int i) {
                if (i % 4 == 0) {
                    return 2;
                } else if (i % 4 == 1) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }, length, times, true);
        StdOut.println(String.format("Half-Quarter-Quarter\t%s\t%d\t%f", sorting.getClass().getSimpleName(), length, t));
        return t;
    }

    private static double halfZeroOne(Sorting sorting, int length, int times) {
        double t = test(sorting, new DataMocker<Integer>() {
            @Override
            public Integer mock(int i) {
                return i % 2;
            }
        }, length, times, true);
        StdOut.println(String.format("Half-Half\t%s\t%d\t%f", sorting.getClass().getSimpleName(), length, t));
        return t;
    }

    private static double halfRandom(Sorting sorting, int length, int times) {
        double t = test(sorting, new DataMocker<Integer>() {
            @Override
            public Integer mock(int i) {
                if (i % 2 == 0) {
                    return 0;
                } else {
                    return StdRandom.uniform(length);
                }
            }
        }, length, times, true);
        StdOut.println(String.format("Half-Random\t%s\t%d\t%f", sorting.getClass().getSimpleName(), length, t));
        return t;
    }

    public static void main(String[] args) {
        Sorting[] sorts = new Sorting[]{
                new Insertion(),
                new Selection(),
                new Shell()
        };
        for (int length = 10000; length < 100000; length <<= 1) {
            for (Sorting s : sorts) {
                halfQuaQua(s, length, 10);
                halfZeroOne(s, length, 10);
                halfRandom(s, length, 10);
            }
        }
    }
}
