# CS216L Project 1: Advanced Task Manager Application

## Group Information
* **Group Name:** KBA
* **Members:**
  1. Khadija Nafeh (Group Leader / Repository Admin)
  2. Aiman Arif
  3. Bushra Rauf

## Project Title
**Colorized Command-Line Task Manager**

## Description
A terminal-based colorized task management dashboard built using custom memory-managed data structures in Java. It allows dynamic task creation, priority sorting, quick linear searches, tracking, and sequential state rollbacks using an action history layers system. The application is built entirely from scratch without using any built-in Java collection libraries (like ArrayList or Vector).

## Data Structures Used
* **Singly Linked List (`TaskList`):** Manages live operations on the main sequence of task elements.
* **Custom Memory Stack (`UndoStack`):** Tracks record changes to drive real-time "Undo Last Action" commands using Last-In, First-Out (LIFO) logic.

## Feature Summary
1. **Add Task:** Dynamically appends a new structural node to the tail of the linked list tracking matrix.
2. **Delete Task:** Traverses and safely clears target identification keys out of system memory.
3. **Search Task:** Uses Linear Search to evaluate exact match criteria across title strings.
4. **Sort Tasks:** Dynamically re-orders system queues using a custom Bubble Sort algorithm matrix.
5. **View Tasks:** Visualizes systemic properties using a custom ANSI-colored ASCII table dashboard layout.
6. **Undo Management:** Pops recent history flags down off the stack layer to restore previous states.

## Algorithmic Complexity Table

| Operation | Target Object / Routine | Best Case Time | Worst Case Time | Complexity Notes |
| :--- | :--- | :--- | :--- | :--- |
| **Insert** | Singly Linked List Tail | O(1) | O(N) | Requires list traversal to find the tail node. |
| **Delete** | Singly Linked List Search | O(1) | O(N) | O(1) if head matches, otherwise requires list traversal. |
| **Search** | Linear Search Algorithm | O(1) | O(N) | Scans nodes linearly until the title match condition is hit. |
| **Sort** | Bubble Sort Algorithm | O(N) | O(N²) | O(N) if already sorted; O(N²) if reversed. |
| **Push** | Custom Stack Layer | O(1) | O(1) | Direct head replacement logic sequence. |
| **Pop** | Custom Stack Layer | O(1) | O(1) | Drops top item references instantly. |

## GitHub Repository Link
[👉 Click here to view our TaskManager.java Source Code](https://github.com)
