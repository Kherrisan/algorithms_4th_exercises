package com.dokyme.alg4.sorting.basic;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/16-22:29
 * Description:
 */
public class Transaction implements Comparable<Transaction> {

    private final double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction:" + amount;
    }

    @Override
    public int compareTo(Transaction o) {
        if (this.amount > o.amount) {
            return 1;
        } else if (this.amount < o.amount) {
            return -1;
        } else {
            return 0;
        }
    }
}
