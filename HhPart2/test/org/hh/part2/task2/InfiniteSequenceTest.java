package org.hh.part2.task2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.hh.part2.task2.version0.InfiniteSequence;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class InfiniteSequenceTest {

    private static InfiniteSequence infiniteSequence;

    @BeforeClass
    public static void beforeClass() {
        infiniteSequence = new InfiniteSequence();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[]{"6789", 6},
                new Object[]{"111", 12});
    }

    private String seq;

    private long res;

    public InfiniteSequenceTest(String seq, long res) {
        this.seq = seq;
        this.res = res;
    }

    @Test
    public void findSequenceTest() throws Exception {
        Assert.assertEquals(this.res, infiniteSequence.findSequence(this.seq));
    }
}
