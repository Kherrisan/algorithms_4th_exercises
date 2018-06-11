# 提高题

## 2.4.21 基础数据结构

栈为单端输入输出数据结构，

## 2.4.22 调整数组大小

```
    @Override
    public void insert(T t) {
        if (n + 1 >= pq.length) {
            int newLength = n + n / 2;
            T[] npq = (T[]) new Comparable[newLength];
            System.arraycopy(pq, 0, npq, 0, pq.length);
            pq = npq;
        }
        pq[++n] = t;
        swim(n);
    }
```

## 2.4.23 Multiway的堆

索引优先队列的实现：

[IndexMinPQ.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/priorityqueue/IndexMinPQ.java)

题目要求有点看不懂。。。

**暂留**

## 2.4.24 使用链接的优先队列

[LinkedMaxHeap.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/priorityqueue/LinkedMaxHeap.java)

要求：即使在无法预知队列大小的情况下，也能保证优先队列的基本操作所需要的时间为对数级别。这个要求的意思应该是，要记录最后一个二叉树中最后一个元素的位置，通过这最后一个元素的位置来得到前一个元素的位置或后一个元素的位置，而不是每次删除最大元素或者插入元素时都要遍历一遍整个二叉树。

## 2.4.25 计算数论

找出0-10^6中所有满足a^3+b^3=c^3+d^3的数。

[CubeSum.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/priorityqueue/CubeSum.java)

本题的核心在于使用N的空间作为Buffer，每次取出一个最小的tuple，并加入一个稍大的tuple。初始化时的一些较大的tuple可能会在队列中存活很长时间，而较小的tuple则会很快被弹出。

这么做的优点是大幅度节约了空间，缺点是浪费了时间，暴力算法先枚举再排序需要N^2+NlogN的时间，而使用最小堆需要N^2*logN的时间。

从程序输出中随便找了两个：

```
1728
	1^3 + 12^3
	9^3 + 10^3
	10^3 + 9^3
	12^3 + 1^3
13825
	2^3 + 24^3
	20^3 + 18^3
	18^3 + 20^3
	24^3 + 2^3
```

满足这个性质的“完全立方数”不多，要把10^6以内的所有情况找出来需要不少时间。

## 2.4.26 无需交换的堆

就是把交换操作移到了最后，上浮或下沉的中途只记录位置，并把前一个元素后移一层。

[MinHeapWithoutExch.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/priorityqueue/MinHeapWithoutExch.java)

## 2.4.27 找出最小元素

```
    public T delMin() {
        int minIndex = n / 2 + 1;
        for (int i = minIndex + 1; i <= n; i++) {
            if (less(i, minIndex)) {
                minIndex = i;
            }
        }
        exch(n, minIndex);
        swim(minIndex);
        T min = pq[n];
        pq[n--] = null;
        return min;
    }
```

这种实现需要线性时间。

## 2.4.28 选择过滤

需要构建一个大小为M的最小堆，输入数据长度为N。

那么所需时间为：M+NlogM。

## 2.4.29 同时面向最大元素和最小元素的优先队列

题目要求insert、delMin、delMax使用对数级别时间，min、max需要常数级别时间。

>最小最大堆（min-max heap）是支持两种操作 DeleteMin 和 DeleteMax 的数据结构，每个操作用时 O(log N)。该结构与二叉堆相同，不过，其堆序性质为：对于在偶数深度上的任意节点 X，存储于 X 上的关键字小于它的父亲但是大于它的祖父（这是有意义的），对于奇数深度上的任意节点 X，存储在 X 上的关键字大于它的父亲但是小于它的祖父。
>以上内容节选自 “Data Structures and Algorithm Analysis in C” 一书，其中文译名为《数据结构与算法分析——C语言描述》。在实际应用中，最小最大堆一般作为双端优先队列使用。注意，在定义中，根节点的深度为 0。

**暂留**

## 2.4.30 动态中位数

最小堆存放比中位数大的元素，最大堆存放比中位数小的元素。最大堆的大小大于或等于最小堆的大小，且二者大小相差不超过1。

[DynamicMedian.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/priorityqueue/DynamicMedian.java)

## 2.4.31 快速插入

在swim和sink操作中，可以使用二分查找的方法来减少比较次数，但是如何将元素移动到正确位置的同时不浪费额外的时间，也是一个值得考虑的问题。

快速插入应该仅仅是减少了比较次数，而没有显著降低访存次数。

[FastInsertPQ.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/priorityqueue/FastInsertPQ.java)


## 2.4.32 下界

**暂留**

## 2.4.33 索引优先队列的实现

[IndexMinPQ.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/priorityqueue/IndexMinPQ.java)

## 2.4.34 索引优先队列的实现（附加操作）

[IndexMinPQ.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/priorityqueue/IndexMinPQ.java)

## 2.4.35 离散概率分布的取样

以满二叉树的形式表达概率密度函数和分布函数的概率，每个节点的double值代表命中以这个节点为根的树中某个节点的概率之和。随机产生一个随机数，然后和树根的值对比，如果大于等于某个节点的值，

**暂留**