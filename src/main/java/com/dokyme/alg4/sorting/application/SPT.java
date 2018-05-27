package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/27-16:20
 * Description:
 */
public class SPT {

    public static class Task implements Comparable<Task> {
        public String name;
        public int duration;

        public Task(String name, int duration) {
            this.name = name;
            this.duration = duration;
        }

        public Task(String line) {
            String[] toks = line.split(" ");
            name = toks[0];
            duration = Integer.valueOf(toks[1]);
        }

        @Override
        public int compareTo(Task o) {
            if (duration < o.duration) {
                return -1;
            } else if (duration > o.duration) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "Task:" + name + "\tduration:" + duration + "\n";
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Task[] tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = new Task(StdIn.readString(),StdIn.readInt());
        }
        new Quick().sort(tasks);
        StdOut.println(Arrays.toString(tasks));
    }
}
