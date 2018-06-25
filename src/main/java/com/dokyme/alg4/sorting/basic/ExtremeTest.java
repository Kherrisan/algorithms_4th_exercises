package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.quick.Quick;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/14-16:13
 * Description:
 */
public class ExtremeTest {

    public static void testOrdered(Sorting[] sortings, int minLength, int maxLength, int times) {
        test(sortings, minLength, maxLength, times, i -> i * 1.1);
    }

    public static void testDescending(Sorting[] sortings, int minLength, int maxLength, int times) {
        for (int l = minLength; l <= maxLength; l *= 2) {
            final int length = l;
            test(sortings, i -> (length - i) * 1.1, l, times);
        }
    }

    public static void testSameValue(Sorting[] sortings, int minLength, int maxLength, int times) {
        test(sortings, minLength, maxLength, times, i -> 1.1);
    }

    public static void main(String[] args) {
        Sorting[] sortings = new Sorting[]{
                new Selection(),
                new Insertion(),
                new Shell()
        };
        testOrdered(sortings, 10000, 100000, 10);
        testDescending(sortings, 10000, 100000, 10);
        testSameValue(sortings, 10000, 100000, 10);
    }
}
