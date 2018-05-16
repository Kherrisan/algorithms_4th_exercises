package com.dokyme.alg4.sorting.priorityqueue;

import edu.princeton.cs.algs4.StdOut;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.4.24
 *
 * @author dokym
 * @date 2018/5/12-20:13
 * Description:
 */
public class LinkedMaxHeap<T extends Comparable> implements PQ<T>, IMaxPQ<T> {

    private int n;
    private int depth;
    private Node<T> root;
    private Node<T> last;

    private static class Node<T> {
        private T val;
        private Node<T> upper;
        private Node<T> left;
        private Node<T> right;

        @Override
        public String toString() {
            return "" + val;
        }
    }

    /**
     * 找到左邻居节点的父节点
     *
     * @param node
     * @return
     */
    private Node<T> leftNeighbourUpper(Node<T> node) {
        Node<T> u = node.upper;
        while (u != root && (node != u.right && u.left != null)) {
            u = u.upper;
            node = node.upper;
        }
        if (u.left == null) {
            return u;
        } else {
            u = u.left;
            while (u.right != null) {
                u = u.right;
            }
            return u.upper;
        }
    }

    /**
     * 找到右邻居节点的父节点
     *
     * @param node
     * @return
     */
    private Node<T> rightNeighbourUpper(Node<T> node) {
        Node<T> u = node.upper;
        while (u != root && (node != u.left && u.right != null)) {
            u = u.upper;
            node = node.upper;
        }
        if (u.right == null) {
            return u;
        } else {
            u = u.right;
            while (u.left != null) {
                u = u.left;
            }
            return u;
        }
    }

    @Override
    public T max() {
        return root.val;
    }

    @Override
    public void insert(T t) {
        Node<T> node = new Node<>();
        node.val = t;
        if (root == null) {
            root = last = node;
            depth = 1;
        } else {
            boolean full = new Double(Math.pow(2, depth * 1.0)).intValue() - 1 == n;
            if (full) {
                //如果是满二叉树，则在最底层的最左节点处插入
                Node<T> cur = root;
                while (cur.left != null) {
                    cur = cur.left;
                }
                cur.left = node;
                node.upper = cur;
                depth++;
            } else {
                //如果不是满二叉树，则在倒数第二层的未满最左节点处插入
                //先找到最后一个节点的右邻居节点的父节点
                Node<T> rnu = rightNeighbourUpper(last);
                if (rnu.left != null) {
                    rnu.right = node;
                } else {
                    rnu.left = node;
                }
                node.upper = rnu;
            }
            last = node;
        }
        swim(last);
        n++;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    /**
     * 删除最大节点
     *
     * @return
     */
    @Override
    public T delMax() {
        n--;
        T max = root.val;
        if (n == 0) {
            root = last = null;
            return max;
        }
        exch(root, last);
//        //从符号的层面交换一下root、last
//        Node<T> temp = root;
//        root = last;
//        last = temp;

        //修正heap的last
        if (n == new Double(Math.pow(2, depth - 1)).intValue() - 1) {
            if (last == last.upper.left) {
                last.upper.left = null;
            } else {
                last.upper.right = null;
            }
            //root是本层的第一个（唯一一个）节点
            Node<T> cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            last = cur;
            depth--;
        } else {
            Node<T> leftOfRootUpper = leftNeighbourUpper(last);
            if (last == last.upper.left) {
                last.upper.left = null;
            } else {
                last.upper.right = null;
            }
            if (leftOfRootUpper.right != null) {
                last = leftOfRootUpper.right;
            } else {
                last = leftOfRootUpper.left;
            }
        }

        sink(root);
        if (n == 1) {
            last = root;
        }
        return max;
    }

    /**
     * 节点上浮
     *
     * @param node
     */
    private void swim(Node<T> node) {
        while (node.upper != null && less(node.upper.val, node.val)) {
            exch(node.upper, node);
        }
    }

    /**
     * 节点下沉
     *
     * @param node
     */
    private void sink(Node<T> node) {
        while (node.right != null) {
            Node<T> childMax = node.left;
            if (node.right != null && less(childMax.val, node.right.val)) {
                childMax = node.right;
            }
            if (less(childMax.val, node.val)) {
                break;
            }
            exch(node, childMax);
        }
    }

    /**
     * 交换两个节点，需要区分两个节点相邻或者不相邻
     *
     * @param i:
     * @param j:下方的节点
     */
    private void exch(Node<T> i, Node<T> j) {
        if (i == j) {
            return;
        }
        if (j == last) {
            last = i;
        }
        Node<T> jl = j.left, jr = j.right;
        Node<T> il = i.left, ir = i.right;
        Node<T> ju = j.upper, iu = i.upper;
        if (j.upper == i) {
            ju = j;
            if (il == j) {
                il = i;
            } else {
                ir = i;
            }
        } else {
            if (j == ju.left) {
                ju.left = i;
            } else {
                ju.right = i;
            }
        }
        //交换i、j的子节点
        j.left = il;
        if (il != null) {
            il.upper = j;
        }
        j.right = ir;
        if (ir != null) {
            ir.upper = j;
        }
        i.left = jl;
        if (jl != null) {
            jl.upper = i;
        }
        i.right = jr;
        if (jr != null) {
            jr.upper = i;
        }
        //交换i、j的upper节点
        if (i == root) {
            root = j;
        } else {
            if (i == iu.left) {
                iu.left = j;
            } else {
                iu.right = j;
            }
        }
        i.upper = ju;
        j.upper = iu;
    }

    public static <T extends Comparable> boolean isMaxHeap(Node<T> node) {
        boolean c = true;
        if (node == null) {
            return true;
        }
        if (node.left != null) {
            c = c && !less(node.val, node.left.val);
        }
        if (node.right != null) {
            c = c && !less(node.val, node.right.val);
        }
        return c && isMaxHeap(node.left) && isMaxHeap(node.right);
    }

    public static void main(String[] args) {
        LinkedMaxHeap<Integer> imh = new LinkedMaxHeap<>();
        Comparable[] arrays = generateTestData(1, 100);
        for (Comparable i : arrays) {
            imh.insert((Integer) i);
        }
        StdOut.println("============================");
        for (int i = 0; i < 100; i++) {
            Integer j = imh.delMax();
            StdOut.print(j + "\t");
            assert isMaxHeap(imh.root);
        }
        assert isMaxHeap(imh.root);
    }
}
