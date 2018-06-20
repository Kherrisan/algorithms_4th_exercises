package com.dokyme.alg4.searching.st;

import com.dokyme.alg4.sorting.merge.Merge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/1-17:51
 * Description:
 */
public class BinarySearchSymbolTable<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    protected Item<Key, Value>[] items;

    private int cached;

    protected class Item<K extends Comparable<K>, V> implements Comparable<Item<K, V>> {
        K key;
        V val;

        public Item(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public int compareTo(Item<K, V> o) {
            return key.compareTo(o.key);
        }
    }

    protected int size;

    public BinarySearchSymbolTable(int capacity) {
        items = new Item[capacity];
    }

    public BinarySearchSymbolTable(Item<Key, Value>[] items) {
        this.items = items;
        size = items.length;
        new Merge().sort(items);
    }

    private void resize(int newSize) {
        Item<Key, Value>[] nItem = new Item[newSize];
        System.arraycopy(items, 0, nItem, 0, size);
        items = nItem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        if (items[cached] != null && items[cached].key.compareTo(key) == 0) {
            return items[cached].val;
        }
        int i = rank(key);
        if (i < size && items[i].key.compareTo(key) == 0) {
            return items[i].val;
        } else {
            return null;
        }
    }

    /**
     * 二分查找
     * 若存在该键，则返回键的位置，若不存在，则返回小于改建的数量（即应当插入的位置）
     *
     * @param key
     * @return
     */
    @Override
    public int rank(Key key) {
        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(items[mid].key);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                cached = mid;
                return mid;
            }
        }
        return lo;
    }

    @Override
    public Key min() {
        if (size == 0) {
            return null;
        }
        return items[0].key;
    }

    @Override
    public Key max() {
        if (size == 0) {
            return null;
        }
        return items[size - 1].key;
    }

    @Override
    public Key select(int n) {
        return items[n].key;
    }

    @Override
    public Key ceiling(Key key) {
        if (contains(key)) {
            return key;
        }
        int i = rank(key);
        if (i == size) {
            return null;
        }
        return items[i].key;
    }

    @Override
    public Key floor(Key key) {
        if (contains(key)) {
            return key;
        }
        int i = rank(key);
        if (i == 0) {
            return null;
        }
        return items[i - 1].key;
    }

    @Override
    public void delete(Key key) {
        int i = rank(key);
        if (i < size && key.compareTo(items[i].key) == 0) {
            for (int j = i; j < size; j++) {
                items[j] = items[j + 1];
            }
            items[size--] = null;
        }
        assert isValid();
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new LinkedList<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            ((LinkedList<Key>) q).push(items[i].key);
        }
        if (contains(hi)) {
            ((LinkedList<Key>) q).push(items[rank(hi)].key);
        }
        return q;
    }

    public int rank(Key key, int lo, int hi) {
        if (hi < lo) {
            return lo;
        }
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(items[mid].key);
        if (cmp < 0) {
            return rank(key, lo, mid - 1);
        } else if (cmp > 0) {
            return rank(key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    @Override
    public void put(Key key, Value val) {
        if (size == items.length - 1) {
            resize(size * 2);
        }
        if (items[cached] != null && items[cached].key.compareTo(key) == 0) {
            items[cached].val = val;
            return;
        }
        if (size != 0 && key.compareTo(max()) > 0) {
            items[size++] = new Item<>(key, val);
            return;
        }
        int i = rank(key);
        if (i < size && items[i].key.compareTo(key) == 0) {
            items[i].val = val;
            return;
        }
        for (int j = size; j > i; j--) {
            items[j] = items[j - 1];
        }
        items[i] = new Item<>(key, val);
        size++;
        assert isValid();
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void deleteMin() {
        delete(items[0].key);
    }

    @Override
    public void deleteMax() {
        delete(items[size - 1].key);
    }

    @Override
    public int size(Key lo, Key hi) {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private boolean isValid() {
        for (int i = 0; i < size; i++) {
            if (!select(rank(items[i].key)).equals(items[i].key)) {
                return false;
            }
            if (i > 0 && items[i].key.compareTo(items[i - 1].key) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
