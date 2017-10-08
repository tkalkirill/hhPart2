package org.hh.part2.task2;

import org.hh.part2.task2.version0.InfiniteSequence;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SequenceTest {

    private static InfiniteSequence.Sequence simple;

    private static InfiniteSequence.Sequence calculate;

    private static int maxValue;

    @BeforeClass
    public static void beforeClass() {
        maxValue = 7000;
        simple = new InfiniteSequence.SimpleSequence(maxValue);
        calculate = new InfiniteSequence.CalculateSequence();
    }

    @Test
    public void test() {
//        for (long i = 0, il = (long) maxValue; i <= il; i++) {
//            Assert.assertEquals(simple.charAt(i), calculate.charAt(i));
//        }
        for (long i : new long[]{5, 6, 7, 17, 18, 19, 777, 778, 779}) {
            Assert.assertEquals(simple.charAt(i), calculate.charAt(i));
        }
    }

}
