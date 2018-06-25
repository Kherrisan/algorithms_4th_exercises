# 实验题

## 2.1.23 纸牌排序

这道题就不找朋友玩纸牌了，估计他们要么用的是随机排序要么是选择排序，插入排序应该用的不多，希尔排序应该是不会有人用的。

## 2.1.24 插入排序的哨兵

```java_holder_method_tree
    public static void sort(Comparable[] a, boolean useGuardian) {
        int guardian = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[guardian].compareTo(a[i]) > 0) {
                guardian = i;
            }
        }
        Example.exch(a, 0, guardian);
        //第一次找出最小的元素放到数组的最左边，这样在内循环中就不需要判断j>0的数组越界的问题。
        for (int i = 2; i < a.length; i++) {
            for (int j = i; a[j].compareTo(a[j - 1]) < 0; j--) {
                Example.exch(a, j, j - 1);
            }
        }
    }
```

## 2.1.25 不需要交换的插入排序

```java_holder_method_tree
    public static void sortWithoutExch(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            Comparable t = a[i];
            int j = i;
            for (; j > 0 && t.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = t;
        }
    }
```

1. 插入排序和不进行逐个交换的插入排序相比，后者更快。
2. 选择排序和不进行逐个交换的插入排序相比，后者更快。
3. 希尔排序和不进行逐个交换的插入排序相比，前者更快，而且差距较前两个比较极为明显。

本实验说明：访问内存和修改内存操作虽然的确需要一定的时间，在同等时间复杂度的情况下，减少访存次数可以降低运行时间，但这种时间开销不会对时间复杂度产生任何影响，快一个等级的算法所带来是优势是减少这种开销所无法弥补的。

## 2.1.26 原始数据类型

隐式的装箱和拆箱应该不会成为影响运行时间的因素，因为这是语法糖，是在编译时进行优化的。从运行结果来看，二者的运行时间也是相等的。

值得注意的是int[]不能装箱为Comparable[]。

## 2.1.27 希尔排序的用时是次平方级的

```java_holder_method_tree
Selection:0.0	Insertion:0.0	Shell:0.7
Selection:0.0	Insertion:0.0	Shell:0.0
Selection:0.0	Insertion:0.0	Shell:0.0
Selection:0.1	Insertion:0.1	Shell:0.0
Selection:0.3	Insertion:0.1	Shell:0.0
Selection:0.7	Insertion:0.6	Shell:0.1
Selection:2.9	Insertion:2.2	Shell:0.1
Selection:11.9	Insertion:9.0	Shell:0.3
Selection:57.4	Insertion:40.4	Shell:0.8
Selection:248.3	Insertion:182.6	Shell:2.2
```

## 2.1.28 相等的主键

如果数组中有相当一部分元素是相等的话，那么选择排序所需要的时间不会产生明显的变化，插入排序中，较小元素前移的步骤所需要的步数应该也不会产生太大的变换。当然这只是我的主观想法。

```java
Selection: 19.037
Insertion: 8.597
```

实际结果是，插入排序快。使用int[]数组进行排序，再进行测试：

```java
Selection: 2.6479999999999997
Insertion: 1.8760000000000001
```

结果是插入排序更快一些。

最后在测试的排序算法中加入了快排，快排一骑红尘。

[Duplication.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/basic/Duplication.java)

## 2.1.29 希尔排序的递增序列

以下是生成该序列（Sedgewick）的代码。

```java_holder_method_tree
public static int[] initIncrementSequence() {
        hArray = new int[1 << 10];
        int i = 0, j = 2;
        int c = 0;
        int value1 = 1;
        int value2 = 5;
        while (c < hArray.length) {
            if (value1 < value2) {
                hArray[c++] = value1;
                i++;
                value1 = Double.valueOf(9 * Math.pow(4.0, i * 1.0) - 9 * Math.pow(2.0, i * 1.0) + 1).intValue();
            } else {
                hArray[c++] = value2;
                j++;
                value2 = Double.valueOf(Math.pow(4.0, j * 1.0) - 3 * Math.pow(2.0, j * 1.0) + 1).intValue();
            }
        }
        return hArray;
    }
```

似乎新的递增序列比3k+1序列的速度更快一些，不，是快很多。

```java_holder_method_tree
With test 100 times of length:10000
Sequence1:30.4	Sequence2:0.2
```

乘此机会，把常用的5种递增序列都测试一下，比较一下速度的差异。

```java_holder_method_tree
With test 500 times of length:10000
Knuth:228.5
Sedgewich:1.4
Gonnet:1.6
Hibbard:1.5
Shell:2.8
```

加入了Arrays.sort方法之后：


```java_holder_method_tree
With test 1000 times of length:10000
Arrays.sort:2.0
Sedgewich:1.8
Gonnet:2.1
Hibbard:2.1
Shell:4.2
```

这里似乎是因为排序使用的是Double类型，通过compareTo方法进行大小比较，导致在函数调用上花费了一些时间。尝试将希尔排序和Arrays.sort都作用在基本类型的数组上。

```java_holder_method_tree
With test 100 times of length:100000
Arrays.sort:1.1
Sedgewich:11.5
Gonnet:8.5
Hibbard:8.5
Shell:14.3
```

的确是Arrays.sort更快一些，毕竟快一个数量级。

已有的4中shell排序序列的时间复杂度之间都只相差一个比例常数，再快的shell排序也还是落后于数量级更小的排序算法。

对于不是特别长的数组，Sedgewich序列有些许优势，但是当数组长度进一步变大时，gonnet和hibbard能够有更加出色的表现。

## 2.1.30

尝试找到一个几何级数使得这个序列作为希尔排序的递增序列时的排序速度最快。

```java
2	1.312
3	1.019
4	1.232
5	1.235
6	1.133
7	1.274
8	1.364
9	1.229
10	1.401
11	1.471
12	1.676
13	1.632
14	1.525
15	1.842
16	1.666
17	1.982
18	1.728
19	1.899
20	2.019
21	1.881
22	1.798
23	2.042
24	1.913
25	2.165
26	1.832
27	1.823
28	2.241
29	2.131
```

第一次测试忘记确保排序数组的一致性了，虽然测试结果和后面几次结果是基本一致的。

```java
2	1.247
3	0.852
4	1.043
5	1.1
6	0.884
7	1.316
8	1.297
9	1.277
10	1.352
11	1.765
12	1.224
13	1.665
14	1.898
15	2.488
16	2.334
17	1.751
18	1.752
19	1.928
20	2.614
21	2.75
22	2.832
23	2.878
24	2.715
25	2.935
26	2.869
27	3.039
28	2.699
29	2.406
```

测试结果是，3和6的几何级数的序列较快。

[GeometricSeries.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/basic/GeometricSeries.java)

## 2.1.31 倍率测试

插入排序：

```java
N:1000	abs_predict:0.011000	rel_predict:4.000000	real:0.011000	ratio:0.011000
N:2000	abs_predict:0.044000	rel_predict:0.044000	real:0.016000	ratio:1.454545
N:4000	abs_predict:0.176000	rel_predict:0.064000	real:0.014000	ratio:0.875000
N:8000	abs_predict:0.704000	rel_predict:0.056000	real:0.085000	ratio:6.071429
N:16000	abs_predict:2.816000	rel_predict:0.340000	real:0.294000	ratio:3.458824
N:32000	abs_predict:11.264000	rel_predict:1.176000	real:1.561000	ratio:5.309524
N:64000	abs_predict:45.056000	rel_predict:6.244000	real:4.124000	ratio:2.641896
N:128000	abs_predict:180.224000	rel_predict:16.496000	real:18.282000	ratio:4.433075
```

选择排序：

```java
N:1000	abs_predict:0.008000	rel_predict:4.000000	real:0.008000	ratio:0.008000
N:2000	abs_predict:0.032000	rel_predict:0.032000	real:0.007000	ratio:0.875000
N:4000	abs_predict:0.128000	rel_predict:0.028000	real:0.025000	ratio:3.571429
N:8000	abs_predict:0.512000	rel_predict:0.100000	real:0.110000	ratio:4.400000
N:16000	abs_predict:2.048000	rel_predict:0.440000	real:0.406000	ratio:3.690909
N:32000	abs_predict:8.192000	rel_predict:1.624000	real:1.884000	ratio:4.640394
N:64000	abs_predict:32.768000	rel_predict:7.536000	real:5.170000	ratio:2.744161
N:128000	abs_predict:131.072000	rel_predict:20.680000	real:25.257000	ratio:4.885300
```

希尔排序：

```java
N:1000	abs_predict:0.003000	rel_predict:2.462289	real:0.003000	ratio:0.003000
N:2000	abs_predict:0.007387	rel_predict:0.007387	real:0.002000	ratio:0.666667
N:4000	abs_predict:0.018189	rel_predict:0.004925	real:0.002000	ratio:1.000000
N:8000	abs_predict:0.044786	rel_predict:0.004925	real:0.005000	ratio:2.500000
N:16000	abs_predict:0.110275	rel_predict:0.012311	real:0.007000	ratio:1.400000
N:32000	abs_predict:0.271529	rel_predict:0.017236	real:0.011000	ratio:1.571429
N:64000	abs_predict:0.668583	rel_predict:0.027085	real:0.032000	ratio:2.909091
N:128000	abs_predict:1.646244	rel_predict:0.078793	real:0.079000	ratio:2.468750
N:256000	abs_predict:4.053528	rel_predict:0.194521	real:0.263000	ratio:3.329114
N:512000	abs_predict:9.980957	rel_predict:0.647582	real:0.975000	ratio:3.707224
N:1024000	abs_predict:24.576000	rel_predict:2.400732	real:1.275000	ratio:1.307692
N:2048000	abs_predict:60.513210	rel_predict:3.139418	real:2.792000	ratio:2.189804
N:4096000	abs_predict:149.001001	rel_predict:6.874710	real:6.942000	ratio:2.486390
```

希尔排序的增长量级大约为1.2到1.3，插入排序和选择排序的增长量级都是2。

[DoubleTest.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/basic/DoubleTest.java)

## 2.1.32 运行时间曲线图

曲线图绘制类：

[CurveGraphDrawer.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/CurveGraphDrawer.java)

同时我把这个类嵌入到了上一个倍率测试的类中。

[DoubleTest.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/basic/DoubleTest.java)

## 2.1.33 分布图

这道题和上一道题的区别在于画图并求平均是实时的。

[DistributionGraph.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/basic/DistributionGraph.java)

## 2.1.34 罕见情况

[ExtremeTest.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/basic/ExtremeTest.java)

希尔排序就是快，这个不必多说，至于选择排序和插入排序之间的菜鸡互啄，选择排序所用的时间是相对稳定的，而插入排序所用的时间取决于一个度量：逆序对的数量，逆序对越少，排序时间越快。

## 2.1.35 不均匀的概率分布

[UnuniformDistribution.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/basic/UnuniformDistribution.java)

```java
Quick	GUASSIAN	1000	0.08200000000000003
Quick	POISSION	1000	0.02300000000000001
Quick	GEOMETRIC	1000	0.014000000000000005
Insertion	GUASSIAN	1000	0.16300000000000012
Insertion	POISSION	1000	0.03100000000000002
Insertion	GEOMETRIC	1000	0.1210000000000001
Selection	GUASSIAN	1000	0.1320000000000001
Selection	POISSION	1000	0.35500000000000026
Selection	GEOMETRIC	1000	0.21600000000000016
Shell	GUASSIAN	1000	0.025000000000000012
Shell	POISSION	1000	0.003
Shell	GEOMETRIC	1000	0.013000000000000005
Merge	GUASSIAN	1000	0.09100000000000003
Merge	POISSION	1000	0.009000000000000001
Merge	GEOMETRIC	1000	0.007
Quick	GUASSIAN	2000	0.016000000000000007
Quick	POISSION	2000	0.02100000000000001
Quick	GEOMETRIC	2000	0.022000000000000013
Insertion	GUASSIAN	2000	0.4200000000000003
Insertion	POISSION	2000	0.06700000000000005
Insertion	GEOMETRIC	2000	0.32200000000000023
Selection	GUASSIAN	2000	0.32800000000000024
Selection	POISSION	2000	0.7640000000000006
Selection	GEOMETRIC	2000	0.3990000000000003
Shell	GUASSIAN	2000	0.02900000000000002
Shell	POISSION	2000	0.004
Shell	GEOMETRIC	2000	0.016
Merge	GUASSIAN	2000	0.024000000000000014
Merge	POISSION	2000	0.014000000000000005
Merge	GEOMETRIC	2000	0.02100000000000001
Quick	GUASSIAN	4000	0.04200000000000003
Quick	POISSION	4000	0.03000000000000002
Quick	GEOMETRIC	4000	0.04200000000000003
Insertion	GUASSIAN	4000	1.3739999999999992
Insertion	POISSION	4000	0.2420000000000002
Insertion	GEOMETRIC	4000	1.3719999999999988
Selection	GUASSIAN	4000	1.3789999999999993
Selection	POISSION	4000	4.520999999999999
Selection	GEOMETRIC	4000	2.4850000000000008
Shell	GUASSIAN	4000	0.06600000000000004
Shell	POISSION	4000	0.012000000000000004
Shell	GEOMETRIC	4000	0.04400000000000003
Merge	GUASSIAN	4000	0.048000000000000036
Merge	POISSION	4000	0.03300000000000002
Merge	GEOMETRIC	4000	0.04900000000000004
Quick	GUASSIAN	8000	0.09600000000000007
Quick	POISSION	8000	0.06900000000000005
Quick	GEOMETRIC	8000	0.08900000000000007
Insertion	GUASSIAN	8000	5.853
Insertion	POISSION	8000	1.6339999999999983
Insertion	GEOMETRIC	8000	9.065999999999999
Selection	GUASSIAN	8000	7.077000000000002
Selection	POISSION	8000	17.322
Selection	GEOMETRIC	8000	9.056000000000001
Shell	GUASSIAN	8000	0.12500000000000008
Shell	POISSION	8000	0.026000000000000016
Shell	GEOMETRIC	8000	0.09000000000000007
Merge	GUASSIAN	8000	0.10300000000000008
Merge	POISSION	8000	0.06700000000000005
Merge	GEOMETRIC	8000	0.09400000000000007
```

初步只测试了较短的数组长度，初步结论为：

- 归并排序：泊松分布的数组的排序时间更短，高斯和几何分布相近。
- 快速排序：在数据量较大的情况下，同样是泊松分布的数据排序时间更短。
- 插入排序：泊松分布的数据的优势极为明显。
- 选择排序：始终是高斯分布的数据排序所需时间更短。
- 希尔排序：泊松分布的数据排序时间更短。

看来，泊松分布对于排序的优势十分明显。

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/2-1-35-1.jpg)

## 2.1.36 不均匀的数据

猜想：shell排序始终是最快的，当存在大量相同元素时，另外两种元素中选择排序更快一些，因为插入排序需要消耗大量的时间在数组元素后移上，而这部分多花的时间会比选择排序额外多出的比较操作所需要的时间更多。

[UnuniformData.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/basic/UnuniformData.java)

```java
Half-Quarter-Quarter	Insertion	10000	0.568000
Half-Half	Insertion	10000	0.702000
Half-Random	Insertion	10000	0.609000
Half-Quarter-Quarter	Selection	10000	0.789000
Half-Half	Selection	10000	0.747000
Half-Random	Selection	10000	0.771000
Half-Quarter-Quarter	Shell	10000	0.008000
Half-Half	Shell	10000	0.007000
Half-Random	Shell	10000	0.007000
Half-Quarter-Quarter	Insertion	20000	1.547000
Half-Half	Insertion	20000	1.246000
Half-Random	Insertion	20000	1.872000
Half-Quarter-Quarter	Selection	20000	2.951000
Half-Half	Selection	20000	2.937000
Half-Random	Selection	20000	3.214000
Half-Quarter-Quarter	Shell	20000	0.008000
Half-Half	Shell	20000	0.002000
Half-Random	Shell	20000	0.016000
Half-Quarter-Quarter	Insertion	40000	6.197000
Half-Half	Insertion	40000	4.969000
Half-Random	Insertion	40000	8.840000
Half-Quarter-Quarter	Selection	40000	11.768000
Half-Half	Selection	40000	11.783000
Half-Random	Selection	40000	14.493000
Half-Quarter-Quarter	Shell	40000	0.012000
Half-Half	Shell	40000	0.010000
Half-Random	Shell	40000	0.042000
Half-Quarter-Quarter	Insertion	80000	25.246000
Half-Half	Insertion	80000	20.017000
Half-Random	Insertion	80000	42.451000
Half-Quarter-Quarter	Selection	80000	47.254000
Half-Half	Selection	80000	48.054000
Half-Random	Selection	80000	83.642000
Half-Quarter-Quarter	Shell	80000	0.030000
Half-Half	Shell	80000	0.026000
Half-Random	Shell	80000	0.112000
```

**然而结果是，希尔排序最快，插入排序明显优于选择排序。**

## 2.1.37 部分有序

"所有的元素和它们的正确位置的距离都不超过10"，这个不知道该怎么写。

猜想：shell排序始终是最快的，插入排序比选择排序快很多很多，接近于shell排序，因为插入排序能够对部分有序作出积极的反映，而选择排序则无动于衷。

```java
95%-Ordered	Insertion	10000	0.097000
5%-Scatter	Insertion	10000	0.070000
95%-Ordered	Insertion	20000	0.309000
5%-Scatter	Insertion	20000	0.570000
95%-Ordered	Insertion	40000	1.133000
5%-Scatter	Insertion	40000	0.816000
95%-Ordered	Insertion	80000	3.939000
5%-Scatter	Insertion	80000	3.423000
95%-Ordered	Selection	10000	1.941000
5%-Scatter	Selection	10000	1.891000
95%-Ordered	Selection	20000	7.975000
5%-Scatter	Selection	20000	7.772000
95%-Ordered	Selection	40000	35.156000
5%-Scatter	Selection	40000	32.574000
95%-Ordered	Selection	80000	125.827000
5%-Scatter	Selection	80000	124.241000
95%-Ordered	Shell	10000	0.018000
5%-Scatter	Shell	10000	0.010000
95%-Ordered	Shell	20000	0.021000
5%-Scatter	Shell	20000	0.020000
95%-Ordered	Shell	40000	0.044000
5%-Scatter	Shell	40000	0.051000
95%-Ordered	Shell	80000	0.101000
5%-Scatter	Shell	80000	0.107000
```

实际结果与假设基本一致。希尔排序对于大部分有序数组所需要的时间几乎是线性的。

[PartialOrdered.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/basic/PartialOrdered.java)

## 2.1.38 不同类型的元素

猜想：鉴于Java使用引用来表示对象，那么不论是int[]还是String[]都不会对排序过程中交换元素顺序所需时间产生较大的影响，倒是比较元素大小所需的时间显得更为重要。
shell排序在各种情况下都是最快的，插入排序需要更少的比较元素的次数，因此比选择排序更快一些。

[DifferentTypeElements.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/basic/DifferentTypeElements.java)

```java
Double-String[10]	Selection	10000	3.402000
Int-Int[20]	        Selection	10000	1.485000
String-Double	    Selection	10000	5.074000
Double-String[10]	Selection	20000	31.248000
Int-Int[20]	        Selection	20000	21.576000
String-Double	    Selection	20000	35.273000
Double-String[10]	Selection	40000	312.879000
Int-Int[20]	        Selection	40000	159.163000
String-Double	    Selection	40000	134.889000
Double-String[10]	Selection	80000	1725.878000
Int-Int[20]	        Selection	80000	1133.537000
String-Double	    Selection	80000	1160.325000
Double-String[10]	Insertion	10000	1.965000
Int-Int[20]	        Insertion	10000	5.208000
String-Double	    Insertion	10000	5.201000
Double-String[10]	Insertion	20000	25.594000
Int-Int[20]	        Insertion	20000	10.468000
String-Double	    Insertion	20000	13.038000
Double-String[10]	Insertion	40000	127.083000
Int-Int[20]	        Insertion	40000	42.355000
String-Double	    Insertion	40000	51.514000
Double-String[10]	Insertion	80000	565.728000
Int-Int[20]	        Insertion	80000	251.464000
String-Double	    Insertion	80000	242.488000
Double-String[10]	Shell	    10000	0.037000
Int-Int[20]	        Shell	    10000	0.029000
String-Double	    Shell	    10000	0.046000
Double-String[10]	Shell	    20000	0.142000
Int-Int[20]	        Shell	    20000	0.066000
String-Double	    Shell	    20000	0.113000
Double-String[10]	Shell	    40000	0.399000
Int-Int[20]	        Shell	    40000	0.245000
String-Double	    Shell	    40000	0.293000
Double-String[10]	Shell	    80000	0.955000
Int-Int[20]	        Shell	    80000	0.610000
String-Double	    Shell	    80000	0.757000
```

- 同一种排序算法，