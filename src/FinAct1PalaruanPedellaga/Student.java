/*
Name: Pedellaga, Ralph Waldo V.
      Palaruan, Princess Laralei F.
Date: April 24, 2025
Activity name: Final LAb Activity  1

Student Class

 This class represents a student in the Student Management System. Each student has a unique ID, a name, an age, and a grade. The class provides methods to access and modify these attributes.

 Attributes:
 - ID: A unique identifier for the student (final, cannot be changed).
 - Name: The name of the student.
 - Age: The age of the student.
 - Grade: The grade of the student (A, B, C, D, E).

 Methods:
 - Constructor: Initializes a new student with the provided ID, name, age, and grade.
 - Getters: Methods to retrieve the values of ID, Name, Age, and Grade.
 - Setters: Methods to update the Name, Age, and Grade of the student.
 - toString: Returns a string representation of the student object, including
   ID, Name, Age, and Grade.

 */

package FinAct1PalaruanPedellaga;

public class Student {

    private final int ID;
    private String Name;
    private int Age;
    private String Grade;

    public Student(int ID, String Name, int Age, String Grade) {
        this.ID = ID;
        this.Name = Name;
        this.Age = Age;
        this.Grade = Grade;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    public String getGrade() {
        return Grade;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public void setGrade(String grade) {
        this.Grade = grade;
    }

    @Override
    public String toString() {
        return "ID: " + ID + ", Name: " + Name + ", Age: " + Age + ", Grade: " + Grade;
    }
}
