# 提高题

## 2.3.15 螺丝和螺帽

对螺丝进行排序时，从螺帽数组中选取pivot，一遍扫描过后，肯定是左侧的螺丝偏小，右侧的螺丝偏大，中间的螺丝正好吻合。再拿这个螺丝作为pivot对螺帽数组进行排序。递归地对左侧子数组和右侧子数组进行排序。

## 2.3.16 最佳情况

**暂留**

## 2.3.17 哨兵

[QuickSentinels.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/quick/QuickSentinels.java)

做划分的时候，由于取的是数组左侧第一个元素，因此左移的指针不可能越界；由于当前数组的右边界的右侧第一个元素肯定比当前数组中所有元素大，因此右移指针最多移动到右边界的右侧一个元素。而最终的交换是交换数组第一个元素和左移指针，和右移指针无关。

## 2.3.18 三取样划分

通过取数个样本的中位数来保证枢轴最终处于相对居中的位置，一般是取数组第一个，中间一个，最后一个，这三个元素，取其中位数作为pivot，同时把最大的一个放在数组末尾，这样右移指针就不可能越界（因为pivot不可能比最后一个大）。

[QuickMedianOf3Partion.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/quick/QuickMedianOf3Partion.java)

```java_holder_method_tree
size:256	time:0.013000	ratio:0.013000
size:512	time:0.038000	ratio:2.923077
size:1024	time:0.021000	ratio:0.552632
size:2048	time:0.034000	ratio:1.619048
size:4096	time:0.071000	ratio:2.088235
size:8192	time:0.128000	ratio:1.802817
size:16384	time:0.274000	ratio:2.140625
size:32768	time:0.866000	ratio:3.160584
size:65536	time:1.127000	ratio:1.301386
size:131072	time:2.401000	ratio:2.130435
size:262144	time:6.572000	ratio:2.737193
size:524288	time:19.641000	ratio:2.988588
```

倍率实验结果如上。

## 2.3.19 五取样划分

**暂留**

## 2.3.20 非递归的快速排序

为什么每次都把较大的子数组先压入栈能够保证栈里最对只会有NlogN个元素？

栈中元素个数的最大值也就是二叉树的深度，或者说递归的最大深度（递归本身就是压栈出栈的过程）。

如果是理想情况，每次划分都把数组均分，那么栈的最大深度为NlogN。

如果是不平衡的划分，二叉树就会不平衡，将较大的子数组先压栈，感性上，可以使得较小的子数组尽快的被划分至0。比如最恶劣的情况，每次划分都得到一个长度为1的数组和另一个长数组，那么，栈中最多也就只有2个元素了。

[QuickNonRecrusive.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/quick/QuickNonRecrusive.java)

## 2.3.21 将重复元素排序的比较次数的下界

## 2.3.22 快速三向切分

Bently-McIlroy三向划分

[QuickFast3Way.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/quick/QuickFast3Way.java)

![Dijkstra三向切分和Bently-McIlroy三向划分的区分](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/2-3-22-1.jpg)

## 2.3.23 Java的排序库函数

其实不同版本的JDK所采用的通用排序算法是不一样的。

JDK6中，对于基本数据类型采用的就是Bently-McIlroy的快速排序，对于引用类型采用的是一种修改过的归并排序。

JDK7中，对于基本数据类型采用的是双枢轴快速排序（Dual-Pivot Quicksort by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch），对于引用类型采用的也是一种修改过的归并排序。

本题要求使用Tukey's ninther方法找出切分元素。

[QuickInJDK6.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/quick/QuickInJDK6.java)

有生之年能够写出运行效率与库函数可比的排序算法也是可以了。

```
Length:1024	Arrays.sort:0.084000	jdk6:0.078000
Length:2048	Arrays.sort:0.248000	jdk6:0.167000
Length:4096	Arrays.sort:0.109000	jdk6:0.258000
Length:8192	Arrays.sort:0.226000	jdk6:0.262000
Length:16384	Arrays.sort:0.362000	jdk6:0.293000
Length:32768	Arrays.sort:0.576000	jdk6:0.535000
Length:65536	Arrays.sort:1.126000	jdk6:1.237000
Length:131072	Arrays.sort:2.583000	jdk6:3.109000
```

## 2.3.24 取样排序

