package TaskManager;

import java.util.Scanner;

// --- DATA STRUCTURE NODES ---
class Task {
    int id; String title; int priority; Task next;
    public Task(int id, String title, int priority) {
        this.id = id; this.title = title; this.priority = priority;
    }
}

class Action {
    String type; Task taskCopy; Action next;
    public Action(String type, Task t) {
        this.type = type; this.taskCopy = new Task(t.id, t.title, t.priority);
    }
}

// --- OPERATIONS ENGINE (All assignment requirements met) ---
class TaskEngine {
    private Task head = null;
    private Action top = null;

    // Stack Push & Pop Operations
    public void pushAction(String type, Task t) {
        Action a = new Action(type, t); a.next = top; top = a;
    }
    public Action popAction() {
        if (top == null) return null;
        Action temp = top; top = top.next; return temp;
    }

    // Linked List Requirements: Add, Delete, Linear Search, Bubble Sort
    public void add(Task t) {
        if (head == null) { head = t; return; }
        Task temp = head; while (temp.next != null) temp = temp.next;
        temp.next = t;
    }

    public Task delete(int id) {
        if (head == null) return null;
        if (head.id == id) { Task d = head; head = head.next; d.next = null; return d; }
        Task curr = head, prev = null;
        while (curr != null && curr.id != id) { prev = curr; curr = curr.next; }
        if (curr != null) { prev.next = curr.next; curr.next = null; return curr; }
        return null;
    }

    public Task linearSearch(String title) {
        Task t = head; while (t != null) { if (t.title.equalsIgnoreCase(title)) return t; t = t.next; }
        return null;
    }

    public void bubbleSort() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false; Task curr = head;
            while (curr.next != null) {
                if (curr.priority > curr.next.priority) {
                    int tId = curr.id; String tTi = curr.title; int tPr = curr.priority;
                    curr.id = curr.next.id; curr.title = curr.next.title; curr.priority = curr.next.priority;
                    curr.next.id = tId; curr.next.title = tTi; curr.next.priority = tPr;
                    swapped = true;
                }
                curr = curr.next;
            }
        } while (swapped);
    }

    public void undo() {
        Action last = popAction();
        if (last == null) { System.out.println("\u001B[31m⚠ No actions to undo!\u001B[0m"); return; }
        if (last.type.equals("ADD")) { delete(last.taskCopy.id); } 
        else { last.taskCopy.next = head; head = last.taskCopy; }
        System.out.println("\u001B[32m✔ Undo successful! Last " + last.type + " action reversed.\u001B[0m");
    }

    public void display() {
        if (head == null) { System.out.println("\n\u001B[33m[!] No tasks active right now.\u001B[0m"); return; }
        System.out.println("\n\u001B[36m┌──────────────────────────────────────────────┐");
        System.out.printf("│ %-5s │ %-20s │ %-13s │\n", "ID", "TASK TITLE", "PRIORITY");
        System.out.println("├──────────────────────────────────────────────┤\u001B[0m");
        Task t = head;
        while (t != null) {
            String pStr = (t.priority == 1) ? "\u001B[31mHigh\u001B[0m" : (t.priority == 2) ? "\u001B[33mMedium\u001B[0m" : "\u001B[32mLow\u001B[0m";
            System.out.printf("│ %-5d │ %-20s │ %-22s │\n", t.id, t.title, pStr);
            t = t.next;
        }
        System.out.println("\u001B[36m└──────────────────────────────────────────────┘\u001B[0m");
    }
}

// --- COLORFUL DRIVER INTERFACE ---
public class TaskManager {
    public static void main(String[] args) {
        TaskEngine engine = new TaskEngine();
        Scanner sc = new Scanner(System.in);
        
        // ANSI Colors
        String RESET = "\u001B[0m", CYAN = "\u001B[36m", PURPLE = "\u001B[35m", RED = "\u001B[31m", GREEN = "\u001B[32m";

        while (true) {
            System.out.println("\n" + CYAN + "╔════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║       ✨ KBA TASK MANAGER DASHBOARD ✨     ║" + RESET);
            System.out.println(CYAN + "╚════════════════════════════════════════╝" + RESET);
            System.out.println(PURPLE + " 1. ➕  Add New Task" + RESET);
            System.out.println(PURPLE + " 2. ❌  Delete Task by ID" + RESET);
            System.out.println(PURPLE + " 3. 🔍  Search Task by Title" + RESET);
            System.out.println(PURPLE + " 4. ⚡  Sort Tasks by Priority" + RESET);
            System.out.println(PURPLE + " 5. 📋  View All Active Tasks" + RESET);
            System.out.println(PURPLE + " 6. ↩   Undo Last Action" + RESET);
            System.out.println(RED + " 7. 🚪  Exit Application" + RESET);
            System.out.print(CYAN + "Select an option (1-7): " + RESET);

            if (!sc.hasNextInt()) { System.out.println(RED + "❌ Error: Invalid numeric selection." + RESET); sc.next(); continue; }
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Title: "); String title = sc.nextLine();
                    System.out.print("Enter Priority (1=High, 2=Med, 3=Low): "); int p = sc.nextInt();
                    Task t = new Task(id, title, p); engine.add(t); engine.pushAction("ADD", t);
                    System.out.println(GREEN + "✔ Task added successfully!" + RESET);
                    break;
                case 2:
                    System.out.print("Enter ID to drop: "); int delId = sc.nextInt();
                    Task rem = engine.delete(delId);
                    if (rem != null) { engine.pushAction("DELETE", rem); System.out.println(GREEN + "✔ Task dropped!" + RESET); }
                    else System.out.println(RED + "❌ ID not found." + RESET);
                    break;
                case 3:
                    System.out.print("Enter Title keyword: "); String sTitle = sc.nextLine();
                    Task f = engine.linearSearch(sTitle);
                    if (f != null) System.out.println(GREEN + "✔ Found! ID: " + f.id + " | Priority: " + f.priority + RESET);
                    else System.out.println(RED + "❌ No task found." + RESET);
                    break;
                case 4:
                    engine.bubbleSort(); System.out.println(GREEN + "✔ Sorted tasks perfectly!" + RESET); break;
                case 5:
                    engine.display(); break;
                case 6:
                    engine.undo(); break;
                case 7:
                    System.out.println(RED + "Exiting. Goodbye!" + RESET); sc.close(); return;
                default:
                    System.out.println(RED + "❌ Choose between 1 and 7." + RESET);
            }
        }
    }
}
