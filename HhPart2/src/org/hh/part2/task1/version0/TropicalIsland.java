package org.hh.part2.task1.version0;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TropicalIsland {

    private static class Point {
        int x, y;

        Integer value;

        public Point(int x, int y, Integer value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            Point other = (Point) o;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            return this.x * 31 + this.y;
        }
    }

    public int getWaterVolume(Integer[][] island) {
        // TODO: разделить на части, немного упростить логику, попробовать оптимизировать
        Point[][] src = points(island);
        int res = 0;
        for (Integer level : levels(src)) {

        }
        return res;
    }



    /** Sorted(natural) levels */
    private Collection<Integer> levels(Point[][] src) {
        TreeSet<Integer> res = new TreeSet<>();
        for (Point[] points : src) {
            for (Point point : points) {
                res.add(point.value);
            }
        }
        //no need max level - remove
        res.remove(res.last());
        return res;
    }

    /** Create array[][] point based on integers */
    private Point[][] points(Integer[][] src) {
        Point[][] res = new Point[src.length][];
        for (int i = 0, il = res.length; i < il; i++) {
            res[i] = new Point[src[i].length];
            for (int j = 0, jl = res[i].length; j < jl; j++) {
                res[i][j] = new Point(i, j, src[i][j]);
            }
        }
        return res;
    }
}
