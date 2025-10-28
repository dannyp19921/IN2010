# IMDB Graph - Six Degrees of Separation

**Course:** IN2010 - Algorithms and Data Structures (UiO)  
**Assignment:** Innlevering 4, Fall 2024  
**Topic:** Graph Algorithms, BFS, Weighted Shortest Path, Connected Components

## Overview

This project builds and analyzes a large-scale graph based on the IMDB database, where actors are nodes and movies create connections between them. It implements classic graph algorithms to explore relationships in the film industry, including the famous "Six Degrees of Kevin Bacon" concept.

## Problem Description

### Graph Representation

The IMDB graph is a **projection graph** (also called a co-actor graph):
- **Nodes:** Actors
- **Edges:** Two actors are connected if they appeared in the same movie
- **Edge weights:** Rating = 10.0 - movie rating (lower weight = better movie)

This is a projection from a bipartite graph (actors ↔ movies) to a single-mode graph (actors ↔ actors).

### Four Core Problems

**Part 1: Graph Construction**
- Build the graph from TSV data files
- Count total nodes (actors) and edges (connections)
- Verify graph structure

**Part 2: Shortest Path (Six Degrees)**
- Find shortest path between any two actors
- Uses BFS (Breadth-First Search)
- Classic "Six Degrees of Separation" problem

**Part 3: Chillest Path**
- Find path with best average movie rating
- Uses weighted shortest path algorithm
- Optimizes for movie quality, not just path length

**Part 4: Connected Components**
- Identify disconnected subgraphs
- Count actors in each component
- Uses DFS (Depth-First Search)

## Implementation Details

### Main.java
Entry point that:
- Loads actor and movie data from TSV files
- Builds the graph structure
- Executes all four tasks sequentially
- Reports timing information

### IMDBGraph.java
Core graph implementation (14KB - comprehensive):

**Graph Construction:**
- `buildGraph()` - Creates actor-to-actor connections via movies
- `addConnection()` - Adds weighted edge between two actors
- `getNodeCount()` - Returns total number of actors
- `countGraphEdges()` - Counts total connections

**Shortest Path (Part 2):**
- `findShortestPath(startId, endId)` - BFS-based shortest path
- `reconstructPath()` - Builds path from BFS parent pointers
- Returns list of actor names with connecting movies

**Chillest Path (Part 3):**
- `findChillestPath(startId, endId)` - Weighted shortest path
- Uses priority queue (similar to Dijkstra's algorithm)
- `reconstructChillestPath()` - Builds optimal quality path
- Returns path with average movie rating

**Connected Components (Part 4):**
- `findConnectedComponents()` - Identifies all components
- `dfs(actor, visited)` - Recursive depth-first search
- `printComponentSizes()` - Statistics about component distribution

### Supporting Classes

**IMDBData.java**
Data container managing:
- HashMap of actors (by ID)
- HashMap of movies (by ID)
- Efficient O(1) lookups

**IMDBFileReader.java**
File parser for TSV data:
- `readActorsFile(filename)` - Parses actors.tsv
- `readMoviesFile(filename)` - Parses movies.tsv
- Handles large files efficiently

**Actor.java**
Actor node representation:
- ID, name, list of movies
- Adjacency list (connections to other actors)

**Movie.java**
Movie data:
- ID, title, rating
- Used to establish connections and calculate weights

## Algorithm Complexity

| Operation | Algorithm | Time Complexity | Space Complexity |
|-----------|-----------|-----------------|------------------|
| Build Graph | Iteration | O(M × A²) worst | O(V + E) |
| Shortest Path | BFS | O(V + E) | O(V) |
| Chillest Path | Weighted BFS | O(E log V) | O(V) |
| Components | DFS | O(V + E) | O(V) |

Where:
- V = number of actors (nodes)
- E = number of connections (edges)
- M = number of movies
- A = average actors per movie

## How to Run

### Compile
```bash
javac *.java
```

### Run with Full Dataset
```bash
java Main

# Output example:
# Velkommen til oblig 4!
# Dette kan ta litt tid...
# 
# Oppgave 1:
# Nodes: 119005
# Edges: 487838
#
# Oppgave 2:
# Shortest path from nm2255973 to nm0000460:
# Actor1 === [Movie] ===> Actor2 === [Movie] ===> ...
#
# Oppgave 3:
# Chillest path (rating: 8.95)
# ...
#
# Oppgave 4:
# Connected Components:
# Component size 1: 42 actors
# Component size 2: 15 actors
# ...
```

### Test with Marvel Dataset
For faster testing, modify Main.java to use smaller dataset:

```java
fileReader.readMoviesFile("marvel_movies.tsv");
fileReader.readActorsFile("marvel_actors.tsv");
```

Then run:
```bash
java Main
```

Marvel dataset has ~10 actors and ~5 movies, completing in milliseconds.

## Data Files

### Full IMDB Dataset

**actors.tsv** (11 MB)
- Format: `actor_id\tactor_name\tmovie_id_1,movie_id_2,...`
- ~119,000 actors
- Real IMDB data

**movies.tsv** (3.7 MB)
- Format: `movie_id\tmovie_title\trating`
- ~44,000 movies
- Ratings from 0.0-10.0

### Marvel Test Dataset

**marvel_actors.tsv** (1 KB)
- Subset with Marvel Cinematic Universe actors
- Perfect for quick testing and debugging

**marvel_movies.tsv** (314 bytes)
- Marvel movies connecting the actors
- Visualized in included PDF files

### Visualizations

**marvelgraph_labeled.pdf**
- Shows Marvel graph with actor names and movie titles
- Useful for understanding graph structure

**marvelgraph_nodetypes.pdf**
- Alternative visualization showing bipartite structure
- Illustrates actor-movie relationships

## Example Queries

### Finding Famous Connections

```java
// Kevin Bacon to some actor
List<String> path = graph.findShortestPath("nm0000102", "nm0000460");

// Tom Hanks to Morgan Freeman
List<String> path = graph.findShortestPath("nm0000158", "nm0000151");
```

### Quality Path

```java
// Find path through best-rated movies
List<String> chillPath = graph.findChillestPath("nm0000102", "nm0000460");
```

## Project Structure

```
06-imdb-graph/
├── Main.java                    # Entry point
├── IMDBGraph.java               # Core graph algorithms (all 4 parts)
├── IMDBData.java                # Data container
├── IMDBFileReader.java          # TSV file parser
├── Actor.java                   # Actor node
├── Movie.java                   # Movie data
├── actors.tsv                   # Full IMDB actor dataset (11 MB)
├── movies.tsv                   # Full IMDB movie dataset (3.7 MB)
├── marvel_actors.tsv            # Test dataset (Marvel actors)
├── marvel_movies.tsv            # Test dataset (Marvel movies)
├── marvelgraph_labeled.pdf      # Graph visualization with labels
├── marvelgraph_nodetypes.pdf    # Bipartite graph visualization
├── data-info.org                # Dataset documentation
└── README.md                    # This file
```

## Key Learning Outcomes

**Graph Algorithms:**
- Building graphs from real-world data
- BFS for unweighted shortest path
- Weighted shortest path (Dijkstra-style)
- DFS for connected components

**Data Structures:**
- Adjacency list representation
- HashMap for O(1) lookups
- Priority queue for weighted algorithms

**Real-World Applications:**
- Social network analysis
- Six Degrees of Separation
- Recommendation systems
- Network connectivity analysis

**Performance Considerations:**
- Handling large datasets (100K+ nodes)
- Memory-efficient graph storage
- Algorithm optimization for sparse graphs

## Performance Notes

**Full Dataset:**
- Loading time: ~3-5 seconds
- Graph building: ~5-10 seconds
- Shortest path: ~0.1-1 second
- Total runtime: ~10-20 seconds

**Marvel Dataset:**
- Total runtime: <1 second
- Perfect for development and testing

## Interesting Facts

The graph demonstrates real-world "small world" properties:
- Most actor pairs connected within 4-6 steps
- Large strongly connected component
- Some isolated actors/small components
- Hub actors (e.g., Kevin Bacon) with many connections

---

**Note:** This is coursework from IN2010 at the University of Oslo (Fall 2024). The implementation demonstrates fundamental graph algorithms applied to real-world social network data from IMDB.
