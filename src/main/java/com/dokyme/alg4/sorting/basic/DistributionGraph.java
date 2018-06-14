package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.CurveGraphDrawer;
import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/14-15:50
 * Description:
 */
public class DistributionGraph {

    public void run(Sorting sorting, int length) {
        Double[] a = new Double[length];
        List<Double> averageTimes = new ArrayList<>();
        double lastAverage = 0;
        int itr = 0;
        CurveGraphDrawer<Double> drawer = new CurveGraphDrawer<>();
        drawer.addDataSet("1", averageTimes);
        drawer.draw();
        while (true) {
            itr++;
            for (int i = 0; i < length; i++) {
                a[i] = StdRandom.uniform();
            }
            Stopwatch stopwatch = new Stopwatch();
            sorting.sort(a);
            double duration = stopwatch.elapsedTime();
            lastAverage = (lastAverage * (itr - 1) + duration) / itr;
            averageTimes.add(lastAverage);
            drawer.update();
        }
    }

    public static void main(String[] args) {
        new DistributionGraph().run(new Quick(), 10000);
    }
}
