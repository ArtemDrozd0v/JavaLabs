package org.example;

public class Student extends Human{
    private String university;
    private String faculty;
    private String specialty;

    public Student(String surname, String name, String patronymic, int age, Gender gender, String university, String faculty, String specialty) {
        super(surname, name, patronymic, age, gender);
        this.university = university;
        this.faculty = faculty;
        this.specialty = specialty;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
