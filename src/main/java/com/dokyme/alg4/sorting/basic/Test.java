package com.dokyme.alg4.sorting.basic;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/11-16:47
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        mySqrt(2147395599);
    }

    public static int mySqrt(int x) {
        int left = 1, right = x;
        int middle = 0;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (middle > x / middle) {
                right = middle - 1;
            } else if (middle < x / middle) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return right;
    }
}
