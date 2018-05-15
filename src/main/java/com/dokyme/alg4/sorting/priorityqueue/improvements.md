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

