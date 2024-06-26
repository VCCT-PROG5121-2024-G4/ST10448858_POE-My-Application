package com.mycompany.st10448858_poe;

import java.util.Scanner;

public class Part_2 {
    private Task[] tasks;

    public void run() {
        System.out.println("Running Part_2...");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to EasyKanban");

        // This boolean will determine if the user's login is correct.
        boolean loggedIn = login();
        if (!loggedIn) {
            System.out.println("Login unsuccessful. Exiting application.");
            return;
        }

        // For demonstration purposes, we'll initialize the tasks array with predefined data
        initializeTestData();

        int choice;
        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add tasks");
            System.out.println("2. Show report");
            System.out.println("3. Search task by name");
            System.out.println("4. Delete task by name");
            System.out.println("5. Quit");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addTasks(scanner);
                    break;
                case 2:
                    if (tasks[0] == null) {
                        System.out.println("No tasks created yet.");
                    } else {
                        showReport();
                    }
                    break;
                case 3:
                    searchTaskByName(scanner);
                    break;
                case 4:
                    deleteTaskByName(scanner);
                    break;
                case 5:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    public boolean login() {
        // Simplified login process, return true if successful, false otherwise
        return true; // Assuming login is always successful for now
    }

    public int getNumTasks(Scanner scanner) {
        System.out.print("Enter the number of tasks: ");
        return scanner.nextInt();
    }

    public void addTasks(Scanner scanner) {
        System.out.print("Enter the number of tasks: ");
        int numTasks = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        tasks = new Task[numTasks]; // Reinitialize the class member variable

        for (int i = 0; i < numTasks; i++) {
            tasks[i] = addTask(scanner, i);
        }
    }

    public Task addTask(Scanner scanner, int taskNumber) {
        scanner.nextLine(); // Consume newline character
        System.out.println("Task " + (taskNumber + 1) + ":");

        System.out.print("Enter Task Name: ");
        String taskName = scanner.nextLine();

        System.out.print("Enter Task Description (max 50 characters): ");
        String taskDescription = scanner.nextLine();
        if (taskDescription.length() > 50) {
            System.out.println("Please enter a task description of less than 50 characters");
            return addTask(scanner, taskNumber); // Retry adding task
        } else {
            System.out.println("Task successfully captured");
        }

        System.out.print("Enter Developer First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Developer Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Task Duration (in hours): ");
        double duration = scanner.nextDouble();

        String taskId = generateTaskID(taskName, taskNumber, firstName, lastName);

        System.out.println("Choose Task Status:");
        System.out.println("1. To Do");
        System.out.println("2. Done");
        System.out.println("3. Doing");
        int statusChoice = scanner.nextInt();
        String status;
        switch (statusChoice) {
            case 1:
                status = "To Do";
                break;
            case 2:
                status = "Done";
                break;
            case 3:
                status = "Doing";
                break;
            default:
                status = "Unknown";
        }

        return new Task(taskName, taskNumber, taskDescription, firstName, lastName, duration, taskId, status);
    }

    public String generateTaskID(String taskName, int taskNumber, String firstName, String lastName) {
        String taskId = taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + lastName.substring(lastName.length() - 3).toUpperCase();
        return taskId;
    }

    public void showReport() {
        int todoCount = 0, doingCount = 0, doneCount = 0;

        System.out.println("\n======= EasyKanban Report =======");

        // Iterate through tasks to count and display tasks in each status
        for (Task task : tasks) {
            if (task != null) {
                switch (task.getStatus()) {
                    case "To Do":
                        todoCount++;
                        break;
                    case "Doing":
                        doingCount++;
                        break;
                    case "Done":
                        doneCount++;
                        break;
                    default:
                        break;
                }
            }
        }

        // Display tasks in each status section
        System.out.println("\n--- To Do ---");
        System.out.println("Number of tasks: " + todoCount);
        displayTasksByStatus("To Do");

        System.out.println("\n--- Doing ---");
        System.out.println("Number of tasks: " + doingCount);
        displayTasksByStatus("Doing");

        System.out.println("\n--- Done ---");
        System.out.println("Number of tasks: " + doneCount);
        displayTasksByStatus("Done");

        System.out.println("===============================");
    }

    private void displayTasksByStatus(String status) {
        for (Task task : tasks) {
            if (task != null && task.getStatus().equals(status)) {
                System.out.println("Task Name: " + task.getTaskName());
                System.out.println("Task Description: " + task.getTaskDescription());
                System.out.println("Developer: " + task.getFirstName() + " " + task.getLastName());
                System.out.println("Task Duration: " + task.getDuration() + " hours");
                System.out.println("Task ID: " + task.getTaskId());
                System.out.println("Task Status: " + task.getStatus());
                System.out.println("---");
            }
        }
    }

    public void searchTaskByName(Scanner scanner) {
        System.out.print("Enter the task name to search: ");
        String taskName = scanner.nextLine();

        for (Task task : tasks) {
            if (task != null && task.getTaskName().equalsIgnoreCase(taskName)) {
                System.out.println("Task found:");
                System.out.println("Task Name: " + task.getTaskName());
                System.out.println("Task Description: " + task.getTaskDescription());
                System.out.println("Developer: " + task.getFirstName() + " " + task.getLastName());
                System.out.println("Task Duration: " + task.getDuration() + " hours");
                System.out.println("Task ID: " + task.getTaskId());
                System.out.println("Task Status: " + task.getStatus());
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void deleteTaskByName(Scanner scanner) {
        System.out.print("Enter the task name to delete: ");
        String taskName = scanner.nextLine();

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null && tasks[i].getTaskName().equalsIgnoreCase(taskName)) {
                System.out.println("Deleting task:");
                System.out.println("Task Name: " + tasks[i].getTaskName());
                tasks[i] = null; // Remove task
                System.out.println("Task deleted.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void initializeTestData() {
        tasks = new Task[4];
        tasks[0] = new Task("Create Login", 1, "Login feature", "Mike", "Smith", 5, generateTaskID("Create Login", 1, "Mike", "Smith"), "To Do");
        tasks[1] = new Task("Create Add Features", 2, "Add features", "Edward", "Harrison", 8, generateTaskID("Create Add Features", 2, "Edward", "Harrison"), "Doing");
        tasks[2] = new Task("Create Reports", 3, "Reports feature", "Samantha", "Paulson", 2, generateTaskID("Create Reports", 3, "Samantha", "Paulson"), "Done");
        tasks[3] = new Task("Add Arrays", 4, "Array feature", "Glenda", "Oberholzer", 11, generateTaskID("Add Arrays", 4, "Glenda", "Oberholzer"), "To Do");
    }

    public static void main(String[] args) {
        Part_2 kanbanApp = new Part_2();
        kanbanApp.run();
    }
}

class Task {
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String firstName;
    private String lastName;
    private double duration;
    private String taskId;
    private String status;

    public Task(String taskName, int taskNumber, String taskDescription, String firstName, String lastName, double duration, String taskId, String status) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.firstName = firstName;
        this.lastName = lastName;
        this.duration = duration;
        this.taskId = taskId;
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getDuration() {
        return duration;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getStatus() {
        return status;
    }
}
