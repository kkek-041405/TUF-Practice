package runner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Standard Singly-Linked List node definition used across Take U Forward & LeetCode.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
        this.val = 0;
        this.next = null;
    }

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * Builds a linked list from an array of integers.
     */
    public static ListNode fromArray(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : arr) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    /**
     * Converts a linked list to an array of integers (safeguarded against cycles).
     */
    public static int[] toArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        Set<ListNode> visited = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (visited.contains(curr)) {
                // Cycle detected, break to avoid infinite loop
                break;
            }
            visited.add(curr);
            list.add(curr.val);
            curr = curr.next;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * Pretty string representation e.g. [1 -> 2 -> 3 -> null].
     */
    public static String stringify(ListNode head) {
        if (head == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Set<ListNode> visited = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (visited.contains(curr)) {
                sb.append("... (cycle detected at node with val ").append(curr.val).append(")");
                break;
            }
            visited.add(curr);
            sb.append(curr.val);
            if (curr.next != null && !visited.contains(curr.next)) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Deep equality check between two linked lists.
     */
    public static boolean equals(ListNode l1, ListNode l2) {
        Set<ListNode> visited1 = new HashSet<>();
        Set<ListNode> visited2 = new HashSet<>();
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false;
            if (visited1.contains(l1) || visited2.contains(l2)) {
                return l1 == l2; // Both entered a cycle at the same place
            }
            visited1.add(l1);
            visited2.add(l2);
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1 == null && l2 == null;
    }

    @Override
    public String toString() {
        return stringify(this);
    }
}
