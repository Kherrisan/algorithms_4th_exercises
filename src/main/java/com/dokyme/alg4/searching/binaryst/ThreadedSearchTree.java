package com.dokyme.alg4.searching.binaryst;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 3.2.34
 *
 * @author dokym
 * @date 2018/6/18-12:17
 * Description:
 */
public class ThreadedSearchTree<Key extends Comparable<Key>, Value> implements com.dokyme.alg4.searching.st.SymbolTable<Key, Value> {

    public class Node {
        private Node left;
        private Node right;
        private Node prev;
        private Node succ;
        private Key key;
        private Value value;
        private int n;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }

    private Node root;

    private Node getNode(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                break;
            }
        }
        return x;
    }

    public Key next(Key key) {
        Node n = getNode(key);
        if (n == null || n.succ == null) {
            return null;
        }
        return n.succ.key;
    }

    public Key prev(Key key) {
        Node n = getNode(key);
        if (n == null || n.prev == null) {
            return null;
        }
        return n.prev.key;
    }

    @Override
    public Value get(Key key) {
        Node n = getNode(key);
        if (n == null) {
            return null;
        }
        return n.value;
    }

    /**
     * 修改节点值或插入新节点，会改变二叉树结构。
     *
     * @param key
     * @param value
     */
    @Override
    public void put(Key key, Value value) {
        if (root == null) {
            root = new Node(key, value, 1);
            return;
        }
        Node x = getNode(key);
        if (x != null) {
            x.value = value;
            return;
        }
        //没找到key对应的节点
        x = root;
        Node newNode = new Node(key, value, 1);
        while (x != null) {
            x.n++;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                if (x.left == null) {
                    x.left = newNode;
                    break;
                } else {
                    x = x.left;
                }
            } else if (cmp > 0) {
                if (x.right == null) {
                    x.right = newNode;
                    break;
                } else {
                    x = x.right;
                }
            }
        }
        //调整succ和prev的关系
        int i = rank(newNode.key);
        Node prev = selectNode(i - 1), succ = selectNode(i + 1);
        newNode.prev = prev;
        if (prev != null) {
            prev.succ = newNode;
        }
        newNode.succ = succ;
        if (succ != null) {
            succ.prev = newNode;
        }
    }

    /**
     * 删除节点，会改变二叉树结构。
     *
     * @param key
     */
    @Override
    public void delete(Key key) {
        Node x = getNode(key);
        if (x == null) {
            return;
        }
        //x确实存在于树中
        x = root;
        Node xprev = x.prev;
        Node xsucc = x.succ;
        Node parent = null;
        while (x != null) {
            x.n--;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                if (x.left == null) {
                    //左子节点为null
                    if (x == root) {
                        root = x.right;
                    }
                    //x的左节点为null，用右节点替换x
                    else if (x == parent.left) {
                        parent.left = x.right;
                    } else {
                        parent.right = x.right;
                    }
                    if (xsucc != null) {
                        xsucc.prev = xprev;
                    }
                    if (xprev != null) {
                        xprev.succ = xsucc;
                    }
                } else if (x.right == null) {
                    //右子节点为null
                    if (x == root) {
                        root = x.left;
                    } else if (x == parent.left) {
                        parent.left = x.left;
                    } else {
                        parent.right = x.left;
                    }
                    if (xsucc != null) {
                        xsucc.prev = xprev;
                    }
                    if (xprev != null) {
                        xprev.succ = xsucc;
                    }
                } else {
                    //x有两个子节点
                    Node succ = min(x.right);
                    Node ssucc = succ.succ;
                    deleteMin(x.right);
                    if (parent == null) {
                        root = succ;
                    } else if (x == parent.left) {
                        parent.left = succ;
                    } else {
                        parent.right = succ;
                    }
                    succ.left = x.left;
                    succ.right = x.right;
                    //a1,a2,a3,a4,a5,假设删除了a3，a4是a3的后继，替补到a3的位置
                    if (ssucc != null) {
                        //由于deletemin方法会释放a4和a5的连接，要重建一下
                        ssucc.prev = succ;
                    }
                    if (xprev != null) {
                        xprev.succ = succ;
                    }
                    succ.prev = xprev;
                }
                break;
            }
            parent = x;
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.n;
    }

    @Override
    public void deleteMin() {
        deleteMin(root);
    }

    private void deleteMin(Node x) {
        if (x == null) {
            return;
        }
        Node c = x;
        Node up = null;
        while (c.left != null) {
            c.n--;
            up = c;
            c = c.left;
        }
        if (up == null) {
            root = x.right;
            return;
        }
        //a1,a2<->a3<->a4,a5
        //a1,a2<->a4,a5
        up.left = c.right;
        if (up.left == null) {
            return;
        }
        up.left.prev = c.prev;
        if (c.prev != null) {
            c.prev.succ = up.left;
        }
    }

    @Override
    public void deleteMax() {
        deleteMax(root);
    }

    private void deleteMax(Node x) {
        Node c = x;
        Node up = null;
        while (c.right != null) {
            c.n--;
            up = c;
            c = c.right;
        }
        if (up == null) {
            root = x.left;
            return;
        }
        up.right = c.left;
        if (up.right == null) {
            return;
        }
        up.right.succ = c.succ;
        if (c.succ != null) {
            c.succ.prev = up.right;
        }
    }

    private Node min(Node x) {
        Node c = x;
        while (c.left != null) {
            c = c.left;
        }
        return c;
    }

    @Override
    public Key min() {
        Node n = min(root);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    @Override
    public Key max() {
        Node n = max(root);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    private Node max(Node x) {
        Node c = x;
        while (c.right != null) {
            c = c.right;
        }
        return c;
    }

    @Override
    public Key floor(Key key) {
        Node x = root;
        Key k = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                //key>x.key，说明x已经有资格成为结果，但还要试探有无比x更大的元素成为最优的结果
                k = x.key;
                x = x.right;
            } else {
                k = x.key;
                break;
            }
        }
        return k;
    }

    @Override
    public Key ceiling(Key key) {
        Node x = root;
        Key k = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                k = x.key;
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                k = x.key;
                break;
            }
        }
        return k;
    }

    private Node selectNode(int n) {
        Node x = root;
        while (x != null) {
            int leftSubSize = 0;
            if (x.left != null) {
                leftSubSize = x.left.n;
            }
            if (n > leftSubSize) {
                n -= (leftSubSize + 1);
                x = x.right;
            } else if (n < leftSubSize) {
                x = x.left;
            } else {
                return x;
            }
        }
        return null;
    }

    @Override
    public Key select(int n) {
        Node node = selectNode(n);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Override
    public int rank(Key key) {
        Node x = root;
        int r = 0;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                r++;
                if (x.left != null) {
                    r += x.left.n;
                }
                x = x.right;
            } else {
                if (x.left != null) {
                    r += x.left.n;
                }
                break;
            }
        }
        return r;
    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    public static void main(String[] args) {

    }
}
