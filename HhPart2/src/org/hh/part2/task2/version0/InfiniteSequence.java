package org.hh.part2.task2.version0;

/*
Возьмём бесконечную цифровую последовательность,
образованную склеиванием последовательных положительных чисел:
S = 123456789101112131415...
Определите первое вхождение заданной последовательности A
в бесконечной последовательности S (нумерация начинается с 1).
Пример входных данных:
6789
111
Пример выходных данных:
6
12
 */

public class InfiniteSequence {

    public interface Sequence {
        /**
         * Get character in sequence by index
         */
        default char charAt(long index) {
            throw new RuntimeException("not supported");
        }
    }

    public static class SimpleSequence implements Sequence {

        private StringBuilder builder = new StringBuilder();

        public SimpleSequence(int maxValue) {
            for (int i = 1; i <= maxValue; i++) {
                this.builder.append(Integer.toString(i));
            }
        }

        @Override
        public char charAt(long index) {
            return this.builder.charAt((int) index);
        }
    }

    public static class CalculateSequence implements Sequence {

        private static final long[][] VALUES = {
                {9L, 1, 0L},
                {99L, 2, 0L},
                {999L, 3, 0L},
                {9_999L, 4, 0L},
                {99_999L, 5, 0L},
                {999_999L, 6, 0L},
                {9_999_999L, 7, 0L},
                {99_999_999L, 8, 0L},
                {999_999_999L, 9, 0L},
                {9_999_999_999L, 10, 0L},
                {99_999_999_999L, 11, 0L},
                {999_999_999_999L, 12, 0L},
                {9_999_999_999_999L, 13, 0L},
                {99_999_999_999_999L, 14, 0L},
                {999_999_999_999_999L, 15, 0L},
                {9_999_999_999_999_999L, 16, 0L},
                {99_999_999_999_999_999L, 17, 0L},
                {999_999_999_999_999_999L, 18, 0L},
                {Long.MAX_VALUE, 19, 0L},
        };

        private static String SINGLE_VALUE = "123456789";

        static {
            for (int i = 0, il = VALUES.length - 1; i < il; i++) {
                VALUES[i][2] = VALUES[i][0] * VALUES[i][1];
            }
        }

        @Override
        public char charAt(long index) {
            if (index < 9)
                return SINGLE_VALUE.charAt((int) index);
            long res = 0;
            int i = 0;
            for (; i < VALUES.length; i++) {
                if (index > VALUES[i][0]) {
                    index -= VALUES[i][2];
                } else {
                    res = index / VALUES[i][1];
                    //now it offset
                    index -= res * VALUES[i][1];
                    //cal res
                    res += VALUES[i - 1][0];
                    if (index > 0) res++;
                    break;
                }
            }
            if (index > 0)
                res /= Math.pow(10, VALUES[i][1] - index);
            return (char) ('0' + (char) (res % 10));
        }
    }

    private static final Sequence SEQUENCE = new SimpleSequence(7000);

    public long findSequence(String A) {
        int j = 0, jl = A.length();
        MAIN:
        for (long i = 0, il = Long.MAX_VALUE - A.length(); i < il; ) {
            if (A.charAt(j) == SEQUENCE.charAt(i)) {
                while (j < jl) {
                    if (A.charAt(j++) != SEQUENCE.charAt(i++)) {
                        j = 0;
                        continue MAIN;
                    } else if (i >= il) {
                        break MAIN;
                    }
                }
                return (i - jl) + 1;
            } else {
                i++;
            }
        }
        return 0L;
    }
}
