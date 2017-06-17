package us.vadimcorp.fromfilesorttoconsole;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String KEYWORD_FOR_EXIT = "--stop";

//        try (Scanner sc = new Scanner("2 sasha 2 mashaNiga and Nice");) {
//            while (sc.hasNext()) {
//                String line = sc.next();
//                System.out.println(line);
//            }
//        }

    public static void main(String[] args) {
        System.out.println("Persons are loading...");
        PersonExtracter personExtracter = new PersonExtracter().invoke();
        if (personExtracter.wasError()){
            System.out.println("App was stopped!");
            return;
        }
        System.out.println("Persons were loaded.");
        System.out.println("App was started. Enter " + KEYWORD_FOR_EXIT + " to exit");
        processCommand(personExtracter);
        System.out.println("App was stopped.");


    }

    public static void processCommand(PersonExtracter personExtracter){
        System.out.println("Enter one of those commands '-f' '-s' '-a'...");
        List<Person> persons = personExtracter.getPersons();
        try(Scanner scanner = new Scanner(System.in)){
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (isStopTheApp(line)){
                    break;
                }
                if (line.equals("-f")) {
                    Collections.sort(persons, new FirstNameComparator());
                } else if (line.equals("-s")) {
                    Collections.sort(persons, new SecondNameComparator());
                } else if (line.equals("-a")) {
                    Collections.sort(persons, new AgeComparator());
                }
                printList(persons);
                System.out.println("Enter one of these commands '-f' '-s' '-a'...");
            }
        }
    }

    private static void printList(List<Person> persons) {
        persons.forEach(System.out::println);
    }

    private static boolean isStopTheApp(String line) {
        return line.equals(KEYWORD_FOR_EXIT);
    }


    }

