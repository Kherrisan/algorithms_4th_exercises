package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.Sorting;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/25-11:56
 * Description:
 */
public class DescendingOrderTest {

    public static void main(String[] args) {
        Sorting[] sortings = new Sorting[]{
                new Insertion(),
                new Selection(),
                new Shell()
        };
        for (int l = 10000; l < 100000; l *= 2) {
            final int length = l;
            test(sortings, i -> (length - i) * 1.0, l, 10);
        }
    }
}
