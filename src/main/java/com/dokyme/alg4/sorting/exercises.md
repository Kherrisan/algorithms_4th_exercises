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

