# 提高题

## 2.1.13 纸牌排序

把一副牌按照黑桃、红桃、梅花、方块的顺序排序。

0位置开始摆放黑桃，13开始拜访红桃，26开始摆放梅花，39开始拜访方块。使用四个指针记录四种花色的下一张牌应该存放的位置，从第一张牌开始翻，然后将其和应该存放的位置的牌进行交换，并移动对应花色指针，换回来的牌变成新的第一张，如此循环，直到四个指针都到位，说明所有的牌都已经按照花色排序到位。

但是我看题目的意思，好像不允许使用额外的指针记录位置。

于是还是借鉴插入排序的思路，使用一个额外的指针（这个应该是不可避免的了）记录已经排好序的序列。从0和1开始，如果后一张牌的花色大于前一张牌，就交换两张牌，否则继续取指针右侧的第一个元素和指针指向的元素进行比较。

## 2.1.14 出列排序

查看牌堆顶部的两张牌，将较小的牌换到牌堆顶部（也可能不需要交换），并移动牌堆顶部的牌到底部。一直这样重复，知道连续52次都没有发生交换，说明已经有序了，只是可能牌堆是循环有序的，需要手动换牌到最小的牌出现到顶部，此时整个排队从上到下才是递增的。

## 2.1.15 昂贵的交换

只有一个空位，需要尽量减少元素移动。基于这两个条件，应该选择选择排序。因为选择排序最坏的情况下也只需要交换N次元素。

## 2.1.16 验证

写倒是挺好写的。

```java_holder_method_tree
    public static boolean check(Comparable[] a) {
        Comparable[] backup = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            backup[i] = a[i];
        }
        sort(a);
        if (!compare(a, backup)) {
            return false;
        }
        Arrays.sort(backup);
        if (!compare(a, backup)) {
            return false;
        }
        return true;
    }
```

### 2.1.17 动画

![](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/resources/2-1-17-1.jpg?raw=true)

[自己写的排序可视化类](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/sorting/basic/SortingDrawer.java)

SortingDrawer类，是一个致力于可复用、自适应、体验良好的专用于排序算法可视化的类。使用方法如下：

```java_holder_method_tree
public static void sort(Comparable[] a) {
        SortingDrawer drawer = new SortingDrawer(a);
        int N = a.length;
        int h = hArray[a.length];
        while (h >= 1) {
            for (int i = 0; i < N; i++) {
                for (int j = i; j >= h && Example.less(a[j], a[j - h]); j -= h) {
                    Example.exch(a, j, j - h);
                    drawer.focus(j).focus(j - h).update(a);
                }
            }
            h = h / 3;
        }
    }
```

参考了CopyOnWrite的思想，在内部维持上一次绘制后的数组，如果新数组的元素和原来数组中对应位置的元素不对应，就重绘那一个bin。
focus用于使用特殊颜色突出强调某个bin，update用于重绘画面。

### 2.1.18 可视轨迹

直接在2.1.17上做了一些修改，使移动的bin的颜色较为突出。

### 2.1.19 希尔排序的最好情况

最好情况应该是顺序排列吧。

### 2.1.20 可比较的交易

```java_holder_method_tree
public class Transaction implements Comparable<Transaction> {

    private final double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(Transaction o) {
        if (this.amount > o.amount) {
            return 1;
        } else if (this.amount < o.amount) {
            return -1;
        } else {
            return 0;
        }
    }
}

```

### 2.1.21 交易排序测试用例

```java_holder_method_tree
public class SortTransaction {

    public static Transaction[] readTransactions() {
        StdOut.print("The number of transactions:");
        int number = StdIn.readInt();
        Transaction[] result = new Transaction[number];
        while (number-- > 0) {
            StdOut.print("Input next transaction amount:");
            result[number] = new Transaction(StdIn.readDouble());
        }
        StdOut.print("Input finished.");
        return result;
    }

    public static void main(String[] args) {
        Transaction[] transactions = readTransactions();
        Shell.sort(transactions);
        for (Transaction t : transactions) {
            StdOut.println(t);
        }
    }
}
```

