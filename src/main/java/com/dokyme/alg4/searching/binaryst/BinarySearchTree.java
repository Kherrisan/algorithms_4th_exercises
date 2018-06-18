package com.dokyme.alg4.searching.binaryst;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/3-17:58
 * Description:
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements BinaryTree<Key, Value> {

    public Node root;

    private Node cached;

    private boolean draw;

    public class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
        private int h;

        private double x;
        private double y;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }

        @Override
        public int hashCode() {
            int hash = key.hashCode() ^ val.hashCode();
            if (left != null) {
                hash ^= left.hashCode();
            }
            if (right != null) {
                hash ^= right.hashCode();
            }
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            Node n = (Node) obj;
            return key == n.key && val == n.val && left == n.left && right == n.right;
        }
    }

    public BinarySearchTree() {
    }

    public BinarySearchTree(boolean draw) {
        this.draw = draw;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    @Override
    public Value get(Key key) {
        if (cached != null && cached.key.equals(key)) {
            return cached.val;
        }
        return get(root, key);
    }

    /**
     * 二分查找的递归形式
     *
     * @param x
     * @param key
     * @return
     */
    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            cached = x;
            return x.val;
        }
    }

    @Override
    public void put(Key key, Value val) {
        if (cached != null && cached.key.equals(key)) {
            cached.val = val;
            return;
        }
        root = put(root, key, val);
    }

    /**
     * 递归地查找键所对应的节点并修改值
     *
     * @param x
     * @param key
     * @param val
     * @return
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        x.h = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    public int height() {
        return height(root);
    }

    /**
     * 计算子树x的高度
     * 规定：叶子节点高度为1
     *
     * @param x
     * @return
     */
    private int height(Node x) {
        if (x == null) {
            return 0;
        }
        return x.h;
    }

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

    @Override
    public Key min() {
        Node n = min(root);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    @Override
    public Key max() {
        Node n = max(root);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return floor(x.left, key);
        } else if (cmp == 0) {
            return x;
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        } else if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    public Key select(int k) {
        Node t = select(root, k);
        if (t == null) {
            return null;
        } else {
            return t.key;
        }
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (k < t) {
            return select(x.left, k);
        } else if (k > t) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    /**
     * 当前子树中键小于key的节点个数
     *
     * @param x
     * @param key
     * @return
     */
    private int rank(Node x, Key key) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(x.left, key);
        } else if (cmp > 0) {
            return size(x.left) + 1 + rank(x.right, key);
        } else {
            return size(x.left);
        }
    }

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

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            //如果x只有一个子节点，就只要把他一个子节点提上来
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            //找到x的后继节点
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        x.h = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    @Override
    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        x.h = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        x.h = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> list = new LinkedList<>();
        keys(list, root, lo, hi);
        return list;
    }

    private void keys(List<Key> list, Node x, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int clo = lo.compareTo(x.key);
        int chi = hi.compareTo(x.key);
        if (clo < 0) {
            keys(list, x.left, lo, hi);
        }
        if (clo <= 0 && chi >= 0) {
            list.add(x.key);
        }
        if (chi > 0) {
            keys(list, x.right, lo, hi);
        }
    }

    private void adjustPosition() {

    }

    public void draw() {
        StdDraw.setXscale(0, 960);
        StdDraw.setYscale(0, 640);
        final double r = 5d;

    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static double optCompares(int n) {
        return 0d;
    }

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

    private boolean hasNoDuplicates(Node x) {

        return true;
    }

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

    public static void main(String[] args) {

    }
}
