package com.dokyme.alg4.sorting.application;

import java.util.Date;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/27-15:55
 * Description:
 */
public class Transaction implements Comparable<Transaction> {

    private Date date;

    private long amount;

    @Override
    public int compareTo(Transaction o) {
        if (amount < o.amount) {
            return -1;
        } else if (amount > o.amount) {
            return 1;
        } else {
            return date.compareTo(o.date);
        }
    }

    public static void main(String[] args) {

    }
}
