package com.dokyme.alg4.searching.binaryst;

import java.util.Arrays;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 3.2.25
 *
 * @author dokym
 * @date 2018/6/11-20:54
 * Description:
 */
public class PerfactBalance {

    public static BinaryTree<String, Integer> build(String[] arrays, BinaryTree<String, Integer> tree) {
        Arrays.sort(arrays);
        insert(arrays, 0, arrays.length - 1, tree);
        return tree;
    }

    private static void insert(String[] arrays, int lo, int hi, BinaryTree<String, Integer> tree) {
        if (lo > hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        tree.put(arrays[mid], 1);
        insert(arrays, lo, mid - 1, tree);
        insert(arrays, mid + 1, hi, tree);
    }

    public static void main(String[] args) {

    }
}
