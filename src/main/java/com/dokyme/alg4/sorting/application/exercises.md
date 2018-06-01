# 练习

## 2.5.1

如果两个String是同一个对象，就跳过实际的比较字符串内容的步骤，那速度肯定会得到提升。

## 2.5.2

由2.5.1中提到是String的比较方式，组合词一定比其中一个词大，因此可以考虑先排序。这样部分词就一定出现在组合词的前面，然后使用类似选择排序的方法进行扫描。

## 2.5.3

> The compareTo() is a flawed implementation of the Comparable interface because it violates the Comparable contract.

根据compare操作的传递性很容易发现这种实现存在问题，如1.0,1.004,1.008。

如何修改：直接比amout的值即可。

## 2.5.4

[Dedup.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/Dedup.java)

## 2.5.5

为何选择排序是不稳定的？

```
public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            //i记录了当前待定的是数组第i小的元素。
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }
```

选择排序每次从无序序列中选择一个最小的元素，移动到有序序列的最后一个位置，而这个移动涉及到将无序序列的第一个位置元素和最小元素进行交换，这个交换操作可能会导致选择排序不稳定。

如5，5，2。

## 2.5.6

[Select.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/Select.java)

## 2.5.7

需要的平均比较次数可以这么计算：

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/2-5-7-1.gif)

## 2.5.8

[Frequency.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/Frequency.java)

## 2.5.9

[Transaction.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/Transaction.java)

## 2.5.10

[Version.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/Version.java)

## 2.5.11

对于有7个相同元素的数组：

- 插入排序：0,1,2,3,4,5,6
- 选择排序：0,1,2,3,4,5,6
- 希尔排序：0,1,2,3,4,5,6
- 归并排序：6,5,4,3,2,1,0
- 堆排序：  0,1,2,3,4,5,6