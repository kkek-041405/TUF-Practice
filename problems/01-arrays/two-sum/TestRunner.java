import runner.TestHarness;
import java.util.Arrays;

public class TestRunner {
    public static void main(String[] args) {
        TestHarness harness = new TestHarness("Two Sum");
        Solution sol = new Solution();

        // Helper wrapper to sort result since order can be arbitrary
        java.util.function.BiFunction<int[], Integer, int[]> run = (nums, target) -> {
            int[] res = sol.twoSum(nums, target);
            if (res != null && res.length == 2) {
                int[] copy = Arrays.copyOf(res, 2);
                Arrays.sort(copy);
                return copy;
            }
            return res;
        };

        // --- Tier 1: Sample Cases ---
        harness.test("Sample 1", "nums = [2,7,11,15], target = 9", new int[]{0, 1},
                () -> run.apply(new int[]{2, 7, 11, 15}, 9));

        harness.test("Sample 2", "nums = [3,2,4], target = 6", new int[]{1, 2},
                () -> run.apply(new int[]{3, 2, 4}, 6));

        harness.test("Sample 3", "nums = [3,3], target = 6", new int[]{0, 1},
                () -> run.apply(new int[]{3, 3}, 6));

        // --- Tier 2: Boundary & Edge Cases ---
        harness.test("Min Length Array (N=2)", "nums = [-10, 20], target = 10", new int[]{0, 1},
                () -> run.apply(new int[]{-10, 20}, 10));

        harness.test("Negative Numbers", "nums = [-3, -4, -1, -5], target = -8", new int[]{0, 3},
                () -> run.apply(new int[]{-3, -4, -1, -5}, -8));

        harness.test("Target Zero with Opposites", "nums = [-5, 1, 2, 5], target = 0", new int[]{0, 3},
                () -> run.apply(new int[]{-5, 1, 2, 5}, 0));

        // --- Tier 3: Corner & Trick Cases ---
        harness.test("Large Values Near Limit", "nums = [1000000000, -1000000000], target = 0", new int[]{0, 1},
                () -> run.apply(new int[]{1000000000, -1000000000}, 0));

        harness.test("Elements Far Apart", "nums = [1, 5, 8, 12, 19, 25, 40], target = 41", new int[]{0, 6},
                () -> run.apply(new int[]{1, 5, 8, 12, 19, 25, 40}, 41));

        // --- Tier 4: Stress / Performance Test ---
        int n = 10000;
        int[] largeNums = new int[n];
        for (int i = 0; i < n; i++) largeNums[i] = 1000 + i * 10;
        largeNums[0] = 5;
        largeNums[n - 1] = 11;
        int largeTarget = 16; // only 5 + 11 == 16 (all other elements >= 1000)
        harness.test("Stress Test (N=10000)", "nums length 10000, target = 16", new int[]{0, n - 1},
                () -> run.apply(largeNums, largeTarget));

        harness.summarize();
    }
}
