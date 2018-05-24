package com.dokyme.alg4.sorting.application;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/27-15:43
 * Description:
 */
public class Frequency {

    public static class Tuple implements Comparable<Tuple> {
        public String str;
        public int counter;

        public Tuple(String str) {
            this.str = str;
            counter=1;
        }

        /**
         * Returns a string representation of the object. In general, the
         * {@code toString} method returns a string that
         * "textually represents" this object. The result should
         * be a concise but informative representation that is easy for a
         * person to read.
         * It is recommended that all subclasses override this method.
         * <p>
         * The {@code toString} method for class {@code Object}
         * returns a string consisting of the name of the class of which the
         * object is an instance, the at-sign character `{@code @}', and
         * the unsigned hexadecimal representation of the hash code of the
         * object. In other words, this method returns a string equal to the
         * value of:
         * <blockquote>
         * <pre>
         * getClass().getName() + '@' + Integer.toHexString(hashCode())
         * </pre></blockquote>
         *
         * @return a string representation of the object.
         */
        @Override
        public String toString() {
            return String.format("%s\t%d times\n", str, counter);
        }

        /**
         * Compares this object with the specified object for order.  Returns a
         * negative integer, zero, or a positive integer as this object is less
         * than, equal to, or greater than the specified object.
         *
         * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
         * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
         * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
         * <tt>y.compareTo(x)</tt> throws an exception.)
         *
         * <p>The implementor must also ensure that the relation is transitive:
         * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
         * <tt>x.compareTo(z)&gt;0</tt>.
         *
         * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
         * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
         * all <tt>z</tt>.
         *
         * <p>It is strongly recommended, but <i>not</i> strictly required that
         * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
         * class that implements the <tt>Comparable</tt> interface and violates
         * this condition should clearly indicate this fact.  The recommended
         * language is "Note: this class has a natural ordering that is
         * inconsistent with equals."
         *
         * <p>In the foregoing description, the notation
         * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
         * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
         * <tt>0</tt>, or <tt>1</tt> according to whether the value of
         * <i>expression</i> is negative, zero or positive.
         *
         * @param o the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
         */
        @Override
        public int compareTo(Tuple o) {
            if (counter < o.counter) {
                return -1;
            } else if (counter == o.counter) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) {
        int num;
        num = StdIn.readInt();
        Tuple[] tuples = new Tuple[num];
        int tsize = 0;
        for (int i = 0; i < num; i++) {
            boolean found = false;
            String in = StdIn.readString();
            for (int j = 0; j < tsize && tuples[j] != null; j++) {
                if (tuples[j].str.equals(in)) {
                    tuples[j].counter++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                tuples[tsize++] = new Tuple(in);
            }
        }
        tuples = Arrays.copyOf(tuples, tsize);
        new com.dokyme.alg4.sorting.quick.Quick().sort(tuples);
        StdOut.println(Arrays.toString(tuples));
    }
}
