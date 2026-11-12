package runner;

import java.util.*;

/**
 * Universal Test Harness & Assertion Engine for TUF Practice.
 */
public class TestHarness {

    // ANSI Colors for terminal output
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    private final String problemTitle;
    private int totalTests = 0;
    private int passedTests = 0;
    private int failedTests = 0;
    private long totalDurationNanos = 0;

    public TestHarness(String problemTitle) {
        this.problemTitle = problemTitle;
        System.out.println(BOLD + CYAN + "============================================================" + RESET);
        System.out.println(BOLD + CYAN + "  TUF Test Suite: " + problemTitle + RESET);
        System.out.println(BOLD + CYAN + "============================================================" + RESET);
    }

    /**
     * Executes a single test case.
     */
    public <T> void test(String testName, Object inputDescription, T expected, java.util.function.Supplier<T> testFn) {
        totalTests++;
        long startTime = System.nanoTime();
        T actual = null;
        Throwable error = null;

        try {
            actual = testFn.get();
        } catch (Throwable t) {
            error = t;
        }
        long durationNanos = System.nanoTime() - startTime;
        totalDurationNanos += durationNanos;
        double durationMs = durationNanos / 1_000_000.0;

        boolean passed = (error == null) && areEqual(expected, actual);

        if (passed) {
            passedTests++;
            System.out.printf("%s[PASS]%s %s%-25s%s (%.2f ms)%n",
                    GREEN + BOLD, RESET, BOLD, testName, RESET, durationMs);
        } else {
            failedTests++;
            System.out.printf("%s[FAIL]%s %s%-25s%s (%.2f ms)%n",
                    RED + BOLD, RESET, BOLD, testName, RESET, durationMs);
            System.out.println(YELLOW + "  Input:    " + RESET + inputDescription);
            System.out.println(GREEN +  "  Expected: " + RESET + formatValue(expected));
            if (error != null) {
                System.out.println(RED + "  Actual:   RUNTIME EXCEPTION: " + error.getClass().getName() + ": " + error.getMessage() + RESET);
                error.printStackTrace(System.out);
            } else {
                System.out.println(RED + "  Actual:   " + RESET + formatValue(actual));
            }
            System.out.println();
        }
    }

    /**
     * Executes an order-agnostic test (e.g. lists of lists where sublist or element ordering can differ).
     */
    public <T extends Collection<?>> void testUnordered(String testName, Object inputDescription, T expected, java.util.function.Supplier<T> testFn) {
        totalTests++;
        long startTime = System.nanoTime();
        T actual = null;
        Throwable error = null;

        try {
            actual = testFn.get();
        } catch (Throwable t) {
            error = t;
        }
        long durationNanos = System.nanoTime() - startTime;
        totalDurationNanos += durationNanos;
        double durationMs = durationNanos / 1_000_000.0;

        boolean passed = (error == null) && areUnorderedEqual(expected, actual);

        if (passed) {
            passedTests++;
            System.out.printf("%s[PASS]%s %s%-25s%s (%.2f ms)%n",
                    GREEN + BOLD, RESET, BOLD, testName, RESET, durationMs);
        } else {
            failedTests++;
            System.out.printf("%s[FAIL]%s %s%-25s%s (%.2f ms)%n",
                    RED + BOLD, RESET, BOLD, testName, RESET, durationMs);
            System.out.println(YELLOW + "  Input:    " + RESET + inputDescription);
            System.out.println(GREEN +  "  Expected: " + RESET + formatValue(expected));
            if (error != null) {
                System.out.println(RED + "  Actual:   RUNTIME EXCEPTION: " + error.getClass().getName() + ": " + error.getMessage() + RESET);
                error.printStackTrace(System.out);
            } else {
                System.out.println(RED + "  Actual:   " + RESET + formatValue(actual));
            }
            System.out.println();
        }
    }

    /**
     * Prints final scorecard and exits with appropriate exit code.
     */
    public void summarize() {
        System.out.println(BOLD + CYAN + "------------------------------------------------------------" + RESET);
        double totalMs = totalDurationNanos / 1_000_000.0;
        if (failedTests == 0) {
            System.out.printf("%s%sALL %d TESTS PASSED!%s in %.2f ms%n",
                    BOLD, GREEN, totalTests, RESET, totalMs);
            System.out.println(BOLD + CYAN + "============================================================" + RESET);
        } else {
            System.out.printf("%s%sTESTS FAILED: %d / %d passed, %d failed%s in %.2f ms%n",
                    BOLD, RED, passedTests, totalTests, failedTests, RESET, totalMs);
            System.out.println(BOLD + CYAN + "============================================================" + RESET);
            System.exit(1);
        }
    }

    // ==========================================
    // EQUALITY & FORMATTING HELPERS
    // ==========================================

    @SuppressWarnings("unchecked")
    public static boolean areEqual(Object o1, Object o2) {
        if (o1 == o2) return true;
        if (o1 == null || o2 == null) return false;

        // Custom DSA types
        if (o1 instanceof ListNode && o2 instanceof ListNode) {
            return ListNode.equals((ListNode) o1, (ListNode) o2);
        }
        if (o1 instanceof TreeNode && o2 instanceof TreeNode) {
            return TreeNode.equals((TreeNode) o1, (TreeNode) o2);
        }

        // Arrays
        if (o1 instanceof int[] && o2 instanceof int[]) {
            return Arrays.equals((int[]) o1, (int[]) o2);
        }
        if (o1 instanceof long[] && o2 instanceof long[]) {
            return Arrays.equals((long[]) o1, (long[]) o2);
        }
        if (o1 instanceof double[] && o2 instanceof double[]) {
            return Arrays.equals((double[]) o1, (double[]) o2);
        }
        if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
            return Arrays.equals((boolean[]) o1, (boolean[]) o2);
        }
        if (o1 instanceof char[] && o2 instanceof char[]) {
            return Arrays.equals((char[]) o1, (char[]) o2);
        }
        if (o1 instanceof Object[] && o2 instanceof Object[]) {
            return Arrays.deepEquals((Object[]) o1, (Object[]) o2);
        }

        // 2D primitive arrays
        if (o1 instanceof int[][] && o2 instanceof int[][]) {
            int[][] a = (int[][]) o1;
            int[][] b = (int[][]) o2;
            if (a.length != b.length) return false;
            for (int i = 0; i < a.length; i++) {
                if (!Arrays.equals(a[i], b[i])) return false;
            }
            return true;
        }

        // Double comparison with tolerance
        if (o1 instanceof Double && o2 instanceof Double) {
            return Math.abs((Double) o1 - (Double) o2) < 1e-5;
        }

        // Default Object.equals
        return Objects.equals(o1, o2);
    }

    public static boolean areUnorderedEqual(Collection<?> c1, Collection<?> c2) {
        if (c1 == c2) return true;
        if (c1 == null || c2 == null) return false;
        if (c1.size() != c2.size()) return false;

        List<?> list1 = new ArrayList<>(c1);
        List<?> list2 = new ArrayList<>(c2);

        // Convert elements to comparable string representations for ordering
        List<String> s1 = new ArrayList<>();
        List<String> s2 = new ArrayList<>();
        for (Object item : list1) s1.add(formatValue(item));
        for (Object item : list2) s2.add(formatValue(item));
        Collections.sort(s1);
        Collections.sort(s2);

        return s1.equals(s2);
    }

    public static String formatValue(Object obj) {
        if (obj == null) return "null";
        if (obj instanceof int[]) return Arrays.toString((int[]) obj);
        if (obj instanceof long[]) return Arrays.toString((long[]) obj);
        if (obj instanceof double[]) return Arrays.toString((double[]) obj);
        if (obj instanceof boolean[]) return Arrays.toString((boolean[]) obj);
        if (obj instanceof char[]) return Arrays.toString((char[]) obj);
        if (obj instanceof Object[]) return Arrays.deepToString((Object[]) obj);
        if (obj instanceof int[][]) {
            StringBuilder sb = new StringBuilder("[");
            int[][] matrix = (int[][]) obj;
            for (int i = 0; i < matrix.length; i++) {
                sb.append(Arrays.toString(matrix[i]));
                if (i < matrix.length - 1) sb.append(", ");
            }
            sb.append("]");
            return sb.toString();
        }
        if (obj instanceof String) {
            String s = (String) obj;
            String[] lines = s.split("\n");
            if (lines.length > 8) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 3; i++) sb.append(lines[i]).append("\n");
                sb.append("... [").append(lines.length - 6).append(" lines omitted] ...\n");
                for (int i = lines.length - 3; i < lines.length; i++) {
                    sb.append(lines[i]);
                    if (i < lines.length - 1) sb.append("\n");
                }
                return sb.toString();
            }
            return s;
        }
        return obj.toString();
    }
}
