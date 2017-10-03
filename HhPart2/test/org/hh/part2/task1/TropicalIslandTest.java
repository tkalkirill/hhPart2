package org.hh.part2.task1;

import java.util.Arrays;
import java.util.Collection;
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
                new Integer[][] {
                        { 4, 5, 4 },
                        { 3, 1, 5 },
                        { 5, 4, 1 }
                        }, 2 },
            new Object[] { new Integer[][] {
                    { 5, 3, 4, 5 },
                    { 6, 2, 1, 4 },
                    { 3, 1, 1, 4 },
                    { 8, 5, 4, 3 }
                    }, 7 },
            new Object[] { new Integer[][] {
                    { 2, 2, 2 },
                    { 2, 1, 2 },
                    { 2, 1, 2 },
                    { 2, 1, 2 }
                    }, 0 },
            new Object[] { new Integer[][] {
                    {4,5,4},
                    {3,3,5},
                    {5,4,1},
                    }, 0 },
            new Object[] { new Integer[][] {
                    {5,3,4,5},
                    {6,2,3,4},
                    {3,3,3,4},
                    {8,5,4,3},
                    }, 1 },
            new Object[] { new Integer[][] {
                    {5,3,4,5,5,4},
                    {4,4,5,1,1,5},
                    {5,5,5,4,2,5},
                    {3,2,1,2,1,3},
                    {4,5,2,1,4,4},
                    {4,4,5,5,3,4},
                    }, 14 },
            new Object[] { new Integer[][] {
                    {5,5,4,5,5,4},
                    {5,5,5,3,3,5},
                    {5,5,5,4,3,5},
                    {3,3,3,3,3,3},
                    {4,5,3,3,4,4},
                    {4,4,5,5,3,4},
            }, 0 }
        );
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
}
