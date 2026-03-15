package org.example;

import java.util.Objects;

public class Student extends Human{
    private String department;

    public Student(String surname, String name, String patronymic, int age, String department) {
        super(surname, name, patronymic, age);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(department, student.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department);
    }
}
