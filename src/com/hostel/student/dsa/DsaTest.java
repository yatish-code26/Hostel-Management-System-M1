package com.hostel.student.dsa;

import com.hostel.student.model.Student;
import java.util.List;

/**
 * Verification test runner for custom StudentLinkedList data structure.
 * Tests node insertion, linear search, and pointer manipulation on deletion.
 */
public class DsaTest {
    public static void main(String[] args) {
        System.out.println(">>> Running Custom DSA (StudentLinkedList) Verification Suite <<<");
        StudentLinkedList list = new StudentLinkedList();

        // Test 1: Empty list state
        assertCondition(list.isEmpty(), "Initial list should be empty.");
        assertCondition(list.size() == 0, "Initial size should be 0.");

        // Test 2: Add students (O(1) insertion)
        Student s1 = new Student("STU101", "Aarav Sharma", 20, "Male", "9876543210", "aarav@hostel.edu", "B.Tech CSE");
        Student s2 = new Student("STU102", "Priya Patel", 21, "Female", "9812345678", "priya@hostel.edu", "B.Tech ECE");
        Student s3 = new Student("STU103", "Rohan Verma", 22, "Male", "9898989898", "rohan@hostel.edu", "MCA");

        list.add(s1);
        list.add(s2);
        list.add(s3);

        assertCondition(list.size() == 3, "Size after 3 additions should be 3.");
        assertCondition(!list.isEmpty(), "List should not be empty.");

        // Test 3: Search (O(n) traversal)
        Student found = list.findById("STU102");
        assertCondition(found != null && found.getName().equals("Priya Patel"), "Should find STU102.");
        Student notFound = list.findById("STU999");
        assertCondition(notFound == null, "Should return null for non-existing student.");

        // Test 4: Delete middle node (pointer relinking)
        boolean deletedMiddle = list.deleteById("STU102");
        assertCondition(deletedMiddle, "Should successfully delete middle node STU102.");
        assertCondition(list.size() == 2, "Size after deleting middle node should be 2.");
        assertCondition(list.findById("STU102") == null, "STU102 should no longer exist.");

        // Test 5: Delete head node
        boolean deletedHead = list.deleteById("STU101");
        assertCondition(deletedHead, "Should successfully delete head node STU101.");
        assertCondition(list.size() == 1, "Size after deleting head node should be 1.");

        // Test 6: Delete tail node
        boolean deletedTail = list.deleteById("STU103");
        assertCondition(deletedTail, "Should successfully delete tail node STU103.");
        assertCondition(list.size() == 0, "Size after deleting all nodes should be 0.");
        assertCondition(list.isEmpty(), "List should now be empty.");

        // Test 7: Conversion to List
        list.add(s1);
        list.add(s2);
        List<Student> students = list.toList();
        assertCondition(students.size() == 2, "toList() should contain 2 students.");

        System.out.println(">>> ALL DSA UNIT TESTS PASSED SUCCESSFULLY! <<<\n");
    }

    private static void assertCondition(boolean condition, String description) {
        if (!condition) {
            System.err.println("[FAIL] " + description);
            throw new AssertionError("Assertion failed: " + description);
        } else {
            System.out.println("[PASS] " + description);
        }
    }
}
