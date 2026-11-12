# Two Sum

- **Topic:** Arrays & Hashing
- **Difficulty:** Easy
- **Platform Source:** [Take U Forward - Two Sum](https://takeuforward.org/)
- **Target Language:** Java

---

## 1. Problem Description

Given an array of integers `nums` and an integer `target`, return the **indices** of the two numbers such that they add up to `target`.

### Rules & Guarantees
- Each input is guaranteed to have **exactly one valid solution**.
- You may not use the same element twice (i.e. indices must be distinct: $i \ne j$).
- You can return the answer in any order (e.g. `[0, 1]` or `[1, 0]`).

---

## 2. Visual Walkthrough

Suppose `nums = [2, 7, 11, 15]` and `target = 9`:

```text
Index:      0     1     2     3
Value:     [2]   [7]   [11]  [15]
            ▲     ▲
            │     │
            └─────┴── 2 + 7 = 9 (Target Match!)
```
- At index `0`, value is `2`. Needed complement: $9 - 2 = 7$.
- At index `1`, value is `7`. Since $7$ is found, return indices `[0, 1]`.

---

## 3. Examples & Detailed Breakdown

### Example 1
- **Input:** `nums = [2, 7, 11, 15]`, `target = 9`
- **Output:** `[0, 1]`
- **Explanation:** `nums[0] + nums[1] == 2 + 7 == 9`, so return `[0, 1]`.

### Example 2
- **Input:** `nums = [3, 2, 4]`, `target = 6`
- **Output:** `[1, 2]`
- **Explanation:** `nums[1] + nums[2] == 2 + 4 == 6`, so return `[1, 2]`.

### Example 3 (Duplicate Values)
- **Input:** `nums = [3, 3]`, `target = 6`
- **Output:** `[0, 1]`
- **Explanation:** `nums[0] + nums[1] == 3 + 3 == 6`, so return `[0, 1]`.

---

## 4. Constraints & Input Specifications

- **Array Length:** $2 \le \text{nums.length} \le 10^4$
- **Element Value Range:** $-10^9 \le \text{nums}[i] \le 10^9$
- **Target Value Range:** $-10^9 \le \text{target} \le 10^9$
- **Guarantee:** Exactly one valid answer exists for all test inputs.

### Complexity Targets
- **Optimal Time Complexity:** $\mathcal{O}(N)$ using a Hash Map lookup.
- **Brute Force Time Complexity:** $\mathcal{O}(N^2)$ using nested loops (will TLE on large arrays).
- **Space Complexity:** $\mathcal{O}(N)$ for the hash table storage.

---

## 5. Algorithmic Approaches

### Approach 1: Brute Force — $\mathcal{O}(N^2)$ Time, $\mathcal{O}(1)$ Space
Iterate through all pairs $(i, j)$ where $i < j$. Check if `nums[i] + nums[j] == target`.

### Approach 2: One-Pass Hash Map (Optimal) — $\mathcal{O}(N)$ Time, $\mathcal{O}(N)$ Space
1. Maintain a map storing `element_value -> index`.
2. As you iterate through each index $i$ with value $x = \text{nums}[i]$:
   - Compute the complement: $\text{complement} = \text{target} - x$.
   - Check if $\text{complement}$ already exists in the map.
   - If yes: return `new int[]{map.get(complement), i}`.
   - If no: insert `map.put(x, i)` and continue.

---

## 6. Common Pitfalls & Edge Cases

1. **Reusing the same element:**
   - For `nums = [3, 2, 4]` and `target = 6`, checking if `target - nums[0] == 3` without verifying indices might wrongly return `[0, 0]`.
2. **Negative numbers & Zero:**
   - E.g. `nums = [-5, 1, 2, 5]`, `target = 0` $\to$ answer is `[0, 3]`.
3. **Large values near 32-bit integer limits:**
   - Values up to $10^9$ fit inside a standard 32-bit signed integer (`int`), but sums must not overflow if tested beyond $2 \times 10^9$.

---

## 7. Implementation & Testing

- Starter file: [`Solution.java`](file:///i:/Projects/TUF-Pratice/problems/01-arrays/two-sum/Solution.java)
- Method signature:
  ```java
  class Solution {
      public int[] twoSum(int[] nums, int target) {
          // Write code here
      }
  }
  ```
- Run verification tests:
  ```powershell
  .\test.ps1 two-sum
  ```
