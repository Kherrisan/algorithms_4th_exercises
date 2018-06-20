package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.2.23
 *
 * @author dokym
 * @date 2018/6/20-14:01
 * Description:
 */
public class TestImprovements {

    public static void test() {
        Sorting[] sortings = new Sorting[12];
        for (int i = 0; i < 10; i++) {
            sortings[i] = new SwitchInsertionMerge(new Double(Math.pow(2, i + 2)).intValue());
        }
        sortings[10] = new SwapArrayMerge();
        sortings[11] = new CheckSortedMerge();
        Example.test(sortings, 1000, 1000000, 100, new DataMocker<Double>() {
            @Override
            public Double mock(int i) {
                return StdRandom.uniform();
            }
        });
    }

    public static void main(String[] args) {
        test();
    }
}
