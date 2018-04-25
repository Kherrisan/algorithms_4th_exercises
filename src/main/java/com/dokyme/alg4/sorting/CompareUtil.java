package com.dokyme.alg4.sorting;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/25-15:55
 * Description:
 */
public class CompareUtil {

    public static int count = 0;

    public static boolean less(Comparable a, Comparable b) {
        count++;
        if (a.compareTo(b) < 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
