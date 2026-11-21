package com.hostel.student.main;

import com.hostel.student.exception.DuplicateStudentException;
import com.hostel.student.exception.InvalidStudentDataException;
import com.hostel.student.exception.StudentNotFoundException;
import com.hostel.student.model.Student;
import com.hostel.student.service.StudentService;
import com.hostel.student.service.StudentServiceImpl;
import com.hostel.student.util.DataInitializer;
import java.util.List;
import java.util.Scanner;

/**
 * Main Console User Interface for the Student Management Module.
 * Driven by java.util.Scanner with comprehensive exception handling.
 */
public class StudentMain {

    private final StudentService studentService;
    private final Scanner scanner;

    public StudentMain() {
        this.studentService = new StudentServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        StudentMain app = new StudentMain();
        app.run();
    }

    /**
     * Main application loop.
     */
    public void run() {
        System.out.println("===============================================================");
        System.out.println("       WELCOME TO HOSTEL STUDENT MANAGEMENT SYSTEM             ");
        System.out.println("       Module: Student Management (Standalone Service)        ");
        System.out.println("===============================================================");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice [1-8]: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleStudentRegistration();
                    break;
                case "2":
                    handleViewAllStudents();
                    break;
                case "3":
                    handleSearchStudent();
                    break;
                case "4":
                    handleUpdateStudent();
                    break;
                case "5":
                    handleDeleteStudent();
                    break;
                case "6":
                    handleDsaTraversalDemo();
                    break;
                case "7":
                    handleLoadSampleData();
                    break;
                case "8":
                    System.out.println("\nThank you for using College Hostel Student Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("\n[ERROR] Invalid menu choice. Please select an option between 1 and 8.");
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===============================================================");
        System.out.println("                     MAIN DASHBOARD MENU                       ");
        System.out.println("===============================================================");
        System.out.println(" 1. Register New Student");
        System.out.println(" 2. View All Registered Students");
        System.out.println(" 3. Search Student by ID");
        System.out.println(" 4. Update Student Details");
        System.out.println(" 5. Delete Student Record");
        System.out.println(" 6. Inspect Custom DSA Linked List (Live Traversal)");
        System.out.println(" 7. Load Sample Resident Data (Examiner Demo Preset)");
        System.out.println(" 8. Exit");
        System.out.println("===============================================================");
    }

    /**
     * Feature 1: Student Registration
     */
    private void handleStudentRegistration() {
        System.out.println("\n--- [1] REGISTER NEW STUDENT ---");
        try {
            System.out.print("Choose ID mode - (A)uto-generate or (M)anual ID entry? [A/M]: ");
            String idMode = scanner.nextLine().trim().toUpperCase();

            String studentId;
            if (idMode.equals("M")) {
                System.out.print("Enter Custom Student ID (e.g., STU101): ");
                studentId = scanner.nextLine().trim().toUpperCase();
            } else {
                studentId = studentService.generateNextStudentId();
                System.out.println("Auto-assigned Unique Student ID: " + studentId);
            }

            System.out.print("Enter Full Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Age (16 - 60): ");
            int age;
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                throw new InvalidStudentDataException("Age must be a valid integer number.");
            }

            System.out.print("Enter Gender (Male/Female/Other): ");
            String gender = scanner.nextLine().trim();

            System.out.print("Enter Contact Number (10 digits): ");
            String contactNumber = scanner.nextLine().trim();

            System.out.print("Enter Email Address: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter Academic Course (e.g., B.Tech CSE, MBA): ");
            String course = scanner.nextLine().trim();

            Student newStudent = new Student(studentId, name, age, gender, contactNumber, email, course);
            studentService.registerStudent(newStudent);

            System.out.println("\n[SUCCESS] Student successfully registered!");
            newStudent.displayDetails();

        } catch (DuplicateStudentException | InvalidStudentDataException e) {
            System.err.println("\n[REGISTRATION FAILED] " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\n[UNEXPECTED ERROR] " + e.getMessage());
        }
    }

    /**
     * Feature 2: View Student Records
     */
    private void handleViewAllStudents() {
        System.out.println("\n--- [2] ALL REGISTERED HOSTEL STUDENTS ---");
        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No student records found in the hostel system.");
            System.out.println("Tip: Use Option 1 to register or Option 7 to load sample data.");
            return;
        }

        System.out.println("Total Registered Students: " + students.size());
        printTableHeader();
        for (Student s : students) {
            System.out.println(s.toTableRow());
        }
        printTableSeparator();
    }

    /**
     * Feature 3: Search Student
     */
    private void handleSearchStudent() {
        System.out.println("\n--- [3] SEARCH STUDENT BY ID ---");
        System.out.print("Enter Student ID to search: ");
        String studentId = scanner.nextLine().trim();

        try {
            Student student = studentService.getStudentById(studentId);
            System.out.println("\n[RECORD FOUND]");
            student.displayDetails();
        } catch (StudentNotFoundException e) {
            System.err.println("\n[SEARCH FAILED] " + e.getMessage());
        }
    }

    /**
     * Feature 4: Update Student
     */
    private void handleUpdateStudent() {
        System.out.println("\n--- [4] UPDATE STUDENT RECORD ---");
        System.out.print("Enter Student ID to update: ");
        String studentId = scanner.nextLine().trim();

        try {
            Student existing = studentService.getStudentById(studentId);
            System.out.println("\nCurrent Record for " + existing.getId() + ":");
            existing.displayDetails();

            System.out.println("\nEnter new details (press Enter to keep current value):");

            System.out.print("New Contact Number [" + existing.getContactNumber() + "]: ");
            String phoneInput = scanner.nextLine().trim();
            String phone = phoneInput.isEmpty() ? existing.getContactNumber() : phoneInput;

            System.out.print("New Email Address [" + existing.getEmail() + "]: ");
            String emailInput = scanner.nextLine().trim();
            String email = emailInput.isEmpty() ? existing.getEmail() : emailInput;

            System.out.print("New Age [" + existing.getAge() + "]: ");
            String ageInput = scanner.nextLine().trim();
            int age = ageInput.isEmpty() ? existing.getAge() : Integer.parseInt(ageInput);

            System.out.print("New Course [" + existing.getCourse() + "]: ");
            String courseInput = scanner.nextLine().trim();
            String course = courseInput.isEmpty() ? existing.getCourse() : courseInput;

            Student updated = studentService.updateStudent(studentId, phone, email, age, course);
            System.out.println("\n[SUCCESS] Student details updated successfully!");
            updated.displayDetails();

        } catch (StudentNotFoundException | InvalidStudentDataException e) {
            System.err.println("\n[UPDATE FAILED] " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("\n[UPDATE FAILED] Age must be an integer.");
        }
    }

    /**
     * Feature 5: Delete Student
     */
    private void handleDeleteStudent() {
        System.out.println("\n--- [5] DELETE STUDENT RECORD ---");
        System.out.print("Enter Student ID to delete: ");
        String studentId = scanner.nextLine().trim();

        try {
            Student existing = studentService.getStudentById(studentId);
            System.out.println("Student identified: " + existing.getName() + " (" + existing.getId() + ")");
            System.out.print("Are you sure you want to permanently delete this student record? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("yes") || confirm.equals("y")) {
                studentService.deleteStudent(studentId);
                System.out.println("\n[SUCCESS] Student " + studentId + " was permanently removed from hostel records.");
            } else {
                System.out.println("\n[CANCELLED] Deletion cancelled by user.");
            }

        } catch (StudentNotFoundException e) {
            System.err.println("\n[DELETE FAILED] " + e.getMessage());
        }
    }

    /**
     * Demonstrates custom manual DSA Linked List traversal.
     */
    private void handleDsaTraversalDemo() {
        System.out.println("\n--- [6] CUSTOM SINGLY LINKED LIST TRAVERSAL (DSA DEMO) ---");
        if (studentService instanceof StudentServiceImpl) {
            StudentServiceImpl impl = (StudentServiceImpl) studentService;
            System.out.println("Custom Linked List Node Count: " + impl.getStudentLinkedList().size());
            impl.getStudentLinkedList().displayAll();
        }
    }

    /**
     * Loads preset student records for instant examiner presentation.
     */
    private void handleLoadSampleData() {
        System.out.println("\n--- [7] LOADING PRESET SAMPLE STUDENTS ---");
        int countBefore = studentService.getTotalStudentCount();
        DataInitializer.seedData(studentService);
        int countAfter = studentService.getTotalStudentCount();
        System.out.println("[SUCCESS] Sample data loaded! Added " + (countAfter - countBefore) + " records.");
    }

    private void printTableHeader() {
        printTableSeparator();
        System.out.printf("| %-10s | %-20s | %-4s | %-8s | %-12s | %-25s | %-15s |%n",
                "ID", "NAME", "AGE", "GENDER", "PHONE", "EMAIL", "COURSE");
        printTableSeparator();
    }

    private void printTableSeparator() {
        System.out.println("+------------+----------------------+------+----------+--------------+---------------------------+-----------------+");
    }
}
