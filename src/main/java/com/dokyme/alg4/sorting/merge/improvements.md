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

