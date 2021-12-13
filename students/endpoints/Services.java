package students.endpoints;

import java.util.ArrayList;

import students.models.Course;
import students.models.Student;

public class Services {

    public static String getHelp() {
        String help = "Type\n" 
        +"\t * `students` to see all the students on our system.\n"
        +"\t * `courses` to see all the courses on our system.\n"
        +"\t * `student ID` to see the details about the student with the specific ID\n"
        +"\t * `course ID` to see the details about the course with the specific ID\n"
        +"\t * `add-student first_name last_name age email` to add a new student to our system.\n"
        +"\t * `delete-student ID` to delete the student with the ID from our system.\n"
        +"\t * `add-course title semester` to add a new course to our system.\n"
        +"\t * `delete-course ID` to delete the course with the ID from our system.\n"
        +"\t * `student STUDENT_ID add COURSE_ID` to add the course with COURSE_ID to the student with the STUDENT_ID\n"
        +"\t * `student STUDENT_ID remove COURSE_ID` to remove the course with COURSE_ID from the student with the STUDENT_ID\n"
        +"\t * `exit` to exit";

        return help;
    }

    public static Student getOneStudentCore(ArrayList<Student> students, int id) {
        Student student = null;
        for (Student stud : students) {
            if (stud.getId() == id) {
                student = stud;
                break;
            }
        }
        return student;
    }

    public static Course getOneCourseCore(ArrayList<Course> courses, int id) {
        Course course = null;
        for (Course cour : courses) {
            if (cour.getId() == id) {
                course = cour;
                break;
            }
        }
        return course;
    }

    public static String getAllStudents(ArrayList<Student> students) {
        if (students.size()==0) {
            return "No students found.";
        }
        String result = "";
        for (Student student : students) {
            result = result.concat(student.toString() + "\n");
        }
        return result;
    }

    public static String getAllCourses(ArrayList<Course> courses) {
        if (courses.size() == 0) {
            return "No courses found.";
        }
        String result = "";
        for (Course course : courses) {
            result = result.concat(course.toString() + "\n");
        }
        return result;
    }

    public static String getOneStudent(ArrayList<Student> students, int id) {
        Student student = getOneStudentCore(students, id);
        if (student != null) {
            String res = student.toString();
            ArrayList<Course> courses = student.getCourses(); 
            if (courses.size()!=0) {
                res += "\n with courses:\n";
                for (Course course : courses) {
                    res += "\t* " + course.toString() + "\n";
                }
            }
            return res;
        }
        return "Student with id " + id + " not found."; 
    }

    public static String getOneCourse(ArrayList<Course> courses, int id) {
        Course course = getOneCourseCore(courses, id);
        if (course != null) {
            return course.toString();
        }
        return "Course with id " + id + " not found.";
    }
    
    public static String updateCourses(
        ArrayList<Student> students,
        ArrayList<Course> courses,
        int student_id,
        int course_id,
        String kind) {
            Student student = getOneStudentCore(students, student_id);
            Course course = getOneCourseCore(courses, course_id);
            if (student==null) {
                return "Student with id " + student_id + " not found."; 
            }
            if (course==null) {
                return "Course with id " + course_id + " not found.";
            }
            if (kind=="attach") {
                student.addCourse(course);
                return "Course added successfully!";
            }
            student.removeCourse(course);
            return "Course removed successfully!";
        }

    public static String addStudent(ArrayList<Student> students, String[] data) {
        int id = -1;
        try {
            int age = Integer.parseInt(data[3]);
            Student student = new Student(data[1], data[2], age, data[4]);
            id = student.getId();
            students.add(student);
        }
        catch (Exception e) {
            return "Sorry, invalid input, try again.";
        }
        return "Student with id '" + id +"' added successfully!!";
    }

    public static String deleteStudent(ArrayList<Student> students, int id) {
        Student student = getOneStudentCore(students, id);
        if (student == null) {
            return "Student with id " + id + " not found."; 
        }
        students.remove(student);
        return "Student with id " + id + " deleted successfully."; 
    }

    public static String addCourse(ArrayList<Course> courses, String[] data) {
        int id = -1;
        try {
            int semester = Integer.parseInt(data[2]);
            Course course = new Course(data[1], semester);
            id = course.getId();
            courses.add(course);
        }
        catch (Exception e) {
            return "Sorry, invalid input, try again.";
        }
        return "Course with id '" + id +"' added successfully!!";
    }

    public static String deleteCourse(ArrayList<Student> students, ArrayList<Course> courses, int id) {
        Course course = getOneCourseCore(courses, id);
        if (course == null) {
            return "Course with id " + id + " not found."; 
        }
        for (Student stud : students) {
            stud.removeCourse(course);
        }
        courses.remove(course);
        return "Course with id " + id + " deleted successfully."; 
    }

}
