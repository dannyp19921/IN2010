# Teque - Triple-Ended Queue

**Academic project from IN2010 (Algorithms and Data Structures), University of Oslo, Fall 2023**

Implementation of a "teque" data structure - an extension of the double-ended queue that supports efficient insertion at front, back, and middle positions.

> **Note:** This is coursework from UiO. Problem from [Kattis](https://open.kattis.com/problems/teque). See `assignment.pdf` for full details and `report.pdf` for my analysis.

## Problem

A teque supports:
- `push_back(x)` - Insert at back in O(1)
- `push_front(x)` - Insert at front in O(1)  
- `push_middle(x)` - Insert at position ⌊(k+1)/2⌋ in O(1)
- `get(i)` - Retrieve element at index i in O(n)

## Solution

Uses a **doubly-linked list** with three pointers:
- `forste` (first) → Front element
- `siste` (last) → Back element  
- `midten` (middle) → Middle element

By maintaining these pointers, all three push operations achieve **O(1)** time complexity.

## Time Complexity

| Operation | Time | Explanation |
|-----------|------|-------------|
| push_back | O(1) | Direct access via `siste` pointer |
| push_front | O(1) | Direct access via `forste` pointer |
| push_middle | O(1) | Direct access via `midten` pointer |
| get | O(n) | Must traverse linked list from start |

## Usage

**Compile:**
```bash
javac Teque.java
```

**Run with example input:**
```bash
java Teque < inputs/eksempel_input
```

**Expected output:**
```
3
5
9
5
1
```

**Run test suite:**
```bash
# No output means test passed ✓
java Teque < inputs/input_100 | cmp - outputs/output_100
java Teque < inputs/input_1000 | cmp - outputs/output_1000
java Teque < inputs/input_100000 | cmp - outputs/output_100000
```

## Test Data

Test cases range from small to large datasets:
- `eksempel_input` - 9 operations (example)
- `input_100` - 100 operations
- `input_1000` - 1,000 operations  
- `input_100000` - 100,000 operations
- `input_1000000` - 1,000,000 operations

## Files

- `Teque.java` - Implementation
- `inputs/`, `outputs/` - Test data and expected results
- `assignment.pdf` - Original assignment (Norwegian)
- `report.pdf` - My analysis with pseudocode and complexity discussion (Norwegian)

## Implementation Notes

- Inner `Node` class with doubly-linked structure
- Handles edge cases: empty queue, size transitions, odd/even sizes
- Variable names in Norwegian retained from academic context

---

**Source:** [Kattis: Teque](https://open.kattis.com/problems/teque)