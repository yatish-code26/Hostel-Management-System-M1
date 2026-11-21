package com.hostel.student.exception;

/**
 * Custom Exception thrown when an attempt is made to register a student
 * with an ID that is already registered in the system.
 */
public class DuplicateStudentException extends Exception {
    public DuplicateStudentException(String message) {
        super(message);
    }

    public DuplicateStudentException(String studentId, boolean isIdDuplicate) {
        super("Registration failed: A student with ID '" + studentId + "' already exists.");
    }
}
