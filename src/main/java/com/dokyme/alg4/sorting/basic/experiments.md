### 2.1.23 纸牌排序

这道题就不找朋友玩纸牌了，估计他们要么用的是随机排序要么是选择排序，插入排序应该用的不多，希尔排序应该是不会有人用的。

### 2.1.24 插入排序的哨兵

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

### 2.1.25 不需要交换的插入排序

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

### 2.1.26 原始数据类型

隐式的装箱和拆箱应该不会成为影响运行时间的因素，因为这是语法糖，是在编译时进行优化的。从运行结果来看，二者的运行时间也是相等的。

值得注意的是int[]不能装箱为Comparable[]。

### 2.1.27 希尔排序的用时是次平方级的

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