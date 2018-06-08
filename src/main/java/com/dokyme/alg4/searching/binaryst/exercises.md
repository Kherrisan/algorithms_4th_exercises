# 练习

## 3.2.1 



## 3.2.2

A C E H R S X 构造最坏的二叉树，可以采用以下几种顺序：

A C E H R S X

A C E H R X S

A C E H X S R

A C E H X R S

A C E X H S R

A C E X H R S

## 3.2.3

H C A E S R X

H C E A S R X

H C E A S X R

H C A E S X R

H C S A E R X

给定一个键的集合，最优二叉树的构造应该只有一种。但是插入顺序是可以调整的。

## 3.2.4

b.不可能，最后一个元素都不是5...

d.不可能，8不可能在7的左子树里

## 3.2.5

因为二叉树的成长总是发生在叶子节点处，所以根节点和上层节点一旦确定就不会改变，因此可以将高频访问的节点先插入，低频访问的节点后插入，这样可以节约查找操作的总的时间。

当然，这种插入规则可能导致树不够平衡，如10、9、8、7、6、5、4、3、2、1，访问频率依次递减，那么树的结构就会退化为线性结构。可否通过增强树的平衡性来换取一部分高频节点下降的损失？

## 3.2.6

我选择在节点处保存一个新的字段，存放该节点的高度。在树的结构发生变化时（put，delete操作），手动调整路径中每个节点的高度值。

```java
        x.h = Math.max(height(x.left), height(x.right)) + 1;
```

## 3.2.7

```java
    /**
     * 查找的平均比较次数
     *
     * @return
     */
    private double average() {
        return innerPath(root) * 1.0 / size() + 1;
    }

    /**
     * 计算以x为树根的子树的内部路径长度
     *
     * @param x
     * @return
     */
    private int innerPath(Node x) {
        if (x == null) {
            return 0;
        }
        return height(x) + innerPath(x.left) + innerPath(x.right);
    }
```

## 3.2.8

题目中的N是什么意思有些不太明白。

## 3.2.9

对于有N个键构成的二叉树有多少种不同的形状，我写了个程序来计算这个个数，就不画出来了。

```java
    public static int shapes(int n) {
        if (n <= 1) {
            return 1;
        } else {
            int t = 0;
            for (int i = 0; i < n; i++) {
                t += shapes(i) * shapes(n - 1 - i);
            }
            return t;
        }
    }
```

## 3.2.10

[TestBST.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/binaryst/TestBST.java)

## 3.2.11

高度和节点个数相等的二叉树即为退化为单链表的二叉树结构，每个节点都只有一个子节点（除了末端的节点之外），因此高度为N且有N个节点的二叉树有2^(N-1)种不同的形状。

满足这种条件的插入序列有下面几种情况：

1. 降序序列
2. 升序序列
3. 在一个范围内，按照最大元素、最小元素、第二大元素、第二小元素。。。这样的顺序排列
4. 和3对称的方式排列

除了以上4种，还可以将1、3组合，即先降序排列，然后以最大元素和次大元素为范围进行蛇形排列。

3、4构造出来的二叉树是完全蛇形（一左一右）的二叉树，1、2构造出来的是向一边偏的二叉树，组合构造出来的是不规则的二叉树。

## 3.2.12

[BinarySearchTreeWithoutRank.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/binaryst/BinarySearchTreeWithoutRank.java)

## 3.2.13


