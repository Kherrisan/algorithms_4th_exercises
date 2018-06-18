package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/14-19:17
 * Description:
 */
public class DifferentTypeElements {

    public static class StringPK implements Comparable<StringPK> {
        private String id;
        private double d;

        public StringPK(String id ){
            this.id = id;
            this.d = StdRandom.uniform();
        }

        @Override
        public int compareTo(StringPK o) {
            return id.compareTo(o.id);
        }
    }

    public static class DoublePK implements Comparable<DoublePK> {
        private double id;
        private String[] list;

        public DoublePK(double id) {
            this.id = id;
            this.list = new String[10];
            for (int i = 0; i < list.length; i++) {
                list[i] = list[i].hashCode() + "" + list[i].hashCode();
            }
        }

        @Override
        public int compareTo(DoublePK o) {
            return (int) (id - o.id);
        }
    }

    public static class IntegerPK implements Comparable<IntegerPK> {
        private int id;
        private int[] list;

        public IntegerPK(int id) {
            this.id = id;
            this.list = new int[20];
            for (int i = 0; i < list.length; i++) {
                list[i] = StdRandom.uniform(100);
            }
        }

        @Override
        public int compareTo(IntegerPK o) {
            return id - o.id;
        }
    }

    public static double testStringPK(Sorting sorting, int length, int times) {
        double t = test(sorting, new DataMocker<StringPK>() {
            @Override
            public StringPK mock(int i) {
                return new StringPK(i + "");
            }
        }, length, times, true);
        StdOut.println(String.format("String-Double\t%s\t%d\t%f", sorting.getClass().getSimpleName(), length, t));
        return t;
    }

    public static double testDoublePK(Sorting sorting, int length, int times) {
        double t = test(sorting, i -> new DoublePK(i * 1.1), length, times, true);
        StdOut.println(String.format("Double-String[10]\t%s\t%d\t%f", sorting.getClass().getSimpleName(), length, t));
        return t;
    }

    public static double testIntegerPK(Sorting sorting, int length, int times) {
        double t = test(sorting, i -> new IntegerPK(i), length, times, true);
        StdOut.println(String.format("Int-Int[20]\t%s\t%d\t%f", sorting.getClass().getSimpleName(), length, t));
        return t;
    }

    public static void main(String[] args) {
        test(new Sorting[]{new Selection(), new Insertion(), new Shell()}, 10000, 100000, 100, ((sorting, length, times) -> {
            testDoublePK(sorting, length, times);
            testIntegerPK(sorting, length, times);
            testStringPK(sorting, length, times);
        }));
    }
}
