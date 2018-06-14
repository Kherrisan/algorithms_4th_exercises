package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.merge.Merge;
import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/14-16:26
 * Description:
 */
public class UnuniformDistribution {

    public enum Probability {
        GUASSIAN,
        POISSION,
        GEOMETRIC
    }

    public static double test(Sorting sorting, Probability p, int times, int length) {
        double total = 0d;
        for (int t = 0; t < times; t++) {
            Double[] a = generate(Double.class, length, new DataMocker<Double>() {
                @Override
                public Double mock(int i) {
                    switch (p) {
                        case GUASSIAN:
                            return StdRandom.gaussian();
                        case POISSION:
                            return StdRandom.poisson(1) * 1.0;
                        case GEOMETRIC:
                            return StdRandom.geometric(0.1) * 1.0;
                        default:
                            return 0d;
                    }
                }
            });
            Stopwatch stopwatch = new Stopwatch();
            sorting.sort(a);
            total += stopwatch.elapsedTime();
        }
        StdOut.println(sorting.getClass().getSimpleName() + "\t" + p.name() + "\t" + length + "\t" + total);
        return total;
    }

    public static void main(String[] args) {
        Sorting[] sorts = new Sorting[]{
                new Selection(),
                new Insertion(),
                new Shell()
        };
        for (Probability p : Probability.values()) {
            Example.test(sorts, 10000, 100000, 100, (sorting, length, times) -> test(sorting, p, times, length));
        }
    }
}
