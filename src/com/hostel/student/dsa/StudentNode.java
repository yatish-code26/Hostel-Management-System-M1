package com.hostel.student.dsa;

import com.hostel.student.model.Student;

/**
 * Node class for the custom Singly Linked List data structure.
 * 
 * Demonstrates manual Data Structures & Algorithms (DSA):
 * - Holds a reference to the payload (Student object).
 * - Holds a pointer/reference to the next node in the linear sequence.
 */
public class StudentNode {
    private Student data;
    private StudentNode next;

    /**
     * Constructs a new node with student data.
     * 
     * @param data The Student object to store in this node
     */
    public StudentNode(Student data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Constructs a node with data and explicitly sets the next pointer.
     */
    public StudentNode(Student data, StudentNode next) {
        this.data = data;
        this.next = next;
    }

    public Student getData() {
        return data;
    }

    public void setData(Student data) {
        this.data = data;
    }

    public StudentNode getNext() {
        return next;
    }

    public void setNext(StudentNode next) {
        this.next = next;
    }
}
