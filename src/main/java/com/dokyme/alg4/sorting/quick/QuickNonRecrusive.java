package com.dokyme.alg4.sorting.quick;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.3.20
 * @author dokym
 * @date 2018/5/5-14:12
 * Description:
 */
public class QuickNonRecrusive implements Sorting {

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    /**
     *
     * @param a
     * @param lo
     * @param hi
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        Stack<Integer> stack = new Stack<>();
        int mid;
        stack.push(lo);
        stack.push(hi);
        while (!stack.isEmpty()) {
            hi = stack.pop();
            lo = stack.pop();
            if (hi <= lo) {
                continue;
            }
            mid = partition(a, lo, hi);
            if (hi - mid > mid - lo) {
                stack.push(lo);
                stack.push(mid - 1);
                stack.push(mid + 1);
                stack.push(hi);
            } else {
                stack.push(mid + 1);
                stack.push(hi);
                stack.push(lo);
                stack.push(mid - 1);
            }
        }
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        Comparable v = a[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) ;
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        testSorting(new QuickNonRecrusive());
    }
}
