package com.dokyme.alg4.sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/17-20:45
 * Description:
 */
public class CurveGraphDrawer<T extends Comparable> {

    private Map<String, List<T>> setCollections;
    private double xMargin;
    private double yMargin;
    private double scaleX;
    private double scaleY;
    private boolean initialized = false;

    private static List<Color> colorList = new ArrayList<>();

    static {
        colorList.add(Color.BLACK);
        colorList.add(Color.BLUE);
        colorList.add(Color.YELLOW);
    }

    public CurveGraphDrawer() {
        setCollections = new HashMap<>();
    }

    private void init() {
        if (initialized) {
            return;
        }
        StdDraw.setCanvasSize(960, 640);
        T max = null;
        Integer width = null;
        for (List<T> dataset : setCollections.values()) {
            if (width == null) {
                width = dataset.size();
            }
            for (T data : dataset) {
                if (max == null) {
                    max = data;
                } else if (max.compareTo(data) < 0) {
                    max = data;
                }
            }
        }

        //默认每个dataset的长度是一致的，即横向的跨度相等。
        xMargin = width / 20;
        double y = Double.valueOf(max.toString());
        yMargin = y / 20;
        scaleX = width + xMargin * 2;
        scaleY = y + yMargin * 2;
        StdDraw.setXscale(0, scaleX);
        StdDraw.setYscale(0, scaleY);
        StdDraw.setPenColor(177, 177, 177);
        StdDraw.setPenRadius(0.01);
        initialized = true;
    }

    public CurveGraphDrawer addDataSet(String name, List<T> dataSet) {
        setCollections.put(name, dataSet);
        return this;
    }

    public void draw() {
        init();
        Double y0, y1;
        Double x0, x1;
        int ci = 0;
        for (String name : setCollections.keySet()) {
            List<T> dataset = setCollections.get(name);
            StdDraw.setPenColor(colorList.get(ci++));
            for (int i = 0; i < dataset.size() - 1; i++) {
                x0 = i * 1.0 + xMargin;
                x1 = (i + 1) * 1.0 + xMargin;
                y0 = Double.valueOf(dataset.get(i).toString()) + yMargin;
                y1 = Double.valueOf(dataset.get(i + 1).toString()) + yMargin;
                StdDraw.line(x0, y0, x1, y1);
            }
        }
    }

    public static void main(String[] args) {
        CurveGraphDrawer<Integer> drawer = new CurveGraphDrawer<>();
        List<Integer> dataset = new ArrayList<>();
        List<Integer> dataset2 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataset.add(i * i);
            dataset2.add(i * i * i);
        }
        drawer.addDataSet("1", dataset).addDataSet("2", dataset2).draw();
    }
}
