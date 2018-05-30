package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.merge.InverseCounter;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.5.19
 *
 * @author dokym
 * @date 2018/5/29-20:04
 * Description:
 */
public class KendallTau {

    public int count(int[] a, int[] b) {
        int[] ainv = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            ainv[a[i]] = i;
        }
        Integer[] bnew = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            bnew[i] = ainv[b[i]];
        }
        return new InverseCounter().count(bnew);
    }

    public static void main(String[] args) {

    }
}
