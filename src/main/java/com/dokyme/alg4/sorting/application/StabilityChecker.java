package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/27-21:22
 * Description:
 */
public class StabilityChecker {

    public static class Element implements Comparable<Element> {
        private int val;

        public int index;

        public Element(int val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Element o) {
            return val - o.val;
        }

        @Override
        public boolean equals(Object obj) {
            return val == ((Element) obj).val;
        }
    }

    public static boolean checkStability(Sorting sorting) {
        Element[] a = generate(Element.class, 1000, new DataMocker<Element>() {
            @Override
            public Element mock(int i) {
                return new Element(StdRandom.uniform(10), i);
            }
        });
        sorting.sort(a);
        Element last = a[0];
        for (int i = 1; i < a.length; i++) {
            if (last.val == a[i].val && last.index > a[i].index) {
                return false;
            }
            last = a[i];
        }
        return true;
    }

    public static void main(String[] args) {
        StdOut.println(checkStability(new Quick()));
    }
}
