package students.endpoints;

import java.util.ArrayList;
import java.util.Map;

import students.models.Course;
import students.models.Student;

import java.util.HashMap;

public class Controllers {

    public static String OMG() {
        System.out.println("OOOMMGGG");
        return "OMG";
    }

    public static void exec(String command, ArrayList<Student> students, ArrayList<Course> courses) {

        
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
                    System.out.println("Invalid student id `" + parts[1] + "`.");
                    return;
                }
                command = "delete-student";
            }
            else if (parts[0].equals("delete-course")) {
                try {
                    id1 = Integer.parseInt(parts[1]);
                }
                catch (Exception e) {
                    System.out.println("Invalid course id `" + parts[1] + "`.");
                    return;
                }
                command = "delete-course";
            }
            else if (parts[0].equals("student") || parts[0].equals("course")) {
                try {
                    id1 = Integer.parseInt(parts[1]);
                }
                catch (Exception e) {
                    System.out.println("Invalid" + parts[0] + " id `" + parts[1] + "`.");
                    return;
                }
                command = "one-" + parts[0];
            }
            else {
                System.out.println("Invalid command `" + command + "`");
                return;
            }
        }

        else if (parts.length == 3) {
            if (parts[0].equals("add-course")) {
                command = "add-course";
                data = parts;
            }
        }

        else if (parts.length == 4) {
            if (parts[0].equals("student") && (parts[2].equals("add") || parts[2].equals("remove"))) {
                try {
                    id1 = Integer.parseInt(parts[1]);
                }
                catch (Exception e) {
                    System.out.println("Invalid student id `" + parts[1] + "`.");
                    return;
                }
                try {
                    id2 = Integer.parseInt(parts[3]);
                }
                catch (Exception e) {
                    System.out.println("Invalid course id `" + parts[3] + "`.");
                    return;
                }
                command = "course-" + (parts[2].equals("add") ? "attach" : "deattach");
            }
            else {
                System.out.println("Invalid command `" + command + "`");
                return;
            }
        }

        else if (parts.length == 5) {
            if (parts[0].equals("add-student")) {
                command = "add-student";
                data = parts;
            }
        }
        
        
        final Map<String, Runnable> actions = new HashMap<String, Runnable>();

        final int idd1 = id1;
        final int idd2 = id2;
        final String[] ddata = data;

        actions.put("help",            () -> { String res = Services.getHelp(); System.out.println(res); });
        actions.put("students",        () -> { String res = Services.getAllStudents(students); System.out.println(res); });
        actions.put("courses",         () -> { String res = Services.getAllCourses(courses); System.out.println(res); });
        actions.put("one-student",     () -> { String res = Services.getOneStudent(students, idd1); System.out.println(res); });
        actions.put("one-course",      () -> { String res = Services.getOneCourse(courses, idd1); System.out.println(res); });
        actions.put("course-attach",   () -> { String res = Services.updateCourses(students, courses, idd1, idd2, "attach"); System.out.println(res); });
        actions.put("course-deattach", () -> { String res = Services.updateCourses(students, courses, idd1, idd2, "deattach"); System.out.println(res); });
        actions.put("add-student",     () -> { String res = Services.addStudent(students, ddata); System.out.println(res); });
        actions.put("delete-student",  () -> { String res = Services.deleteStudent(students, idd1); System.out.println(res); });
        actions.put("add-course",      () -> { String res = Services.addCourse(courses, ddata); System.out.println(res); });
        actions.put("delete-course",   () -> { String res = Services.deleteCourse(students, courses, idd1); System.out.println(res); });
        actions.put("test",            () -> { String res = OMG(); System.out.println(res); });

        if (actions.containsKey(command)) {
            /* String res =  */
            actions.get(command).run();
            return;
            //return res;
        }
        System.out.println("Invalid command `" + command + "`");
        return;
    }
    
}
