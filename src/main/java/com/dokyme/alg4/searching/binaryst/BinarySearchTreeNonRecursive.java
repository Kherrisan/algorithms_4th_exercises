package com.dokyme.alg4.searching.binaryst;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 3.2.13
 * 3.2.14
 *
 * @author dokym
 * @date 2018/6/8-19:29
 * Description:
 */
public class BinarySearchTreeNonRecursive<Key extends Comparable<Key>, Value> implements com.dokyme.alg4.searching.st.SymbolTable<Key, Value> {

    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count;

        public Node(Key key, Value val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }

    private Node root;

    @Override
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        //第一趟寻找插入的位置，第二趟修正沿线所有的size
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                x.val = value;
                return;
            }
        }

        if (root == null) {
            root = new Node(key, value, 1);
            return;
        }
        x = root;
        while (x != null) {
            x.count++;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                if (x.left != null) {
                    x = x.left;
                } else {
                    x.left = new Node(key, value, 1);
                    return;
                }
            } else if (cmp > 0) {
                if (x.right != null) {
                    x = x.right;
                } else {
                    x.right = new Node(key, value, 1);
                    return;
                }
            }
        }
    }

    @Override
    public void delete(Key key) {
        if (get(key) == null) {
            return;
        }
        Node x = root;
        if (x == null) {
            return;
        }
        Node up = null;
        while (x != null) {
            x.count--;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                if (x.left == null) {
                    if (up == null) {
                        //x是根节点
                        root = x.right;
                    } else if (x.key.compareTo(up.key) < 0) {
                        //待删除节点是一个左子节点
                        up.left = x.right;
                    } else {
                        //待删除节点是一个右子节点
                        up.right = x.right;
                    }
                    break;
                }
                if (x.right == null) {
                    if (up == null) {
                        root = x.left;
                    } else if (x.key.compareTo(up.key) > 0) {
                        up.left = x.left;
                    } else {
                        up.right = x.left;
                    }
                    break;
                }
                //如果待删除的节点有2个子节点
                Node leftMin = min(x.right);
                deleteMin(x.right);
                if (up == null) {
                    root = leftMin;
                } else if (x.key.compareTo(up.key) < 0) {
                    up.left = leftMin;
                } else {
                    up.right = leftMin;
                }
                leftMin.left = x.left;
                leftMin.right = x.right;
                leftMin.count = x.count - 1;
                break;
            }
            up = x;

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
        return root.count;
    }

    @Override
    public void deleteMin() {
        deleteMin(root);
    }

    private void deleteMin(Node x) {
        if (x == null) {
            return;
        }
        Node up = null;
        while (x.left != null) {
            up = x;
            x.count--;
            x = x.left;
        }
        if (up == null) {
            root = x.right;
            return;
        }
        up.left = x.right;
    }

    @Override
    public void deleteMax() {
        deleteMax(root);
    }

    private void deleteMax(Node x) {
        if (x == null) {
            return;
        }
        Node up = null;
        while (x.right != null) {
            up = x;
            x.count--;
            x = x.right;
        }
        if (up == null) {
            root = x.left;
            return;
        }
        up.right = x.left;
    }

    public Node min(Node x) {
        if (x == null) {
            return null;
        }
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    @Override
    public Key min() {
        Node x = root;
        if (x == null) {
            return null;
        }
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    @Override
    public Key max() {
        Node x = root;
        if (x == null) {
            return null;
        }
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    @Override
    public Key floor(Key key) {
        Node x = root;
        Key temp = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp == 0) {
                return x.key;
            } else {
                //不知道右子树是否存在比他小但是比根节点大的节点
                //此时x是比key小的节点，满足条件，但不一定是最优
                temp = x.key;
                x = x.right;
            }
        }
        return temp;
    }

    @Override
    public Key ceiling(Key key) {
        Node x = root;
        Key temp = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                temp = x.key;
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.key;
            }
        }
        return temp;
    }

    @Override
    public int rank(Key key) {
        Node x = root;
        int current = 0;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                current++;
                if (x.left != null) {
                    current += x.left.count;
                }
                x = x.right;
            } else {
                if (x.left != null) {
                    current += x.left.count;
                }
                break;
            }
        }
        return current;
    }

    @Override
    public Key select(int n) {
        Node x = root;
        while (x != null) {
            int leftSubSize = 0;
            if (x.left != null) {
                leftSubSize = x.left.count;
            }
            if (leftSubSize > n) {
                x = x.left;
            } else if (leftSubSize == n) {
                return x.key;
            } else {
                n -= (leftSubSize + 1);
                x = x.right;
            }
        }
        return null;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(root, min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        return keys(root, lo, hi);
    }

    public Iterable<Key> keys(Node x, Key lo, Key hi) {
        Stack<Node> stack = new Stack<>();
        List<Key> res = new LinkedList<>();

        return res;
    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

}
