package com.dokyme.alg4.sorting.basic;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/2-22:22
 * Description:
 */
public class SortingDrawer<T extends Comparable> {

    private int length;

    private double xMargin;
    private double yMargin;
    private double scaleX;
    private double scaleY;

    private T[] dataArray;

    private List<T> dataList;

    private int[] focus;

    public SortingDrawer(T[] data) {
        dataArray = data;
        init();
    }

    public SortingDrawer(List<T> data) {
        dataList = data;
        init();
    }

    public SortingDrawer focus(int i) {
        focus[i] = 2;
        return this;
    }

    public void update() {
        for (int i = 0; i < length; i++) {
            double value = Double.valueOf(getByIndex(i).toString());
            StdDraw.filledRectangle(xMargin + i + 0.5, yMargin + value / 2, 0.5, value / 2);
        }
    }

    public void update(T[] newArray) {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < length; i++) {
            double newValue = Double.valueOf(newArray[i].toString());
            if (newArray[i] != dataArray[i]) {
                //重绘这个bin
                StdDraw.setPenColor(255, 255, 255);
                StdDraw.filledRectangle(xMargin + i + 0.5, scaleY / 2, 0.5, scaleY / 2);
                if (focus[i] == 2) {
                    StdDraw.setPenColor(99, 99, 99);
                    focus[i] = 1;
                } else {
                    StdDraw.setPenColor(177, 177, 177);
                }
                StdDraw.filledRectangle(xMargin + i + 0.5, yMargin + newValue / 2, 0.5, newValue / 2);
            } else if (focus[i] == 1) {
                focus[i] = 0;
                StdDraw.setPenColor(177, 177, 177);
                StdDraw.filledRectangle(xMargin + i + 0.5, yMargin + newValue / 2, 0.5, newValue / 2);
            }
        }
        copyArray(newArray);
    }

    private void copyArray(T[] newArray) {
        dataArray = Arrays.copyOf(newArray, newArray.length);
    }

    private void init() {
        if (dataArray == null) {
            length = dataList.size();
        } else {
            length = dataArray.length;
        }
        focus = new int[length];
        StdDraw.setCanvasSize(960, 640);
        T max = getByIndex(0);
        for (int i = 1; i < length; i++) {
            if (getByIndex(i).compareTo(max) > 0) {
                max = getByIndex(i);
            }
        }
        //假设每个bin宽度为1，长度为自身值的长度。
        double yUnit = Double.valueOf(max.toString());
        xMargin = length / 20;
        yMargin = yUnit / 20;
        scaleX = length + 2 * xMargin;
        scaleY = yUnit + 2 * yMargin;
        StdDraw.setXscale(0, scaleX);
        StdDraw.setYscale(0, scaleY);
        StdDraw.setPenColor(177, 177, 177);
        update();
    }

    private T getByIndex(int i) {
        if (dataArray == null) {
            return dataList.get(i);
        } else {
            return dataArray[i];
        }
    }

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 1; i++) {
                Double[] array = new Double[100];
                for (int j = 0; j < array.length; j++) {
                    array[j] = StdRandom.uniform();
                }
                SortingDrawer<Double> drawer = new SortingDrawer(array);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
