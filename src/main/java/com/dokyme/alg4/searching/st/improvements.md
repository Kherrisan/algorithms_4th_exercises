# 提高题

## 3.1.21 内存使用

由题意，数组占用的空间比例最小为25%，说明是在满的情况下进行四倍扩容。

N对键值，SequentialSearchST使用链表存储键值，因此占用的空间始终为2N。而BinarySearchST使用数组存放键值，存放N对键值的数组的容量可能并不等于N，根据容量为25%-100%的规定，数组占用的内存平均为2.5N。

## 3.1.22 自组织查找

前移编码。

```java
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
```

## 3.1.23 二分查找的分析

假设数组长度为N，一次二分操作后若没有命中，则目标数组长度缩减为floor(N/2)，而floor(N/2)小于等于N>>1，每次比较需要进行一次按位右移操作。因此比较次数应小于等于二进制位数，当N为2的幂次的时候，最大比较次数正好等于二进制位数。

## 3.1.24 插值法查找

直觉上看，差值法查找比二分查找能够更好的利用有序序列的分布情况。

```java
@Override
    public int rank(Integer k) {
        if (k == null) {
            throw new NoSuchElementException();
        }

        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            if (items[lo].key > k) {
                return lo;
            }
            int pos;
            if (items[hi].key.equals(items[lo].key)) {
                pos = lo;
            } else {
                pos = lo + ((k - items[lo].key) * (hi - lo)) / (items[hi].key - items[lo].key);
            }
            if (pos > hi) {
                pos = hi;
            }
            int cmp = k.compareTo(items[pos].key);
            if (cmp < 0) {
                hi = pos - 1;
            } else if (cmp > 0) {
                lo = pos + 1;
            } else {
                return pos;
            }
        }
        return lo;
    }
```

不知道是这个插值查找的函数有问题还是别的函数有问题，再数组容量大到一定程度的时候总是会出现数组越界的错误。仅看查找的话似乎插值查找更快一些。

## 3.1.25 缓存

在BinarySearchST中定义一个整数来缓存上一次访问的元素的索引，在SequentialSearchST中定义一个Node来缓存上一次访问的节点。

## 3.1.26 基于字典的频率统计

[FrequencyCountDictionary.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/symbolTable/FrequencyCountDictionary.java)

## 3.1.27 小符号表

## 3.1.28 有序的插入

太简单了，略。

## 3.1.29 测试用例

[TestBinarySearch.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/symbolTable/TestBinarySearch.java)

## 3.1.30 验证

写了一个验证函数。

```java
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
```