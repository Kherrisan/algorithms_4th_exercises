package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.quick.Quick;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/14-16:13
 * Description:
 */
public class ExtremeTest {

    public static boolean test(Sorting sorting) {
        boolean pass = true;

        int length = 10000;

        Double[] emptyArray = new Double[0];
        sorting.sort(emptyArray);

        Double[] oneArray = new Double[1];
        sorting.sort(oneArray);

        Double[] ascArray = new Double[length];
        for (int i = 0; i < length; i++) {
            ascArray[i] = i * 1.0;
        }
        sorting.sort(ascArray);
        pass = isSorted(ascArray) && pass;

        Double[] descArray = new Double[length];
        for (int i = 0; i < length; i++) {
            descArray[i] = (length - i) * 1.0;
        }
        sorting.sort(descArray);
        pass = isSorted(descArray) && pass;

        Double[] sameArray = new Double[length];
        for (int i = 0; i < length; i++) {
            sameArray[i] = 5.0;
        }
        sorting.sort(sameArray);
        pass = isSorted(sameArray) && pass;

        Double[] binaryArray = new Double[length];
        for (int i = 0; i < length; i++) {
            binaryArray[i] = i % 2 * 1.0;
        }
        sorting.sort(binaryArray);
        pass = isSorted(binaryArray) && pass;

        binaryArray = generate(Double.class, length, new DataMocker<Double>() {
            @Override
            public Double mock(int i) {
                if (i > 100) {
                    return 1.0;
                } else {
                    return 0d;
                }
            }
        });
        sorting.sort(binaryArray);
        pass = isSorted(binaryArray) && pass;

        return pass;
    }

    public static void main(String[] args) {
        assert test(new Quick());
    }
}
