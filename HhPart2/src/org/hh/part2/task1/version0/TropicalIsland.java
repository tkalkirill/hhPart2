package org.hh.part2.task1.version0;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
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
        Point[][] field = createPoints(island);
        int res = 0;
        for (Integer level : levels(field)) {
            clearByBorder(field, level);
            res += water(field, level);
        }
        return res;
    }

    /** Calculate water by level */
    private int water(Point[][] field, Integer level) {
        Point point;
        int res = 0;
        Integer ZERO = 0, min;
        Collection<Point> space;
        for (int i = 1, il = field.length - 1; i < il; i++) {
            for (int j = 1, jl = field[i].length - 1; j < jl; j++) {
                point = field[i][j];
                if (point.value.equals(level)) {
                    space = space(field, point);
                    min = min(field, space);
                    if (min.equals(ZERO)) {
                        setValue(space, -level);
                    } else {
                        res += space.size() * (min - level);
                        setValue(space, min);
                    }
                }
            }
        }
        return res;
    }

    /** Get min point value near space */
    private Integer min(Point[][] field, Collection<Point> space) {
        Integer min = Integer.MAX_VALUE, ZERO = 0;
        for (Point spacePoint : space) {
            min = Math.min(min,
                min(spacePoint, field[spacePoint.x - 1][spacePoint.y]));//up
            min = Math.min(min,
                min(spacePoint, field[spacePoint.x][spacePoint.y + 1]));//right
            min = Math.min(min,
                min(spacePoint, field[spacePoint.x + 1][spacePoint.y]));//down
            min = Math.min(min,
                min(spacePoint, field[spacePoint.x][spacePoint.y - 1]));//left
            if (min.equals(ZERO))
                break;
        }
        return min;
    }

    private Integer min(Point point, Point nextPoint) {
        switch (point.value.compareTo(nextPoint.value)) {
            case 1:
                return 0;
            case -1:
                return nextPoint.value;
            default:
                return Integer.MAX_VALUE;
        }
    }

    /** Clear lowlend that touch border */
    private void clearByBorder(Point[][] field, Integer level) {
        //by horizontal
        for (int i = 0, il = field.length; i < il; i += field.length - 1) {
            for (int j = 0, jl = field[i].length; j < jl; j++) {
                clearByPoint(field, field[i][j], level);
            }
        }
        //by vertical
        for (int i = 0, il = field.length; i < il; i++) {
            for (int j = 0, jl = field[i].length; j < jl; j +=
                field[i].length - 1) {
                clearByPoint(field, field[i][j], level);
            }
        }
    }

    /**
     * Cleat cpace start from border, for
     * {@link #clearByBorder(Point[][], Integer)}
     */
    private void clearByPoint(Point[][] field, Point point, Integer level) {
        if (!point.value.equals(level))
            return;
        setValue(space(field, point), -level);
    }

    /** Set value for createPoints(all) */
    private void setValue(Collection<Point> points, Integer value) {
        for (Point point : points)
            point.value = value;
    }

    /** Get space of point by start point */
    private Collection<Point> space(Point[][] field, Point start) {
        Set<Point> all = new HashSet<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        Point current;
        while ((current = queue.poll()) != null) {
            addPointToSpace(point(field, current.x - 1, current.y), current,
                all, queue);//up
            addPointToSpace(point(field, current.x, current.y + 1), current,
                all, queue);//right
            addPointToSpace(point(field, current.x + 1, current.y), current,
                all, queue);//down
            addPointToSpace(point(field, current.x, current.y - 1), current,
                all, queue);//left
            all.add(current);
        }
        return all;
    }

    /** App newPoint for {@link #space(Point[][], Point)} */
    private void addPointToSpace(Point newPoint, Point current, Set<Point> all,
            Queue<Point> queue) {
        if (newPoint == null || all.contains(newPoint)
            || !newPoint.value.equals(current.value))
            return;
        queue.add(newPoint);
    }

    /** Get point from field(with check borders) */
    private Point point(Point[][] field, int i, int j) {
        return i < 0 || j < 0 || i >= field.length || j >= field[i].length//beyond borders 
            ? null : field[i][j];
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
    private Point[][] createPoints(Integer[][] src) {
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
