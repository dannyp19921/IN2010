# Advanced Sorting Algorithms

**Course:** IN2010 - Algorithms and Data Structures (UiO)  
**Assignment:** Innlevering 3 - Part 2, Fall 2024  
**Topic:** Comparative Analysis of Sorting Algorithms

## Overview

This project implements and benchmarks **8 different sorting algorithms** to analyze their performance characteristics on various input types and sizes. The framework automatically runs all algorithms, generates sorted outputs, and collects timing metrics for comparative analysis.

## Implemented Algorithms

### Comparison-Based Algorithms

**1. Insertion Sort** (`Insertion.java`)
- **Best case:** O(n) - already sorted
- **Average/Worst:** O(n²)
- **Use case:** Small arrays, nearly sorted data

**2. Merge Sort** (`Merge.java`)
- **All cases:** O(n log n)
- **Space:** O(n)
- **Use case:** Stable sort, guaranteed performance

**3. Quick Sort** (`Quick.java`)
- **Average:** O(n log n)
- **Worst:** O(n²) - poor pivot selection
- **Use case:** General purpose, fast in practice

**4. Heap Sort** (`Heap.java`)
- **All cases:** O(n log n)
- **Space:** O(1)
- **Use case:** Guaranteed performance, in-place

**5. Bubble Sort** (`Bubble.java`)
- **Best:** O(n) - already sorted
- **Average/Worst:** O(n²)
- **Use case:** Educational, very small arrays

**6. Selection Sort** (`Selection.java`)
- **All cases:** O(n²)
- **Use case:** Minimal swaps needed

### Non-Comparison Algorithms

**7. Bucket Sort** (`Bucket.java`)
- **Average:** O(n + k) where k is number of buckets
- **Use case:** Uniformly distributed data

**8. Radix Sort** (`Radix.java`)
- **Time:** O(d × n) where d is number of digits
- **Use case:** Fixed-length integer keys

## Framework Components

### Main.java
Entry point that:
- Reads input file specified as command-line argument
- Parses integers from file into array
- Invokes SortRunner with the data

### SortRunner.java
Testing framework that:
- **Part 1:** Runs all algorithms on complete input, generates `.out` files
- **Part 2:** Incremental testing with timing data, generates `.csv` files
- Enforces time limit (100ms default) to prevent hanging on slow algorithms
- Collects performance metrics for analysis

### Sorter.java
Abstract base class defining the sorting interface:
- `sort(int[] A)` - main sorting method
- `algorithmName()` - returns algorithm name for output files

## How to Run

### Compile
```bash
javac *.java
```

### Run Performance Tests

**Basic usage:**
```bash
java Main inputs/random_100
```

**This generates:**
- `random_100_insertion.out` - Sorted array from Insertion Sort
- `random_100_merge.out` - Sorted array from Merge Sort
- ... (one .out file per algorithm)
- `random_100_results.csv` - Timing data for all algorithms

### Test Multiple Input Sizes

```bash
# Small inputs
java Main inputs/random_10
java Main inputs/random_100
java Main inputs/random_1000

# Large inputs
java Main inputs/random_10000
java Main inputs/random_100000
```

### Test Different Input Types

```bash
# Random data (worst case for some algorithms)
java Main inputs/random_10000

# Nearly sorted data (best case for adaptive algorithms)
java Main inputs/nearly_sorted_10000
```

## Input Format

Input files contain one integer per line:
```
42
17
93
8
...
```

## Output Files

### .out Files
Contain sorted arrays, one number per line:
```
8
17
42
93
...
```

### .csv Files
Performance metrics with columns:
- n (input size)
- algorithm1_time
- algorithm2_time
- ...

Example:
```csv
n,insertion,merge,quick,heap,bubble,selection,bucket,radix
100,0.45,0.82,0.63,1.21,2.34,1.87,0.91,0.56
200,1.23,1.54,1.12,2.43,8.91,7.32,1.82,1.03
```

## Available Test Data

### Random Inputs
- `random_10` to `random_1000000`
- Completely random integers
- Tests average/worst case performance

### Nearly Sorted Inputs
- `nearly_sorted_10` to `nearly_sorted_1000000`
- Mostly sorted with few out-of-place elements
- Tests best case and adaptive behavior

## Performance Analysis

### Expected Results

**On Random Data:**
- **Fastest:** Quick Sort, Merge Sort, Heap Sort
- **Medium:** Bucket Sort, Radix Sort (depends on distribution)
- **Slowest:** Insertion Sort, Bubble Sort, Selection Sort (O(n²))

**On Nearly Sorted Data:**
- **Fastest:** Insertion Sort (adaptive, nearly O(n))
- **Medium:** Quick Sort, Merge Sort, Heap Sort
- **Slowest:** Selection Sort, Bubble Sort (always O(n²))

### Key Insights

**Stability:**
- Stable: Insertion, Merge, Bubble, Bucket, Radix
- Unstable: Quick, Heap, Selection

**Space Complexity:**
- In-place: Insertion, Quick, Heap, Bubble, Selection
- Extra space: Merge (O(n)), Bucket, Radix

**Practical Considerations:**
- Quick Sort fastest on average despite O(n²) worst case
- Insertion Sort excellent for small or nearly sorted arrays
- Merge Sort guarantees O(n log n) but uses extra space
- Radix/Bucket require specific data characteristics

## Project Structure

```
05-advanced-sorting-algorithms/
├── Main.java                  # Entry point
├── SortRunner.java            # Testing framework
├── Sorter.java                # Abstract base class
├── Insertion.java             # Simple O(n²) adaptive
├── Merge.java                 # O(n log n) stable
├── Quick.java                 # O(n log n) average, fast
├── Heap.java                  # O(n log n) in-place
├── Bubble.java                # Simple O(n²)
├── Selection.java             # O(n²) minimum swaps
├── Bucket.java                # O(n + k) for uniform data
├── Radix.java                 # O(d × n) for integers
├── inputs/                    # Test data
│   ├── random_10
│   ├── random_100
│   ├── random_1000
│   ├── random_10000
│   ├── random_100000
│   ├── nearly_sorted_10
│   ├── nearly_sorted_100
│   └── ... (pre-generated outputs and CSV files)
├── assignment.pdf             # Original assignment
└── README.md                  # This file
```

## Configuration

Edit `SortRunner.java` to adjust:

```java
// Select algorithms to test
static final Sorter[] ALGS1 = { new Insertion(), new Merge(), ... };

// Time limit per sort (milliseconds)
static final long TIME_LIMIT_MS = 100;

// Increment for incremental testing
static final int INCREMENT = 1;
```

## Key Learning Outcomes

**Algorithm Analysis:**
- Empirical verification of theoretical complexity
- Trade-offs between time, space, and stability
- Impact of input characteristics on performance

**Comparative Study:**
- When to use each algorithm
- Understanding worst/average/best cases
- Real-world performance vs. theoretical bounds

**Software Engineering:**
- Benchmarking methodology
- Abstract class design patterns
- Automated testing frameworks

---

**Note:** This is coursework from IN2010 at the University of Oslo (Fall 2024). The implementations demonstrate comparative analysis of fundamental sorting algorithms with empirical performance testing.
