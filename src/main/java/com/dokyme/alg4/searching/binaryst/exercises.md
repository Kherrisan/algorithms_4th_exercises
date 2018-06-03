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
    private double avgCompares() {
        return avgCompares(root) * 1.0 / size() + 1;
    }


```

