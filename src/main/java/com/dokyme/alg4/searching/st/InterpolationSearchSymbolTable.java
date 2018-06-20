package com.dokyme.alg4.searching.st;

import com.dokyme.alg4.sorting.merge.Merge;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.NoSuchElementException;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 3.1.24
 *
 * @author dokym
 * @date 2018/6/2-17:58
 * Description:
 */
public class InterpolationSearchSymbolTable<Value> extends BinarySearchSymbolTable<Integer, Value> {

    public InterpolationSearchSymbolTable(Item<Integer, Value>[] items) {
        super(items);
    }

    public InterpolationSearchSymbolTable(Value[] values) {
        super(values.length);
        items = new Item[values.length];
        for (int i = 0; i < values.length; i++) {
            items[i] = new Item<Integer, Value>(i, values[i]);
        }
        size = values.length;
        new Merge().sort(items);
    }

    public InterpolationSearchSymbolTable(int capacity) {
        super(capacity);
    }

    @Override
    public int rank(Integer k) {
        if (k == null) {
            throw new NoSuchElementException();
        }

        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            if (items[lo].key > k) {
                return lo;
            }
            int pos;
            if (items[hi].key.equals(items[lo].key)) {
                pos = lo;
            } else {
                pos = lo + ((k - items[lo].key) * (hi - lo)) / (items[hi].key - items[lo].key);
            }
            if (pos > hi) {
                pos = hi;
            }
            int cmp = k.compareTo(items[pos].key);
            if (cmp < 0) {
                hi = pos - 1;
            } else if (cmp > 0) {
                lo = pos + 1;
            } else {
                return pos;
            }
        }
        return lo;
    }

    public static void testRank() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }
        InterpolationSearchSymbolTable st = new InterpolationSearchSymbolTable<>(array);
        assert st.rank(0) == 0;
        for (int i = 0; i < 100; i++) {
            assert st.rank(i) == i;
        }
    }

    public static void main(String[] args) {
        for (int i = 512; i < 1 << 16; i *= 2) {
            final int ic = i;
            Integer[] keys = generate(Integer.class, i, new DataMocker<Integer>() {
                @Override
                public Integer mock(int j) {
                    return StdRandom.uniform(ic);
                }
            });
            InterpolationSearchSymbolTable<String> itro = new InterpolationSearchSymbolTable<>(i);
            BinarySearchSymbolTable<Integer, String> bin = new BinarySearchSymbolTable<>(i);
            for (int k = 0; k < keys.length; k++) {
                try {
                    itro.put(keys[k], "");
                    bin.put(keys[k], "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            double itotal = 0d;
            double btotal = 0d;
            int j;
            for (j = 0; j < 1000000; j++) {
                int target = StdRandom.uniform(i);
                Stopwatch iw = new Stopwatch();
                itro.rank(target);
                itotal += iw.elapsedTime();
                iw = new Stopwatch();
                bin.rank(target);
                btotal += iw.elapsedTime();
            }
            StdOut.println("Size:" + i);
            StdOut.println("Interpolation(x" + j + "):" + itotal);
            StdOut.println("Binary(x" + j + "):" + btotal);
        }
    }
}
