package com.dokyme.alg4.sorting.priorityqueue;

import com.dokyme.alg4.sorting.basic.Transaction;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;
import java.util.Scanner;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/8-22:04
 * Description:
 */
public class TopM {

    public static void main(String[] args) {
        try {
            int M = 5;
            Scanner scanner = new Scanner(new File("tinyBatch.txt"));
            MinHeap<Transaction> minHeap = new MinHeap<>(20);
            while (scanner.hasNextLine()) {
                minHeap.insert(new Transaction(scanner.nextLine()));
                if (minHeap.size() > M) {
                    minHeap.delMin();
                }
            }
            Stack<Transaction> stack = new Stack<>();
            while (!minHeap.isEmpty()) {
                stack.push(minHeap.delMin());
            }
            for (Transaction t : stack) {
                StdOut.println(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
