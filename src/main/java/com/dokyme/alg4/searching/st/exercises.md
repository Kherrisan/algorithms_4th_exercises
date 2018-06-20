# 练习

## 3.1.1 

[GPA.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/symbolTable/GPA.java)

## 3.1.2

[ArrayST.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/symbolTable/ArrayST.java)

## 3.1.3

[OrderedSequentialSearchST.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/symbolTable/OrderedSequentialSearchST.java)

## 3.1.4

[Event.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/symbolTable/Event.java)

## 3.1.5

[SequentialSearchST.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/symbolTable/SequentialSearchST.java)

## 3.1.6

输入单词总数是W，不同单词总数为D，那么调用put方法的次数为W，调用get方法的次数为W-D+2D+1=W+D+1。

## 3.1.7

N=10,100,1000,平均有N个不同的键，N>1000时，还是只有N个不同的键。

## 3.1.8

[FrequencyCounter.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/symbolTable/FrequencyCounter.java)

monseigneur 101

## 3.1.9

Last word:known, 135643 words so far.
Last word:faltering, 14346 words so far.
Last word:disfigurement, 4579 words so far.

## 3.1.10

```
E
A   E
S   A   E
Y   S   A   E
Q   Y   S   A   E
U   Q   Y   S   A   E
U   Q   Y   S   A  +E
U   Q   Y  +S   A   E  
T   U   Q   Y   S   A   E   
I   T   U   Q   Y   S   A   E
O   I   T   U   Q   Y   S   A   E
N   O   I   T   U   Q   Y   S   A   E
```

进行了1+2+3+4+5+5+4+6+7+8+9=54次比较。

## 3.1.11

```
0   E
1   A   E
3   A   E   S
6   A   E   S   Y
9   A   E   Q   S   Y
......
```

轨迹就不画全了，总共进行了29次比较。

## 3.1.12

[BinarySearchST.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/symbolTable/BinarySearchST.java)

## 3.1.13

由题意，put操作远少于get操作，我认为应该选择一种get操作尽可能快的符号表的实现，即使用二分查找的有序数组的符号表实现。

## 3.1.14

和上题恰恰相反的情况下，我选择使用链表实现的符号表，应为put操作更快。至于链表是否需要有序不会对运行时间产生太大影响。

## 3.1.15

get操作次数是put操作次数的1000倍，当

进行1000次查找时，平均进行了1次put，1000次get所耗时间为log_2^1000约等于10，1次put平均需要耗费时间为500。

进行1000000次查找时，平均进行了1000次put，1000000次get所耗时间为log_2^1000000约等于20，1000次put平均需要耗费时间为500000。

进行1000000000次查找时，平均进行了1000000次put，1000000000次get所耗时间为log_2^1000000000约等于30，一次put平均需要耗费时间为500000000。

比例就不算了，总之当get和put操作越多，get操作进行二分查找所带来的优势越明显。换句话说，顺序数组的符号表也难以应付大量的put操作。

## 3.1.16

```java
    @Override
    public void delete(Key key) {
        int i = rank(key);
        if (i < size && key.compareTo(items[i].key) == 0) {
            for (int j = i; j < size; j++) {
                items[j] = items[j + 1];
            }
            items[size--] = null;
        }
    }
```

## 3.1.17

```java
    @Override
    public Key floor(Key key) {
        int i = rank(key);
        if (key.compareTo(items[i].key) == 0) {
            return key;
        } else {
            return items[i - 1].key;
        }
    }
```

## 3.1.18

rank方法，即二分查找，递归的正确性是显而易见的，重点在于出口情况的正确性，根据经验发现，当二分查找循环结束时，lo和hi的取值为i+1和i，即上一次循环中lo,mid,hi的取值为i,i,i或者i,i,i+1。

1. lo=mid=hi=i，在这种情况下，作为最终值的lo可能会出现两种走向。不论何种走向，lo都能够反映比v小的元素个数。
1.1 a[lo-1]<v<a[mid=lo]->lo不变（这种情况是否有可能出现a[lo-1]>v的情况？有可能，比如a[lo-1]=a[lo]）
1.2 v>a[mid=lo]->lo++
2. lo=mid=i=hi-1，在这种情况下，作为最终值的lo只会有一种走向，即lo++，出现这种走向的条件为a[mid]<v，此时lo=mid+1，即a[lo-1]<v<a[hi]，此时比v小的元素个数还是lo。

如果目标元素出现在数组中，那么命中时mid即为目标元素在数组中的位置，这个是显然的。

如果目标元素不在数组中，那么搜索范围会逐步缩小，最终回归到上面所说的情况。

## 3.1.19

