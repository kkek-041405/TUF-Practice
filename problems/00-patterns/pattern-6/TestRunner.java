import runner.TestHarness;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    private static String captureOutput(Solution sol, int n) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        PrintStream customOut = new PrintStream(baos, true, StandardCharsets.UTF_8);

        try {
            System.setOut(customOut);
            sol.pattern6(n);
        } finally {
            System.setOut(originalOut);
        }

        return baos.toString(StandardCharsets.UTF_8);
    }

    private static String generateExpected(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                sb.append(j);
                if (j < i) sb.append(" ");
            }
            if (i > 1) sb.append("\n");
        }
        return sb.toString();
    }

    private static String normalizeOutput(String raw) {
        if (raw == null) return "";
        String[] lines = raw.replace("\r\n", "\n").replace("\r", "\n").split("\n");
        List<String> cleaned = new ArrayList<>();
        for (String line : lines) {
            String trimmed = line.trim();
            if (!trimmed.isEmpty()) {
                // If user printed numbers without space (e.g. "1234"), normalize single-digit runs
                if (!trimmed.contains(" ") && trimmed.matches("\\d+")) {
                    StringBuilder spaced = new StringBuilder();
                    for (int k = 0; k < trimmed.length(); k++) {
                        spaced.append(trimmed.charAt(k));
                        if (k < trimmed.length() - 1) spaced.append(" ");
                    }
                    trimmed = spaced.toString();
                } else {
                    trimmed = trimmed.replaceAll("\\s+", " ");
                }
                cleaned.add(trimmed);
            }
        }
        return String.join("\n", cleaned);
    }

    public static void main(String[] args) {
        TestHarness harness = new TestHarness("Pattern 6 - Inverted Number Pyramid");
        Solution sol = new Solution();

        // Runner wrapper
        java.util.function.Function<Integer, String> run = (n) -> {
            String raw = captureOutput(sol, n);
            return normalizeOutput(raw);
        };

        // --- Tier 1: Sample Cases ---
        harness.test("Sample 1 (n=4)", "n = 4", generateExpected(4), () -> run.apply(4));
        harness.test("Sample 2 (n=2)", "n = 2", generateExpected(2), () -> run.apply(2));
        harness.test("Sample 3 (n=5)", "n = 5", generateExpected(5), () -> run.apply(5));

        // --- Tier 2: Boundary Cases ---
        harness.test("Min Bound (n=1)", "n = 1", generateExpected(1), () -> run.apply(1));
        harness.test("Small (n=3)", "n = 3", generateExpected(3), () -> run.apply(3));

        // --- Tier 3: Corner & Trick Cases ---
        harness.test("Odd Size (n=7)", "n = 7", generateExpected(7), () -> run.apply(7));
        harness.test("Multi-Digit (n=10)", "n = 10", generateExpected(10), () -> run.apply(10));

        // --- Tier 4: Stress / Max Bounds ---
        harness.test("Medium-Large (n=25)", "n = 25", generateExpected(25), () -> run.apply(25));
        harness.test("Max Bound (n=100)", "n = 100", generateExpected(100), () -> run.apply(100));

        harness.summarize();
    }
}
