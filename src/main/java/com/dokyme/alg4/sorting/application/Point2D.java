package com.dokyme.alg4.sorting.application;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/30-22:37
 * Description:
 */
public class Point2D implements Comparable<Point2D> {

    private double x;
    private double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double r() {
        return Math.sqrt(x * x + y * y);
    }

    public double theta() {
        return Math.acos(x / r());
    }

    public double thetaTo(Point2D p) {
        return Math.acos((x - p.x) / distanceTo(p));
    }

    public double distanceTo(Point2D p) {
        return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
    }

    public static class XComparator implements Comparator<Point2D> {
        @Override
        public int compare(Point2D o1, Point2D o2) {
            return (int) (o1.x - o2.x);
        }
    }

    public static class YComparator implements Comparator<Point2D> {

        private Comparator<Point2D> comparator;

        public YComparator(Comparator<Point2D> comparator) {
            this.comparator = comparator;
        }

        @Override
        public int compare(Point2D o1, Point2D o2) {
            if ((int) (o1.y - o2.y) != 0) {
                return (int) (o1.y - o2.y);
            }
            return comparator.compare(o1, o2);
        }
    }

    public class DistanceComparator implements Comparator<Point2D> {
        @Override
        public int compare(Point2D o1, Point2D o2) {
            return (int) (o1.distanceTo(Point2D.this) - o2.distanceTo(Point2D.this));
        }
    }

    public class ThetaComparator implements Comparator<Point2D> {
        @Override
        public int compare(Point2D o1, Point2D o2) {
            double theta1 = Math.acos((o1.x - x) / distanceTo(o1));
            double theta2 = Math.acos((o2.x - x) / distanceTo(o2));
            return (int) (theta1 - theta2);
        }
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    @Override
    public int compareTo(Point2D o) {
        return new YComparator(new XComparator()).compare(this, o);
    }

    public static void main(String[] args) {

    }
}
