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
    }

    public static boolean checkStability(Sorting sorting) {
        Element[] a = generate(1000, new DataMocker<Element>() {
            @Override
            public Element mock(int i) {
                return new Element(0, i);
            }
        });
        Element[] copy = Arrays.copyOf(a, 1000);
        sorting.sort(a);
        for (int i = 1; i < 1000; i++) {
            if (copy[i - 1].index > copy[i].index) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StdOut.println(checkStability(new Quick()));
    }
}
