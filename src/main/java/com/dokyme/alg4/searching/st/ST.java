package com.dokyme.alg4.searching.st;

import java.util.Iterator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/1-17:33
 * Description:
 */
public abstract class ST<Key extends Comparable<Key>, Value> {

    public abstract void put(Key key, Value val);

    public abstract Value get(Key key);

    public abstract void delete(Key key);

    public abstract boolean contains(Key key);

    public abstract boolean isEmpty();

    public abstract int size();

    public abstract Key min();

    public abstract Key max();

    /**
     * 小于等于key的最大键
     *
     * @param key
     * @return
     */
    public abstract Key floor(Key key);

    /**
     * 大于等于key的最小键
     *
     * @param key
     * @return
     */
    public abstract Key ceiling(Key key);

    public abstract int rank(Key key);

    /**
     * 返回排名为n的键
     *
     * @param n
     * @return
     */
    public abstract Key select(int n);

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    public abstract Iterable<Key> keys(Key lo, Key hi);

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {

    }
}
