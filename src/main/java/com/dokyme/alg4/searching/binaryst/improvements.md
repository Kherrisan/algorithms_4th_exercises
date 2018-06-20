# 提高题

## 3.2.25 完美平衡

[PerfactBalance.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/binaryst/PerfactBalance.java)

## 3.2.26 准确的概率


## 3.2.27 内存使用

二叉搜索树：

Tree

- root:Node:4B
   
Node

- key:String:4B
- val:Integer:4B
- left:Node:4B
- right:Node:4B
- N:int:4B

总的内存使用为 20N+4 bytes

BinarySearchST

- size:int:4B
- items[]
    - key:String:4B
    - val:Integer:4B

总的内存使用为 8N+4 bytes

SequentialSearchST

- size:int:4B
- first:Node:4B
    - key:String:4B
    - val:Integer:4B
    - next:Node:4B
    
总的内存使用为 12N+8 bytes

以上是只考虑引用，忽略额外的对象头的，对三种符号表的内存占用空间的评估。

如果只考虑二叉搜索树：

(64-bits Windows)

- Object overhead:16B
- root:Node:4B
    - Object overhead:16B
    - key:String:4B
    - val:Integer:4B
        - Object overhead:16B
        - value:int:4B
    - left:Node:4B
    - right:Node:4B
    - n:Int:4B
    
字符串是从外存中读入的，不计入内存空间的使用。

总的内存使用为 56N+20 bytes。

## 3.2.28 缓存

```java
    @Override
    public Value get(Key key) {
        if (cached != null && cached.key.equals(key)) {
            return cached.val;
        }
        return get(root, key);
    }
```

## 3.2.29 二叉树检查

```java
    public boolean isBinaryTree() {
        try {
            isBinaryTree(root);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private int isBinaryTree(Node node) {
        if (node == null) {
            return 0;
        }
        int c = isBinaryTree(node.left) + isBinaryTree(node.right) + 1;
        assert c == node.N;
        return c;
    }
```

## 3.2.30 有序性检查

网上有人是这么写的：

```java
    public boolean isOrdered(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }
        if (min != null && min.compareTo(x.key) >= 0) {
            return false;
        }
        if (max != null && max.compareTo(x.key) <= 0) {
            return false;
        }
        return isOrdered(x.left, min, x.key)
                && isOrdered(x.right, x.key, max);
    }
```

我认为这么写是有问题的，因为只要有一边不为null，就不会对子树进行递归的判断。我修改之后的代码如下：

```java
    public boolean isOrdered() {
        return isOrdered(root, min(root), max(root));
    }

    private boolean isOrdered(Node x, Node min, Node max) {
        if (x == null) {
            return true;
        }
        return isOrdered(x.left, min, x)
                && isOrdered(x.right, x, max)
                && (x.key.compareTo(min.key) > 0 || (x.key.compareTo(min.key) == 0 && x == min))
                && (x.key.compareTo(max.key) < 0 || (x.key.compareTo(max.key) == 0 && x == max));
    }
```

一棵树是有序的<<--左子树是有序的且右子树是有序的且当前这棵树的根节点在min-max范围

## 3.2.31 等值键检查

## 3.2.32 验证

书上已经给出了答案：

```java
    public boolean isBST() {
        if (!isBinaryTree()) {
            return false;
        } else if (!isOrdered()) {
            return false;
        } else if (!hasNoDuplicates(root)) {
            return false;
        }
        return true;
    }
```

先检查是否有环（是不是单向生长的树状结构），再检查是否有序（满足二叉搜索树定义），最后检查是否有重复键。

## 3.2.33 选择/排名检查

```java
    public boolean checkSelectAndRank() {
        for (int i = 0; i < size() - 1; i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (Key key : keys()) {
            if (!key.equals(select(rank(key)))) {
                return false;
            }
        }
        return true;
    }
```

## 3.2.34 线性符号表

除了put方法插入新的节点需要用select和rank来获得前驱和后继节点之外（需要对数时间），其他改变树结构的操作都只需要利用节点的succ和prev属性并修正他们，即只需要常数时间。

[ThreadedSearchTree.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/binaryst/ThreadedSearchTree.java)

## 3.2.35 改进的分析


## 3.2.36 迭代器

```java
    public Iterable<Key> keys(Node x, Key lo, Key hi) {
        Stack<Node> stack = new Stack<>();
        List<Key> res = new LinkedList<>();
        while (x != null || !stack.empty()) {
            //把x的所有范围内的左子节点都压入栈
            while (x != null && x.key.compareTo(lo) >= 0) {
                stack.push(x);
                x = x.left;
            }
            //弹出一个x，这个x肯定在范围内
            if (!stack.empty()) {
                x = stack.pop();
                res.add(x.key);
                x = x.right;
            }
        }
        return res;
    }
```

## 3.2.37 层级遍历

实际上就是广度优先搜索。

```java
    public void printLevel(){
        printLevel(root);
    }

    public void printLevel(Node x) {
        Queue<Node> q = new LinkedList<>();
        q.offer(x);
        while (!q.isEmpty()) {
            x = q.poll();
            if (x != null) {
                StdOut.println(x);
                q.offer(x.left);
                q.offer(x.right);
            }
        }
    }
```

个人感觉广度优先遍历比深度更简单一些。

## 3.2.38 绘图

这道题工作量很大。

[一个介绍树的绘制算法的PPT](http://graphics.stanford.edu/courses/cs448b-02-winter/lectures/treesgraphs/tree.graph.pdf)

[另一个国外的材料](https://llimllib.github.io/pymag-trees/)

