package com.dokyme.alg4.searching.st;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/1-18:09
 * Description:
 */
public class ArraySymbolTable<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private Key[] keys;

    private Value[] vals;

    private int size;

    public ArraySymbolTable(int capacity) {
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
        Value val = null;
        for (int i = 0; i < size; i++) {
            if (key.compareTo(keys[i]) == 0) {
                val = vals[i];
                //前移编码
                for (int j = i; j > 0; j--) {
                    keys[j] = keys[j - 1];
                    vals[j] = vals[j - 1];
                }
                keys[0] = key;
                vals[0] = val;
            }
        }
        return val;
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
        delete(min());
    }

    @Override
    public void deleteMax() {
        delete(max());
    }

    @Override
    public int size(Key lo, Key hi) {
        return size;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(),max());
    }

    public static void main(String[] args) {

    }
}
