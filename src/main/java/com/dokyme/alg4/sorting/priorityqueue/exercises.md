# 练习

## 2.4.1 

```
R   R   P   O   T   Y   I   I   U   Q   E   U
```

## 2.4.2

我不懂题目里所说的用栈或者队列是怎么记录的，是保持栈顶的元素最大？

如果只要找到最大元素，那不用栈也不用队列，用一个变量就可以了。

## 2.4.3

实现了四种优先队列。

[DifferentPQ.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/priorityqueue/DifferentPQ.java)

实现 | insert | delMax 
- | :-: | -: 
有序数组 | n | 1 
无序数组 | 1 | n 
有序链表 | n | 1
无序链表 | 1 | n

## 2.4.4

降序排列的数组应该是一个最大堆，但最大堆不一定是降序排列数组。

## 2.4.5

```
Y   T   U   S   Q   N   E   A   S   I   O   E
```

## 2.4.6

## 2.4.7

一般的，在最大堆中，第k大的数据可能出现的位置为k/2+1,k/2+2,...,k+1

k=2时，第2大的数据可能出现在2,3

k=3时，第3大的数据可能出现在2,3

k=4时，第4大的数据可能出现在4,5,6,7

## 2.4.8

一般的，在最大堆中，第k小的数据