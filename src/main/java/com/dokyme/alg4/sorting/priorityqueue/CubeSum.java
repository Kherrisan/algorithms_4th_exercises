package com.dokyme.alg4.sorting.priorityqueue;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.4.25
 *
 * @author dokym
 * @date 2018/5/15-20:16
 * Description:
 */
public class CubeSum {

    public static void main(String[] args) {
        findAllPerfactCubes();
    }

    public static void findAllPerfactCubes() {
        int N = 100;
        List<Tuple> tuples = new ArrayList<>();
        MinHeap<Tuple> ab = new MinHeap<>(N);
        for (int i = 0; i < N; i++) {
            ab.insert(new Tuple(i, 0));
        }
        while (!ab.isEmpty()) {
            Tuple t = ab.delMin();
            if (tuples.size() != 0 && tuples.get(0).cubeSum == t.cubeSum) {
                tuples.add(t);
            } else {
                for (Tuple tup : tuples) {
                    StdOut.println("\t" + tup);
                }
                if (tuples.size() != 0) {
                    StdOut.println(tuples.get(0).cubeSum);
                }
                tuples.clear();
                tuples.add(t);
            }
            if (t.j < N) {
                ab.insert(new Tuple(t.i, t.j + 1));
            }
        }
    }

    public static void printAllCubes() {
        int N = 1000000;
        MinHeap<Tuple> ab = new MinHeap<>(N);
        for (int i = 0; i < N; i++) {
            ab.insert(new Tuple(i, 0));
        }
        while (!ab.isEmpty()) {
            Tuple t = ab.delMin();
            StdOut.println(t.fomular());
            if (t.j < N) {
                ab.insert(new Tuple(t.i, t.j + 1));
            }
        }
    }
}

class Tuple implements Comparable<Tuple> {
    public int i;
    public int j;
    public long cubeSum;

    public Tuple(int i, int j) {
        this.i = i;
        this.j = j;
        cubeSum = (long) i * i * i + (long) j * j * j;
    }

    public String fomular() {
        return this.toString() + " = " + cubeSum;
    }

    @Override
    public String toString() {
        return i + "^3 + " + j + "^3";
    }

    @Override
    public boolean equals(Object obj) {
        Tuple o = (Tuple) obj;
        return i == o.i && j == o.j && cubeSum == o.cubeSum;
    }

    @Override
    public int compareTo(Tuple o) {
        if (cubeSum < o.cubeSum) {
            return -1;
        } else if (cubeSum == o.cubeSum) {
            return 0;
        } else {
            return 1;
        }
    }
}
