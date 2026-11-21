package com.hostel.student.util;

import com.hostel.student.model.Student;
import com.hostel.student.service.StudentService;

/**
 * Utility helper to preload realistic sample student data for demonstration
 * and evaluation purposes.
 */
public final class DataInitializer {

    private DataInitializer() {
        // Prevent instantiation
    }

    /**
     * Seeds the student service with sample student records.
     */
    public static void seedData(StudentService service) {
        Student[] sampleStudents = new Student[] {
            new Student("STU101", "Aarav Sharma", 20, "Male", "9876543210", "aarav.sharma@college.edu", "B.Tech CSE"),
            new Student("STU102", "Priya Patel", 21, "Female", "9812345678", "priya.patel@college.edu", "B.Tech ECE"),
            new Student("STU103", "Rohan Verma", 22, "Male", "9898989898", "rohan.verma@college.edu", "MCA"),
            new Student("STU104", "Ananya Iyer", 19, "Female", "9765432109", "ananya.iyer@college.edu", "B.Sc Data Science"),
            new Student("STU105", "Vikram Singh", 23, "Male", "9654321098", "vikram.singh@college.edu", "MBA")
        };

        for (Student s : sampleStudents) {
            try {
                if (!service.existsById(s.getId())) {
                    service.registerStudent(s);
                }
            } catch (Exception ignored) {
                // Ignore duplicates if already loaded
            }
        }
    }
}
