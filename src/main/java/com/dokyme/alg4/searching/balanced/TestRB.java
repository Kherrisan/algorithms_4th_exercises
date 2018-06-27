package com.dokyme.alg4.searching.balanced;

import com.dokyme.alg4.FileIn;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

import static com.dokyme.alg4.Utils.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/27-14:41
 * Description:
 */
public class TestRB {

    public static void test() {
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        List<String> records = new ArrayList<>();
        int sz = FileIn.readInt();
        String line;
        FileIn.setFile("src/main/resources/testrb.txt");
        for (int i = 0; i < sz; i++) {
            line = FileIn.readString();
            tree.put(line, "");
            records.add(line);
        }
        printSeperation();

        StdOut.println("Test min()");
        StdOut.println("Min key: " + tree.min());
        printSeperation();

        StdOut.println("Test max()");
        StdOut.println("Max key: " + tree.max());
        printSeperation();

        StdOut.println("Test floor()");
        for (char a = 'a'; a <= 'z'; a++) {
            StdOut.println("Floor of " + a + " is " + tree.floor("" + a));
        }
        printSeperation();

        StdOut.println("Test ceiling()");
        for (char a = 'a'; a <= 'z'; a++) {
            StdOut.println("Ceiling of " + a + " is " + tree.ceiling("" + a));
        }
        printSeperation();

        StdOut.println("Test select()");
        for (int i = 0; i < tree.size(); i++) {
            StdOut.println("Select " + i + " is " + tree.select(i));
        }
        printSeperation();

        StdOut.println("Test rank()");
        for (String l : records) {
            StdOut.println("Rank of " + l + " is " + tree.rank(l));
        }
        printSeperation();

        StdOut.println("Test deleteMin()");
        final int size = tree.size();
        for (int i = 0; i < size / 2; i++) {
            StdOut.println("Delete min: " + tree.min());
            tree.deleteMin();
        }
        printSeperation();

        StdOut.println("Test deleteMax()");
        for (int i = 0; i < size / 2; i++) {
            StdOut.println("Delete max: " + tree.max());
            tree.deleteMax();
        }
        printSeperation();
    }

    public static void main(String[] args) {

    }
}
