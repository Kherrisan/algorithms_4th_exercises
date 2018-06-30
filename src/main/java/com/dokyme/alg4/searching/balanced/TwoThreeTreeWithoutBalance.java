package com.dokyme.alg4.searching.balanced;

import com.dokyme.alg4.NotImplementedException;
import com.dokyme.alg4.searching.st.SymbolTable;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;

import static com.dokyme.alg4.Utils.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 3.3.23
 *
 * @author dokym
 * @date 2018/6/30-12:08
 * Description:
 */
public class TwoThreeTreeWithoutBalance<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    public static void main(String[] args) {
        //测试其平均路径长度
        for (int n = 1000; n <= 100000; n <<= 2) {
            TwoThreeTreeWithoutBalance<String, Integer> tree = new TwoThreeTreeWithoutBalance<>();
            for (int i = 0; i < n; i++) {
                tree.put(randomString(16), 1);
            }
            StdOut.println(String.format("Size:%d\tInnerPathLength:%d\tAveragePathLength:%f", tree.size(), tree.innerPath(), tree.innerPath() * 1.0 / tree.size()));
            printSeperation();
        }
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

    private void flipColors(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    private boolean isRed(Node n) {
        if (n == null) {
            return false;
        }
        return n.color == RED;
    }

    private Node put(Node x, Key key, Value val, Node parent) {
        if (x == null) {
            //在x处填充新的节点
            //判断x的父节点是2还是3
            boolean isLeftChild = key.compareTo(parent.key) < 0;
            boolean isThreeNode = false;
            if (isLeftChild && isRed(parent.right)) {
                //如果x是parent的左子树并且parent的右子节点是红色
                isThreeNode = true;
            } else if (!isLeftChild && isRed(parent.left)) {
                //如果x是parent的右子树并且parent的左子节点是红色
                isThreeNode = true;
            }
            return new Node(key, val, !isThreeNode, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = put(x.right, key, val, x);
        } else if (cmp < 0) {
            x.left = put(x.left, key, val, x);
        } else {
            x.value = val;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void put(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val, BLACK, 1);
            return;
        }
        root = put(root, key, val, null);
        root.color = BLACK;
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
        if (key == null) {
            return null;
        }
        Node x = get(root, key);
        if (x == null) {
            return null;
        }
        return x.value;
    }

    @Override
    public void delete(Key key) {
        throw new NotImplementedException();
    }

    @Override
    public boolean contains(Key key) {
        return get(root, key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    @Override
    public Key floor(Key key) {
        Node x = root;
        Key floor = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                floor = x.key;
                x = x.right;
            } else {
                return x.key;
            }
        }
        return floor;
    }

    @Override
    public Key ceiling(Key key) {
        Node x = root;
        Key ceil = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                ceil = x.key;
                x = x.right;
            } else if (cmp > 0) {
                x = x.left;
            } else {
                return x.key;
            }
        }
        return ceil;
    }

    @Override
    public int rank(Key key) {
        int size = 0;
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                size += size(x.left);
                size++;
                x = x.right;
            } else {
                size += size(x.left);
                break;
            }
        }
        return size;
    }

    @Override
    public Key select(int n) {
        Node x = root;
        while (x != null) {
            int leftSize = size(x.left);
            if (n > leftSize) {
                n -= leftSize;
                n--;
                x = x.right;
            } else if (n < leftSize) {
                x = x.left;
            } else {
                return x.key;
            }
        }
        return null;
    }

    @Override
    public void deleteMin() {
        throw new NotImplementedException();
    }

    @Override
    public void deleteMax() {
        throw new NotImplementedException();

    }

    @Override
    public int size(Key lo, Key hi) {
        throw new NotImplementedException();
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        throw new NotImplementedException();
    }

    @Override
    public Iterable<Key> keys() {
        throw new NotImplementedException();
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
