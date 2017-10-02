package org.hh.part2.task1;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import org.hh.part2.task1.version0.TropicalIsland;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TropicalIslandTest {

    private Integer[][] island;

    private int res;

    public TropicalIslandTest(Integer[][] island, int res) {
        this.island = island;
        this.res = res;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(
            new Object[] {
                new Integer[][] { { 4, 5, 4 }, { 3, 1, 5 }, { 5, 4, 1 } }, 2 },
            new Object[] { new Integer[][] { { 5, 3, 4, 5 }, { 6, 2, 1, 4 },
                { 3, 1, 1, 4 }, { 8, 5, 4, 3 } }, 7 },
            new Object[] { new Integer[][] { { 2, 2, 2 }, { 2, 1, 2 },
                { 2, 1, 2 }, { 2, 1, 2 } }, 0 });
    }

    private static TropicalIsland tropicalIsland;

    @BeforeClass
    public static void beforeClass() {
        tropicalIsland = new TropicalIsland();
    }

    @Test
    public void test() {
        Assert.assertEquals(this.res,
            tropicalIsland.getWaterVolume(this.island));
    }

    @Test
    public void test1() {
        PriorityQueue<Integer> integers = new PriorityQueue<>();
        for (int i = 0; i < 200; i++) {
            integers.add(i);
        }
    }
}
