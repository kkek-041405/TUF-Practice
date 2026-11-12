# Pattern 2: Right-Angled Triangle Star Pattern

- **Topic:** Logic Building & Patterns
- **Difficulty:** Easy
- **Platform Source:** [Take U Forward - Pattern 2](https://takeuforward.org/practice/dsa/pattern-2)
- **Target Language:** Java

---

## 1. Problem Description

Given an integer `n`, your task is to print a right-angled triangle pattern made of asterisks (`*`) of height and base $N$.

For any positive integer $N$:
- The pattern consists of exactly $N$ rows.
- The $i$-th row (where row indexing starts at $1$ and goes up to $N$) must contain exactly $i$ stars.
- Each star on the same row must be separated by a single space.
- Each row must be terminated with a newline.

---

## 2. Visual Representation & Grid Mechanics

For $N = 5$:

```text
Row 1 (i = 1):  *                          -> 1 star
Row 2 (i = 2):  * *                        -> 2 stars
Row 3 (i = 3):  * * *                      -> 3 stars
Row 4 (i = 4):  * * * *                    -> 4 stars
Row 5 (i = 5):  * * * * *                  -> 5 stars
```

### Grid Matrix Breakdown

| Row Index ($i$) | Stars in Row | Column Loop Range ($j$) | Characters Printed |
| :---: | :---: | :---: | :--- |
| **1** | 1 | $1 \le j \le 1$ | `*` |
| **2** | 2 | $1 \le j \le 2$ | `* *` |
| **3** | 3 | $1 \le j \le 3$ | `* * *` |
| **4** | 4 | $1 \le j \le 4$ | `* * * *` |
| **5** | 5 | $1 \le j \le 5$ | `* * * * *` |

Total stars printed: $\sum_{i=1}^{N} i = \frac{N(N + 1)}{2}$.

---

## 3. Examples & Walkthroughs

### Example 1
- **Input:** `n = 4`
- **Output:**
  ```text
  * 
  * * 
  * * * 
  * * * * 
  ```

### Example 2
- **Input:** `n = 2`
- **Output:**
  ```text
  * 
  * * 
  ```

### Example 3 (Minimum Boundary)
- **Input:** `n = 1`
- **Output:**
  ```text
  * 
  ```
- **Explanation:** $N = 1$ contains only 1 row with 1 star.

---

## 4. Constraints & Input Specifications

- **Constraint:** $1 \le n \le 100$
- **Expected Time Complexity:** $\mathcal{O}(N^2)$ — You must print $\frac{N(N+1)}{2}$ stars. For $N=100$, this is 5,050 iterations, taking less than 5 milliseconds in Java.
- **Expected Auxiliary Space:** $\mathcal{O}(1)$ — No extra arrays or collections required.

---

## 5. Algorithmic Approach & Logic

1. **Outer Loop (Row Count):**
   - Runs $N$ times from row $1$ to $N$:
     ```java
     for (int i = 1; i <= n; i++)
     ```
2. **Inner Loop (Columns / Stars per Row):**
   - For row $i$, runs $i$ times:
     ```java
     for (int j = 1; j <= i; j++) {
         System.out.print("* ");
     }
     ```
3. **Newline Break:**
   - After printing the $i$ stars for that row:
     ```java
     System.out.println();
     ```

---

## 6. Common Pitfalls & Corner Cases

> [!WARNING]
> Keep the following in mind:

1. **Fixed vs Variable Columns:**
   - In Pattern 1, the inner loop ran $N$ times (fixed square).
   - In Pattern 2, the inner loop runs $i$ times (variable triangle). Do not hardcode the inner loop to $N$.
2. **Off-by-One in Inner Loop:**
   - Ensure the inner loop condition is `j <= i` (if 1-indexed) or `j <= i` (if `i` is 0-indexed, `j <= i` gives $i+1$ stars).
3. **Spacing:**
   - Use `System.out.print("* ")` to ensure spaces between stars as shown in TUF diagrams.

---

## 7. Implementation & Testing

- Starter file: [`Solution.java`](file:///i:/Projects/TUF-Pratice/problems/00-patterns/pattern-2/Solution.java)
- Method signature:
  ```java
  class Solution {
      public void pattern2(int n) {
          // Write code here
      }
  }
  ```
- Run verification tests:
  ```powershell
  .\test.ps1 pattern-2
  ```
