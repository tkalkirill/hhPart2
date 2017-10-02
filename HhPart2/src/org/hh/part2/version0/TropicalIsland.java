package org.hh.part2.version0;

import java.util.Arrays;
import java.util.StringJoiner;

public class TropicalIsland {

    private class Lowland {
        Integer[][] lowland;

        boolean hasLowland;

        boolean hasBadLowland;

        public Lowland(Integer[][] lowland) {
            this.lowland = lowland;
        }
    }

    public int getWaterVolume(Integer[][] island) {
        //prepare
        Integer[][] src = clone(island);
        Lowland lowland = new Lowland(clone(island));
        int res = 0;
        //logic
        while (true) {
            clearLowland(lowland);
            fillLowland(src, lowland);
            checkLowland(lowland);
            if (lowland.hasBadLowland) {
                correctLowland(src, lowland);
            }
            printMatrix(lowland.lowland);
            // TODO: убрать
            lowland.hasLowland = false;
            if (!lowland.hasLowland)
                break;
            res += calulateWaterLowland(src, lowland);
        }
        return res;
    }

    private int calulateWaterLowland(Integer[][] src, Lowland lowland) {
        // TODO:  
        return 0;
    }

    private void correctLowland(Integer[][] src, Lowland lowland) {
        do {
            //find bad lowland and clear
            for (int i = 1; i < src.length - 1; i++) {
                for (int j = 1; j < src[i].length - 1; j++) {
                    if (lowland.lowland[i][j] < 0) {
                    }
                }
            }
            checkLowland(lowland);
        } while (lowland.hasBadLowland);
    }

    private void correctLowlandPoint(Lowland lowland, int i, int j) {

    }

    private void fillLowland(Integer[][] src, Lowland lowland) {
        for (int i = 1, il = src.length - 1; i < il; i++) {
            for (int j = 1, jl = src[i].length - 1; j < jl; j++) {
                fillLowlandPoint(src, lowland, src[i][j], i, j);
            }
        }
    }

    private void fillLowlandPoint(Integer[][] src, Lowland lowland,
            int srcValue, int i, int j) {
        int point = point(src, lowland, srcValue, i - 1, j) //north
            + point(src, lowland, srcValue, i, j + 1) //east
            + point(src, lowland, srcValue, i + 1, j) //south
            + point(src, lowland, srcValue, i, j - 1); //west
        if (point > 2)
            lowland.lowland[i][j] = srcValue;
        else if (point < -15)
            lowland.lowland[i][j] = -srcValue;
        else
            lowland.lowland[i][j] = 0;
    }

    private int point(Integer[][] src, Lowland lowland, int srcValue, int i,
            int j) {
        int value = src[i][j];
        if (value > srcValue)
            return 1;
        boolean nearBorder = i == 0 || j == 0 || i == lowland.lowland.length
            || j == lowland.lowland[i].length;
        if (value < srcValue)
            return nearBorder ? -20 : 0;
        return nearBorder ? -20 : lowland.lowland[i][j] > 0 ? 1 : 0;
    }

    private void checkLowland(Lowland lowland) {
        int value;
        for (int i = 1, il = lowland.lowland.length - 1; i < il; i++) {
            for (int j = 1, jl = lowland.lowland[i].length - 1; j < jl; j++) {
                value = lowland.lowland[i][j];
                if (value > 0)
                    lowland.hasLowland = true;
                else if (value < 0)
                    lowland.hasBadLowland = true;
                if (lowland.hasLowland && lowland.hasBadLowland)
                    return;
            }
        }
    }

    private void clearLowland(Lowland lowland) {
        clear(lowland.lowland);
        lowland.hasLowland = false;
        lowland.hasBadLowland = false;
    }

    private Integer[][] clone(Integer[][] src) {
        Integer[][] res = new Integer[src.length][];
        for (int i = 0; i < res.length; i++) {
            res[i] = Arrays.copyOf(src[i], src[i].length);
        }
        return res;
    }

    private void clear(Integer[][] src) {
        for (Integer[] integers : src) {
            Arrays.fill(integers, 0);
        }
    }

    // TODO: удалить перед сдачей 
    private void printMatrix(Integer[][] src) {
        StringJoiner rowJoiner = new StringJoiner(System.lineSeparator());
        String lineSeparator = System.lineSeparator();
        for (Integer[] integers : src) {
            StringJoiner columnJoiner = new StringJoiner(" | ");
            for (Integer integer : integers) {
                columnJoiner.add(integer.toString());
            }
            rowJoiner.add(columnJoiner.toString());
        }
        System.out.println(rowJoiner);
    }
}
