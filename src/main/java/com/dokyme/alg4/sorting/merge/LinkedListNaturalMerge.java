package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.2.17
 *
 * @author dokym
 * @date 2018/4/23-2:38
 * Description:
 */
public class LinkedListNaturalMerge implements Sorting {

    public class LinkedList<T> implements AbstractList {

        public Node<T> first;
        private int size = 0;

        public LinkedList() {

        }

        public int size() {
            return size;
        }

        public class Node<T> {
            public T data;
            public Node<T> next;
        }

    }

    @Override
    public void sort(Comparable[] a) {

    }

    public void sort(LinkedList a) {

    }

    private void merge() {

    }

    public static void main(String[] args) {

    }
}
