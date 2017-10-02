package org.hh.part2;

import org.hh.part2.version1.TropicalIsland;

public class App {
    public static void main(String[] args) throws Throwable {
        task1();
    }

    private static void task1() throws Throwable {
        TropicalIsland tropicalIsland = new TropicalIsland();
        System.out.println("RESULT=" + tropicalIsland.getWaterVolume(new Integer[][] {
                {4,5,4},
                {3,1,5},
                {5,4,1},
        }));
        System.out.println("RESULT=" + tropicalIsland.getWaterVolume(new Integer[][] {
                {4,5,4},
                {3,3,5},
                {5,4,1},
        }));
        System.out.println("RESULT=" + tropicalIsland.getWaterVolume(new Integer[][] {
                {5,3,4,5},
                {6,2,1,4},
                {3,1,1,4},
                {8,5,4,3},
        }));
        System.out.println("RESULT=" + tropicalIsland.getWaterVolume(new Integer[][] {
                {5,3,4,5},
                {6,2,3,4},
                {3,3,3,4},
                {8,5,4,3},
        }));
        System.out.println("RESULT=" + tropicalIsland.getWaterVolume(new Integer[][] {
                {5,3,4,5,5,4},
                {4,4,5,1,1,5},
                {5,5,5,4,2,5},
                {3,2,1,2,1,3},
                {4,5,2,1,4,4},
                {4,4,5,5,3,4},

        }));
        System.out.println("RESULT=" + tropicalIsland.getWaterVolume(new Integer[][] {
                {5,5,4,5,5,4},
                {5,5,5,3,3,5},
                {5,5,5,4,3,5},
                {3,3,3,3,3,3},
                {4,5,3,3,4,4},
                {4,4,5,5,3,4},
        }));
    }
}
