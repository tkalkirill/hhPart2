package org.hh.part2.version1;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

public class TropicalIsland {

    private static class Point {
        int x;

        int y;

        Integer value;

        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            Point other = (Point) o;
            if (this.x != other.x)
                return false;
            return this.y == other.y;
        }

        @Override
        public int hashCode() {
            return 31 * this.x + this.y;
        }
    }

    public int getWaterVolume(Integer[][] island) {
        Set<Integer> levels = levels(island);
        Point[][] src = clone(island);
        Point[][] byLevel = clone(island);
        int res = 0;
        for (Integer level : levels) {
            fillByLevel(src, clean(byLevel), level);
            clearByBorder(src, byLevel);
            res += calculateWater(src, byLevel);
        }
        return res;
    }

    private int calculateWater(Point[][] src, Point[][] byLevel) {
        int res = 0;
        for (int i = 1, il = byLevel.length - 1; i < il; i++) {
            for (int j = 1, jl = byLevel[i].length - 1; j < jl; j++) {
                if (byLevel[i][j].value.intValue() > 0) {
                    Set<Point> area = area(byLevel, byLevel[i][j]);
                    int min = min(src, area);
                    if (min > 0) {
                        res += area.size()
                            * (min - byLevel[i][j].value.intValue());
                        fill(src, area, min);
                    }
                    clean(area);
                }
            }
        }
        return res;
    }

    private int min(Point[][] src, Set<Point> points) {
        int min = Integer.MAX_VALUE;
        for (Point point : points) {
            min = min(point(src, point.x - 1, point.y), min,
                point.value.intValue());//up
            min = min(point(src, point.x, point.y + 1), min,
                point.value.intValue());//right
            min = min(point(src, point.x + 1, point.y), min,
                point.value.intValue());//down
            min = min(point(src, point.x, point.y - 1), min,
                point.value.intValue());//left
            if (min == 0)
                break;
        }
        return min;
    }

    private int min(Point point, int min, int value) {
        if (point == null || point.value.intValue() == value)
            return min;
        return point.value.intValue() < value ? 0
            : Math.min(min, point.value.intValue());
    }

    private void clearByBorder(Point[][] src, Point[][] byLevel) {
        //by horizontal
        int i, il, j, jl;
        Integer ZERO = 0;
        for (i = 0, il = byLevel.length; i < il; i += byLevel.length - 1) {
            for (j = 0, jl = byLevel[i].length; j < jl; j++) {
                if (byLevel[i][j].value > 0) {
                    clean(area(byLevel, byLevel[i][j]));
                }
            }
        }
        //by vertical
        for (i = 0, il = byLevel.length; i < il; i++) {
            for (j = 0, jl = byLevel[i].length; j < jl; j +=
                byLevel[i].length - 1) {
                if (byLevel[i][j].value > 0) {
                    clean(area(byLevel, byLevel[i][j]));
                }
            }
        }
    }

    private Set<Point> area(Point[][] field, Point start) {
        Set<Point> res = new HashSet<>();
        res.add(start);
        Point current;
        while ((current = next(res)) != null) {
            addPoint(res, point(field, current.x - 1, current.y), current);//up
            addPoint(res, point(field, current.x, current.y + 1), current);//right
            addPoint(res, point(field, current.x + 1, current.y), current);//down
            addPoint(res, point(field, current.x, current.y - 1), current);//left
            current.value = -current.value;
        }
        for (Point res1 : res) {
            res1.value = -res1.value;
        }
        return res;
    }

    private void addPoint(Set<Point> points, Point newPoint, Point current) {
        if (newPoint == null
            || newPoint.value.intValue() != current.value.intValue()
            || points.contains(newPoint))
            return;
        points.add(newPoint);
    }

    private Point point(Point[][] field, int i, int j) {
        return i < 0 || j < 0 || i == field.length || j == field[i].length//beyond the border
            ? null : field[i][j];
    }

    private Point next(Set<Point> points) {
        for (Point point : points) {
            if (point.value > 0)
                return point;
        }
        return null;
    }

    private void fillByLevel(Point[][] src, Point[][] byLevel, Integer level) {
        int levelI = level.intValue();
        for (int i = 0, il = src.length; i < il; i++) {
            for (int j = 0, jl = src[i].length; j < jl; j++) {
                Integer value = src[i][j].value;
                if (value.intValue() == levelI)
                    byLevel[i][j].value = value;
            }
        }
    }

    private void clean(Set<Point> points) {
        Integer ZERO = 0;
        for (Point point : points) {
            point.value = ZERO;
        }
    }

    private void fill(Point[][] src, Set<Point> points, Integer value) {
        for (Point point : points) {
            src[point.x][point.y].value = value;
        }
    }

    private Point[][] clean(Point[][] src) {
        Integer ZERO = 0;
        for (int i = 0, il = src.length; i < il; i++) {
            for (int j = 0, jl = src[i].length; j < jl; j++) {
                src[i][j].value = ZERO;
            }
        }
        return src;
    }

    private Point[][] clone(Integer[][] src) {
        Point[][] res = new Point[src.length][];
        for (int i = 0, il = src.length; i < il; i++) {
            res[i] = new Point[src[i].length];
            for (int j = 0, jl = src[i].length; j < jl; j++) {
                res[i][j] = new Point(i, j, src[i][j]);
            }
        }
        return res;
    }

    private Set<Integer> levels(Integer[][] island) {
        Set<Integer> levels = new TreeSet<>();
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                levels.add(island[i][j]);
            }
        }
        return levels;
    }

    // TODO: удалить перед сдачей
    private String printMatrix(Point[][] src) {
        StringJoiner rowJoiner = new StringJoiner(System.lineSeparator());
        String lineSeparator = System.lineSeparator();
        for (Point[] integers : src) {
            StringJoiner columnJoiner = new StringJoiner(" | ");
            for (Point p : integers) {
                columnJoiner.add(p.value.toString());
            }
            rowJoiner.add(columnJoiner.toString());
        }
        return rowJoiner.toString();
    }
}
