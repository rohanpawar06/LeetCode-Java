# 1606. Find Servers That Handled Most Number of Requests

- **Difficulty:** Hard
- **Problem:** [LeetCode 1606](https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/)
- **Tags:** Greedy, Ordered Set, Priority Queue, Simulation
- **Language:** Java

## Problem Statement

There are `k` servers numbered from `0` to `k - 1`. Requests arrive at times given by `arrival`, and each request takes the corresponding duration in `load`. Request `i` prefers server `i % k`; if that server is unavailable, the next available server with a larger index is selected, wrapping around to the smallest available index. If all servers are busy, the request is dropped. Return the server indices that handled the maximum number of requests.

## Intuition

Maintain available servers in sorted order so the preferred server or the next server can be found quickly. Track busy servers by their completion time, releasing them whenever a new request arrives.

## Approach

- Use a `TreeSet` to store available server indices.
- Use a min-priority queue to store busy servers as `(finishTime, serverIndex)`.
- Before each request, release every server whose finish time is no later than the request arrival time.
- Find the smallest available server index at least `i % k`. If none exists, wrap around to the smallest available index.
- Assign the request, increment that server's count, and add its completion time to the priority queue.
- Finally, collect all servers with the maximum request count.

## Algorithm

1. Initialize all server indices as available.
2. Process requests in arrival order.
3. Release completed servers.
4. Skip the request if no server is available.
5. Select the preferred available server using `ceiling`; wrap with `first` if necessary.
6. Update its count and busy finish time.
7. Return every server tied for the highest count.

## Example

**Input:** `k = 3`, `arrival = [1,2,3,4,5]`, `load = [5,2,3,3,3]`

**Output:** `[1]`

Server `1` handles the most requests.

## Key Learning

A `TreeSet` supports efficient successor lookup for cyclic server assignment, while a min-heap efficiently releases servers in completion-time order.

## Complexity

- **Time:** `O((n + k) log k)`
- **Space:** `O(k)`

