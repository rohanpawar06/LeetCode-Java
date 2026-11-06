# 2747. Count Zero Request Servers

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/count-zero-request-servers/

**Tags:** Array, Sorting, Sliding Window

## Problem Statement

Given n servers, request logs containing server IDs and timestamps, a window length x, and query times, return for each query the number of servers that received no requests during the inclusive interval [query - x, query].

## Intuition

Sort logs and queries by time. Maintain a sliding window of logs for the current query and frequencies for each server. The number of zero-request servers is n minus the number of distinct active servers.

## Approach

1. Sort logs by timestamp.
2. Pair each query with its original index and sort by query time.
3. Add logs with timestamps at or before the query time.
4. Remove logs earlier than query time minus x.
5. Track the number of distinct servers in the window.
6. Store n minus active servers at the query's original index.

## Algorithm

Use two pointers and a frequency array. Expand the right pointer as logs enter the window; advance the left pointer as logs expire. Update the active distinct-server count when a frequency changes from zero to one or one to zero.

## Example

Input: n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]

Output: [1,2]

## Key Learning

Offline query sorting enables sliding-window processing. Frequencies are necessary because one server can have multiple requests in the same window.

## Time Complexity

O(L log L + Q log Q), where L is the number of logs and Q is the number of queries.

## Space Complexity

O(n + Q) auxiliary space, excluding sorting internals.

## Language

Java
