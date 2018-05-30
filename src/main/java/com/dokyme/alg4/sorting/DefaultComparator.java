package com.dokyme.alg4.sorting;

import java.util.Comparator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/29-19:14
 * Description:
 */
public class DefaultComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Comparable c1 = (Comparable) o1;
        Comparable c2 = (Comparable) o2;
        return c1.compareTo(c2);
    }

    public static void main(String[] args) {

    }
}
