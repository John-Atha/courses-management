package students;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private ArrayList<Course> courses = new ArrayList<Course>();
    private Map<Student, Course> grades = new HashMap<Student, Course>();
    static int students_num = 0;

    public Student(String firstName, String lastName, int age, String email) {
        this.id = ++students_num;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String new_firstName) {
        this.firstName = new_firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String new_lastName) {
        this.lastName = new_lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int new_age) {
        this.age = new_age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        if (this.courses.contains(course)) {
            return;
        }
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        if (!this.courses.contains(course)) {
            return;
        }
        this.courses.remove(course);
    }

    @Override
    public String toString() {
        return "student " + id + ": " + firstName + " " + lastName + ", " + age + ", " + email;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        Student other_student = (Student) other;
        return  this.id == other_student.id &&
                this.firstName == other_student.firstName &&
                this.lastName == other_student.lastName &&
                this.age == other_student.age &&
                this.email == other_student.email;
    }

    @Override
    public int hashCode() {
        final int KEY = 52;
        return KEY * getId();
    }

}
