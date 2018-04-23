# 提高题

## 2.2.10 快速归并

```java_holder_method_tree
    private static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        int i = lo, j = hi;
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }
        for (int k = mid + 1; k <= hi; k++) {
            aux[k] = a[hi - (k - mid - 1)];
        }
        //如果前半部分耗尽，那么i会移动到后半部分去，i和j都在后半部分移动直到相遇。
        for (int k = lo; k <= hi; k++) {
            if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j--];
            }
        }
    }
```

## 2.2.11 改进

```java_holder_method_tree
public static void sort(Comparable[] a, int lo, int hi, Comparable[] aux, boolean inverse) {
        if (lo >= hi) {
            return;
        }
        if (hi - lo <= INSERT_THRESHOLD) {
            if (inverse) {
                insertSort(aux, lo, hi);
            } else {
                insertSort(a, lo, hi);
            }
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux, !inverse);
        sort(a, mid + 1, hi, aux, !inverse);
        if (inverse) {
            mergeTo(a, aux, lo, mid, hi);
        } else {
            mergeTo(aux, a, lo, mid, hi);
        }
    }
```

使用一个boolean变量来记录是从a数组到aux数组，还是从aux数组到a数组。其实数组的复制仅仅只是存在于merge函数中，sort函数只负责控制排序的边界和中点的位置。
此外merge函数中判断a[mid]和a[mid+1]的关系，如果是小于，就直接复制过去。其实这里是可以考虑不复制的。
实际上只要交换参数顺序就可以做到。

然而，并没有快很多。

## 2.2.12 次线性的额外空间

**暂留**

## 2.2.13 平均情况的下界

**暂留**

## 2.2.14 归并有序队列

这个不难写。

```java_holder_method_tree
    public static <T extends Comparable> Queue<T> merge(Queue<T> a, Queue<T> b) {
        Queue<T> r = new LinkedList<>();
        T ai = a.poll();
        T bi = b.poll();
        while (ai != null && bi != null) {
            if (Example.less(ai, bi)) {
                r.add(ai);
                ai = a.poll();
            } else {
                r.add(bi);
                bi = b.poll();
            }
        }
        if (ai == null) {
            r.add(bi);
            r.addAll(b);
        } else if (bi == null) {
            r.add(ai);
            r.addAll(a);
        }
        return r;
    }

## 2.2.15 自底向上的有序队列归并排序

```java_holder_method_tree
    @Override
    public void sort(Comparable[] comparables) {
        Queue<Queue<Comparable>> qq = new LinkedList<>();
        for (int i = 0; i < comparables.length; i++) {
            Queue<Comparable> q = new LinkedList<>();
            q.add(comparables[i]);
            qq.add(q);
        }
        while (qq.size() > 1) {
            qq.add(Merge.merge(qq.poll(), qq.poll()));
        }
        Queue<Comparable> r = qq.poll();
        for (int i = 0; i < comparables.length; i++) {
            comparables[i] = r.poll();
        }
    }
```

## 2.2.16 自然的归并排序

这是一种自底向上的归并排序算法，与传统的自底向上归并所不同的是，每次归并的子数组长度并不仅限于2的幂，而是数组中某个区域内最大有序子数组的长度。

要付出的代价是，每次归并后要重新扫描一遍数组来确定有哪些段是有序的。即每次归并后都需要花费线性的时间。

时间复杂度也应该是O(nlogn)，但是在处理有较长有序数组的输入用例时应该会较快。

```java_holder_method_tree
public class NaturalMerge implements Sorting {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        int[] p = new int[N];
        int psize = pass(a, p);
        while (psize != 2) {
            for (int i = 0; i < psize; i += 2) {
                Merge.merge(a, p[i], p[i + 1] - 1, p[i + 2] - 1, aux);
            }
            psize = pass(a, p);
        }
    }

    private int pass(Comparable[] a, int[] r) {
        int ri = 0;
        r[ri++] = 0;
        for (int i = 1; i < a.length; i++) {
            if (!Example.less(a[i - 1], a[i])) {
                r[ri++] = i;
            }
        }
        r[ri++] = a.length;
        return ri;
    }

    public static void main(String[] args) {
        Example.testSorting(new NaturalMerge());
    }
}
```

## 2.2.17 链表排序

要对链表做归并排序，我觉得有一些问题需要考虑：

1. 如何高效访问链表节点：肯定是不能像数组一下用下标取访问，可能需要使用Node对象或迭代器。
2. 如果界定子序列的边界：也不能用下标访问。
2. 如何归并两个子序列。

但是考虑到LinkedListNode是private，我访问不到。emmmmmm。只能字节写一个简单的LinkedList了。

**暂留**

## 2.2.18 打乱链表

我不知题目所说的打乱要打乱到何种程度。

链表和数组的区别在于，数组可以通过随机取索引来做shuffle，但是链表不能够通过索引访问（效率太低）。

题目要求对数级别的额外空间，那么可以理解成一个树状的结构，每层分配一个空间。

在Stack Overflow上找到了这道题的答案，logn空间复杂度的意思就是这是要递归的所以你栈至少要有logn吧。。。。。。

**暂留**

## 2.2.19 倒置

可以调整归并排序的算法，应该统计倒置本身就是一个规模逐步扩大的问题。一边统计一边修正逆序对，局部的修正可以保证每次只修正一个逆序对，不会影响局部内外元素的逆序关系。

```java_holder_method_tree

    private int merge(Comparable[] a, int lo, int mid, int hi, Comparable[] copy) {
        int count = 0;
        int i = lo, j = mid + 1;
        System.arraycopy(a, lo, copy, lo, hi - lo + 1);
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) {
                a[k] = copy[j++];
            } else if (j == hi + 1) {
                a[k] = copy[i++];
            } else if (Example.less(copy[j], copy[i])) {
                count += (mid - i + 1);
                a[k] = copy[j++];
            } else {
                a[k] = copy[i++];
            }
        }
        return count;

```

**倒置的数量=左半段倒置的数量+右半段倒置数量+跨中点一左一右倒置数量**

## 2.2.20 间接排序

相当于对数组的元素值排序，但只记录索引的变化，不改变元素的位置。

## 2.2.21 一式三份

第一反应是把三个列表排序，然后逐个扫描就可以定位到同时出现在三个名单里的名字。先找到两张表里同时出现的名字，再在第三张表里二分查找他。最坏情况是，两张列表中的名字完全相同，但是没有任何一个名字出现在第三张列表里，那么所需要的时间为NlogN。

## 2.2.22 三向归并排序

假设分成三段，分别排序，然后归并。三段各有一个指针，每次比较三个指针指向的元素大小，让较小的元素胜出，并向后移动一位较小元素所在段的指针。每一层归并所需要的时间仍然是线性的。

T(n)=3T(1/3*n)+O(n)