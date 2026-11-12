# Pattern 7: Star Pyramid

- **Topic:** Logic Building & Patterns
- **Difficulty:** Easy
- **Platform Source:** [Take U Forward - Pattern 7](https://takeuforward.org/practice/dsa/pattern-7)
- **Target Language:** Java

---

## 1. Problem Description

Given an integer `n`, your task is to print a symmetric **Star Pyramid** of height $N$.

For any positive integer $N$:
- The pattern consists of exactly $N$ rows.
- Each row $i$ (using 0-based indexing from $0$ to $N - 1$) is composed of:
  1. **Leading Spaces:** Exactly $(N - i - 1)$ space characters (`" "`) to center-align the pyramid.
  2. **Stars:** Exactly $(2 \times i + 1)$ asterisk characters (`*`).
- Each row is terminated with a newline.

---

## 2. Visual Representation & Grid Mechanics

For $N = 5$:

```text
Row 0:     *                               -> 4 spaces, 1 star  (2*0 + 1)
Row 1:    ***                              -> 3 spaces, 3 stars (2*1 + 1)
Row 2:   *****                             -> 2 spaces, 5 stars (2*2 + 1)
Row 3:  *******                            -> 1 space,  7 stars (2*3 + 1)
Row 4: *********                           -> 0 spaces, 9 stars (2*4 + 1)
```

### Grid Matrix Breakdown

| Row Index ($i$) | Leading Spaces ($N - i - 1$) | Star Count ($2i + 1$) | Row String |
| :---: | :---: | :---: | :--- |
| **0** | $4$ | $1$ | `    *` |
| **1** | $3$ | $3$ | `   ***` |
| **2** | $2$ | $5$ | `  *****` |
| **3** | $1$ | $7$ | ` *******` |
| **4** | $0$ | $9$ | `*********` |

Total stars printed: $\sum_{i=0}^{N-1} (2i + 1) = N^2$.

---

## 3. Examples & Walkthroughs

### Example 1
- **Input:** `n = 4`
- **Output:**
  ```text
     *
    ***
   *****
  *******
  ```
- **Explanation:**
  - Row 0: 3 spaces + 1 star
  - Row 1: 2 spaces + 3 stars
  - Row 2: 1 space + 5 stars
  - Row 3: 0 spaces + 7 stars

### Example 2
- **Input:** `n = 2`
- **Output:**
  ```text
   *
  ***
  ```

### Example 3 (Minimum Boundary)
- **Input:** `n = 1`
- **Output:**
  ```text
  *
  ```
- **Explanation:** For $N = 1$, row 0 has $1 - 0 - 1 = 0$ spaces and $2(0) + 1 = 1$ star.

---

## 4. Constraints & Input Specifications

- **Constraint:** $1 \le n \le 100$
- **Expected Time Complexity:** $\mathcal{O}(N^2)$ — Total characters printed is roughly $N \times (2N) \approx 2N^2$. For $N=100$, this is $\approx 20,000$ operations, executing in $< 3$ ms in Java.
- **Expected Auxiliary Space:** $\mathcal{O}(1)$ — Constant extra space.

---

## 5. Algorithmic Approach & Logic

Each row requires two separate inner loops:

1. **Outer Loop (Row $i$ from $0$ to $N - 1$):**
   ```java
   for (int i = 0; i < n; i++) {
   ```
2. **First Inner Loop (Leading Spaces):**
   - Prints $(N - i - 1)$ spaces:
     ```java
     for (int j = 0; j < n - i - 1; j++) {
         System.out.print(" ");
     }
     ```
3. **Second Inner Loop (Stars):**
   - Prints $(2 \times i + 1)$ stars:
     ```java
     for (int j = 0; j < 2 * i + 1; j++) {
         System.out.print("*");
     }
     ```
4. **Newline Break:**
   - Move to next row:
     ```java
     System.out.println();
     ```

---

## 6. Common Pitfalls & Corner Cases

> [!WARNING]
> Keep the following in mind:

1. **Star Formula Off-by-One:**
   - If using 0-based indexing ($i = 0 \dots n-1$), the star count is `2 * i + 1`.
   - If using 1-based indexing ($i = 1 \dots n$), the star count is `2 * i - 1`.
2. **Trailing Spaces:**
   - Do not print trailing spaces unless explicitly required; simply call `System.out.println()` after the stars.
3. **Leading Spaces Calculation:**
   - On the last row ($i = n - 1$), the number of leading spaces is $n - (n - 1) - 1 = 0$. The base of the pyramid must align directly with the left margin.

---

## 7. Implementation & Testing

- Starter file: [`Solution.java`](file:///i:/Projects/TUF-Pratice/problems/00-patterns/pattern-7/Solution.java)
- Method signature:
  ```java
  class Solution {
      public void pattern7(int n) {
          // Write code here
      }
  }
  ```
- Run verification tests:
  ```powershell
  .\test.ps1 pattern-7
  ```
