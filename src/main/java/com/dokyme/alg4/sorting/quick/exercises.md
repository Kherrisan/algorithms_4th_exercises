# 练习

## 2.3.1

```java_holder_method_tree
i   j   0   1   2   3   4   5   6   7   8   9   10  11
0  11   e   a   s   y   q   u   e   s   t   i   o   n
2   6   e   a  +s   y   q   u  +e   s   t   i   o   n
3   2   e   a  +e  +y   q   u   s   s   t   i   o   n
        e   a   e   y   q   u   s   s   t   i   o   n
```

## 2.3.2

```java_holder_method_tree
lo  j   hi  0   1   2   3   4   5   6   7   8   9   10  11
            e   a   s   y   q   u   e   s   t   i   o   n
0   2   11  e   a   e   y   q   u   s   s   t   i   o   n        
0   1   1   a   e   e   y   q   u   s   s   t   i   o   n
0   1   1   a   e   e   y   q   u   s   s   t   i   o   n
0       0   
2       1   
2   2   11  a   e   e   y   q   u   s   s   t   i   o   n
2       1   
3  11   11  a   e   e   n   q   u   s   s   t   i   o   y
3   4   10  a   e   e   i   n   u   s   s   t   q   o   y
3   3
5  10   10  a   e   e   i   n   o   s   s   t   q   u   y
5   5   9   a   e   e   i   n   o   s   s   t   q   u   y
5       4
6   7   9   a   e   e   i   n   o   q   s   t   s   u   y
6       6
8   9   9   a   e   e   i   n   o   q   s   s   t   u   y
8       8
10      9
11      10
12      11  
```

## 2.3.3

每一层，每个元素至多会被交换一次（也就是位置改变至多一次）。

因此最大的元素最多会被交换logN次。

## 2.3.4

1. 10  9   8   7   6   5   4   3   2   1

暂留

## 2.3.5

有意思的是，直接对一个二值的数组做partion，得到的结构不一定是有序的。

略作修改的partion函数。

[Quick2Way.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/quick/Quick2PK.java)

## 2.3.6

```java_holder_method_tree
N:100	C:718	Estimate:921
N:1000	C:11662	Estimate:13815
N:10000	C:169900	Estimate:184206
N:100000	C:2099877	Estimate:2302585
N:1000000	C:25277969	Estimate:27631021
```

实际比较次数会比估计值2NlnN小一些。

## 2.3.7

估计大小为0、1、2的子数组的数量。子数组的长度反应的是在原数组中枢轴点所在的正确位置。如果正确位置为lo或lo+1，则左侧没有子数组；如果正确位置为lo+2，则左侧子数组长度为1。

只考虑左侧子数组，其长度为N的概率等于其在父数组中是第N+1小元素的概率。假设子数组长度为2，那么枢轴点在父数组中是第3小的元素。有2个元素比他小，这个概率与他本身的值以及全局的值域有关。

**暂留**

## 2.3.8

```java_holder_method_tree
N:100	C:584	NlogN:664
N:1000	C:9016	NlogN:9965
N:10000	C:124496	NlogN:132877
N:100000	C:1571550	NlogN:1660964
N:1000000	C:18995734	NlogN:19931568
```

试验发现，处理长度为N的单值数组所需要进行的比较，比多值情况下要少。

我认为原因应该是（按照Quick.sort()的实现方法）单值数组中每次枢轴点都会被置于数组的正中间，划分的路径是一颗较为对称的二叉树。

NlogN？

## 2.3.9

这道题和2.3.5有关联。

在处理二值数组的时候，Quick.sort并不能在一次partion中就把整个数组排好序，原因在于：假如枢轴是较大值，那么j从后往前移动的时候遇到较大值并不会跳过；如果枢轴是较小值，那么i从前往后移动的时候，遇到较小值，并不会跳过。不可能存在一个枢轴是既大于较小值又小于较大值的。在一个分布较为均匀的二值数组里，Quick.sort会对数组做多次二分。
在处理三值数组的时候，。。。。

**暂留**

## 2.3.10

一看就不会。

**暂留**

## 2.3.11

在切分的过程中，如果遇到和切分元素相等的元素，不停下来，而是继续扫描。即和切分元素相等的元素不参与前后交换。那么在切分结束之后得到的前半段和后半段数组中都会含有切分元素，并且位置不确定。

如二值数组，其中一种值为切分元素的值，那么其中一个指针很有可能会移动到数组的头部或尾部（所有的元素都满足要求），在这种情况下切分后的子数组的长度为原数组长度-1，即整个排序过程退化成了一个选择排序。递归调用次数为N。那么时间复杂度为O(N^2)。

## 2.3.12

```java_holder_method_tree
v=b
lt  i   gt  0   1   2   3   4   5   6   7   8   9   10  11  12  13  14
0   1   14 -b  -a   b   a   b   a   b   a   c   a   d   a   b   r  -a   
1   2   14  a  -b  -b   a   b   a   b   a   c   a   d   a   b   r  -a   
1   3   14  a  -b   b  -a   b   a   b   a   c   a   d   a   b   r  -a  
2   4   14  a   a  -b   b  -b   a   b   a   c   a   d   a   b   r  -a
2   5   14  a   a  -b   b   b  -a   b   a   c   a   d   a   b   r  -a
3   6   14  a   a   a  -b   b   b  -b   a   c   a   d   a   b   r  -a
3   7   14  a   a   a  -b   b   b   b  -a   c   a   d   a   b   r  -a
4   8   14  a   a   a   a  -b   b   b   b  -c   a   d   a   b   r  -a
4   8   13  a   a   a   a  -b   b   b   b  -a   a   d   a   b  -r   c
5   9   13  a   a   a   a   a  -b   b   b   b  -a   d   a   b  -r   c
6  10   13  a   a   a   a   a   a  -b   b   b   b  -d   a   b  -r   c
6  10   12  a   a   a   a   a   a  -b   b   b   b  -r   a  -b   d   c
6  10   12  a   a   a   a   a   a  -b   b   b   b  -b  -a   r   d   c
6  11   11  a   a   a   a   a   a  -b   b   b   b   b  -a   r   d   c
6  12   11  a   a   a   a   a   a  -a   b   b   b   b  -b  -r   d   c
```

## 2.3.13

最佳情况：logN
平均情况：1.39logN
最坏情况：N

## 2.3.14

**暂留**