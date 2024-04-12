package com.google.main;

import com.google.service.StudentService;
import java.util.Scanner;

public class RunApplication {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initialization();
    }

    private static void initialization() {
        System.out.println("------------------------------------------------");
        System.out.println("Welcome to Student Management Information System");
        System.out.println("------------------------------------------------");
        System.out.println("Student information includes id, age and name");
        System.out.println("Enter a number to implement the corresponding function");
        System.out.println("Please separate the data with Spaces");
        System.out.println("-------------------------------------------------");
        System.out.println("1.Create a set of data including id, age and name\n2.delete by id\n3.View all the information in the table\n4. view a set of information by id\n5.update a set of data including id, age and name\n6.exit");
        System.out.println("-------------------------------------------------");
        Interface();
    }

    private static void Interface() {
       while (true){
        switch (scanner.nextLine()) {
            case "1":
                System.out.println(StudentService.create(scanner.next(), scanner.nextInt(), scanner.next()));
                break;
            case "2":
                System.out.println(StudentService.deleteById(scanner.next()));
                break;
            case "3":
                System.out.println(StudentService.readAll());
                break;
            case "4":
                System.out.println(StudentService.readById(scanner.next()));
                break;
            case "5":
                System.out.println(StudentService.update(scanner.next(), scanner.nextInt(), scanner.next()));
                break;
            case "6":
                System.exit(0);
                break;
            default:
                break;
        }
        }
    }
}
