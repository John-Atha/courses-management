package students;

import java.util.ArrayList;

import students.Course;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private ArrayList<Course> courses = new ArrayList<Course>();
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

}
