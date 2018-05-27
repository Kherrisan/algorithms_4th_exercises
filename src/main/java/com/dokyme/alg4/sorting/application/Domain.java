package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/27-20:47
 * Description:
 */
public class Domain implements Comparable<Domain> {

    private String url;

    public Domain(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }

    @Override
    public int compareTo(Domain o) {
        String[] toks = url.split("\\.");
        String[] otoks = o.url.split("\\.");
        int i = toks.length, j = otoks.length;
        while (i > 0 && j > 0) {
            int p = toks[--i].compareTo(otoks[--j]);
            if (p != 0) {
                return p;
            }
        }
        return toks.length - otoks.length;
    }

    public static void main(String[] args) {
        String[] strList = new String[]{
                "seu.edu.cn",
                "rsc.seu.edu.cn",
                "jwc.seu.edu.cn",
                "arch.seu.edu.cn",
                "nju.edu.cn",
                "xxgk.nju.edu.cn"
        };
        Domain[] domains = new Domain[strList.length];
        for (int i = 0; i < strList.length; i++) {
            domains[i] = new Domain(strList[i]);
        }
        new Quick().sort(domains);
        StdOut.println(Arrays.toString(domains));
    }
}
