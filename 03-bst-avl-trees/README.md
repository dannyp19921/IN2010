# Binary Search Trees and AVL Trees

**Course:** IN2010 - Algorithms and Data Structures (UiO)  
**Assignment:** Innlevering 2, Fall 2024  
**Topics:** Binary Search Trees, Self-Balancing Trees, Tree Construction

## Overview

This assignment explores efficient set implementations using tree-based data structures. It consists of three parts:

1. **Part A:** Implementing a Set ADT using a standard Binary Search Tree (BST)
2. **Part B:** Implementing the same Set ADT using a self-balancing AVL Tree
3. **Part C:** Building perfectly balanced BSTs from sorted input using heap-based algorithms

## Problem Description

### Part A & B: Efficient Sets

The Set abstract data type must support four operations:
- `contains(x)` - Check if element x exists in the set
- `insert(x)` - Add element x to the set (no duplicates)
- `remove(x)` - Remove element x from the set
- `size()` - Return the number of elements in the set

**Requirements:**
- `contains`, `insert`, and `remove` must be **O(log n)** assuming balanced trees
- `size` must be **O(1)**
- Input: Up to 1,000,000 operations on integers between 1 and 1,000,000,000

### Part C: Building Balanced Trees

Given a sorted array of integers, output them in an order that guarantees a perfectly balanced BST when inserted sequentially using standard BST insertion.

**Definition of perfectly balanced:** A binary tree where there are exactly 2^d nodes at depth d for all 0 ≤ d < h, where h is the tree height. Equivalently, the shortest and longest paths from root to empty subtree differ by at most 1.

**Constraint:** The heap-based solution may only use heap data structures (but can use multiple heaps).

## Implementation Details

### BSTMain.java (Part A)
Main program for Set ADT using Binary Search Tree:
- Contains inline BinarySearchTree class implementation
- Reads N operations from stdin
- Supports insert, remove, contains, size operations
- O(h) operations where h is tree height (worst case O(n))
- Uses recursive insertion and deletion with parent pointers

### AVLMain.java (Part B)
Main program for Set ADT using AVL Tree:
- Uses the existing AVLtree.java class
- Same interface as BSTMain but with guaranteed O(log n) performance
- Self-balancing through rotations after insertions/deletions

### Oblig2.java (Part C)
Heap-based tree construction algorithm:
- Reads sorted integers from stdin
- Uses recursive divide-and-conquer with PriorityQueues
- Outputs insertion order that creates perfectly balanced BST
- Only uses heap data structure as required by assignment
- Works by repeatedly selecting middle elements

### Supporting Files

**AVLtree.java**
Complete AVL tree implementation:
- Self-balancing with rotation methods (leftRotate, rightRotate)
- Height tracking and balance factor calculation
- Used by AVLMain.java for Part B

**BinarySearchTree.java**
Reference BST implementation (mostly commented code and notes)

**BalanceChecker.java**
Utility for verifying tree balance properties:
- Checks if a BST is perfectly balanced
- Compares minimum and maximum height from root
- Useful for validating Part C output

## Time Complexity Analysis

| Operation | BST (worst case) | BST (balanced) | AVL Tree |
|-----------|------------------|----------------|----------|
| Insert    | O(n)            | O(log n)       | O(log n) |
| Remove    | O(n)            | O(log n)       | O(log n) |
| Contains  | O(n)            | O(log n)       | O(log n) |
| Size      | O(1)            | O(1)           | O(1)     |

**Key insight:** AVL trees guarantee logarithmic performance even with adversarial input patterns, while standard BSTs can degrade to linear time with sorted input.

## How to Run

### Compile
```bash
javac *.java
```

### Part A: BST Set Operations
```bash
# Run BST implementation with example input
java BSTMain < inputs/eksempel_input

# Expected output:
# true
# false
# false
# 2

# Test with larger inputs
java BSTMain < inputs/input_100
java BSTMain < inputs/input_1000
```

### Part B: AVL Set Operations
```bash
# Run AVL implementation with example input
java AVLMain < inputs/eksempel_input

# Expected output:
# true
# false
# false
# 2

# Test with larger inputs (guaranteed O(log n) performance)
java AVLMain < inputs/input_100
java AVLMain < inputs/input_10000
```

### Part C: Balanced Tree Construction
```bash
# Build balanced insertion order using heap algorithm
java Oblig2 < inputs/input_10

# Expected output for input 0,1,2,3,4,5,6,7,8,9,10:
# 5
# 8
# 2
# 10
# 9
# 6
# 7
# 4
# 3
# 1
# 0
```

### Input Format (Parts A & B)
```
9                    # Number of operations
insert 1
insert 2
insert 3
insert 1             # Duplicate (ignored)
contains 1           # Outputs: true
contains 0           # Outputs: false
remove 1
contains 1           # Outputs: false (after removal)
size                 # Outputs: 2
```

### Input Format (Part C)
```
0                    # Sorted integers, one per line
1
2
3
4
5
6
7
8
9
10
```

## Testing

Verify correctness by comparing output with expected results:

```bash
# Test Part A (BST)
java BSTMain < inputs/eksempel_input
# Should output: true, false, false, 2

# Test Part B (AVL)
java AVLMain < inputs/eksempel_input
# Should output: true, false, false, 2

# Test Part C (Balanced Tree Builder)
java Oblig2 < inputs/input_10

# Automated testing with comparison
java BSTMain < inputs/input_100 > my_output.txt
cmp my_output.txt outputs/output_100
# No output means test passed!
```

**Available test cases:**
- `inputs/eksempel_input` - Small example for Parts A & B (9 operations)
- `inputs/input_10` through `input_100000` - Varying sizes for all parts
- `outputs/eksempel_output` - Expected output for eksempel_input
- `outputs/output_X` - Expected outputs for corresponding input files

## Project Structure

```
03-bst-avl-trees/
├── BSTMain.java             # Part A: Set with BST (main program)
├── AVLMain.java             # Part B: Set with AVL tree (main program)
├── Oblig2.java              # Part C: Balanced tree builder (main program)
├── AVLtree.java             # AVL tree implementation (used by AVLMain)
├── BinarySearchTree.java    # BST reference code
├── BalanceChecker.java      # Tree validation utility
├── inputs/                  # Test input files
│   ├── eksempel_input       # Example for Parts A & B
│   ├── input_10             # Small test (all parts)
│   ├── input_100
│   ├── input_1000
│   ├── input_10000
│   └── input_100000         # Large scale test
├── outputs/                 # Expected output files
│   ├── eksempel_output
│   ├── output_10
│   └── ...
├── assignment.pdf           # Original assignment description
└── README.md               # This file
```

## Key Learning Outcomes

**Tree Data Structures:**
- Understanding BST properties and limitations
- Height-balanced trees and rotation operations
- Trade-offs between simplicity and guaranteed performance

**Algorithm Design:**
- Recursive problem decomposition
- Tree construction strategies
- Creative use of auxiliary data structures (heaps for tree building)

**Complexity Analysis:**
- Worst-case vs. average-case performance
- Impact of input patterns on tree structure
- Importance of balance for guaranteed efficiency

**Implementation Skills:**
- Managing tree rotations and rebalancing
- Handling edge cases in node removal
- Testing tree invariants programmatically

---

**Note:** This is coursework from IN2010 at the University of Oslo (Fall 2024). The implementations demonstrate fundamental concepts in tree-based data structures and self-balancing techniques essential for efficient set operations.# Binary Search Trees and AVL Trees

**Course:** IN2010 - Algorithms and Data Structures (UiO)  
**Assignment:** Innlevering 2, Fall 2024  
**Topics:** Binary Search Trees, Self-Balancing Trees, Tree Construction

## Overview

This assignment explores efficient set implementations using tree-based data structures. It consists of three parts:

1. **Part A:** Implementing a Set ADT using a standard Binary Search Tree (BST)
2. **Part B:** Implementing the same Set ADT using a self-balancing AVL Tree
3. **Part C:** Building perfectly balanced BSTs from sorted input using heap-based algorithms

## Problem Description

### Part A & B: Efficient Sets

The Set abstract data type must support four operations:
- `contains(x)` - Check if element x exists in the set
- `insert(x)` - Add element x to the set (no duplicates)
- `remove(x)` - Remove element x from the set
- `size()` - Return the number of elements in the set

**Requirements:**
- `contains`, `insert`, and `remove` must be **O(log n)** assuming balanced trees
- `size` must be **O(1)**
- Input: Up to 1,000,000 operations on integers between 1 and 1,000,000,000

### Part C: Building Balanced Trees

Given a sorted array of integers, output them in an order that guarantees a perfectly balanced BST when inserted sequentially using standard BST insertion.

**Definition of perfectly balanced:** A binary tree where there are exactly 2^d nodes at depth d for all 0 ≤ d < h, where h is the tree height. Equivalently, the shortest and longest paths from root to empty subtree differ by at most 1.

**Constraint:** The heap-based solution may only use heap data structures (but can use multiple heaps).

## Implementation Details

### BinarySearchTree.java
Standard BST implementation with:
- Recursive insertion maintaining BST property
- Node removal with three cases (leaf, one child, two children)
- In-order traversal for sorted access
- O(h) operations where h is tree height

### AVLtree.java
Self-balancing BST using AVL invariant (balance factor ∈ {-1, 0, 1}):
- Height tracking for each node
- Four rotation cases (LL, RR, LR, RL)
- Automatic rebalancing after insertions and deletions
- Guaranteed O(log n) height

### Oblig2.java
Heap-based tree construction algorithm:
- Reads sorted integers from stdin
- Uses recursive approach with priority queues
- Outputs insertion order for perfectly balanced tree
- Demonstrates creative use of heaps beyond sorting

### BalanceChecker.java
Utility for verifying tree balance properties:
- Checks height balance invariants
- Validates BST ordering properties
- Useful for testing and debugging

## Time Complexity Analysis

| Operation | BST (worst case) | BST (balanced) | AVL Tree |
|-----------|------------------|----------------|----------|
| Insert    | O(n)            | O(log n)       | O(log n) |
| Remove    | O(n)            | O(log n)       | O(log n) |
| Contains  | O(n)            | O(log n)       | O(log n) |
| Size      | O(1)            | O(1)           | O(1)     |

**Key insight:** AVL trees guarantee logarithmic performance even with adversarial input patterns, while standard BSTs can degrade to linear time with sorted input.

## How to Run

### Compile
```bash
javac *.java
```

### Part A & B: Set Operations
```bash
# Test BST implementation
java BinarySearchTree < inputs/input_100

# Test AVL implementation
java AVLtree < inputs/input_100
```

### Part C: Balanced Tree Construction
```bash
# Build balanced insertion order
java Oblig2 < inputs/eksempel_input
```

### Input Format (Parts A & B)
```
N                    # Number of operations
insert 5
insert 3
contains 5           # Outputs: true
contains 10          # Outputs: false
remove 3
size                 # Outputs: 1
```

### Input Format (Part C)
```
0                    # Sorted integers
1
2
...
10
```

### Output (Part C)
```
5                    # Middle element first
8                    # Then middle of right half
10                   # And so on...
```

## Testing

Verify correctness by comparing output with expected results:

```bash
# Test specific input size
java Oblig2 < inputs/input_100 | cmp - outputs/output_100

# If no output, test passed!
# If there's a difference, it will show where outputs diverge
```

**Available test cases:**
- `eksempel_input` - Small example from assignment (11 elements)
- `input_10` through `input_100000` - Varying sizes for performance testing

## Project Structure

```
03-bst-avl-trees/
├── BinarySearchTree.java    # Standard BST implementation
├── AVLtree.java             # Self-balancing AVL tree
├── Oblig2.java              # Heap-based balanced tree builder
├── BalanceChecker.java      # Tree validation utility
├── inputs/                  # Test input files
│   ├── eksempel_input
│   ├── input_10
│   ├── input_100
│   └── ...
├── outputs/                 # Expected output files
├── assignment.pdf           # Original assignment description
└── README.md               # This file
```

## Key Learning Outcomes

**Tree Data Structures:**
- Understanding BST properties and limitations
- Height-balanced trees and rotation operations
- Trade-offs between simplicity and guaranteed performance

**Algorithm Design:**
- Recursive problem decomposition
- Tree construction strategies
- Creative use of auxiliary data structures (heaps for tree building)

**Complexity Analysis:**
- Worst-case vs. average-case performance
- Impact of input patterns on tree structure
- Importance of balance for guaranteed efficiency

**Implementation Skills:**
- Managing tree rotations and rebalancing
- Handling edge cases in node removal
- Testing tree invariants programmatically

---

**Note:** This is coursework from IN2010 at the University of Oslo (Fall 2024). The implementations demonstrate fundamental concepts in tree-based data structures and self-balancing techniques essential for efficient set operations.
