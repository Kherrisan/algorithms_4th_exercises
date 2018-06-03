package com.dokyme.alg4.searching.binaryst;

import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/3-17:58
 * Description:
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    public Node root;

    private boolean draw;

    public class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

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

    public BinarySearchTree(boolean draw) {
        this.draw = draw;
    }

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

    public Value get(Key key) {
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
            return x.val;
        }
    }

    public void put(Key key, Value val) {
        put(root, key, val);
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
            return new Node(key, val);
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
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
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
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> list = new LinkedList<>();
        keys(list, root, lo, hi);
        return list;
    }

    private void keys(List<String> list, Node x, Key lo, Key hi) {
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

    private void adjustPosition(){
        
    }

    public void draw() {
        StdDraw.setXscale(0, 960);
        StdDraw.setYscale(0, 640);
        final double r = 5d;

    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {

    }
}
