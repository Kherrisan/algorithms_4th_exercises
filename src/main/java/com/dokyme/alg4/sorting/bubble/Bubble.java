package com.dokyme.alg4.sorting.bubble;

import com.dokyme.alg4.sorting.Sorting;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 自己写的冒泡排序
 *
 * @author dokym
 * @date 2018/5/9-13:23
 * Description:
 */
public class Bubble implements Sorting {

    @Override
    public void sort(Comparable[] a) {
        boolean swp = true;
        int N = a.length;
        for (int i = 1; i < N && swp; i++) {
            swp = false;
            for (int j = 1; j < N - i + 1; j++) {
                if (less(a[j], a[j - 1])) {
                    swp = true;
                    exch(a, j, j - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        testSorting(new Bubble());
    }
}
