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
