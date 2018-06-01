package com.dokyme.alg4.searching.st;

import java.util.Iterator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/1-18:20
 * Description:
 */
public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private int size;

    private Node first;

    @Override
    public void put(Key key, Value val) {
        Node node = new Node(key, val, first);
        Node last = null;
        for (Node i = first; i != null; i = i.next) {
            if (key.compareTo(i.key) > 0) {
                if (last == null) {
                    first = node;
                } else {
                    last.next = node;
                    node.next = i;
                }
                size++;
                return;
            } else if (key.compareTo(i.key) == 0) {
                i.val = val;
                return;
            }
            last = i;
        }
        if (last == null) {
            first = node;
        } else {
            last.next = node;
        }
        node.next = null;
        size++;
    }

    @Override
    public Value get(Key key) {
        for (Node i = first; i != null; i = i.next) {
            if (key.compareTo(i.key) == 0) {
                return i.val;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        Node last = null;
        for (Node i = first; i != null; i = i.next) {
            if (key.compareTo(i.key) == 0) {
                if (last == null) {
                    first = i.next;
                } else {
                    last.next = i.next;
                }
                size--;
                return;
            }
            last = i;
        }
    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
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
        super.deleteMin();
    }

    @Override
    public void deleteMax() {
        super.deleteMax();
    }

    @Override
    public int size(Key lo, Key hi) {
        return super.size(lo, hi);
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public Iterable<Key> keys() {
        return super.keys();
    }

    public static void main(String[] args) {

    }
}
