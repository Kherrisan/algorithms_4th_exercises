package com.dokyme.alg4.searching.binaryst;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

import static com.dokyme.alg4.Utils.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 3.2.10
 *
 * @author dokym
 * @date 2018/6/8-18:13
 * Description:
 */
public class TestBST {

    public void test() {
        List<String> records = new ArrayList<>();
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        int sz = StdIn.readInt();
        String line;
        for (int i = 0; i < sz; i++) {
            line = StdIn.readString();
            bst.put(line, 1);
            records.add(line);
        }
        printSeperation();

        StdOut.println("Test min()");
        StdOut.println("Min key: " + bst.min());
        printSeperation();

        StdOut.println("Test max()");
        StdOut.println("Max key: " + bst.max());
        printSeperation();

        StdOut.println("Test floor()");
        for (char a = 'a'; a <= 'z'; a++) {
            StdOut.println("Floor of " + a + " is " + bst.floor("" + a));
        }
        printSeperation();

        StdOut.println("Test ceiling()");
        for (char a = 'a'; a <= 'z'; a++) {
            StdOut.println("Ceiling of " + a + " is " + bst.ceiling("" + a));
        }
        printSeperation();

        StdOut.println("Test select()");
        for (int i = 0; i < bst.size(); i++) {
            StdOut.println("Select " + i + " is " + bst.select(i));
        }
        printSeperation();

        StdOut.println("Test rank()");
        for (String l : records) {
            StdOut.println("Rank of " + l + " is " + bst.rank(l));
        }
        printSeperation();

        StdOut.println("Test deleteMin()");
        final int size = bst.size();
        for (int i = 0; i < size / 2; i++) {
            StdOut.println("Delete min: " + bst.min());
            bst.deleteMin();
        }
        printSeperation();

        StdOut.println("Test deleteMin()");
        for (int i = 0; i < size / 2; i++) {
            StdOut.println("Delete max: " + bst.max());
            bst.deleteMax();
        }
        printSeperation();
    }

    public static void main(String[] args) {
        new TestBST().test();
    }
}
