package com.dokyme.alg4.graph.directed;

import java.util.Stack;

/**
 * 寻找有向环
 */
public class DirectedCycle {

    private boolean[] marked;

    //用于在找到环时反向遍历节点
    private int[] edgeTo;

    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph g) {
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                //深度优先遍历每一个顶点
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        //v节点为遍历的路径中的某个节点
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (this.hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                //如果w是当前路径中的之前遍历过的节点，说明有环
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                //插入一个头节点w
                cycle.push(w);
                //插入节点v来表示从v到v的环
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
