package run;

import service.DegreeService;
import service.DepartmentService;
import service.LectorService;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String inputDepartment = "Input department name: ";
    private static final String inputTemplate = "Input template: ";
    private static final DepartmentService departmentService = new DepartmentService();
    private static final LectorService lectorService = new LectorService();
    private static final DegreeService degreeService = new DegreeService();
    private static final Scanner scanner = new Scanner(System.in);

    private static String input(String shouldUserInput) {
        System.out.println(shouldUserInput);
        StringBuilder stringBuilder = new StringBuilder();
        scanner.nextLine();
        if (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }

    private static void printCountOfLectorsByDepartment() {
        String departmentName = input(inputDepartment);
        if (departmentService.existDepartment(departmentName)) {
            departmentService.getCountOfLectorsByDepartment(departmentName).forEach((key, value) -> {
                System.out.format("%s - %d\n", key, value);
            });
        } else {
            System.out.println("Sorry, the input department does not exist");
        }
    }

    private static void printHeadOfDepartment() {
        String departmentName = input(inputDepartment);
        if (departmentService.existDepartment(departmentName)) {
            System.out.printf("Head of %s department is %s%n", departmentName, departmentService.getHeadOfDepartment(departmentName));
        } else {
            System.out.println("Sorry, the input department does not exist");
        }
    }

    private static void printAverageSalaryInDepartment() {
        String departmentName = input(inputDepartment);
        if (departmentService.existDepartment(departmentName)) {
            System.out.format("The average salary of %s is %.2f%n", departmentName, departmentService.averageSalaryInDepartment(departmentName));
        } else {
            System.out.println("Sorry, the input department does not exist");
        }
    }

    private static void printCountOfEmployeeInDepartment() {
        String departmentName = input(inputDepartment);
        if (departmentService.existDepartment(departmentName)) {
            System.out.println(departmentService.countOfEmployeeInDepartment(departmentName));
        } else {
            System.out.println("Sorry, the input department does not exist");
        }
    }

    private static void printSearchingByTemplate() {
        String template = input(inputTemplate);
        List<String> departmentTemplate = departmentService.searchByTemplate(template);
        List<String> lectorTemplate = lectorService.searchByTemplate(template);
        List<String> degreeTemplate = degreeService.searchByTemplate(template);
        if (departmentTemplate.isEmpty()
                && lectorTemplate.isEmpty()
                && degreeTemplate.isEmpty()) {
            System.out.println("There are no results for this template");
        } else {
            printResultOfSearching(degreeTemplate, "Search by degrees: ");
            printResultOfSearching(lectorTemplate, "Search by lectors: ");
            printResultOfSearching(departmentTemplate, "Search by department:");
        }
    }

    public static void choice() {
        int choice = -1;
        String options = "1.Who is head of department \n" +
                "2.Show department  statistic\n" +
                "3.Show the average salary for department\n" +
                "4.Show count of employee for department\n" +
                "5.Global search by your template\n" +
                "0.Exit";
        do {
            System.out.println(options);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        printHeadOfDepartment();
                        break;
                    case 2:
                        printCountOfLectorsByDepartment();
                        break;
                    case 3:
                        printAverageSalaryInDepartment();
                        break;
                    case 4:
                        printCountOfEmployeeInDepartment();
                        break;
                    case 5:
                        printSearchingByTemplate();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Cannot find such option");
                }
            } else {
                scanner.next();
            }
        } while (choice != 0);
    }

    private static void printResultOfSearching(List resultList, String outputString) {
        if (!resultList.isEmpty()) {
            System.out.println(outputString);
            for (int i = 0; i < resultList.size(); i++) {
                if (i != resultList.size() - 1) {
                    System.out.printf("%s, ", resultList.get(i));
                } else {
                    System.out.printf("%s%n", resultList.get(i));
                }
            }
        }
    }

}
