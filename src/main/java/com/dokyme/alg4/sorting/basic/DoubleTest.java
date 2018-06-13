package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.CurveGraphDrawer;
import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/13-10:21
 * Description:
 */
public class DoubleTest {

    public interface IncreasingRatio {
        double ratio(double n);
    }

    public static CurveGraphDrawer<Double> test(Sorting sorting, IncreasingRatio ratio) {
        return test(sorting, 5000000, ratio);
    }

    public static CurveGraphDrawer<Double> test(Sorting sorting, int maximum, IncreasingRatio ratio) {
        List<Double> relaPredicts = new ArrayList<>();
        List<Double> reals = new ArrayList<>();
        List<Double> ratios = new ArrayList<>();
        int i = 0;
        double last = 1;
        double rr = 1;
        for (int n = 1000; n < 5000000; n *= 2, i++) {
            Double[] a = generate(Double.class, n, new DataMocker<Double>() {
                @Override
                public Double mock(int i) {
                    return StdRandom.uniform();
                }
            });
            Stopwatch st = new Stopwatch();
            sorting.sort(a);
            double t = st.elapsedTime();
            double predict = ratio.ratio(n);
            if (i == 0) {
                rr = t / predict;
            }
            double relative = ratio.ratio(n) / ratio.ratio(n / 2) * last;
            relaPredicts.add(relative);
            reals.add(t);
            ratios.add(t / last);
            StdOut.println(String.format("N:%d\tabs_predict:%f\trel_predict:%f\treal:%f\tratio:%f", n, rr * predict, relative, t, t / last));
            last = t;
        }
        CurveGraphDrawer<Double> drawer = new CurveGraphDrawer<>();
        drawer.addDataSet("1", reals).addDataSet("2", relaPredicts).addDataSet("3", ratios);
        return drawer;
    }

    public static void main(String[] args) {
        test(new Shell(), new IncreasingRatio() {
            @Override
            public double ratio(double n) {
                return Math.pow(n, 2);
            }
        }).draw();
    }
}
