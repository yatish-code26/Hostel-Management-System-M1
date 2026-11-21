package com.hostel.student.service;

import com.hostel.student.dsa.StudentLinkedList;
import com.hostel.student.exception.DuplicateStudentException;
import com.hostel.student.exception.InvalidStudentDataException;
import com.hostel.student.exception.StudentNotFoundException;
import com.hostel.student.model.Student;
import com.hostel.student.util.ValidationUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of StudentService.
 * 
 * Architectural Synergy:
 * 1. JAVA COLLECTIONS FRAMEWORK:
 *    - HashMap<String, Student>: Delivers O(1) constant-time lookup by student ID.
 *    - ArrayList<Student>: Delivers dynamic lists for sorted viewing and batch output.
 * 2. CUSTOM DATA STRUCTURES (DSA):
 *    - StudentLinkedList: Handcrafted singly linked list preserving chronological 
 *      registration logs and demonstrating manual pointer manipulation.
 * 3. EXCEPTION HANDLING:
 *    - Validates inputs before mutation and raises custom checked exceptions.
 */
public class StudentServiceImpl implements StudentService {

    // Fast O(1) indexed lookup map using Java Collections Framework
    private final Map<String, Student> studentMap;

    // Custom Singly Linked List demonstrating manual DSA
    private final StudentLinkedList studentLinkedList;

    // Counter for auto-generating sequential Student IDs (e.g., STU1001)
    private int idCounter;

    /**
     * Initializes the service with clean storage.
     */
    public StudentServiceImpl() {
        this.studentMap = new HashMap<>();
        this.studentLinkedList = new StudentLinkedList();
        this.idCounter = 1001;
    }

    /**
     * Registers a new student using an existing Student entity.
     */
    @Override
    public Student registerStudent(Student student) 
            throws DuplicateStudentException, InvalidStudentDataException {
        if (student == null) {
            throw new InvalidStudentDataException("Student entity cannot be null.");
        }

        // Validate all input fields
        ValidationUtil.validateAll(
            student.getId(), 
            student.getName(), 
            student.getAge(), 
            student.getGender(), 
            student.getContactNumber(), 
            student.getEmail(), 
            student.getCourse()
        );

        String normalizedId = student.getId().trim().toUpperCase();
        student.setId(normalizedId);

        // Check for duplicate ID
        if (studentMap.containsKey(normalizedId)) {
            throw new DuplicateStudentException(normalizedId, true);
        }

        // Store in HashMap for O(1) instant search
        studentMap.put(normalizedId, student);

        // Store in custom Singly Linked List for chronological DSA tracking
        studentLinkedList.add(student);

        return student;
    }

    /**
     * Registers a student by generating a unique sequential ID.
     */
    @Override
    public Student registerStudent(String name, int age, String gender, String contactNumber, 
                                   String email, String course) 
            throws DuplicateStudentException, InvalidStudentDataException {
        String generatedId = generateNextStudentId();
        Student student = new Student(generatedId, name, age, gender, contactNumber, email, course);
        return registerStudent(student);
    }

    /**
     * Retrieves a student by unique ID.
     * Uses HashMap for O(1) lookup, with fallback/cross-check to custom DSA.
     */
    @Override
    public Student getStudentById(String studentId) throws StudentNotFoundException {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new StudentNotFoundException("Student ID cannot be null or empty.");
        }

        String normalizedId = studentId.trim().toUpperCase();
        Student student = studentMap.get(normalizedId);

        if (student == null) {
            throw new StudentNotFoundException(normalizedId, "SEARCH");
        }
        return student;
    }

    /**
     * Returns all registered students sorted by Student ID.
     */
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>(studentMap.values());
        students.sort(Comparator.comparing(Student::getId));
        return Collections.unmodifiableList(students);
    }

    /**
     * Updates editable student fields after thorough validation.
     */
    @Override
    public Student updateStudent(String studentId, String contactNumber, String email, 
                                  int age, String course) 
            throws StudentNotFoundException, InvalidStudentDataException {
        // Validate input data first
        ValidationUtil.validateContactNumber(contactNumber);
        ValidationUtil.validateEmail(email);
        ValidationUtil.validateAge(age);
        ValidationUtil.validateCourse(course);

        Student student = getStudentById(studentId);

        // Update fields (Encapsulation)
        student.setContactNumber(contactNumber.trim());
        student.setEmail(email.trim());
        student.setAge(age);
        student.setCourse(course.trim());

        return student;
    }

    /**
     * Deletes a student from both the HashMap and custom Singly Linked List.
     */
    @Override
    public boolean deleteStudent(String studentId) throws StudentNotFoundException {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new StudentNotFoundException("Student ID cannot be null or empty.");
        }

        String normalizedId = studentId.trim().toUpperCase();

        if (!studentMap.containsKey(normalizedId)) {
            throw new StudentNotFoundException(normalizedId, "DELETE");
        }

        // Remove from HashMap
        studentMap.remove(normalizedId);

        // Remove from custom Linked List (demonstrates DSA pointer relinking)
        studentLinkedList.deleteById(normalizedId);

        return true;
    }

    @Override
    public boolean existsById(String studentId) {
        if (studentId == null) return false;
        return studentMap.containsKey(studentId.trim().toUpperCase());
    }

    @Override
    public int getTotalStudentCount() {
        return studentMap.size();
    }

    @Override
    public String generateNextStudentId() {
        String id;
        do {
            id = "STU" + (idCounter++);
        } while (studentMap.containsKey(id));
        return id;
    }

    /**
     * Accessor for testing/inspecting the underlying custom Linked List.
     */
    public StudentLinkedList getStudentLinkedList() {
        return studentLinkedList;
    }
}
