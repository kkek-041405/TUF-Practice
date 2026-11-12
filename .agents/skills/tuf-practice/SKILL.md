---
name: tuf-practice
description: Use when the user wants to practice Take U Forward (TUF) DSA problems in Java, extract problems from TUF links, generate and mathematically verify test suites (including edge, corner, and stress cases), scaffold problem directories, or run and evaluate Java solutions against high-standard test cases.
---

# Take U Forward (TUF) Practice & Test Suite Skill

This skill defines the rigorous, standardized process for extracting TUF problems, generating comprehensive and verified test suites, scaffolding the Java workspace, and testing user solutions without requiring a platform subscription.

---

## 1. Core Operating Principles

1. **Zero Invalid Test Cases:** Every test case must be independently calculated and validated via a reference algorithm (oracle) before making the problem ready for the user.
2. **Exhaustive Edge Coverage:** No boundary condition or algorithmic trick case may be omitted.
3. **Pure Java Matching:** `Solution.java` must always match the exact class and method signature as found on the Take U Forward editor.
4. **Independent Sandbox Runner:** Uses the local `.runner/` harness (`TestHarness`, `ListNode`, `TreeNode`) for deep equality checks, execution timing, and clear diff reporting.

---

## 2. Step-by-Step Procedure

### Step 1: Problem Ingestion & Extraction

When the user provides a TUF problem URL:
1. Use the `browser` subagent to open and inspect the page (leveraging the user's active session).
2. Extract the following essential parameters:
   - **Problem Title & Slug** (e.g., `two-sum`, `longest-consecutive-sequence`).
   - **Topic / Sheet Category** (e.g., `01-arrays`, `02-two-pointers`, `03-binary-search`, etc.).
   - **Exact Method Signature:** Class name, method name, return type, and argument list types.
   - **Problem Statement & Rules:** Core requirements, return specifications.
   - **Constraints:**
     - Array size limits ($1 \le N \le 10^5$, etc.)
     - Value ranges ($-10^9 \le \text{nums}[i] \le 10^9$, etc.)
     - Space & Time complexity expectations.
   - **Sample Examples:** Inputs, outputs, and explanations.

---

### Step 2: 4-Tier Test Suite Formulation

Formulate a minimum of 10–20 high-quality test cases distributed across 4 tiers:

1. **Tier 1: Sample Cases**
   - Exact examples given in the problem statement.
2. **Tier 2: Boundary & Edge Cases**
   - Minimum possible input size ($N = 0, 1, 2$).
   - Maximum constraint bounds ($N = 10^5$ or boundary values).
   - Extreme values: `Integer.MAX_VALUE`, `Integer.MIN_VALUE`, $0$, negative numbers.
   - Single distinct value vs. all unique values.
   - Monotonic inputs: strictly ascending, strictly descending.
3. **Tier 3: Corner & Algorithmic Trap Cases**
   - Integer overflow traps: calculations that exceed 32-bit signed integer (`int`) requiring `long`.
   - Duplicate target or element values.
   - Off-by-one boundary traps (start/end pointers, binary search midpoints).
   - Parity and odd/even lengths.
4. **Tier 4: Stress & Performance Cases**
   - Large input size ($N \approx 5 \times 10^4$ to $10^5$) designed to trigger Time Limit Exceeded (TLE) on suboptimal $O(N^2)$ solutions.

---

### Step 3: MANDATORY Pre-Solve Oracle Verification

> [!IMPORTANT]
> **Before declaring the problem ready for the user**, you MUST mathematically and algorithmically verify every single test case.

1. Create a temporary scratch script or execute a reference solution to calculate the exact outputs.
2. Verify that:
   - Every input strictly abides by the problem's stated constraints.
   - The expected output matches the mathematical ground truth.
   - Any order-agnostic answers (e.g. subsets, group anagrams) use `testUnordered` in the harness.
3. Only when all test cases are 100% verified do you create the workspace files.

---

### Step 4: Workspace Scaffolding & Standalone Documentation

Create the following folder and files under `problems/<category>/<problem-slug>/`:

```text
problems/<category>/<problem-slug>/
├── Problem.md         # Comprehensive standalone problem reference (see specification below)
├── Solution.java      # Starter template matching TUF editor (USER WRITES CODE HERE)
├── testcases.json     # All verified test cases in JSON format
└── TestRunner.java    # Runnable test runner wired to TestHarness
```

#### Standalone `Problem.md` Specification
Every `Problem.md` MUST be self-contained so anyone browsing the repository without Take U Forward access has the complete context. It must contain the following 7 sections:
1. **Header & Metadata:** Title, Topic, Difficulty, Original Source Link, Target Language.
2. **Complete Problem Description:** Precise rules, return/print specifications, and goals.
3. **Visual Representation / ASCII Matrix:** Diagrams, row-by-row indices, loop ranges, and character counts.
4. **Examples & Step-by-Step Walkthroughs:** Minimum of 2–3 examples with detailed explanations.
5. **Constraints & Complexity:** Input bounds, expected Time Complexity, and Auxiliary Space Complexity.
6. **Algorithmic Approach & Logic Breakdown:** Outer loop vs inner loop roles, data structure choices, and logic hints.
7. **Common Pitfalls & Corner Cases:** Off-by-one errors, formatting traps, overflow pitfalls, spacing requirements.
8. **Implementation & Testing Links:** Direct links to `Solution.java` and terminal test command.

#### Template for `Solution.java`:
```java
// TUF Problem: <Title>
// Write your code inside this method. Do not change the class or method signature.

class Solution {
    public <ReturnType> <methodName>(<Args>) {
        // TODO: Write your solution here
        return null;
    }
}
```

#### Template for `TestRunner.java`:
```java
import runner.TestHarness;
import runner.ListNode;
import runner.TreeNode;

public class TestRunner {
    public static void main(String[] args) {
        TestHarness harness = new TestHarness("<Problem Title>");
        Solution sol = new Solution();

        // Tier 1: Samples
        harness.test("Sample 1", "<input>", <expected>, () -> sol.<methodName>(<args>));

        // Tier 2: Edge Cases
        // ...

        // Tier 3: Corner & Trick Cases
        // ...

        // Tier 4: Stress Cases
        // ...

        harness.summarize();
    }
}
```

---

### Step 5: Execution & Evaluation

When the user announces their code is ready:
1. Run the test script:
   ```powershell
   .\test.ps1 <problem-slug>
   ```
2. Parse the results and present a clean report:
   - **Scorecard:** Total tests, Passed, Failed.
   - **Execution Time & Peak Memory.**
   - **Failure Analysis:** If any test fails, display:
     - Test Name & Input
     - Expected Output
     - Actual Output
     - Diagnostic hint (e.g., Integer Overflow, Off-by-one Index, NullPointerException, TLE).
