# 提高题

## 3.3.23 没有平衡限制的2-3树

[TwoThreeTreeWithoutBalance.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/balanced/TwoThreeTreeWithoutBalance.java)

```
:TwoThreeTreeWithoutBalance.main()
Size:869	InnerPathLength:9995	AveragePathLength:11.501726
===========================
Size:1777	InnerPathLength:24054	AveragePathLength:13.536297
===========================
Size:3521	InnerPathLength:51304	AveragePathLength:14.570861
===========================
Size:6879	InnerPathLength:118687	AveragePathLength:17.253525
===========================
Size:13585	InnerPathLength:234200	AveragePathLength:17.239603
===========================
Size:26585	InnerPathLength:481865	AveragePathLength:18.125447
===========================
Size:52371	InnerPathLength:1017018	AveragePathLength:19.419488
===========================
Size:103088	InnerPathLength:2128009	AveragePathLength:20.642645
===========================
Size:202983	InnerPathLength:4828567	AveragePathLength:23.788036
===========================
Size:398679	InnerPathLength:9360215	AveragePathLength:23.478074
===========================
Size:782348	InnerPathLength:19262044	AveragePathLength:24.620813
===========================
Size:1537410	InnerPathLength:41675121	AveragePathLength:27.107357
===========================
Size:3027917	InnerPathLength:82828559	AveragePathLength:27.354963
===========================
```

## 3.3.24 红黑树的最坏情况

从浅显的理解上考虑，红黑树的最坏情况应该发生于在仅在红黑树一侧（一条路径上）进行插入的情况。

## 3.3.25 自顶向下的2-3-4树

[TwoThreeFourTree.java](https://github.com/Dokyme/algorithms_4th_exercises/blob/master/src/main/java/com/dokyme/alg4/searching/balanced/TwoThreeFourTree.java)

```
:TwoThreeFourTreeTopDown.main()
Size:875	InnerPathLength:8022	AveragePathLength:9.168000
===========================
Size:3517	InnerPathLength:39422	AveragePathLength:11.208985
===========================
Size:13581	InnerPathLength:179058	AveragePathLength:13.184449
===========================
Size:52190	InnerPathLength:794942	AveragePathLength:15.231692
===========================
Size:202786	InnerPathLength:3529463	AveragePathLength:17.404865
===========================
Size:781145	InnerPathLength:15088358	AveragePathLength:19.315694
===========================
Size:3026774	InnerPathLength:64643128	AveragePathLength:21.357104
===========================
```

保持平衡的2-3-4树比不平衡的2-3树更低一些。