package com.dokyme.alg4.searching.st;

import java.util.LinkedList;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/1-17:44
 * Description:
 */
public class SequentialSearchST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

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

    private Node first;

    private int size;

    @Override
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    @Override
    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        size++;
    }

    @Override
    public void delete(Key key) {
        Node last = null;
        for (Node x = first; x != null; x = x.next) {
            if (key.compareTo(x.key) == 0) {
                if (last == null) {
                    first = first.next;
                } else {
                    last.next = x.next;
                }
                size--;
                return;
            }
            last = x;
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
        LinkedList<Key> q = new LinkedList<>();
        for (Node x = first; x != null; x = x.next) {
            if (lo.compareTo(x.key) == 0) {
                for (; x != null && hi.compareTo(x.key) != 0; x = x.next) {
                    q.push(x.key);
                }
                return q;
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
