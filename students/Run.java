package students;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Run {


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


    public static String exec(String command, Scanner scanner, ArrayList<Student> students, ArrayList<Course> courses) {
        String help = "Type\n" 
            +"\t * `students` to see all the students on our system.\n"
            +"\t * `courses` to see all the courses on our system.\n"
            +"\t * `student ID` to see the details about the student with the specific ID\n"
            +"\t * `course ID` to see the details about the course with the specific ID\n"
            +"\t * `add-student first_name last_name age email` to add a new student to our system.\n"
            +"\t * `delete-student ID` to delete the student with the ID from our system.\n"
            +"\t * `student STUDENT_ID add COURSE_ID` to add the course with COURSE_ID to the student with the STUDENT_ID\n"
            +"\t * `student STUDENT_ID remove COURSE_ID` to remove the course with COURSE_ID from the student with the STUDENT_ID\n"
            +"\t * `exit` to exit";
        
        String[] parts = command.split(" ");
        int id1 = 0;
        int id2 = 0;
        String[] data = new String[0];

        if (parts.length == 2) {
            if (parts[0].equals("delete-student")) {
                try {
                    id1 = Integer.parseInt(parts[1]);
                }
                catch (Exception e) {
                    return "Invalid student id `" + parts[1] + "`.";
                }
                command = "delete-student";
            }
            else if (parts[0].equals("student") || parts[0].equals("course")) {
                try {
                    id1 = Integer.parseInt(parts[1]);
                }
                catch (Exception e) {
                    return "Invalid" + parts[0] + " id `" + parts[1] + "`.";
                }
                command = "one-" + parts[0];
            }
            else {
                return "Invalid command `" + command + "`";
            }
        }
        else if (parts.length == 4) {
            if (parts[0].equals("student") && (parts[2].equals("add") || parts[2].equals("remove"))) {
                try {
                    id1 = Integer.parseInt(parts[1]);
                }
                catch (Exception e) {
                    return "Invalid student id `" + parts[1] + "`.";
                }
                try {
                    id2 = Integer.parseInt(parts[3]);
                }
                catch (Exception e) {
                    return "Invalid course id `" + parts[3] + "`.";
                }
                command = "course-" + (parts[2].equals("add") ? "attach" : "deattach");
            }
            else {
                return "Invalid command `" + command + "`";
            }
        }
        else if (parts.length == 5) {
            if (parts[0].equals("add-student")) {
                command = "add-student";
                data = parts;
            }
        }
        
        
        final Map<String, String> actions = new HashMap<String, String>();

        actions.put("help",            help);
        actions.put("students",        getAllStudents(students));
        actions.put("courses",         getAllCourses(courses));
        actions.put("one-student",     getOneStudent(students, id1));
        actions.put("one-course",      getOneCourse(courses, id1));
        actions.put("course-attach",   updateCourses(students, courses, id1, id2, "attach"));
        actions.put("course-deattach", updateCourses(students, courses, id1, id2, "deattach"));
        actions.put("add-student",     addStudent(students, data));
        actions.put("delete-student",  deleteStudent(students, id1));

        if (actions.containsKey(command)) {
            String res = actions.get(command);
            return res;
        }
        return "Invalid command `" + command + "`";
    }
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Course> courses   = new ArrayList<Course>();

        System.out.println("Welcome to your new students managements system...");
        String help_message = "Type `help` to see the available commands.";
        System.out.println(help_message);
        
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                System.out.println("Thank you, bye bye...");
                break;
            }
            String output = exec(input, scanner, students, courses);
            System.out.println(output);
        }
        scanner.close();

    }
}
