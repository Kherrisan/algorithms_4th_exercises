package com.dokyme.alg4.graph.mst;

import com.dokyme.alg4.sorting.priorityqueue.IndexMinPQ;

/**
 * 加权无向图的最小生成树
 * ElogV
 */
public class PrimMST {

    //edgeTo[i]表示非树节点i与树种任意节点距离最小的路径
    private Edge[] edgeTo;

    //distTo[i]=edgeTo[i].weight()
    private double[] distTo;

    private boolean[] marked;

    //所有非树节点与树中任意节点距离的最小值，即横切边的集合
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph g) {
        edgeTo = new Edge[g.V()];
        distTo = new double[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(g.V());

        distTo[0] = 0.0;
        pq.insert(0, 0);
        while (!pq.isEmpty()) {
            visit(g, pq.delMin());
        }
    }

    public Iterable<Edge> edges() {
        return null;
    }

    public double weight() {
        return 0d;
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;//将v加入到生成树中
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;//如果w已经在树中，就跳过之
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                //修正横切集
                if (pq.contains(w)) {
                    pq.change(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }
}
