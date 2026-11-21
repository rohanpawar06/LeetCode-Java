# 3331. Find Subtree Sizes After Changes

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/find-subtree-sizes-after-changes/

**Tags:** Tree, Depth-First Search, Hash Table

## Problem Statement

You are given a rooted tree with node 0 as the root. Each node has a lowercase English letter assigned to it. For every node other than the root, find the nearest ancestor (if one exists) that has the same character. If such an ancestor exists, reassign the node's parent to that ancestor. These parent changes are determined using the original tree. Return an array where each element is the size of the corresponding node's subtree in the modified tree.

## Intuition

The problem can be solved in two DFS passes.

First, traverse the original tree while maintaining the most recently visited ancestor for each character. This identifies the nearest ancestor with the same character. If no matching ancestor exists, the node keeps its original parent.

Second, construct the modified tree from the updated parent array and run DFS again to calculate every subtree size.

## Approach

- Build an adjacency list representing the original tree.
- Clone the original parent array so nodes without a matching ancestor retain their original parent.
- Use a 26-element array to track the nearest ancestor for each lowercase character during DFS.
- Restore the previous character mapping when returning from each DFS branch.
- Build a new adjacency list using the modified parent array.
- Run a postorder DFS to calculate subtree sizes.

## Algorithm

1. Build the original tree using the parent array.
2. Initialize `newParent` as a copy of `parent`.
3. Initialize the `last` array with -1.
4. During DFS from root 0:
   - Store the previous node associated with the current character.
   - If a matching ancestor exists, update the current node's parent.
   - Set the current node as the latest ancestor for its character.
   - Visit its children.
   - Restore the previous character mapping.
5. Build the modified tree using `newParent`.
6. Perform DFS from root 0. For each node, add 1 to the sizes of all its child subtrees.
7. Store and return the calculated subtree sizes.

## Example

**Input:** `parent = [-1,0,0,1,1,1]`, `s = "abaabc"`

For each node, the nearest ancestor with the same character is used as its new parent when one exists. Otherwise, its original parent is retained.

The subtree sizes are calculated from the resulting modified tree.

## Key Learning

When a DFS operation depends on the current path from the root, maintain path-specific state and restore it during backtracking. A second traversal can then process a tree built from the updated relationships.

## Complexity Analysis

- **Time Complexity:** O(n), as each node and edge is processed a constant number of times.
- **Space Complexity:** O(n), for the adjacency lists, parent and answer arrays, and DFS recursion stack.

## Language

Java
