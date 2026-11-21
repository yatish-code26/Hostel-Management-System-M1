package com.hostel.student.service;

import com.hostel.student.exception.DuplicateStudentException;
import com.hostel.student.exception.InvalidStudentDataException;
import com.hostel.student.exception.StudentNotFoundException;
import com.hostel.student.model.Student;

/**
 * Automated test suite for StudentServiceImpl.
 * Verifies business logic, custom exceptions, validation, and storage integrity.
 */
public class StudentServiceTest {
    public static void main(String[] args) {
        System.out.println(">>> Running StudentService Integration Test Suite <<<");
        StudentService service = new StudentServiceImpl();

        try {
            // Test 1: Register valid student with custom ID
            Student s1 = new Student("STU101", "Karan Sharma", 20, "Male", "9876543210", "karan@hostel.edu", "B.Tech CSE");
            service.registerStudent(s1);
            assertEquals(1, service.getTotalStudentCount(), "Total students should be 1.");

            // Test 2: Auto-generated ID registration
            Student s2 = service.registerStudent("Sneha Gupta", 19, "Female", "9123456780", "sneha@hostel.edu", "B.Tech IT");
            assertCondition(s2.getId() != null && s2.getId().startsWith("STU"), "Generated ID should start with STU.");
            assertEquals(2, service.getTotalStudentCount(), "Total students should be 2.");

            // Test 3: Duplicate ID rejection
            boolean duplicateCaught = false;
            try {
                service.registerStudent(new Student("STU101", "Imposter", 22, "Male", "9998887776", "imp@hostel.edu", "MBA"));
            } catch (DuplicateStudentException e) {
                duplicateCaught = true;
                System.out.println("[PASS] DuplicateStudentException caught as expected: " + e.getMessage());
            }
            assertCondition(duplicateCaught, "Duplicate student registration must throw DuplicateStudentException.");

            // Test 4: Invalid email format
            boolean invalidEmailCaught = false;
            try {
                service.registerStudent(new Student("STU103", "Test User", 21, "Male", "9876543210", "invalid-email", "MCA"));
            } catch (InvalidStudentDataException e) {
                invalidEmailCaught = true;
                System.out.println("[PASS] InvalidStudentDataException caught for email: " + e.getMessage());
            }
            assertCondition(invalidEmailCaught, "Invalid email must throw InvalidStudentDataException.");

            // Test 5: Search existing student
            Student found = service.getStudentById("STU101");
            assertEquals("Karan Sharma", found.getName(), "Found student name should match.");

            // Test 6: Search non-existing student throws StudentNotFoundException
            boolean notFoundCaught = false;
            try {
                service.getStudentById("STU999");
            } catch (StudentNotFoundException e) {
                notFoundCaught = true;
                System.out.println("[PASS] StudentNotFoundException caught for search: " + e.getMessage());
            }
            assertCondition(notFoundCaught, "Searching non-existent ID must throw StudentNotFoundException.");

            // Test 7: Update student details
            service.updateStudent("STU101", "9000000000", "karan.new@hostel.edu", 21, "M.Tech CSE");
            Student updated = service.getStudentById("STU101");
            assertEquals("9000000000", updated.getContactNumber(), "Updated phone should match.");
            assertEquals("karan.new@hostel.edu", updated.getEmail(), "Updated email should match.");
            assertEquals(21, updated.getAge(), "Updated age should match.");

            // Test 8: Delete student
            boolean deleted = service.deleteStudent("STU101");
            assertCondition(deleted, "Delete should return true.");
            assertEquals(1, service.getTotalStudentCount(), "Count after delete should be 1.");
            assertCondition(!service.existsById("STU101"), "Deleted student must no longer exist.");

            System.out.println("\n>>> ALL SERVICE INTEGRATION TESTS PASSED SUCCESSFULLY! <<<\n");
        } catch (Exception e) {
            System.err.println("[FAIL] Unexpected exception occurred: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            System.err.println("[FAIL] " + message + " Expected: " + expected + ", Got: " + actual);
            throw new AssertionError(message);
        } else {
            System.out.println("[PASS] " + message);
        }
    }

    private static void assertCondition(boolean condition, String message) {
        if (!condition) {
            System.err.println("[FAIL] " + message);
            throw new AssertionError(message);
        } else {
            System.out.println("[PASS] " + message);
        }
    }
}
