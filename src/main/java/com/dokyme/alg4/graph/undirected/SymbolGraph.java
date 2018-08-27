package com.dokyme.alg4.graph.undirected;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class SymbolGraph {
    private ST<String, Integer> st;
    private String[] keys;
    private Graph g;

    public SymbolGraph(String stream, String sp) {
        st = new ST<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            for (String anA : a) {
                if (!st.contains(anA)) {
                    st.put(anA, st.size());
                }
            }
            keys = new String[st.size()];
            for (String name : st.keys()) {
                keys[st.get(name)] = name;
            }
            g = new Graph(st.size());
            in = new In(stream);
            while (in.hasNextLine()) {
                String[] a = in.readLine().split(sp);
                int v = st.get(a[0]);
                for (int i = 1; i < a.length; i++) {
                    g.addEdge(v, st.get(a[i]));
                }
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return g;
    }
}
