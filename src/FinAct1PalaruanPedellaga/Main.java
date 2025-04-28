/*
   Name: Palaruan, Princess Laralei F.
         Pedellaga, Ralph Waldo
   Date: April 24, 2025
   Activity Name: Finals Laboratory Activity 2 - Student Management System Version 2.0
   Algorithm:
   1. Load students from StudentRecord.txt on program start.
   2. Display menu for Add, Remove, Update, Display, Search, Sort, Find Highest Grade, Exit.
   3. Every Add, Remove, or Update will automatically save the updated list back to StudentRecord.txt.
   4. On Exit, the program terminates after updating the file.
*/

        package FinAct2PalaruanPedellaga;
        import java.util.*;
        import java.io.*;

public class Main {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "StudentRecord.txt";

    public static void main(String[] args) {
        loadStudentsFromFile(); // Load students at program start

        int choice;
        do {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student Information");
            System.out.println("4. Display All Students");
            System.out.println("5. Search Student by Name");
            System.out.println("6. Sort Students by Name");
            System.out.println("7. Find Student with Highest Grade");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    addStudent();
                    saveStudentsToFile();
                }
                case 2 -> {
                    removeStudent();
                    saveStudentsToFile();
                }
                case 3 -> {
                    updateStudent();
                    saveStudentsToFile();
                }
                case 4 -> displayStudents();
                case 5 -> searchStudentByName();
                case 6 -> sortStudentsByName();
                case 7 -> findHighestGradeStudent();
                case 8 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 8);
    }

    // Load students from file
    private static void loadStudentsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String grade = parts[3];
                    students.add(new Student(id, name, age, grade));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }

    // Save students to file
    private static void saveStudentsToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.getId() + "," + s.getName() + "," + s.getAge() + "," + s.getGrade());
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    private static void addStudent() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();

        students.add(new Student(id, name, age, grade));
        System.out.println("Student added.");
    }

    private static void removeStudent() {
        System.out.print("Enter ID to remove: ");
        int id = scanner.nextInt();
        boolean removed = students.removeIf(s -> s.getId() == id);
        System.out.println(removed ? "Student removed." : "Student not found.");
    }

    private static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter new name: ");
                s.setName(scanner.nextLine());
                System.out.print("Enter new age: ");
                s.setAge(scanner.nextInt());
                scanner.nextLine();
                System.out.print("Enter new grade: ");
                s.setGrade(scanner.nextLine());
                System.out.println("Student updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void searchStudentByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(name)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) System.out.println("No student found with that name.");
    }

    private static void sortStudentsByName() {
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("Students sorted by name:");
        displayStudents();
    }

    private static void findHighestGradeStudent() {
        if (students.isEmpty()) {
            System.out.println("No students in the list.");
            return;
        }

        Student topStudent = Collections.max(students, Comparator.comparing(Student::getGrade));
        System.out.println("Student with highest grade: " + topStudent);
    }
}

