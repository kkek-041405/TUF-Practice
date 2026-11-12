import runner.TestHarness;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRunner {

    private static String captureOutput(Solution sol, int n) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        PrintStream customOut = new PrintStream(baos, true, StandardCharsets.UTF_8);

        try {
            System.setOut(customOut);
            sol.pattern1(n);
        } finally {
            System.setOut(originalOut);
        }

        return baos.toString(StandardCharsets.UTF_8);
    }

    private static String generateExpected(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append("*");
                if (j < n - 1) sb.append(" ");
            }
            if (i < n - 1) sb.append("\n");
        }
        return sb.toString();
    }

    private static String normalizeOutput(String raw) {
        if (raw == null) return "";
        String[] lines = raw.replace("\r\n", "\n").replace("\r", "\n").split("\n");
        List<String> cleaned = new ArrayList<>();
        for (String line : lines) {
            String trimmed = line.trim();
            // Accept both "* * *" and "****" by standardizing spacing
            if (!trimmed.isEmpty()) {
                // If user printed without space, insert spaces for standard comparison
                if (trimmed.equals("*".repeat(trimmed.length()))) {
                    trimmed = String.join(" ", trimmed.split(""));
                }
                cleaned.add(trimmed);
            }
        }
        return String.join("\n", cleaned);
    }

    public static void main(String[] args) {
        TestHarness harness = new TestHarness("Pattern 1 - Rectangular Star Pattern");
        Solution sol = new Solution();

        // Runner wrapper
        java.util.function.Function<Integer, String> run = (n) -> {
            String raw = captureOutput(sol, n);
            return normalizeOutput(raw);
        };

        // --- Tier 1: Sample Cases ---
        harness.test("Sample 1 (n=4)", "n = 4", generateExpected(4), () -> run.apply(4));
        harness.test("Sample 2 (n=2)", "n = 2", generateExpected(2), () -> run.apply(2));
        harness.test("Sample 3 (n=3)", "n = 3", generateExpected(3), () -> run.apply(3));

        // --- Tier 2: Boundary Cases ---
        harness.test("Min Bound (n=1)", "n = 1", generateExpected(1), () -> run.apply(1));
        harness.test("Intermediate (n=5)", "n = 5", generateExpected(5), () -> run.apply(5));

        // --- Tier 3: Corner & Trick Cases ---
        harness.test("Odd Size (n=7)", "n = 7", generateExpected(7), () -> run.apply(7));
        harness.test("Even Size (n=10)", "n = 10", generateExpected(10), () -> run.apply(10));

        // --- Tier 4: Stress / Max Bounds ---
        harness.test("Medium-Large (n=25)", "n = 25", generateExpected(25), () -> run.apply(25));
        harness.test("Max Bound (n=100)", "n = 100", generateExpected(100), () -> run.apply(100));

        harness.summarize();
    }
}
