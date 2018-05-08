package com.dokyme.alg4.sorting.basic;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/16-22:29
 * Description:
 */
public class Transaction implements Comparable<Transaction> {

    private double amount;
    private String name;
    private Date date;

    public Transaction(String line) {
        String[] tokens = line.split("\\s+");
        name = tokens[0].trim();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(tokens[1].trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        amount = new Double(tokens[2].trim());
    }

    @Override
    public String toString() {
        return "Transaction:" + name + "\t" + date.toString() + "\t" + amount;
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
