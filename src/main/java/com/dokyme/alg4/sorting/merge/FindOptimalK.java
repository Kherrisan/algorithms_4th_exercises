package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/20-16:55
 * Description:
 */
public class FindOptimalK {

    public static void main(String[] args) {
        Sorting[] sortings = new Sorting[10];
        for (int i = 0; i < 10; i++) {
            sortings[i] = new MultiWayMerge(i + 2);
        }
        test(sortings, 1000, 1000000, 100, new DataMocker<Double>() {
            @Override
            public Double mock(int i) {
                return StdRandom.uniform();
            }
        });
    }
}
