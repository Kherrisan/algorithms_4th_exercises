package com.dokyme.alg4.sorting.priorityqueue;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/28-11:41
 * Description:
 */
public class Particle {

    private static final double INFINITY = 1.0D / 0.0;

    public static final double RADIUS = 0.01d;
    public static final double MASS = 0.5d;

    private double rx, ry;
    private double vx, vy;
    private final double radius = RADIUS;
    private final double mass = MASS;
    public int count;
    private final Color color = Color.BLACK;

    public Particle() {
        this.rx = StdRandom.uniform(0.0d, 1.0d);
        this.ry = StdRandom.uniform(0.0d, 1.0d);
        this.vx = StdRandom.uniform(-0.005d, 0.005d);
        this.vy = StdRandom.uniform(-0.005d, 0.005d);
    }

    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }

    public double timeToHit(Particle that) {
        if (this == that) {
            return INFINITY;
        }
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0.0d) {
            return INFINITY;
        }
        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        double sigma = radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0.0d) {
            return INFINITY;
        }
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitVerticalWall() {
        if (vx > 0.0d) {
            return (1d - rx - radius) / vx;
        } else if (vx < 0.0d) {
            return (radius - rx) / vx;
        }
        return INFINITY;
    }

    public double timeToHitHorizontalWall() {
        if (vy > 0.0d) {
            return (1 - ry - radius) / vy;
        } else if (vy < 0.0d) {
            return (radius - ry) / vy;
        }
        return INFINITY;
    }

    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;
        double magnitude = 2.0d * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double fx = magnitude * dx / dist;
        double fy = magnitude * dy / dist;
        this.vx += fx / this.mass;
        this.vy += fy / this.mass;
        that.vx -= fx / that.mass;
        that.vy -= fx / that.mass;
        this.count++;
        that.count++;
    }

    public void bonceOffVerticalWall() {
        vx = -vx;
        count++;
    }

    public void bonceOfHorizontalWall() {
        vy = -vy;
        count++;
    }

    @Override
    public String toString() {
        return String.format("(%f,%f)", rx, ry);
    }
}
