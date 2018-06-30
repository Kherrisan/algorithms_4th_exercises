package com.dokyme.alg4.searching.balanced;

import com.dokyme.alg4.searching.st.SymbolTable;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;

import static com.dokyme.alg4.Utils.printSeperation;
import static com.dokyme.alg4.Utils.randomString;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 3.3.25
 *
 * @author dokym
 * @date 2018/6/30-13:45
 * Description:
 */
public class TwoThreeFourTreeTopDown<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    public static void main(String[] args) {
        //测试其平均路径长度
        for (int n = 1000; n <= 100000; n <<= 2) {
            TwoThreeFourTreeTopDown<String, Integer> tree = new TwoThreeFourTreeTopDown<>();
            for (int i = 0; i < n; i++) {
                tree.put(randomString(16), 1);
            }
            StdOut.println(String.format("Size:%d\tInnerPathLength:%d\tAveragePathLength:%f", tree.size(), tree.innerPath(), tree.innerPath() * 1.0 / tree.size()));
            printSeperation();
        }
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
        return y;
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

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private void flipColor(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    /**
     * 2-3-4树插入元素的方法
     * 简而言之，就是将flip操作提前到递归到左右子树之前
     *
     * @param x
     * @param key
     * @param value
     * @return
     */
    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, RED, 1);
        }
        if (isRed(x.left) && isRed(x.right)) {
            //将红连接上移，这样，如果父节点是3变为4节点，父节点为2节点变为3节点
            flipColor(x);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
        if (isRed(root.left) && isRed(root.right)) {
            //根节点是4节点，将其拆为2个2节点
            flipColor(root);
            root.color = BLACK;
        }
    }

    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x;
        }
    }

    @Override
    public Value get(Key key) {
        Node x = get(root, key);
        if (x == null) {
            return null;
        }
        return x.value;
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public boolean contains(Key key) {
        return get(key) == null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
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

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
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

    private int innerPath() {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        int path = 0;
        queue.offer(root);
        root.depth = 1;
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            path += n.depth;
            if (n.left != null) {
                n.left.depth = n.depth + 1;
                queue.offer(n.left);
            }
            if (n.right != null) {
                n.right.depth = n.depth + 1;
                queue.offer(n.right);
            }
        }
        return path;
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
