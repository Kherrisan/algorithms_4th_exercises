package com.dokyme.alg4.searching.st;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/1-18:31
 * Description:
 */
public class GPA {

    public static void main(String[] args) {
        SymbolTable<String, Double> gpaTable = new BinarySearchSymbolTable<>(10);
        gpaTable.put("A+", 4.33);
        gpaTable.put("A", 4.00);
        gpaTable.put("A-", 3.67);
        gpaTable.put("B+", 3.33);
        gpaTable.put("B", 3.00);
        gpaTable.put("B-", 2.67);
        gpaTable.put("C+", 2.33);
        gpaTable.put("C", 2.00);
        gpaTable.put("C-", 1.67);
        gpaTable.put("D", 1.00);
        gpaTable.put("F", 0.0);
        StdOut.println("Floor of A+ is " + gpaTable.floor("A+"));
        StdOut.println("Floor of A-- is " + gpaTable.floor("A--"));
        StdOut.println("Floor of E is " + gpaTable.floor("E"));

//        String grade = StdIn.readString();
//        double sum = 0d;
//        int count = 0;
//        while (grade != null) {
//            count++;
//            sum += gpaTable.get(grade);
//            StdOut.println("GPA:" + sum / count);
//            grade = StdIn.readString();
//        }
    }
}
