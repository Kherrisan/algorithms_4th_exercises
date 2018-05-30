package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.merge.Merge;
import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/30-22:51
 * Description:
 */
public class Polygon {

    public static void draw(Point2D[] points) {
        StdDraw.setCanvasSize(960, 640);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        new Merge().sort(points);
        Point2D p = points[0];
        Point2D[] others = new Point2D[points.length - 1];
        System.arraycopy(points, 1, others, 0, points.length - 1);
        Quick.sort(others, 0, others.length - 1, p.new ThetaComparator());
        StdDraw.line(p.x(), p.y(), others[0].x(), others[0].y());
        for (int i = 1; i < others.length; i++) {
            StdOut.println(p.thetaTo(others[i]));
            StdDraw.line(others[i - 1].x(), others[i - 1].y(), others[i].x(), others[i].y());
        }
    }

    public static void main(String[] args) {
        Point2D[] ps = generate(Point2D.class, 100, new DataMocker<Point2D>() {
            @Override
            public Point2D mock(int i) {
                return new Point2D(StdRandom.uniform(100), StdRandom.uniform(100));
            }
        });
        draw(ps);
    }
}
