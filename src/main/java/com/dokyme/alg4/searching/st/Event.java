package com.dokyme.alg4.searching.st;

import java.util.Date;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/1-18:47
 * Description:
 */
public class Event implements Comparable<Event> {

    private Date time;

    private String location;

    @Override
    public int compareTo(Event o) {
        return time.compareTo(o.time);
    }

    public static void main(String[] args) {

    }
}
