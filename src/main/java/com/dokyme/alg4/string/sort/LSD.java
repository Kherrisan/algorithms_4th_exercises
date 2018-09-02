package com.dokyme.alg4.string.sort;

/**
 * 低位优先字符串排序
 */
public class LSD {
    public static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for (int d = W - 1; d >= 0; d--) {
            //根据指定位上的字符用键索引计数法排序
            //应该是用空间换时间
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                //统计所有字符串第d位的出现字符的频率
                count[a[i].charAt(d) + 1]++;
            }
            for (int r = 0; r < R; r++) {
                //将频率转换为索引
                count[r + 1] += count[r];
            }
            for (int i = 0; i < N; i++) {
                //将元素分类
                //count为每一个类提供一个指针
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }
}
