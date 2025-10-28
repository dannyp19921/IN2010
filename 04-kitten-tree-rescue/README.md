# Kitten Tree Rescue

**Course:** IN2010 - Algorithms and Data Structures (UiO)  
**Assignment:** Innlevering 3 - Part 1, Fall 2024  
**Topic:** Tree Traversal, Parent Pointers, Path Finding

## Problem Description

A kitten is stuck in a tree! Given an upside-down tree structure where the kitten is at the top and the ground (root) is at the bottom, find and print the path from the kitten down to the ground.

### Tree Structure

In this problem, trees are represented upside-down:
- **Top nodes** (leaves in traditional trees) represent branches where the kitten might be
- **Bottom node** (root in traditional terms) represents the ground/trunk
- Each node knows its **parent** (the node below it)
- The kitten's location is given as a specific node value

### Task

Given a tree structure and the kitten's position, output the sequence of nodes from the kitten down to the ground (following parent pointers).

## Implementation Details

### Kattunge.java

**Data Structure:**
- Uses `HashMap<Integer, Node>` for O(1) node lookup by value
- Each `Node` contains:
  - `int data` - the node's value
  - `Node parent` - pointer to parent (node below)
  
**Algorithm:**
1. Read tree structure from stdin (child-parent relationships)
2. Identify the kitten's location (given node value)
3. Follow parent pointers from kitten to root
4. Print the path as space-separated integers

**Complexity:**
- Time: O(n) for building tree + O(h) for path traversal where h is tree height
- Space: O(n) for HashMap storage

## Input Format

```
N K                          # N = number of edges, K = kitten's node value
child_1 parent_1             # Edge from child to parent
child_2 parent_2
...
child_N parent_N
```

**Example:**
```
4 14                         # 4 edges, kitten at node 14
14 19                        # Node 14's parent is 19
25 24                        # Node 25's parent is 24
19 23                        # Node 19's parent is 23
23 24                        # Node 23's parent is 24
```

In this tree:
```
        14 (kitten here!)
        |
        19
        |
        23
        |
        24 (ground/root)
       /
      25
```

## Output Format

Space-separated sequence of node values from kitten to ground:

```
14 19 23 24
```

## How to Run

### Compile
```bash
javac Kattunge.java
```

### Run with Example Input
```bash
java Kattunge < inputs/eksempel_input
# Expected output: 14 19 23 24 25
```

### Test with Input Files
```bash
# Test individual inputs
java Kattunge < inputs/input_1

# Automated testing (no output = test passed)
java Kattunge < inputs/input_1 | cmp - outputs/output_1
java Kattunge < inputs/input_2 | cmp - outputs/output_2
```

## Testing

Available test cases:
- `inputs/eksempel_input` - Small example from assignment
- `inputs/input_1` through `input_5` - Generated test cases

Verify correctness by comparing with expected outputs:

```bash
# Run all tests
for i in eksempel 1 2 3 4 5; do
    echo "Testing input_$i..."
    java Kattunge < inputs/input_${i/eksempel/eksempel_input} | \
    cmp - outputs/output_${i/eksempel/eksempel_output} && echo "✓ Passed"
done
```

## Project Structure

```
04-kitten-tree-rescue/
├── Kattunge.java           # Main program (upside-down tree traversal)
├── inputs/                 # Test input files
│   ├── eksempel_input
│   ├── input_1
│   ├── input_2
│   ├── input_3
│   ├── input_4
│   └── input_5
├── outputs/                # Expected output files
│   ├── eksempel_output
│   ├── output_1
│   ├── output_2
│   ├── output_3
│   ├── output_4
│   └── output_5
├── tester-info.org        # Original testing instructions
└── README.md              # This file
```

## Key Learning Outcomes

**Tree Data Structures:**
- Understanding parent pointers in tree structures
- Working with non-traditional tree orientations (upside-down)
- Path finding through pointer following

**Data Structure Design:**
- Using HashMap for efficient node lookup
- Bidirectional relationships in tree structures
- Trade-offs between different tree representations

**Algorithm Skills:**
- Simple path traversal algorithms
- Building trees from edge descriptions
- Input/output handling with standard streams

---

**Note:** This is coursework from IN2010 at the University of Oslo (Fall 2024). The implementation demonstrates fundamental concepts in tree traversal and parent pointer manipulation.
