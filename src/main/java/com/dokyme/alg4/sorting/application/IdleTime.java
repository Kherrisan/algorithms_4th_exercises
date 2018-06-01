package com.dokyme.alg4.sorting.application;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/31-20:45
 * Description:
 */
public class IdleTime {

    private static class Job implements Comparable<Job>{

        private int startTime;
        private int endTime;

        public Job(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Job o) {
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
