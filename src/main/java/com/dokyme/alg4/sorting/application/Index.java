package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.Sorting;

import java.util.Comparator;
import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/31-16:28
 * Description:
 */
public class Index implements Sorting {

    private static class Element implements Comparable<Element> {
        private Comparable val;
        private int index;

        public Element(Comparable val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Element o) {
            return val.compareTo(o.val);
        }
    }

    private int[] index;

    public int[] getIndex() {
        return index;
    }

    private Sorting sorting;

    public Index(Sorting sorting) {
        this.sorting = sorting;
    }

    @Override
    public void sort(Comparable[] a) {
        Element[] elements = new Element[a.length];
        for (int i = 0; i < a.length; i++) {
            elements[i] = new Element(a[i], i);
        }
        sorting.sort(elements);
        copyIndex(elements);

    }

    private void copyIndex(Element[] elements) {
        index = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            index[i] = elements[i].index;
        }
    }

    @Override
    public void sort(Comparable[] a, Comparator c) {
        Element[] elements = new Element[a.length];
        for (int i = 0; i < a.length; i++) {
            elements[i] = new Element(a[i], i);
        }
        sorting.sort(a, c);
        copyIndex(elements);
    }

    public static void main(String[] args) {

    }
}
