package com.dokyme.alg4.sorting.basic;

import java.util.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/11-16:47
 * Description:
 */
public class Test {
    public static void main(String[] args) {
//        addBinary("11", "1");
        int i = 1;
        while (i++ > 2) {
            System.out.println(i);
            break;
        }
        System.out.println(i);
    }

    public static String addBinary(String a, String b) {
        char[] ach = a.toCharArray();
        char[] bch = b.toCharArray();
        String result = "";
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 && j >= 0) {
            result = (ach[i] + bch[j] - 96 + carry) % 2 + result;
            carry = (ach[i--] + bch[j--] - 96 + carry) / 2;
        }
        if (i < 0) {
            while (j >= 0) {
                result = (bch[j] - 48 + carry) % 2 + result;
                carry = (bch[j--] - 48 + carry) / 2;
            }
        } else if (j < 0) {
            while (i >= 0) {
                result = (ach[i] - 48 + carry) % 2 + result;
                carry = (ach[i--] - 48 + carry) / 2;
            }
        }
        if (carry == 1) {
            result = 1 + result;
        }
        return result;
    }
}
