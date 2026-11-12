# Take U Forward (TUF) Java Practice System

An automated, local development environment for mastering DSA problems from [Take U Forward](https://takeuforward.org/) in **Java**, without requiring a platform subscription.

---

## 🛠️ Architecture & Components

```text
TUF-Pratice/
├── .agents/
│   └── skills/
│       └── tuf-practice/
│           └── SKILL.md            # The high-quality SOP skill for TUF workflows
├── .runner/
│   ├── ListNode.java               # Standard singly-linked list node + helpers
│   ├── TreeNode.java               # Standard binary tree node + level-order serializer
│   └── TestHarness.java            # Universal assertion engine, benchmarking & diffs
├── test.ps1                        # One-command compiler & test runner script
└── problems/                       # Problem directories categorized by topic
    └── 01-arrays/
        └── two-sum/
            ├── Problem.md          # Formatted problem statement & constraints
            ├── Solution.java       # Exact TUF template (WRITE YOUR CODE HERE)
            └── TestRunner.java     # Pre-verified 4-tier test suite
```

---

## 🚀 How to Practice

1. **Provide a Problem Link:**
   - Share a TUF problem link (e.g., `https://takeuforward.org/data-structure/...`).
   - The assistant will extract the problem signature, constraints, and samples.
2. **Pre-Solve Oracle Verification:**
   - The assistant independently validates every single test case (Sample, Boundary, Corner, Stress) against constraints and expected mathematical truth before setting up the files.
3. **Write Your Solution:**
   - Open `problems/<topic>/<problem-name>/Solution.java` and write your Java code.
4. **Run Tests:**
   - Run tests directly in terminal:
     ```powershell
     .\test.ps1 <problem-slug>
     ```
   - Or simply tell the assistant: *"Run the tests for `<problem-slug>`"*.
