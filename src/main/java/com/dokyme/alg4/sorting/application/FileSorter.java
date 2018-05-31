package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.merge.Merge;
import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.Comparator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/31-19:58
 * Description:
 */
public class FileSorter {

    public static class FileSizeComparator implements Comparator<File> {

        private Comparator comparator;

        public FileSizeComparator(Comparator comparator) {
            this.comparator = comparator;
        }

        @Override
        public int compare(File o1, File o2) {
            if (o1.getTotalSpace() == o2.getTotalSpace() && comparator != null) {
                return comparator.compare(o1, o2);
            }
            return o1.getTotalSpace() > o2.getTotalSpace() ? 1 : -1;
        }
    }

    public static class FileNameComparator implements Comparator<File> {
        private Comparator comparator;

        public FileNameComparator(Comparator comparator) {
            this.comparator = comparator;
        }

        @Override
        public int compare(File o1, File o2) {
            if (o1.getName().equals(o2.getName()) && comparator != null) {
                return comparator.compare(o1, o2);
            }
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static class ModifiedComparator implements Comparator<File> {
        private Comparator comparator;

        public ModifiedComparator(Comparator comparator) {
            this.comparator = comparator;
        }

        @Override
        public int compare(File o1, File o2) {
            if (o1.lastModified() == o2.lastModified() && comparator != null) {
                return comparator.compare(o1, o2);
            }
            return o1.lastModified() > o2.lastModified() ? 1 : -1;
        }
    }

    public void sort(String path, Comparator c) {
        File[] files = new File(path).listFiles();
        new Merge().sort(files, c);
        for (File f : files) {
            StdOut.println(f.getName());
        }
    }

    public static void main(String[] args) {
        boolean asc = true;
        String path = null;
        Comparator c = null;
        for (String arg : args) {
            switch (arg) {
                case "-t":
                    c = new ModifiedComparator(c);
                    break;
                case "-n":
                    c = new FileNameComparator(c);
                    break;
                case "-s":
                    c = new FileSizeComparator(c);
                    break;
                default:
                    path = arg;
                    break;
            }
        }
        if (path == null) {
            StdOut.println("Error:Path is empty!");
        }
        new FileSorter().sort(path, c);
    }
}
