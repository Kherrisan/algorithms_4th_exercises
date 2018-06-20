package com.dokyme.alg4.searching.st;

import java.util.Iterator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/1-17:33
 * Description:
 */
public interface SymbolTable<Key extends Comparable<Key>, Value> {

    void put(Key key, Value val);

    Value get(Key key);

    void delete(Key key);

    boolean contains(Key key);

    boolean isEmpty();

    int size();

    Key min();

    Key max();

    /**
     * 小于等于key的最大键
     *
     * @param key
     * @return
     */
    Key floor(Key key);

    /**
     * 大于等于key的最小键
     *
     * @param key
     * @return
     */
    Key ceiling(Key key);

    int rank(Key key);

    /**
     * 返回排名为n的键
     *
     * @param n
     * @return
     */
    Key select(int n);

    void deleteMin();

    void deleteMax();

    int size(Key lo, Key hi);

    Iterable<Key> keys(Key lo, Key hi);

    Iterable<Key> keys();
}
