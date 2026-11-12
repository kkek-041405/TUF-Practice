# Pattern 1: Rectangular Star Pattern

- **Topic:** Logic Building & Patterns
- **Difficulty:** Easy
- **Platform Source:** [Take U Forward - Pattern 1](https://takeuforward.org/practice/dsa/pattern-1)
- **Target Language:** Java

---

## 1. Problem Description

Given an integer `n`, your task is to print a rectangular (square) matrix of stars (`*`) of dimension $N \times N$.

For any positive integer $N$:
- The pattern consists of exactly $N$ rows and $N$ columns.
- Every position in the $N \times N$ grid contains an asterisk (`*`).
- Each star on the same row must be separated by a single space.
- Each row must be terminated with a newline.

---

## 2. Visual Representation & Grid Mechanics

For $N = 5$:

```text
Row 1:  * * * * *                          -> 5 stars
Row 2:  * * * * *                          -> 5 stars
Row 3:  * * * * *                          -> 5 stars
Row 4:  * * * * *                          -> 5 stars
Row 5:  * * * * *                          -> 5 stars
```

### Grid Matrix Breakdown

| Row Index ($i$) | Columns to Print | Characters Printed |
| :---: | :---: | :--- |
| **0** | $0 \le j < N$ | `* * * * *` |
| **1** | $0 \le j < N$ | `* * * * *` |
| **2** | $0 \le j < N$ | `* * * * *` |
| **3** | $0 \le j < N$ | `* * * * *` |
| **4** | $0 \le j < N$ | `* * * * *` |

Total stars printed: $N \times N = N^2$.

---

## 3. Examples & Walkthroughs

### Example 1
- **Input:** `n = 4`
- **Output:**
  ```text
  * * * * 
  * * * * 
  * * * * 
  * * * * 
  ```

### Example 2
- **Input:** `n = 2`
- **Output:**
  ```text
  * * 
  * * 
  ```

### Example 3 (Minimum Boundary)
- **Input:** `n = 1`
- **Output:**
  ```text
  * 
  ```
- **Explanation:** A $1 \times 1$ grid is just a single star.

---

## 4. Constraints & Input Specifications

- **Constraint:** $1 \le n \le 100$
- **Expected Time Complexity:** $\mathcal{O}(N^2)$ — You must print $N^2$ characters. For $N=100$, this is 10,000 iterations, completing in $\approx 2$ milliseconds.
- **Expected Auxiliary Space:** $\mathcal{O}(1)$ — Constant extra space.

---

## 5. Algorithmic Approach & Logic

1. **Outer Loop (Rows):**
   - Controls the row count ($N$ times):
     ```java
     for (int i = 0; i < n; i++)
     ```
2. **Inner Loop (Columns):**
   - For every row, prints $N$ stars:
     ```java
     for (int j = 0; j < n; j++) {
         System.out.print("* ");
     }
     ```
3. **Line Break:**
   - After the inner loop completes for row $i$:
     ```java
     System.out.println();
     ```

---

## 6. Common Pitfalls & Corner Cases

1. **Using `println` instead of `print`:**
   - Calling `System.out.println("* ")` inside the inner loop will put every star on a separate row. Make sure to use `System.out.print("* ")` in the inner loop and `System.out.println()` only after the row completes.
2. **Spacing:**
   - Separate stars with a space as shown in the Take U Forward diagrams.
3. **Boundary Condition ($N = 1$):**
   - Ensure your loop works for $N = 1$ without printing extra rows.

---

## 7. Implementation & Testing

- Starter file: [`Solution.java`](file:///i:/Projects/TUF-Pratice/problems/00-patterns/pattern-1/Solution.java)
- Method signature:
  ```java
  class Solution {
      public void pattern1(int n) {
          // Write code here
      }
  }
  ```
- Run verification tests:
  ```powershell
  .\test.ps1 pattern-1
  ```
