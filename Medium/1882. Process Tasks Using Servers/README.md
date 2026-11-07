# 1882. Process Tasks Using Servers

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/process-tasks-using-servers/

**Tags:** Array, Heap, Priority Queue

## Problem Statement

Given server weights and task durations, assign each task to an available server with the smallest weight, breaking ties by server index. Tasks arrive in order, and if no server is available, the task waits until one becomes free.

## Intuition

Maintain two priority queues: available servers ordered by weight and index, and busy servers ordered by finish time, then weight, then index. Simulate tasks in arrival order and release servers when their finish time is reached.

## Approach

1. Put all servers in the available heap ordered by weight and index.
2. Put busy servers in a heap ordered by finish time, weight, and index.
3. For each task, advance current time to at least its arrival index.
4. Move every server that has finished by current time into the available heap.
5. If no server is available, jump time to the earliest finish time and release all servers finishing then.
6. Assign the task to the best available server and record its finish time.

## Algorithm

Use priority queues to simulate scheduling. Each busy entry stores finish time, server weight, and server index. After assigning a task, its finish time is current time plus task duration.

## Example

Input: servers = [3,3,2], tasks = [1,2,3,2,1,2]

Output: [2,2,0,2,1,2]

## Key Learning

Two heaps efficiently handle server selection and availability. Preserve the original task order while allowing simulated time to jump forward when all servers are busy.

## Time Complexity

O((n + m) log n), where n is the number of servers and m is the number of tasks.

## Space Complexity

O(n) auxiliary space for the heaps.

## Language

Java
