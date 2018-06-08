package com.dokyme.alg4.searching.binaryst;

import java.util.NoSuchElementException;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 3.2.13
 * 3.2.14
 *
 * @author dokym
 * @date 2018/6/8-19:29
 * Description:
 */
public class BinarySearchTreeNonRecursive<Key extends Comparable<Key>, Value> implements BinaryTree<Key, Value> {

    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count;

        public Node(Key key, Value val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    private Node root;

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
                return x.val;
            }
        }
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        //第一趟寻找插入的位置，第二趟修正沿线所有的size
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                x.val = value;
                return;
            }
        }

        if (root == null) {
            root = new Node(key, value, 1);
            return;
        }
        x = root;
        while (x != null) {
            x.count++;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                if (x.left != null) {
                    x = x.left;
                } else {
                    x.left = new Node(key, value, 1);
                    return;
                }
            } else if (cmp > 0) {
                if (x.right != null) {
                    x = x.right;
                } else {
                    x.right = new Node(key, value, 1);
                    return;
                }
            }
        }
    }

    @Override
    public void delete(Key key) {
        boolean keyExists = false;
        Node x = root;
        if (x == null) {
            return;
        }
        Node up;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            up = x;
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                keyExists = true;
                if (x.left == null) {
                    if (x.key.compareTo(up.key) < 0) {
                        up.left = x.right;
                    } else {
                        up.right = x.right;
                    }
                    break;
                }
                if (x.right == null) {
                    if (x.key.compareTo(up.key) > 0) {
                        up.left = x.left;
                    } else {
                        up.right = x.left;
                    }
                    break;
                }
                //如果待删除的节点有2个子节点
                Node t = min(x.right);
                deleteMin(x.right);
            }
        }
        if (!keyExists) {
            return;
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.count;
    }

    @Override
    public void deleteMin() {
        deleteMin(root);
    }

    private void deleteMin(Node x) {
        Node t = x;
        if (x == null) {
            return;
        }
        Node up = null;
        while (x.left != null) {
            up = x;
            x = x.left;
        }
        if (up == null) {
            root = null;
            return;
        }
        up.left = x.right;
        up.count--;
        x=t;
        while (){

        }
    }

    @Override
    public void deleteMax() {
        deleteMax(root);
    }

    private void deleteMax(Node x) {
        if (x == null) {
            return;
        }
        Node up = null;
        while (x.right != null) {
            up = x;
            x = x.right;
        }
        if (up == null) {
            root = null;
            return;
        }
        up.right = x.left;
        up.count--;
    }

    public Node min(Node x) {
        if (x == null) {
            return null;
        }
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    @Override
    public Key min() {
        Node x = root;
        if (x == null) {
            return null;
        }
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    @Override
    public Key max() {
        Node x = root;
        if (x == null) {
            return null;
        }
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    public static void main(String[] args) {

    }
}
