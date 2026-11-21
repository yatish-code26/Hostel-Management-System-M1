package com.hostel.student.exception;

/**
 * Custom Exception thrown when an operation refers to a student ID
 * that does not exist in the hostel records.
 */
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String studentId, String action) {
        super("Student with ID '" + studentId + "' was not found for operation: " + action);
    }
}
