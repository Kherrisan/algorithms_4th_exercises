package com.dokyme.alg4.sorting.priorityqueue;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/28-11:54
 * Description:
 */
public class CollisionSystem {

    private class Event implements Comparable<Event> {
        private final double time;
        private final Particle a, b;
        private final int countA, countB;

        public Event(double time, Particle a, Particle b) {
            this.time = time;
            this.a = a;
            this.b = b;
            if (a != null) {
                countA = a.count;
            } else {
                countA = -1;
            }
            if (b != null) {
                countB = b.count;
            } else {
                countB = -1;
            }
        }

        @Override
        public String toString() {
            return "" + time;
        }

        @Override
        public int compareTo(Event o) {
            return Double.compare(this.time, o.time);
        }

        public boolean isValid() {
            if (a != null && a.count != countA) {
                return false;
            }
            if (b != null && b.count != countB) {
                return false;
            }
            return true;
        }
    }

    private MinHeap<Event> pq;
    private double t = 0d;
    private Particle[] particles;
    private int N;

    public CollisionSystem(Particle[] particles) {
        this.particles = particles;
        N = particles.length;
    }

    private void predict(Particle a, double limit) {
        if (a == null) {
            return;
        }
        for (int i = 0; i < N; i++) {
            double dt = a.timeToHit(particles[i]);
            if (t + dt <= limit) {
                pq.insert(new Event(t + dt, a, particles[i]));
            }
        }
        double dtX = a.timeToHitVerticalWall(), dtY = a.timeToHitHorizontalWall();
        if (t + dtX <= limit) {
            pq.insert(new Event(t + dtX, a, null));
        }
        if (t + dtY <= limit) {
            pq.insert(new Event(t + dtY, null, a));
        }
    }

    private void redraw(double limit) {
        StdDraw.clear();
        for (int i = 0; i < N; i++) {
            particles[i].draw();
        }
        StdDraw.show();
        StdDraw.pause(20);
        if (t <= limit) {
            pq.insert(new Event(t + 2.0d, null, null));
        }
    }

    public void simulate(double limit) {
        pq = new MinHeap<>(100);
        for (int i = 0; i < N; i++) {
            predict(particles[i], limit);
        }
        pq.insert(new Event(0.0d, null, null));
        while (true) {
            Event event;
            do {
                if (pq.isEmpty()) {
                    return;
                }
                event = pq.delMin();
            } while (!event.isValid());
            Particle a = event.a, b = event.b;
            for (int i = 0; i < N; i++) {
                particles[i].move(event.time - t);
            }
            t = event.time;
            if (a != null && b != null) {
                a.bounceOff(b);
            } else if (a != null && b == null) {
                a.bonceOffVerticalWall();
            } else if (a == null && b != null) {
                b.bonceOfHorizontalWall();
            } else if (a == null && b == null) {
                redraw(limit);
            }
            predict(a, limit);
            predict(b, limit);
        }
    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.enableDoubleBuffering();
        Particle[] particles = new Particle[100];
        for (int i = 0; i < particles.length; i++) {
            particles[i] = new Particle();
        }
        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(100000d);
//        edu.princeton.cs.algs4.CollisionSystem.main(new String[]{"100"});
    }
}
