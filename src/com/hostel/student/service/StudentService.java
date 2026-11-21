package com.hostel.student.service;

import com.hostel.student.exception.DuplicateStudentException;
import com.hostel.student.exception.InvalidStudentDataException;
import com.hostel.student.exception.StudentNotFoundException;
import com.hostel.student.model.Student;
import java.util.List;

/**
 * Service interface defining operations for Student Lifecycle Management.
 * 
 * Demonstrates ABSTRACTION by decoupling business interface contracts
 * from the underlying storage mechanism (Collections + custom DSA).
 * 
 * Also serves as the primary Integration Specification for teammates:
 * Room Management, Fee Management, and Complaint Management modules.
 */
public interface StudentService {

    /**
     * Registers a new student with a custom or pre-set student ID.
     * 
     * @param student The student object to register
     * @return The registered student
     * @throws DuplicateStudentException If student ID is already taken
     * @throws InvalidStudentDataException If any student field fails validation
     */
    Student registerStudent(Student student) 
            throws DuplicateStudentException, InvalidStudentDataException;

    /**
     * Registers a new student and automatically generates a unique sequential ID.
     * 
     * @param name Full name
     * @param age Age
     * @param gender Gender
     * @param contactNumber 10-digit phone
     * @param email Valid email
     * @param course Enrolled academic course
     * @return The registered student with generated ID
     * @throws DuplicateStudentException If generated ID collides (rare)
     * @throws InvalidStudentDataException If input fails validation
     */
    Student registerStudent(String name, int age, String gender, String contactNumber, 
                            String email, String course)
            throws DuplicateStudentException, InvalidStudentDataException;

    /**
     * Retrieves a student by their unique student ID.
     * 
     * @param studentId The unique ID to search
     * @return The matching Student object
     * @throws StudentNotFoundException If student ID does not exist
     */
    Student getStudentById(String studentId) throws StudentNotFoundException;

    /**
     * Retrieves all registered students.
     * 
     * @return List of all students
     */
    List<Student> getAllStudents();

    /**
     * Updates editable details of an existing student.
     * 
     * @param studentId ID of the student to update
     * @param contactNumber New contact number
     * @param email New email address
     * @param age New age
     * @param course New course
     * @return The updated Student object
     * @throws StudentNotFoundException If student does not exist
     * @throws InvalidStudentDataException If any updated value is invalid
     */
    Student updateStudent(String studentId, String contactNumber, String email, 
                          int age, String course) 
            throws StudentNotFoundException, InvalidStudentDataException;

    /**
     * Deletes a student from the hostel records.
     * 
     * @param studentId ID of the student to remove
     * @return true if deletion was successful
     * @throws StudentNotFoundException If student does not exist
     */
    boolean deleteStudent(String studentId) throws StudentNotFoundException;

    /**
     * Checks if a student exists by ID.
     * 
     * @param studentId ID to verify
     * @return true if registered, false otherwise
     */
    boolean existsById(String studentId);

    /**
     * Returns the total count of registered students.
     */
    int getTotalStudentCount();

    /**
     * Generates a guaranteed unique next student ID (e.g., STU1001, STU1002).
     */
    String generateNextStudentId();
}
