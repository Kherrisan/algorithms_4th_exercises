package com.dokyme.alg4.searching.st;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/2-19:35
 * Description:
 */
public class TestBinarySearch {

    public static boolean test() {
        List<String> list = new ArrayList<>();
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(10);
        for (int i = 0; !StdIn.isEmpty(); i++) {
            st.put(StdIn.readString(), i);
        }
        StdOut.println("Min:" + st.min());
        StdOut.println("Max:" + st.max());

        StdOut.println(">>Testing rank(),floor(),ceiling()");
        StdOut.println("key\trank\tfloor\tceil");
        for (char ch = 'a'; ch <= 'z'; ch++) {
            String key = String.valueOf(ch);
            StdOut.printf("%s\t%d\t%s\t%s\n", key, st.rank(key), st.floor(key), st.ceiling(key));
        }
        StdOut.println();

        StdOut.println(">>Testing deleteMin()");
        int size = st.size();
        for (int i = 0; i < size / 2; i++) {
            StdOut.print(st.min());
            st.deleteMin();
        }
        StdOut.println();

        StdOut.println(">>Testing deleteMax()");
        for (int i = 0; i < size / 2; i++) {
            StdOut.print(st.max());
            st.deleteMax();
        }

        return true;
    }

    public static void main(String[] args) {
        test();
    }
}
