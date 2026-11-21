# College Hostel Management System - Student Management Module

> **Individual Module Component**  
> **Author:** Yatish (`yatish-code26`)  
> **Module Responsibility:** Student Management Module  
> **System Scope:** Console-based, pure Java application without external frameworks or databases.
> **Final Repo where all the code is there of each member and also pull request** https://github.com/thanav-badboy/Hostel-Management-System.git

---

## 1. Project Overview & Responsibility Boundaries

This repository contains the standalone, fully functional **Student Management Module** for a collaborative College Hostel Management System.

In a 4-member distributed team project:
- **Student Management Module (THIS REPOSITORY):** Responsible for student lifecycle management (registration, unique student ID generation/validation, viewing, searching, updating, deleting, input validation, and integration contracts).
- **Room Management Module (Teammate A):** Independently maintains rooms, bed capacities, and allocations.
- **Fee Management Module (Teammate B):** Independently maintains hostel fees, dues, and payment transactions.
- **Complaint Management Module (Teammate C):** Independently maintains student grievances, status, and resolutions.

> **Integration Anchor:** The `studentId` acts as the universal foreign key / identifier across all team modules.

---

## 2. Core Functional Features

1. **Student Registration:**
   - Register new hostel residents with unique IDs (e.g., `STU101` or auto-generated `STU1001+`).
   - Store and validate `name`, `age`, `gender`, `contactNumber`, `email`, and `course`.
2. **View Student Records:**
   - Formatted tabular view of all registered students.
   - Detailed individual record inspection by ID.
3. **Search Student:**
   - Constant-time $O(1)$ search by Student ID via HashMap index and $O(n)$ search via custom Linked List traversal.
   - Meaningful error feedback when records do not exist.
4. **Update Student Details:**
   - Update editable contact info (`contactNumber`, `email`), `age`, and `course`.
5. **Delete Student Records:**
   - Safe deletion of student records with existence validation and pointer relinking in custom DSA.

---

## 3. Technical & Architectural Highlights

### A. Object-Oriented Programming (OOP)
- **Abstraction:** Abstract base class `Person` encapsulates universal personal attributes. `StudentService` interface defines public service contracts.
- **Inheritance:** `Student` extends `Person`, inheriting personal attributes while introducing hostel-specific academic course details.
- **Polymorphism:** Method overriding in `Student.displayDetails()` and `Student.toString()`. Interface-driven programming with `StudentService` implemented by `StudentServiceImpl`.
- **Encapsulation:** Private attributes with rigorous getter/setter validations.

### B. Custom Exception Hierarchy
- `StudentNotFoundException`: Thrown when an operation targets a non-existent student ID.
- `DuplicateStudentException`: Thrown when attempting to register an ID that already exists.
- `InvalidStudentDataException`: Thrown when input validation fails (e.g., malformed email, invalid 10-digit phone, invalid age).

### C. Data Structures & Algorithms (DSA)
- **Custom Singly Linked List (`StudentLinkedList`):** Handcrafted node-based data structure demonstrating low-level pointer manipulation (`head`, `tail`, `next`), linear search, node insertion, and pointer-relinking deletion.
- **Java Collections Framework:** `HashMap<String, Student>` provides instantaneous $O(1)$ key-based lookup indexing, while `ArrayList<Student>` supports dynamic listing and sorting.

---

## 4. Package Structure

```
src/
└── com/
    └── hostel/
        └── student/
            ├── model/
            │   ├── Person.java
            │   └── Student.java
            ├── exception/
            │   ├── StudentNotFoundException.java
            │   ├── DuplicateStudentException.java
            │   └── InvalidStudentDataException.java
            ├── dsa/
            │   ├── StudentNode.java
            │   └── StudentLinkedList.java
            ├── service/
            │   ├── StudentService.java
            │   └── StudentServiceImpl.java
            ├── util/
            │   ├── ValidationUtil.java
            │   └── DataInitializer.java
            └── main/
                └── StudentMain.java
```

---

## 5. How to Compile and Run

### Prerequisites
- Java JDK 17 or higher (tested on OpenJDK / Oracle JDK 25)

### Build Commands (Windows Terminal / PowerShell)
```powershell
# Compile all source files into bin directory
javac -d bin (Get-ChildItem -Recurse -Filter *.java src).FullName

# Run the console application
java -cp bin com.hostel.student.main.StudentMain
```
