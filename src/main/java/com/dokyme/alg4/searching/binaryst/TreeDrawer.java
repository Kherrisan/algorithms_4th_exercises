package com.dokyme.alg4.searching.binaryst;

import java.util.Map;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/27-19:02
 * Description:
 */
public class TreeDrawer {

    private Map<Integer, Float> nexts;

    private Map<Integer, Float> offset;

    public class NodeWrapper {
        private float x;
        private float y;
        private float mod;
        private BinaryTreeNode node;
        private NodeWrapper[] children = new NodeWrapper[2];
    }

    public void draw() {

    }

}
