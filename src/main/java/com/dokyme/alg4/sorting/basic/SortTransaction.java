package com.dokyme.alg4.sorting.basic;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/16-22:31
 * Description:
 */
public class SortTransaction {

    public static Transaction[] readTransactions() {
        StdOut.print("The number of transactions:");
        int number = StdIn.readInt();
        Transaction[] result = new Transaction[number];
        while (number-- > 0) {
            StdOut.print("Input next transaction amount:");
            result[number] = new Transaction(StdIn.readLine());
        }
        StdOut.print("Input finished.");
        return result;
    }

    public static void main(String[] args) {
        Transaction[] transactions = readTransactions();
        new Shell().sort(transactions);
        for (Transaction t : transactions) {
            StdOut.println(t);
        }
    }
}
