# Pascal’s Triangle (Recursive Implementations)

## Overview
This project generates Pascal’s Triangle using recursion. Two implementations are included to explore different design approaches and their tradeoffs.

Both versions calculate the triangle values recursively. The difference lies in how the output is structured and displayed.

## Implementations

### Recursive with Loop-Based Layout
**`PascalTriangleRecursive.java`**

This version uses recursion to compute the values in Pascal’s Triangle, while a loop is used to handle formatting and layout of the output.

This approach separates concerns:
- recursion handles the mathematical logic
- iteration handles presentation

It results in clearer output formatting and is generally easier to read and maintain.

### Fully Recursive (No Loops)
**`PascalTriangleRecursiveNoLoop.java`**

This version avoids loops entirely, using recursion for both value calculation and layout.

The goal of this implementation was to explore how far recursion can be pushed, including tasks that are often more naturally handled with iteration. While less practical for real-world use, it demonstrates a deeper understanding of recursion and control flow.

## Concepts Demonstrated
- Recursive problem decomposition
- Base cases and recursive relationships
- Tradeoffs between recursion and iteration
- Readability vs. theoretical purity
- Output formatting strategies

## Reflection
Implementing both versions highlighted that the “best” solution depends on context. The loop-assisted version is clearer and more maintainable, while the fully recursive version serves as a useful exercise in understanding recursion at a deeper level.
