package com.hostel.student.exception;

/**
 * Custom Exception thrown when student input validation fails
 * (e.g., negative/invalid age, malformed email, non-10-digit phone number, empty name).
 */
public class InvalidStudentDataException extends Exception {
    public InvalidStudentDataException(String message) {
        super(message);
    }
}
