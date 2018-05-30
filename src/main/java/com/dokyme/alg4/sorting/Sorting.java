package com.dokyme.alg4.sorting;

import java.util.Comparator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/22-14:14
 * Description:
 */
public interface Sorting {
    void sort(Comparable[] a);

    void sort(Comparable[] a, Comparator c);
}
