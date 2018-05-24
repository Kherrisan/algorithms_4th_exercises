package com.dokyme.alg4.sorting.merge;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.merge.LinkedListNaturalMerge.LinkedList;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.2.18
 *
 * @author dokym
 * @date 2018/4/23-17:43
 * Description:
 */
public class DisorderLinkedList {

    private LinkedList.Node middleNode(LinkedList.Node start, LinkedList.Node end) {
        //快慢指针查找链表中点（中点或中间靠前的一个节点）
        LinkedList.Node fast = start, slow = start;
        while (fast != end && fast.next != end) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private void shuffleMerge(LinkedList.Node lo, LinkedList.Node hi) {
        LinkedList.Node mi = middleNode(lo, hi);
        //乱序归并a[lo+1...mi]和a[mi+1...hi]
        LinkedList.Node i = lo.next, j = mi.next;
        final LinkedList.Node leftEnd = mi.next, rightEnd = hi.next;
        while (i != leftEnd || j != rightEnd) {
            if (i == leftEnd) {
                lo.next = j;
                j = j.next;
            } else if (j == rightEnd) {
                lo.next = i;
                i = i.next;
            } else if (StdRandom.bernoulli()) {
                lo.next = j;
                j = j.next;
            } else {
                lo.next = i;
                i = i.next;
            }
            lo = lo.next;
        }
        lo.next = rightEnd;
    }

    public void shuffle(LinkedList a) {
        shuffle(a.first, a.last);
    }

    private void shuffle(LinkedList.Node start, LinkedList.Node end) {
        if (start == end || start.next == end) {
            return;
        }
        LinkedList.Node mid = middleNode(start, end);
        LinkedList.Node midNext = mid.next;
        shuffle(start, mid);
        shuffle(midNext, end);
        shuffleMerge(start, end);
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList();
        for (int i = 0; i < 19; i++) {
            list.add(i);
        }
        new DisorderLinkedList().shuffle(list);
        for (int i = 0; i < 16; i++) {
            StdOut.print(list.poll() + "\n");
        }
    }
}
