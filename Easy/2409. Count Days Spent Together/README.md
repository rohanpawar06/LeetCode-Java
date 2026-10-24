# LeetCode 2409 - Count Days Spent Together

**Difficulty:** Easy

**Problem Link:** https://leetcode.com/problems/count-days-spent-together/

**Tags:** String, Math

## Problem Statement

Alice and Bob each have an arrival date and a leaving date in the format `MM-DD`. Return the number of days they are both present, counting both arrival and leaving dates. If their stays do not overlap, return `0`.

## Intuition

The days they spend together are the overlap between their two date ranges. The overlap starts at the later arrival date and ends at the earlier leaving date.

## Approach

1. Convert each date into its day number in the same non-leap year.
2. Choose the later of the two arrival dates as the overlap start.
3. Choose the earlier of the two leaving dates as the overlap end.
4. If the end is before the start, return `0`; otherwise return `end - start + 1`.

## Java Solution

    class Solution {
        public int countDaysTogether(String arriveAlice, String leaveAlice,
                                     String arriveBob, String leaveBob) {
            int start = Math.max(toDay(arriveAlice), toDay(arriveBob));
            int end = Math.min(toDay(leaveAlice), toDay(leaveBob));

            return Math.max(0, end - start + 1);
        }

        private int toDay(String date) {
            int[] days = {31, 28, 31, 30, 31, 30,
                          31, 31, 30, 31, 30, 31};

            int month = Integer.parseInt(date.substring(0, 2));
            int day = Integer.parseInt(date.substring(3, 5));
            int total = day;

            for (int i = 0; i < month - 1; i++) {
                total += days[i];
            }

            return total;
        }
    }

## Example

**Input:** `arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"`

The overlapping dates are August 16, 17, and 18.

**Output:** `3`

## Key Learning

For inclusive date ranges, calculate the intersection using the maximum start date and minimum end date. Add `1` because both boundary dates count.

## Complexity

- **Time Complexity:** `O(1)`
- **Space Complexity:** `O(1)`

**Language:** Java