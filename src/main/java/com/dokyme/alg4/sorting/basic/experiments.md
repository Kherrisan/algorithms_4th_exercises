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

如果数组中有相当一部分元素是相等的话，那么选择排序所需要的时间不会产生明显的变换，插入排序中，较小元素前移的步骤所需要的步数应该也不会产生太大的变换。当然这只是我的主观想法。

```java
Selection: 19.037
Insertion: 8.597
```

实际结果是，选择排序快。使用int[]数组进行排序，再进行测试：

```java
Selection: 2.6479999999999997
Insertion: 1.8760000000000001
```

插入排序是稳定排序，选择排序不稳定，然而结果却是选择排序更快一些。尝试分析其中缘由：选择排序是所有排序中移动元素位置最少的排序方法，但其需要的比较次数较插入排序更多，出现了这种结果，说明在大量重复元素的情况下，在int[]中移动元素的消耗大于比较int变量大小的消耗。当然这种消耗即便是在对中长数组排序的情况下也还是仅仅会造成常数级别的差距。

最后在测试的排序算法中加入了快排，快排一骑红尘。

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

## 