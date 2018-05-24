package com.dokyme.alg4.sorting.priorityqueue;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.4.35
 *
 * @author dokym
 * @date 2018/5/18-13:26
 * Description:
 */
public class Example {

    private double[] p;

    public Example(double[] p) {
        this.p = p;
        for (int i = p.length / 2; i >= 0; i--) {
            int left = i * 2 + 1, right = left * 2 + 2;
            if (left < p.length) {
                p[i] += p[left];
            }
            if (right < p.length) {
                p[i] += p[right];
            }
        }
    }

    /**
     * 返回i的概率为p[i]/sum(p[i])
     *
     * @return
     */
    public int random() {
        double t = StdRandom.uniform(0, p[0]);
        int k = 0;
        while (k < p.length) {

        }
        return 1;
    }

    public void change(int k, double value) {
        p[k] = value;
    }

    public static void main(String[] args) {

    }
}
