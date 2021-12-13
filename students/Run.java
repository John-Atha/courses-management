package students;

import java.util.ArrayList;
import java.util.Scanner;

import students.endpoints.Controllers;
import students.models.Course;
import students.models.Student;

public class Run {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Course> courses   = new ArrayList<Course>();

        System.out.println("Welcome to your new students managements system...");
        String help_message = "Type `help` to see the available commands.";
        System.out.println(help_message);
        
        while (true) {
            System.out.println("-----------------------------");
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                System.out.println("Thank you, bye bye...");
                break;
            }
            System.out.println();
            Controllers.exec(input, students, courses);
            //System.out.println(output);
        }
        scanner.close();

    }
}
