# 练习题

## 3.3.1

![](https://s1.ax1x.com/2018/06/25/PCdnKK.jpg)

## 3.3.2

![](https://s1.ax1x.com/2018/06/25/PCdZgx.jpg)

## 3.3.3

插入6个节点后，2-3树必然是根节点有两个主键，其3个子节点中有1个子节点有两个主键，另2个子节点各有一个主键，只要满足：最后一个插入发生在那两个只含有1个主键的节点上即可。

例如插入顺序为：SEACHXM

## 3.3.4

首先2-3树是一棵完全平衡的树，如果所有节点均为2节点，那么树的高度为ceil(lgN)，如果均为3节点，那么树的高度为ceil(log_3N)，那么一棵2-3树的高度应该在二者之间，我不知道题目中所说的证明是不是这个意思。

## 3.3.5

看来给定节点个数的2-3树的不同结构的个数远没有二叉搜索树的结构来的多。

![](https://s1.ax1x.com/2018/06/25/PCdECR.jpg)

## 3.3.6

计算节点个数为N时所有可能树结构出现的概率，可以通过计算N-1时各结构的出现次数乘以在该结构上各位置插入一个新节点所构成的新的树个数。

![](https://s1.ax1x.com/2018/06/25/PCdev6.jpg)

## 3.3.7

![](https://s1.ax1x.com/2018/06/25/PCdV81.jpg)

## 3.3.8

题目不太理解

**暂留**

## 3.3.9

## 3.3.10

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/3-3-10-1.jpg)

## 3.3.11

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/3-3-11-1.jpg)

## 3.3.12

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/3-3-12-1.jpg)

## 3.3.13

如果按照升序将键插入一棵红黑树，那么红黑树的树的高度的确是单调递增的，当然不是每次插入都会使高度增加。

## 3.3.14

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/3-3-14-1.jpg)

## 3.3.15

如果按照降序将键插入一棵红黑树，那么高度还是递增的，偶尔不增，但不会减。

## 3.3.16

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/3-3-16-1.jpg)

## 3.3.17

## 3.3.18

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/3-3-18-1.jpg)

## 3.3.19

**暂留**

## 3.3.20

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/3-3-20-1.jpg)

## 3.3.21

[TestRB.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/balanced/TestRB.java)

## 3.3.22