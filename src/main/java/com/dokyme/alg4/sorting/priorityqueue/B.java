package com.dokyme.alg4.sorting.priorityqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/9-10:46
 * Description:
 */
public class B {

    public static void main(String[] args) {

    }
}

class SuperClass {
    static {
        System.out.println("SuperClass init");
    }

    public SuperClass() {
        System.out.println("SuperClass constructor");
    }
}

class DerivedClass extends SuperClass {
    static {
        System.out.println("DerivedClass init");
    }

    public DerivedClass() {
        System.out.println("DerivedClass Constrcutor");
    }
}
