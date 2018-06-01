package com.dokyme.alg4.searching.st;

import java.util.Iterator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/1-18:09
 * Description:
 */
public class ArrayST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

    private Key[] keys;

    private Value[] vals;

    private int size;

    public ArrayST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int newSize) {
        Key[] newKeys = (Key[]) new Comparable[newSize];
        Value[] newVals = (Value[]) new Object[newSize];
        System.arraycopy(keys, 0, newKeys, 0, size);
        System.arraycopy(vals, 0, newVals, 0, size);
        keys = newKeys;
        vals = newVals;
    }

    @Override
    public void put(Key key, Value val) {
        for (int i = 0; i < size; i++) {
            if (keys[i] == null) {
                size++;
                keys[i] = key;
                vals[i] = val;
                return;
            } else if (key.compareTo(keys[i]) == 0) {
                vals[i] = val;
                return;
            }
        }
        keys[size] = key;
        vals[size] = val;
        size++;
        if (size == keys.length) {
            resize(size * 2);
        }
    }

    @Override
    public Value get(Key key) {
        for (int i = 0; i < size; i++) {
            if (key.compareTo(keys[i]) == 0) {
                return vals[i];
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        size--;
        put(key, null);
        for (int i = 0; i < size; i++) {
            if (key.compareTo(keys[i]) == 0) {
                keys[i] = null;
                return;
            }
        }
    }

    @Override
    public boolean contains(Key key) {
        for (int i = 0; i < size; i++) {
            if (key.compareTo(keys[i]) == 0) {
                return true;
            }
        }
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
