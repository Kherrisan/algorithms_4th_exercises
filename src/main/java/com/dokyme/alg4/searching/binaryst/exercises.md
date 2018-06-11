# 练习

## 3.2.1 

好像是29次比较。

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

**暂留**

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

[BinarySearchTreeNonRecursive.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/binaryst/BinarySearchTreeNonRecursive.java)

## 3.2.14

[BinarySearchTreeNonRecursive.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/binaryst/BinarySearchTreeNonRecursive.java)

## 3.2.15

a:E Q

b:E Q J M

c:E Q

d:E(3) Q(3) J(4)

e:E(8) D(7) Q() J M T S

f:E D Q J M T S

## 3.2.16

内部路径长度：所有节点的深度之和。

外部路径长度：从根节点到空链接的所有路径上的节点总数。

一颗二叉树每插入一个新的节点，假设内部路径变大e，即这个节点是路径上的第e个节点，那么外部路径变大了2*(e+1)-e=e+2，因此，连续插入N个节点，则外部路径长度总是比内部路径长度多2N。

## 3.2.17

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/3-2-17-1.jpg)

## 3.2.18

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/3-2-18-1.jpg)

## 3.2.19

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/3-2-19-1.jpg)

## 3.2.20

keys范围操作可以分为三个步骤：

1. 先不停地向右子树试探，直到找到比lo小的节点，说明这个节点为根的子树可以被排除掉。
2. 回溯，将范围内的所有节点加入到列表中。
3. 再回溯的过程中判断节点是否比hi大，如果大，则该节点为根节点的子树被排除。

其中步骤1需要O(logN)的时间，步骤2和步骤3合起来需要范围内元素个数成正比的时间。

最坏情况是，步骤1花费了logN时间到了底层，步骤2和步骤3合起来将所有节点都纳入列表中。

## 3.2.21

```java
    public Key randomKey() {
        if (root == null) {
            return null;
        }
        Node x = root;
        while (x != null) {
            int move = StdRandom.uniform(3);
            if (move == 0) {
                if (x.left != null) {
                    x = x.left;
                } else {
                    return x.key;

                }
            } else if (move == 1) {
                if (x.right != null) {
                    x = x.right;
                } else {
                    return x.key;
                }
            } else {
                return x.key;
            }
        }

        return null;
    }
```

## 3.2.22

如果其后继节点有左子节点，那么这个左子节点肯定比其后继节点小，那么左子节点才应该是真正的后继节点。

反之亦然。

## 3.2.23

不满足交换律，因为如果删除某个节点使的一个节点由原来的有两个子节点变为只有一个子节点，那么删除的策略就会不一样，即删除操作是否涉及到后继节点的情况是不同的。在这种情况下，先删谁后删谁的顺序是会产生影响的。

## 3.2.24

**暂留**