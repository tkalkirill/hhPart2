package org.hh.part2.task2.version0;

public class InfiniteSequence {

    public interface Sequence {
        /**
         * Get character in sequence by index
         */
        default char charAt(long index) {
            throw new RuntimeException("not supported");
        }
    }

    /** A lot of memory is needed. Only for max_lenght_sequence({@link Integer#MAX_VALUE} - 2) */
    public static class SimpleSequence implements Sequence {

        private StringBuilder builder = new StringBuilder();

        /**Have to maxValue < {@link Integer#MAX_VALUE} - 2)*/
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

    /** Base on math */
    public static class CalculateSequence implements Sequence {

        private static final long[][] VALUES = {
                { 9L, 1, 9L },
                { 99L, 2, 0L },
                { 999L, 3, 0L },
                { 9_999L, 4, 0L },
                { 99_999L, 5, 0L },
                { 999_999L, 6, 0L },
                { 9_999_999L, 7, 0L },
                { 99_999_999L, 8, 0L },
                { 999_999_999L, 9, 0L },
                { 9_999_999_999L, 10, 0L },
                { 99_999_999_999L, 11, 0L },
                { 999_999_999_999L, 12, 0L },
                { 9_999_999_999_999L, 13, 0L },
                { 99_999_999_999_999L, 14, 0L },
                { 999_999_999_999_999L, 15, 0L },
                { 9_999_999_999_999_999L, 16, 0L },
                { 99_999_999_999_999_999L, 17, 0L },
                { 999_999_999_999_999_999L, 18, 0L },
                { Long.MAX_VALUE, 19, 0L }
        };

        private static final char[] SINGLE_VALUE = "123456789".toCharArray();

        static {
            for (int i = 1, j = 0, il = VALUES.length - 1; i < il; i++) {
                VALUES[i][2] = (VALUES[i][0] - VALUES[i - 1][0]) * VALUES[i][1];
            }
        }

        @Override
        public char charAt(long index) {
            if (index < SINGLE_VALUE.length)
                return SINGLE_VALUE[(int) index];
            index++;
            long res = 0;
            int i = 0, il = VALUES.length;
            for (; i < il; i++) {
                if (index > VALUES[i][2]) {
                    index -= VALUES[i][2];
                } else {
                    res = index / VALUES[i][1];
                    //now it offset
                    index -= res * VALUES[i][1];
                    //cal res
                    res += VALUES[i - 1][0];
                    if (index > 0)
                        res++;
                    break;
                }
            }
            if (index > 0)
                res /= Math.pow(10, VALUES[i][1] - index);
            return (char) ('0' + (char) (res % 10));
        }
    }

    private static final Sequence SEQUENCE = new CalculateSequence();

    public long findSequence(String A) {
        int j = 0, jl = A.length();
        MAIN: for (long i = 0, il = Long.MAX_VALUE - A.length(); i < il;) {
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
