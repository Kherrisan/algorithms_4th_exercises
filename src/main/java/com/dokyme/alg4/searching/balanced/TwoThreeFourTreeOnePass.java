package com.dokyme.alg4.searching.balanced;

import com.dokyme.alg4.searching.st.SymbolTable;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 3.3.26
 *
 * @author dokym
 * @date 2018/6/30-14:53
 * Description:
 */
public class TwoThreeFourTreeOnePass<Key extends Comparable<Key>, Value> extends SymbolTable<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node root;

    public static void main(String[] args) {

    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private void flipColors(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        boolean xc = x.color;
        x.color = y.color;
        y.color = xc;
        y.size = x.size;
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        boolean xc = x.color;
        x.color = y.color;
        y.color = xc;
        y.size = x.size;
        x.size = size(x.left) + size(x.right) + 1;
        return y;
    }

    private void adjustParentRelation(Node child, Node parent) {
        if (child.key.compareTo(parent.key) < 0) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    /**
     * 非递归的2-3-4树的插入元素方法
     *
     * @param key
     * @param val
     */
    @Override
    public void put(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val, BLACK, 1);
        }
        boolean newNode = !contains(key);
        Node parent = null, grandParent = null, grandGrandParent = null;
        Node x = root;
        while (x != null) {
            //先检查是否左右节点都是红
            if (isRed(x.left) && isRed(x.right)) {
                flipColors(x);
            }
            //检查flip操作有没有导致rotate
            if (grandParent != null && isRed(grandParent.left) && isRed(grandParent.left.right)) {
                //左旋
                parent = rotateLeft(grandParent.left);
                adjustParentRelation(parent, grandParent);
            }
            if (grandParent != null && isRed(grandParent.left) && isRed(grandParent.left.left)) {
                //右旋
                grandParent = rotateRight(grandParent);
                adjustParentRelation(grandParent, grandGrandParent);
            }
            grandGrandParent = grandParent;
            grandParent = parent;
            parent = x;
            if (newNode) {
                x.size++;
            }
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                x.value = val;
                return;
            }
        }

    }

    @Override
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.value;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int n) {
        return null;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int size;
        int depth;

        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }
}
