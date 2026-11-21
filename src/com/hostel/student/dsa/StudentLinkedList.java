package com.hostel.student.dsa;

import com.hostel.student.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Handcrafted Singly Linked List data structure for managing Student entities.
 * 
 * Demonstrates manual Data Structures & Algorithms (DSA):
 * 1. POINTER MANIPULATION: Direct management of 'head', 'tail', and 'next' pointers.
 * 2. INSERTION: O(1) time complexity when appending to the tail pointer.
 * 3. LINEAR SEARCH: O(n) node traversal to find a student by ID.
 * 4. DELETION: O(n) search + O(1) pointer-relinking (prev.next = current.next).
 * 5. DYNAMIC SIZING: Grows and shrinks in memory dynamically without fixed-size arrays.
 */
public class StudentLinkedList {
    private StudentNode head;
    private StudentNode tail;
    private int size;

    public StudentLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Appends a student to the end of the linked list.
     * Time Complexity: O(1) because we maintain a tail pointer.
     * 
     * @param student The student to add
     */
    public void add(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot add null student to linked list.");
        }

        StudentNode newNode = new StudentNode(student);

        if (head == null) {
            // Case 1: List is empty. New node becomes both head and tail.
            head = newNode;
            tail = newNode;
        } else {
            // Case 2: List has elements. Link current tail to new node and update tail pointer.
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * Traverses the linked list to find a student by unique ID.
     * Time Complexity: O(n) linear search.
     * 
     * @param studentId The unique ID to search for
     * @return Student if found, null otherwise
     */
    public Student findById(String studentId) {
        if (studentId == null || head == null) {
            return null;
        }

        StudentNode current = head;
        while (current != null) {
            if (current.getData().getId().equalsIgnoreCase(studentId.trim())) {
                return current.getData();
            }
            current = current.getNext(); // Move to next node
        }
        return null; // Not found
    }

    /**
     * Deletes a student from the linked list by their unique ID.
     * Demonstrates manual pointer relinking.
     * Time Complexity: O(n) search + O(1) pointer updates.
     * 
     * @param studentId The ID of the student to remove
     * @return true if found and removed, false otherwise
     */
    public boolean deleteById(String studentId) {
        if (studentId == null || head == null) {
            return false;
        }

        String targetId = studentId.trim();

        // Case 1: The node to delete is the head node
        if (head.getData().getId().equalsIgnoreCase(targetId)) {
            head = head.getNext(); // Move head to the next node
            if (head == null) {
                // List is now empty
                tail = null;
            }
            size--;
            return true;
        }

        // Case 2: The node to delete is in the middle or at the tail
        StudentNode prev = head;
        StudentNode current = head.getNext();

        while (current != null) {
            if (current.getData().getId().equalsIgnoreCase(targetId)) {
                // Found node to delete: bypass current node by relinking prev pointer
                prev.setNext(current.getNext());

                // If deleting the tail node, update tail pointer to prev
                if (current == tail) {
                    tail = prev;
                }

                size--;
                return true;
            }
            prev = current;
            current = current.getNext();
        }

        return false; // Target ID was not present in the list
    }

    /**
     * Converts all linked list nodes into an ArrayList.
     * Useful for integration, sorting, and batch display.
     * 
     * @return List of all students in insertion order
     */
    public List<Student> toList() {
        List<Student> list = new ArrayList<>();
        StudentNode current = head;
        while (current != null) {
            list.add(current.getData());
            current = current.getNext();
        }
        return list;
    }

    /**
     * Traverses the list and displays each student's card.
     */
    public void displayAll() {
        if (head == null) {
            System.out.println("No students found in the custom linked list.");
            return;
        }
        StudentNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println("\n[Record #" + (index++) + "]");
            current.getData().displayDetails();
            current = current.getNext();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}
