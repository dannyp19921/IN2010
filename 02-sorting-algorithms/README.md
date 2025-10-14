# Sorting Algorithms Implementation

**Academic project from IN2010 (Algorithms and Data Structures), University of Oslo, Fall 2023**

Implementation of four classic sorting algorithms with performance benchmarking and comparative analysis.

> **Note:** This is coursework from UiO. See `assignment.pdf` for full details and `report.pdf` for my experimental analysis with performance graphs.

## Algorithms Implemented

- **Insertion Sort** - O(n²) average, O(n) best case on nearly-sorted data
- **Merge Sort** - O(n log n) guaranteed performance
- **Bubble Sort** - O(n²) average, demonstrates inefficiency on large datasets
- **Selection Sort** - O(n²) average, minimizes number of swaps

## Features

- **Performance tracking:** Counts comparisons, swaps, and execution time (microseconds)
- **CSV output:** Generates detailed performance data for analysis
- **Extensible OOP framework:** Algorithm classes extend abstract `Sorter` base class
- **Automated testing:** Tests algorithms on datasets from 0 to n elements
- **Scalable:** Handles datasets from small (10 elements) to very large (1,000,000 elements)

## Architecture

```
Sorter (abstract base class)
├── Insertion
├── Merge
├── Bubble
└── Selection
```

**Key classes:**
- `Sorter.java` - Abstract base with counting mechanisms (`swap()`, `lt()`, `eq()`, etc.)
- `SortRunner.java` - Test runner and CSV generator
- `Main.java` - Entry point, reads input file and runs both parts
- Algorithm classes - Implement `sort()` method with their specific logic

## Usage

**Compile all files:**
```bash
javac *.java
```

**Run on input file:**
```bash
java Main inputs/random_100
```

**Generates two types of output:**

1. **Sorted result files** (Part 1):
   - `random_100_insertion.out`
   - `random_100_merge.out`
   - `random_100_bubble.out`
   - `random_100_selection.out`

2. **Performance CSV** (Part 2):
   - `random_100_results.csv`

## CSV Output Format

The CSV file contains performance metrics for each algorithm at different data sizes:

```csv
n, insert_cmp, insert_swaps, insert_time, merge_cmp, merge_swaps, merge_time
0, 0, 0, 1, 0, 0, 0
1, 0, 0, 0, 0, 0, 1
2, 1, 0, 2, 5, 2, 5
...
```

Where:
- `n` - Number of elements sorted
- `*_cmp` - Number of comparisons
- `*_swaps` - Number of swaps
- `*_time` - Execution time in microseconds

## Test Data

The `inputs/` directory contains test datasets:
- **Random data:** `random_10`, `random_100`, `random_1000`, `random_10000`, `random_100000`, `random_1000000`
- **Nearly sorted data:** `nearly_sorted_10`, `nearly_sorted_100`, etc.

Use nearly-sorted data to observe how different algorithms perform on partially ordered input.

## Implementation Notes

**Comparison counting:**
- All comparisons go through `Sorter` methods: `lt()`, `leq()`, `gt()`, `geq()`, `eq()`
- Loop condition checks are NOT counted
- Only element-to-element comparisons are counted

**Swap counting:**
- All swaps go through `Sorter.swap()` method
- Merge Sort does NOT count swaps (uses merging instead of swapping)

**Time measurement:**
- Measured in microseconds for precision
- Includes only the sorting operation itself
- Excludes array copying and initialization

## Performance Insights

From experimental analysis (see `report.pdf` for details):

- **Merge Sort:** Best on large random datasets (consistent O(n log n))
- **Insertion Sort:** Excellent on nearly-sorted data (approaches O(n))
- **Bubble Sort:** Generally inefficient, included for educational comparison
- **Selection Sort:** Predictable behavior but slow on large datasets

## Files

- **Implementation:** `Main.java`, `Sorter.java`, `SortRunner.java`
- **Algorithms:** `Insertion.java`, `Merge.java`, `Bubble.java`, `Selection.java`
- **Documentation:** `assignment.pdf` (Norwegian), `report.pdf` (Norwegian with performance graphs)
- **Test data:** `inputs/` directory

---

**Note:** This is academic coursework. Variable names and comments in Norwegian are retained from the original academic context.