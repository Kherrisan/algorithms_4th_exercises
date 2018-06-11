package com.dokyme.alg4.searching.binaryst;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/8-18:56
 * Description:
 */
public interface BinaryTree<Key extends Comparable<Key>, Value> {

    Value get(Key key);

    void put(Key key, Value value);

    void delete(Key key);

    boolean isEmpty();

    int size();

    void deleteMin();

    void deleteMax();

    Key min();

    Key max();

    Key floor(Key key);

    Key ceiling(Key key);

    Key select(int n);

    int rank(Key key);

    Iterable<Key> keys();
}
