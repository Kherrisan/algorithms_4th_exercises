package com.dokyme.alg4.sorting;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/8-14:50
 * Description:
 */
public class TestThreads {

    private static CustomedLock lock = new CustomedLock();

    public static class T extends Thread{
        /**
         * If this thread was constructed using a separate
         * <code>Runnable</code> run object, then that
         * <code>Runnable</code> object's <code>run</code> method is called;
         * otherwise, this method does nothing and returns.
         * <p>
         * Subclasses of <code>Thread</code> should override this method.
         *
         * @see #start()
         * @see #stop()
         * @see #Thread(ThreadGroup, Runnable, String)
         */
        @Override
        public void run() {
            isInterrupted();
        }
    }

    public static class Task1 implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            try {

                System.out.println("Task1 started to run.");
                synchronized (lock) {
                    System.out.println("Task1 got lock.");
                    Thread.sleep(2000);
                    System.out.println("Task1 wait.");
                    lock.wait();
                    Thread.interrupted();
                    System.out.println("Task1 get notified.");
                    System.out.println("Task1 released lock");
                }
                System.out.println("Task1 finished.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Task2 implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            System.out.println("Task2 started to run.");
            synchronized (lock) {
                System.out.println("Task2 got lock.");
                System.out.println("Task2 notify Task1.");
                lock.notify();
                System.out.println("Task2 released lock");
            }
            System.out.println("Task2 finished.");
        }
    }

    public static void main(String[] args) {
        try {
            new Thread(new Task1()).start();
            Thread.sleep(2000);
            new Thread(new Task2()).start();
        } catch (Exception e) {

        }
    }

    public static class CustomedLock {

    }
}
