package FinAct1PalaruanPedellaga;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);
    private static int nextStudentID = 1; // Counter for unique student IDs

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println(" Welcome to the Student Management System ");
        System.out.println("========================================");

        initializeFiveStudents();
        mainMenu();
    }

    public static void initializeFiveStudents() {
        students.add(new Student(nextStudentID++, "Alice Johnson", 20, "E"));
        students.add(new Student(nextStudentID++, "Bob Smith", 22, "B"));
        students.add(new Student(nextStudentID++, "Charlie Brown", 19, "C"));
        students.add(new Student(nextStudentID++, "Diana Prince", 21, "A"));
        students.add(new Student(nextStudentID++, "Ethan Hunt", 23, "B"));
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("\n=======================================");
            System.out.println("               Main Menu               ");
            System.out.println("=======================================");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student Information");
            System.out.println("4. Display All Students");
            System.out.println("5. Search Student by Name");
            System.out.println("6. Sort Students by Name");
            System.out.println("7. Find Student with Highest Grade");
            System.out.println("8. Exit");
            System.out.println("=======================================");

            int choice = enterChoice(1, 8);

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> updateStudent();
                case 4 -> displayAllStudents();
                case 5 -> searchStudent();
                case 6 -> sortStudentsByName();
                case 7 -> findStudentWithHighestGrade();
                case 8 -> {
                    System.out.println("\nThank you for using the Student Management System!");
                    return;
                }
            }
        }
    }



    public static int enterChoice(int min, int max) {
        while (true) {
            System.out.print("\nEnter your choice: ");
            try {
                int choice = Integer.parseInt(input.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static void addStudent() {
        try {
            System.out.print("\nEnter Student Name: ");
            String studentName = input.nextLine();

            System.out.print("Enter Student Age: ");
            int studentAge = Integer.parseInt(input.nextLine());

            System.out.print("Enter Student Grade (A, B, C, D, E): ");
            String studentGrade = input.nextLine().toUpperCase();
            if (!studentGrade.matches("[A-E]")) {
                System.out.println("Invalid grade. Please enter a grade between A and E.");
                return;
            }

            students.add(new Student(nextStudentID++, studentName, studentAge, studentGrade));
            System.out.println("\nStudent added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for Age. Please enter a valid number.");
        }
    }

    public static void removeStudent() {
        System.out.print("\nEnter Student ID to remove: ");
        try {
            int studentID = Integer.parseInt(input.nextLine());
            boolean removed = students.removeIf(student -> student.getID() == studentID);
            if (removed) {
                System.out.println("\nStudent with ID " + studentID + " removed successfully.");
            } else {
                System.out.println("\nStudent with ID " + studentID + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    public static void updateStudent() {
        System.out.print("\nEnter Student ID to update: ");
        try {
            int studentID = Integer.parseInt(input.nextLine());
            for (Student student : students) {
                if (student.getID() == studentID) {
                    System.out.print("Enter new Name (current: " + student.getName() + "): ");
                    String newName = input.nextLine();
                    if (!newName.isBlank()) student.setName(newName);

                    System.out.print("Enter new Age (current: " + student.getAge() + "): ");
                    String newAge = input.nextLine();
                    if (!newAge.isBlank()) student.setAge(Integer.parseInt(newAge));

                    System.out.print("Enter new Grade (A, B, C, D, E) (current: " + student.getGrade() + "): ");
                    String newGrade = input.nextLine().toUpperCase();
                    if (!newGrade.isBlank() && !newGrade.matches("[A-E]")) {
                        System.out.println("Invalid grade. Please enter a grade between A and E.");
                        return;
                    }
                    if (!newGrade.isBlank()) student.setGrade(newGrade);

                    System.out.println("\nStudent updated successfully.");
                    return;
                }
            }
            System.out.println("\nStudent with ID " + studentID + " not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    public static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo students available.");
        } else {
            System.out.println("\n=======================================");
            System.out.println("           All Students List           ");
            System.out.println("=======================================");
            students.forEach(student -> System.out.println(student));
        }
    }

    public static void searchStudent() {
        System.out.print("\nEnter the first name of the student to search: ");
        String searchName = input.nextLine().trim().toLowerCase();
        boolean found = false;
        for (Student student : students) {
            String firstName = student.getName().split(" ")[0].toLowerCase();
            if (firstName.equals(searchName)) {
                System.out.println("\nStudent found: " + student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("\nNo student found with the first name \"" + searchName + "\".");
        }
    }

    public static void sortStudentsByName() {
        students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        System.out.println("\nStudents sorted by name.");
    }

    public static void findStudentWithHighestGrade() {
        if (students.isEmpty()) {
            System.out.println("\nNo students available.");
            return;
        }
        Student topStudent = students.stream()
                .min((s1, s2) -> s1.getGrade().compareToIgnoreCase(s2.getGrade()))
                .orElse(null);
        System.out.println("\nStudent with the highest grade: " + topStudent);
    }
}