package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.DefaultComparator;
import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.5.18
 *
 * @author dokym
 * @date 2018/5/29-18:02
 * Description:
 */
public class Stablize implements Sorting {

    private static class Element implements Comparable<Element> {
        public int index;
        public Comparable val;

        public Element(Comparable val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Element o) {
            return val.compareTo(o.val);
        }
    }

    private Sorting sorting;

    public Stablize(Sorting sorting) {
        this.sorting = sorting;
    }

    @Override
    public void sort(Comparable[] a, Comparator c) {
        sort(a, new DefaultComparator());
    }

    @Override
    public void sort(Comparable[] a) {
        Element[] wrapper = new Element[a.length];
        for (int i = 0; i < wrapper.length; i++) {
            wrapper[i] = new Element(a[i], i);
        }
        sorting.sort(wrapper);
        for (int i = 0; i < wrapper.length; ) {
            int j = i;
            while (i < wrapper.length && wrapper[j].val.equals(wrapper[i].val)) {
                i++;
            }
            Quick.sort(wrapper, j, i - 1, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    Element c1 = (Element) o1;
                    Element c2 = (Element) o2;
                    return c1.index - c2.index;
                }
            });
        }
        for (int i = 0; i < wrapper.length; i++) {
            a[i] = wrapper[i].val;
        }
    }

    public static void main(String[] args) {
        Sorting sorting = new Stablize(new Quick());
        StdOut.println(StabilityChecker.check(sorting));
    }
}
