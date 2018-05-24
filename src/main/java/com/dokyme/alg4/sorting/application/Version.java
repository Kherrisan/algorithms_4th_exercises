package com.dokyme.alg4.sorting.application;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/27-15:57
 * Description:
 */
public class Version implements Comparable<Version> {

    private int major;

    private int minor;

    private int patch;

    @Override
    public int compareTo(Version o) {
        if (major < o.major) {
            return -1;
        } else if (major > o.major) {
            return 1;
        } else if (minor < o.minor) {
            return -1;
        } else if (minor > o.minor) {
            return 1;
        } else if (patch < o.patch) {
            return -1;
        } else if (patch > o.patch) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
