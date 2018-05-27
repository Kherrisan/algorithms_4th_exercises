package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.priorityqueue.IndexMinPQ;
import com.dokyme.alg4.sorting.priorityqueue.MaxHeap;
import com.dokyme.alg4.sorting.priorityqueue.MinHeap;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/27-18:29
 * Description:
 */
public class LPT {

    public static class Processor implements Comparable<Processor> {
        private int load;
        private int id;
        private Queue<SPT.Task> tasks;
        private IndexMinPQ<Processor> cpus;

        public Processor(int id, IndexMinPQ<Processor> cpus) {
            this.id = id;
            tasks = new LinkedList<>();
            this.cpus = cpus;
        }

        public void addTask(SPT.Task task) {
            tasks.add(task);
            load += task.duration;
            cpus.change(id, this);
        }

        public boolean isIdle() {
            return load == 0;
        }

        public void run() {
            if (load == 0) {
                StdOut.println("Processor-" + id + " idling......");
            } else {
                StdOut.println("Processor-" + id + " run concurrently, current task is " + tasks.peek());
                tasks.peek().duration--;
                load--;
                cpus.change(id, this);
                if (tasks.peek().duration == 0) {
                    tasks.poll();
                }
            }
        }

        @Override
        public int compareTo(Processor o) {
            if (load < o.load) {
                return -1;
            } else if (load > o.load) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static int run(int m, SPT.Task[] a) {
        int time = 0;
        MaxHeap<SPT.Task> pq = new MaxHeap<>(a);
        IndexMinPQ<Processor> cpus = new IndexMinPQ<Processor>(m);
        for (int i = 0; i < m; i++) {
            cpus.insert(i, new Processor(i, cpus));
        }
        while (!pq.isEmpty()) {
            Processor p = cpus.min();
            p.addTask(pq.delMax());
        }
        boolean running = true;
        while (running) {
            time++;
            running = false;
            for (int i = 0; i < cpus.size(); i++) {
                cpus.get(i).run();
                running |= !cpus.get(i).isIdle();
            }
        }
        return time;
    }

    public static void main(String[] args) {
        int m = StdIn.readInt();
        int num = StdIn.readInt();
        SPT.Task[] a = new SPT.Task[num];
        for (int i = 0; i < num; i++) {
            a[i] = new SPT.Task(StdIn.readString(), StdIn.readInt());
        }
        run(m, a);
    }
}
