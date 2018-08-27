package com.dokyme.alg4.graph.undirected;

public abstract class Paths {

    public abstract boolean hasPathTo(int v);

    public abstract Iterable<Integer> pathTo(int v);
}
