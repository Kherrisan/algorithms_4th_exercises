# 提高题

## 2.5.12 调度

不是很理解短作业优先的意义在哪里。在进程调度中，短作业优先是用来最小化周转时间的。

[SPT.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/SPT.java)

## 2.5.13 负载均衡

[LPT.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/LPT.java)

## 2.5.14 逆域名排序

[Domain.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/Domain.java)

## 2.5.15 垃圾邮件大战

根据@符号进行邮件地址的划分，取邮件地址的域名部分，根据这个域名进行排序，这样就可以把邮件地址样本划分成各个域名的部分，有利于群发过程的进行吧。

## 2.5.16 公正的选举

[California.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/California.java)

## 2.5.17 检测稳定性

[StabilityChecker.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/StabilityChecker.java)

## 2.5.18 强制稳定

[Stablize.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/Stablize.java)

在排序的过程中保持每个元素的初始位置索引，在排序结束之后，每个相等元素短内做一次排序。我认为应该有更加高效的方法。

## 2.5.19 Kendall tau距离

计算一个序列中逆序对的个数：分治————分别计算左子序列中逆序个数、右子序列中逆序个数、元素一左一右的逆序个数。

Kendall tau距离：两个序列之间的逆序个数，显然不能分别求两个序列的逆序对个数然后做差。如12354和21345，Kt距离是2而不是0。

基本思路：首先针对其中一个序列a，作出a的逆序索引，即index[a[i]]=i。那么就能快速得到a中任一元素e在a中位置。再作出index[b[i]]，即b中第i个元素在a中的位置bindex，那么bindex的逆序数个数即为a和b之间的逆序对个数。

假设b中第1个元素m在a中的位置为5，b中第2个元素n在a中的位置为4，即bindex[1]=5，bindex[2]=4，那么a和b之间的逆序对个数就要加1。因为在b中m在n前面，在a中m在n的后面。

[KendallTau.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/KendallTau.java)

## 2.5.20 空闲时间

**暂留**

## 2.5.21 多维排序

[Vector.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/Vector.java)

## 2.5.22 股票交易

**暂留**

## 2.5.23 选择的取样

**暂留**

## 2.5.24 稳定的优先队列

## 2.5.25 平面上的点

[Point2D.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/Point2D.java)

## 2.5.26 简单多边形

[Polygon.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/Polygon.java)

## 2.5.27 平行数组的排序

[Index.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/Index.java)

## 2.5.28 按文件名排序

[FileSorter.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/FileSorter.java)

## 2.5.29 按大小和最后修改日期将文件排序

[FileSorter.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/application/FileSorter.java)

## 2.5.30 Boerner定理

从直觉上看应该是有问题的，但是举不出反例。CSDN上有一个人写了程序验证了这个定理是正确的。
