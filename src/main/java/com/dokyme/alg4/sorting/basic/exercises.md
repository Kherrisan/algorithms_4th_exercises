# 练习

## 2.1.1

```
i     0   1   2   3   4   5   6   7   8   9   10  11  min
      E   A   S   Y   Q   U   E   S   T   I   O   N
0     E   A   S   Y   Q   U   E   S   T   I   O   N   1
1     A   E   S   Y   Q   U   E   S   T   I   O   N   1
2     A   E   S   Y   Q   U   E   S   T   I   O   N   6
3     A   E   E   Y   Q   U   S   S   T   I   O   N   9
4     A   E   E   I   Q   U   S   S   T   Y   O   N   11  
5     A   E   E   I   N   U   S   S   T   Y   O   Q   10
6     A   E   E   I   N   O   S   S   T   Y   U   Q   11    
7     A   E   E   I   N   O   Q   S   T   Y   U   S   7
8     A   E   E   I   N   O   Q   S   T   Y   U   S   11
9     A   E   E   I   N   O   Q   S   S   Y   U   T   11
10    A   E   E   I   N   O   Q   S   S   T   U   Y   10
11    A   E   E   I   N   O   Q   S   S   T   U   Y   11
```

## 2.1.2

选择排序中，元素交换发生在当一个元素为i右侧部分的最小元素的时候，一个元素最多可能会被交换N-1次，即每次都交换次元素。
例如：10,0,1,2,3,4,5,6,7,8,9，在这样的情况下，10会被交换9次。

每个元素平均被交换1次。

## 2.1.3

要使得选择排序的过程中a[j]<a[min]的次数最大，需要使得每次扫描选取最小值的时候，i右侧部分都是递减序列。但这样是不可能的，因为如果i右侧是递减序列，那么最小的那个元素所在位置在下一次扫描时必然就是一个较大的值（因为交换过了）。因此一个递减的序列，满足a[i]<a[min]的次数应该是N^2/4（对于序列长度为奇数或偶数不作进一步区分）。
例如：10,9,8,7,6,5,4,3,2,1，在这样的情况下，i=0时为9次，i=1时为7次，i=2时为5次，i=3时是3次，i=4时是1次。

## 2.1.4

```
i   0   1   2   3   4   5   6   7   8   9   10  11  j
    E   A   S   Y   Q   U   E   S   T   I   O   N      
1   E   A   S   Y   Q   U   E   S   T   I   O   N   0
2   A   E   S   Y   Q   U   E   S   T   I   O   N   2
3   A   E   S   Y   Q   U   E   S   T   I   O   N   3
4   A   E   S   Y   Q   U   E   S   T   I   O   N   2
5   A   E   Q   S   Y   U   E   S   T   I   O   N   4
6   A   E   Q   S   U   Y   E   S   T   I   O   N   2
7   A   E   E   Q   S   U   Y   S   T   I   O   N   5
8   A   E   E   Q   S   S   U   Y   T   I   O   N   6
9   A   E   E   Q   S   S   T   U   Y   I   O   N   3
10  A   E   E   I   Q   S   S   T   U   Y   O   N   4
11  A   E   E   I   O   Q   S   S   T   U   Y   N   4
12  A   E   E   I   N   O   Q   S   S   T   U   Y   
```

## 2.1.5

要使得插入排序的内循环for的两个条件均为假(j>0和less(a[j],a[j-1]))，首先j>0这个条件不会总是假，除非每次都在0位置处插入新元素，其次要使得less(a[j],a[j-1])总是假，只需要给定是数组为递增数组即可。这样未排序部分的第一个元素肯定比排序部分的第一个元素大。

## 2.1.6

在所有主键相同时，选择排序还是需要每次寻找未排序部分的最小元素，需要平方级别时间。而插入排序则只需要线性级别时间。

## 2.1.7

对于逆序数组，选择排序需要平方级别时间，插入排序也需要平方级别的时间。从访问数组元素的角度衡量，二者时间相同，从交换元素的角度考虑，选择排序更快一些。

## 2.1.8

假设一个数组中只有3种值，每个值出现的概率为1/3。
当数组为顺序数组时，需要线性时间。
当数组为逆序数组（前1/3为最大值，中1/3为中间值，后1/3为最小值）时，所需时间可以计算为1/3*N+1/3*N*1/3*N+1/3*N*2/3*N。
可以这么考虑：数组中有1/3*N个最小值，这些值每个值最多需要移动2/3*N（当遇到最大值和中间值时需要移动），有1/3*N的中间值，这些值每个值最多需要移动1/3*N（遇到最大值时需要移动），剩下的最大值不需要移动。因此至多需要平方级别是时间，至少需要线性级别的时间，平均需要的时间复杂度应该在二者之间。

## 2.1.9

```
input       E   A   S   Y   S   H   E   L   L   S   O   R   T   Q   U   E   S   T   I   O   N
13-SORT     -E  A   S   Y   S   H   E   L   L   S   O   R   T  -Q   U   E   S   T   I   O   N
            E  -A   S   Y   S   H   E   L   L   S   O   R   T   Q  -U   E   S   T   I   O   N
            E   A  -E   Y   S   H   E   L   L   S   O   R   T   Q   U  -S   S   T   I   O   N
            E   A   E  -S   S   H   E   L   L   S   O   R   T   Q   U   S  -Y   T   I   O   N
            E   A   E   S  -S   H   E   L   L   S   O   R   T   Q   U   S   Y  -T   I   O   N
            E   A   E   S   S  -H   E   L   L   S   O   R   T   Q   U   S   Y   T  -I   O   N
            E   A   E   S   S   H  -E   L   L   S   O   R   T   Q   U   S   Y   T   I  -O   N
            E   A   E   S   S   H   E  -L   L   S   O   R   T   Q   U   S   Y   T   I   O  -N
4-SORT     -E   A   E   S  -S   H   E   L   L   S   O   R   T   Q   U   S   Y   T   I   O   N
            E  -A   E   S   S  -H   E   L   L   S   O   R   T   Q   U   S   Y   T   I   O   N
            E   A  -E   S   S   H  -E   L   L   S   O   R   T   Q   U   S   Y   T   I   O   N
            E   A   E  -L   S   H   E  -S   L   S   O   R   T   Q   U   S   Y   T   I   O   N
           -E   A   E   L  -L   H   E   S  -S   S   O   R   T   Q   U   S   Y   T   I   O   N
            E  -A   E   L   L  -H   E   S   S  -S   O   R   T   Q   U   S   Y   T   I   O   N
            E   A  -E   L   L   H  -E   S   S   S  -O   R   T   Q   U   S   Y   T   I   O   N
            E   A   E  -L   L   H   E  -S   S   S   O  -R   T   Q   U   S   Y   T   I   O   N
           -E   A   E   L  -L   H   E   S  -S   S   O   R  -T   Q   U   S   Y   T   I   O   N
            E  -A   E   L   L  -H   E   S   S  -Q   O   R   T  -S   U   S   Y   T   I   O   N
            E   A  -E   L   L   H  -E   S   S   Q  -O   R   T   S  -U   S   Y   T   I   O   N
            E   A   E  -L   L   H   E  -R   S   Q   O  -S   T   S   U  -S   Y   T   I   O   N
           -E   A   E   L  -L   H   E   R  -S   Q   O   S  -T   S   U   S  -Y   T   I   O   N
            E  -A   E   L   L  -H   E   R   S  -Q   O   S   T  -S   U   S   Y  -T   I   O   N
            E   A  -E   L   L   H  -E   R   S   Q  -I   S   T   S  -O   S   Y   T  -U   O   N
            E   A   E  -L   L   H   E  -R   S   Q   O  -S   T   S   I  -S   Y   T   U  -O   N
           -E   A   E   L  -L   H   E   S  -N   Q   O   R  -S   S   I   O  -T   T   U   S  -Y
1-SORT     -A  -E   E   L   L   H   E   S   S   Q   O   R   T   S   I   O   N   T   U   S   Y
           -A  -E  -E   L   L   H   E   S   S   Q   O   R   T   S   I   O   N   T   U   S   Y
           -A  -E  -E  -L   L   H   E   S   S   Q   O   R   T   S   I   O   N   T   U   S   Y
           -A  -E  -E  -L  -L   H   E   S   S   Q   O   R   T   S   I   O   N   T   U   S   Y
           -A  -E  -E  -H  -L  -L   E   S   S   Q   O   R   T   S   I   O   N   T   U   S   Y
           -A  -E  -E  -E  -H  -L  -L   S   S   Q   O   R   T   S   I   O   N   T   U   S   Y
           -A  -E  -E  -E  -H  -L  -L  -S   S   Q   O   R   T   S   I   O   N   T   U   S   Y
           -A  -E  -E  -E  -H  -L  -L  -S  -S   Q   O   R   T   S   I   O   N   T   U   S   Y
           -A  -E  -E  -E  -H  -L  -L  -Q  -S  -S   O   R   T   S   I   O   N   T   U   S   Y
           -A  -E  -E  -E  -H  -L  -L  -O  -Q  -S  -S   R   T   S   I   O   N   T   U   S   Y
           -A  -E  -E  -E  -H  -L  -L  -O  -Q  -R  -S  -S   T   S   I   O   N   T   U   S   Y
           -A  -E  -E  -E  -H  -L  -L  -O  -Q  -R  -S  -S  -T   S   I   O   N   T   U   S   Y
           -A  -E  -E  -E  -H  -L  -L  -O  -Q  -R  -S  -S  -S  -T   I   O   N   T   U   S   Y
           -A  -E  -E  -E  -H  -I  -L  -L  -O  -Q  -R  -S  -S  -S  -T   O   N   T   U   S   Y
           -A  -E  -E  -E  -H  -I  -L  -L  -O  -O  -Q  -R  -S  -S  -S  -T   N   T   U   S   Y
           -A  -E  -E  -E  -H  -I  -L  -L  -N  -O  -O  -Q  -R  -S  -S  -S  -T   T   U   S   Y
           -A  -E  -E  -E  -H  -I  -L  -L  -N  -O  -O  -Q  -R  -S  -S  -S  -T  -T   U   S   Y
           -A  -E  -E  -E  -H  -I  -L  -L  -N  -O  -O  -Q  -R  -S  -S  -S  -T  -T  -U   S   Y
           -A  -E  -E  -E  -H  -I  -L  -L  -N  -O  -O  -Q  -R  -S  -S  -S  -S  -T  -T  -U   Y
           -A  -E  -E  -E  -H  -I  -L  -L  -N  -O  -O  -Q  -R  -S  -S  -S  -S  -T  -T  -U  -Y
```
            
## 2.1.10

因为在对一个子序列实现h有序时，这个子序列往往是已经局部有序的了。如在进行4-sort的过程中，会先对a[0],a[4]进行局部排序，然后会对a[0],a[4],a[8]进行局部排序，这是0和4位置的元素已经是有序的了。为了充分利用这种局部有序的性质，减少比较的次数，选择插入排序而不是选择排序。

## 2.1.11

```java_holder_method_tree
    public static int[] hArray;

    static {
        int i = 1, h = 1;
        hArray = new int[30000];
        while (i < hArray.length) {
            while (i < (h + 1) * 3 && i < hArray.length) {
                hArray[i] = h;
                i++;
            }
            h = 3 * h + 1;
        }
        System.out.println(Arrays.toString(hArray));
    }
```

## 2.1.12

```java_holder_method_tree
    public static void sort(Comparable[] a, boolean x) {
        int N = a.length;
        int h = hArray[a.length];
        Map<Integer, Double> comparsionFactor = new LinkedHashMap();
        while (h >= 1) {
            int time = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i; j >= h; j -= h) {
                    time++;
                    if (Example.less(a[j], a[j - h])) {
                        Example.exch(a, j, j - h);
                    } else {
                        break;
                    }
                }
            }
            comparsionFactor.put(h, (double) time / (double) N);
            h = h / 3;
        }
        System.out.println("N=" + a.length);
        for (Map.Entry<Integer, Double> hi : comparsionFactor.entrySet()) {
            System.out.println("\th=" + hi.getKey() + "\tcomparsion/N=" + hi.getValue());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        for (int i = 100; i < 1 << 30; i *= 10) {
            Double[] array = new Double[i];
            for (int j = 0; j < array.length; j++) {
                array[j] = StdRandom.uniform();
            }
            sort(array, true);
        }
    }

```

我也不知道统计逻辑的对不对，对排序中比较次数进行统计得到的结果是：

```java_holder_method_tree
N=100
	h=40	comparsion/N=0.71
	h=13	comparsion/N=1.51
	h=4	comparsion/N=2.29
	h=1	comparsion/N=3.16

N=1000
	h=364	comparsion/N=0.807
	h=121	comparsion/N=1.677
	h=40	comparsion/N=2.251
	h=13	comparsion/N=3.05
	h=4	comparsion/N=3.319
	h=1	comparsion/N=2.732

N=10000
	h=9841	comparsion/N=0.0159
	h=3280	comparsion/N=0.8945
	h=1093	comparsion/N=1.7302
	h=364	comparsion/N=2.3623
	h=121	comparsion/N=2.8685
	h=40	comparsion/N=3.3581
	h=13	comparsion/N=4.2925
	h=4	comparsion/N=4.2929
	h=1	comparsion/N=2.7762

N=100000
	h=88573	comparsion/N=0.11427
	h=29524	comparsion/N=0.97871
	h=9841	comparsion/N=1.76028
	h=3280	comparsion/N=2.40965
	h=1093	comparsion/N=2.99653
	h=364	comparsion/N=3.8316
	h=121	comparsion/N=4.84592
	h=40	comparsion/N=5.91445
	h=13	comparsion/N=6.58539
	h=4	comparsion/N=4.72084
	h=1	comparsion/N=2.76235

N=1000000
	h=797161	comparsion/N=0.202839
	h=265720	comparsion/N=1.050042
	h=88573	comparsion/N=1.831309
	h=29524	comparsion/N=2.450498
	h=9841	comparsion/N=3.074946
	h=3280	comparsion/N=3.867689
	h=1093	comparsion/N=5.025239
	h=364	comparsion/N=7.030636
	h=121	comparsion/N=10.206639
	h=40	comparsion/N=12.588854
	h=13	comparsion/N=13.15827
	h=4	comparsion/N=4.621902
	h=1	comparsion/N=2.759154

N=10000000
	h=7174453	comparsion/N=0.2825547
	h=2391484	comparsion/N=1.1210637
	h=797161	comparsion/N=1.8975449
	h=265720	comparsion/N=2.5011382
	h=88573	comparsion/N=3.1162255
	h=29524	comparsion/N=3.9292178
	h=9841	comparsion/N=5.0785526
	h=3280	comparsion/N=6.9474069
	h=1093	comparsion/N=10.0585983
	h=364	comparsion/N=15.5543458
	h=121	comparsion/N=18.6002489
	h=40	comparsion/N=22.6961629
	h=13	comparsion/N=12.2431535
	h=4	comparsion/N=4.6181411
	h=1	comparsion/N=2.7555026
```

较小的h如1、4，他们比较次数与整个数组长度的比值接近于一个常数，其他的数字倒没有体现出这种特征，不知道是不是我代码写错了。