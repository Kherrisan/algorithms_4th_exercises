# 练习

## 2.2.1

```java_holder_method_tree
 a[]                                                     aux[]
 k   0   1   2   3   4   5   6   7   8   9   10  11      i   j   0   1   2   3   4   5   6   7   8   9   10  11
     A   E   Q   S   U   Y   E   I   N   O   S   T       
     A   E   Q   S   U   Y   E   I   N   O   S   T               A   E   Q   S   U   Y   E   I   N   O   S   T
                                                         0   6
 0  -A                                                   1   6  -A   E   Q   S   U   Y  -E   I   N   O   S   T  
 1   A  -E                                               2   6      -E   Q   S   U   Y  -E   I   N   O   S   T
 2   A   E  -E                                           2   7          -Q   S   U   Y  -E   I   N   O   S   T
 3   A   E   E  -I                                       2   8          -Q   S   U   Y      -I   N   O   S   T
 4   A   E   E   I  -N                                   2   9          -Q   S   U   Y          -N   O   S   T
 5   A   E   E   I   N  -O                               2  10          -Q   S   U   Y              -O   S   T
 6   A   E   E   I   N   O  -Q                           3  10          -Q   S   U   Y                  -S   T
 7   A   E   E   I   N   O   Q  -S                       4  10              -S   U   Y                  -S   T
 8   A   E   E   I   N   O   Q   S  -S                   4  11                  -U   Y                  -S   T
 9   A   E   E   I   N   O   Q   S   S  -T               4  12                  -U   Y                      -T
10   A   E   E   I   N   O   Q   S   S   T  -U           5  12                  -U   Y   
11   A   E   E   I   N   O   Q   S   S   T   U  -Y       6  12                      -Y                  
12   A   E   E   I   N   O   Q   S   S   T   U  -Y       7  12                           
```
## 2.2.2

```java_holder_method_tree
sort(a,0,11)
    sort(a,0,5)
        sort(a,0,2)
            sort(a,0,1)
                sort(a,0,0)
                sort(a,1,1)
                merge(a,0,0,1)
            sort(a,2,2)
            merge(a,0,1,2)
        sort(a,3,5)
            sort(a,3,4)
                sort(a,3,3)
                sort(a,4,4)
                merge(a,3,3,4)
            sort(a,5,5)
            merge(a,3,4,5)
        merge(a,0,2,5)
    sort(a,6,11)
        sort(a,6,8)
            sort(a,6,7)
                sort(a,6,6)
                sort(a,7,7)
                merge(a,6,6,7)
            sort(a,8,8)
            merge(a,6,7,8)
        sort(a,9,11)
            sort(a,9,10)
                sort(a,9,9)
                sort(a,10,10)
                merge(a,9,9,10)
            sort(a,11,11)
            merge(a,9,10,11)
        merge(a,6,8,11)
    merge(a,0,5,11)
```
```    
  a[]
                                0   1   2   3   4   5   6   7   8   9   10  11
                                E   A   S   Y   Q   U   E   S   T   I   O   N
    merge(a,0,0,1)             -A  -E   S   Y   Q   U   E   S   T   I   O   N
   merge(a,0,1,2)              -A  -E  -S   Y   Q   U   E   S   T   I   O   N
    merge(a,3,3,4)              A   E   S  -Q  -Y   U   E   S   T   I   O   N
   merge(a,3,4,5)               A   E   S  -Q  -U  -Y   E   S   T   I   O   N
  merge(a,0,2,5)               -A  -E  -Q  -S  -U  -Y   E   S   T   I   O   N
    merge(a,6,6,7)              A   E   Q   S   U   Y  -E  -S   T   I   O   N
   merge(a,6,7,8)               A   E   Q   S   U   Y  -E  -S  -T   I   O   N
    merge(a,9,9,10)             A   E   Q   S   U   Y   E   S   T  -I  -O   N
   merge(a,9,10,11)             A   E   Q   S   U   Y   E   S   T  -I  -N  -O
  merge(a,6,8,11)               A   E   Q   S   U   Y  -E  -I  -N  -O  -S  -T
 merge(a,0,5,11)               -A  -E  -E  -I  -N  -O  -Q  -S  -S  -T  -U  -Y
```

画轨迹图好累，再也不想画轨迹图了。

## 2.2.3

自底向上的归并和自顶向下的归并的路径不一定是一致的。自顶向下的时候顶部优先满足二分裂，子底向上的时候底部有限满足二分裂。二者的归并树是不一样的。

```java_holder_method_tree
            sz=1                0   1   2   3   4   5   6   7   8   9   10  11
            merge(a,0,0,1)      A   E   S   Y   Q   U   E   S   T   I   O   N
            merge(a,2,2,3)      A   E   S   Y   Q   U   E   S   T   I   O   N
            merge(a,4,4,5)      A   E   S   Y   Q   U   E   S   T   I   O   N
            merge(a,6,6,7)      A   E   S   Y   Q   U   E   S   T   I   O   N
            merge(a,8,8,9)      A   E   S   Y   Q   U   E   S   I   T   O   N
            merge(a,10,10,11)   A   E   S   Y   Q   U   E   S   I   T   N   O
        sz=2
        merge(a,0,1,3)          A   E   S   Y   Q   U   E   S   I   T   N   O
        merge(a,4,5,7)          A   E   S   Y   E   Q   S   U   I   T   N   O
        merge(a,8,9,11)         A   E   S   Y   E   Q   S   U   I   N   O   T
    sz=4
    merge(a,0,3,7)              A   E   E   Q   S   S   U   Y   I   N   O   T
    merge(a,8,11,11)            A   E   E   Q   S   S   U   Y   I   N   O   T
sz=8
merge(a,0,7,11)                 A   E   E   I   N   O   Q   S   S   T   U   Y
```

## 2.2.5

当两个子数组都是有序的时候，原地归并后的数组肯定是有序的。
当两个子数组各自无序的时候，原地归并后的数组也一定是无序的，因为子数组中元素的相对前后关系在归并之后的数组中是一致的。

## 2.2.6

N=39时

自顶向下：

```java_holder_method_tree
                    merge(a,0,0,1)
                merge(a,0,1,2)
                merge(a,3,3,4)
            merge(a,0,2,4)
                    merge(a,5,5,6)
                merge(a,5,6,7)
                merge(a,8,8,9)
            merge(a,5,7,9)
        merge(a,0,4,9)
                    merge(a,10,10,11)
                merge(a,10,11,12)
                merge(a,13,13,14)
            merge(a,10,12,14)
                    merge(a,15,15,16)
                merge(a,15,16,17)
                merge(18,18,19)
            merge(a,15,17,19)
        merge(a,10,14,19)
    merge(a,0,9,19)
                    merge(a,20,20,21)
                merge(a,20,21,22)
                merge(a,23,23,24)
            merge(a,20,22,24)
                    merge(a,25,25,26)
                merge(a,25,26,27)
                merge(a,28,28,29)
            merge(a,25,27,29)
        merge(a,20,24,29)
                    merge(a,30,30,31)
                merge(a,30,31,32)
                merge(a,33,33,34)
            merge(a,30,32,34)
                merge(a,35,35,36)
                merge(a,37,37,38)
            merge(a,35,36,38)
        merge(a,30,34,38)
    merge(a,20,29,38)
merge(a,0,19,38)
```

自底向上：
(我才意识到我可以用程序直接输出啊！！！)

```java_holder_method_tree
sz=1
merge(a,0,0,1)
merge(a,2,2,3)
merge(a,4,4,5)
merge(a,6,6,7)
merge(a,8,8,9)
merge(a,10,10,11)
merge(a,12,12,13)
merge(a,14,14,15)
merge(a,16,16,17)
merge(a,18,18,19)
merge(a,20,20,21)
merge(a,22,22,23)
merge(a,24,24,25)
merge(a,26,26,27)
merge(a,28,28,29)
merge(a,30,30,31)
merge(a,32,32,33)
merge(a,34,34,35)
merge(a,36,36,37)
sz=2
merge(a,0,1,3)
merge(a,4,5,7)
merge(a,8,9,11)
merge(a,12,13,15)
merge(a,16,17,19)
merge(a,20,21,23)
merge(a,24,25,27)
merge(a,28,29,31)
merge(a,32,33,35)
merge(a,36,37,38)
sz=4
merge(a,0,3,7)
merge(a,8,11,15)
merge(a,16,19,23)
merge(a,24,27,31)
merge(a,32,35,38)
sz=8
merge(a,0,7,15)
merge(a,16,23,31)
sz=16
merge(a,0,15,31)
sz=32
merge(a,0,31,38)
```

感觉自底向上有一种和希尔排序相似的模式。

## 2.2.6

本来在上一节的提高题里有一道题是需要自己画曲线图的，我没做那道题，所以也就没有实现曲线图绘制的工具。

于是在做到此题是顺便实现了此功能。

[]

图中反映了对于不同规模（从1到512）的数组，两种归并排序所需要的访问内存的总次数。

图中黑线为MergeBU，蓝线为Merge。二者访存次数的值和增长趋势基本一致。蓝线更平滑一些。

