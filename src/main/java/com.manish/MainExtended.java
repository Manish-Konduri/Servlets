package com.manish;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MainExtended {

    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner((System.in));
        TaskManager taskManager = new TaskManager();
        int id = 1;
        while (true) {
            System.out.println("1-Add Data, 2-Print Data, 3-Search, 4-Delete, 5-Change Status, 6-View by status,7-Today's due, 8-Quit");
            int inputq = sc.nextInt();
            String gr = sc.nextLine();
            if (inputq == 1) {
                System.out.println("Enter Name");
                String name = sc.nextLine();
                System.out.println("Enter Description");
                String description = sc.nextLine();
                System.out.println("Enter the Date in format of 'yyyy-mm-dd'");
                try {
                    Date date = new SimpleDateFormat("yyyy-mm-dd").parse(sc.nextLine());
                    taskManager.adding(id, name, description, date);
                    id = id + 1;
                } catch (Exception e) {
                    System.out.println("Enter in correct format");
                }
            } else if (inputq == 2) {
                ArrayList<Task> returnDisplay;
                if (taskManager.display() == null) {
                    System.out.println("No Data");
                } else {
                    returnDisplay = taskManager.display();
                    for (Task task : returnDisplay) {
                        System.out.println(task);
                    }
                }
            } else if (inputq == 4) {
                try {

                    System.out.println("Enter id to be deleted");
                    int q = sc.nextInt();
                    if (taskManager.delete(q) == 1) {
                        System.out.println("Deleted Successfully");
                    } else {
                        System.out.println("No ID");
                    }
                } catch (Exception e) {
                    System.out.println("No ID");
                }
            } else if (inputq == 3) {
                ArrayList<Task> returnSearch;
                System.out.println("Enter the id");
                int searchIndex = sc.nextInt();
                returnSearch = taskManager.search(searchIndex);
                try {
                    if (returnSearch.size() > 0) {
                        int counter = 0;
                        for (Task search : returnSearch) {
                            if (search.getId() == searchIndex) {
                                System.out.println(search);
                                counter++;
                            }
                            if (counter == 0)
                                System.out.println("No such Id Found");
                        }
                    } else {
                        System.out.println("No such Id Found");
                    }
                } catch (Exception e) {
                    System.out.println("No Data");
                }
            } else if (inputq == 5) {
                try {
                    System.out.println("Do you want to change the status 1-Yes, 2-No:");
                    ArrayList<Task> ArrayListDisplay;
                    int inp = sc.nextInt();
                    if (inp == 1) {
//                        ArrayListDisplay=taskManager.search();
//                        for (Task task : ArrayListDisplay) System.out.println(task.getId() + " " + task.getName());
                        System.out.println("Enter the id to be changed:");
                        int i = sc.nextInt();
                        System.out.println("What status do you want to modify to:");
                        System.out.println("1.Initial, 2.In_progress, 3.Done");
                        int s = sc.nextInt();
                        if (taskManager.changeStatus(s, i))
                            System.out.println("Success");
                        else
                            System.out.println("No such Id found");
                    } else {
                        System.out.println("1-Add Data, 2-Print Data, 3-Search, 4-Delete, 5-Change Status, 6-View by status,7-Today's due, 8-Quit");
                    }
                } catch (Exception e) {
                    System.out.println("No Data");
                }
            } else if (inputq == 6) {
                try {
                    ArrayList<Task> arl1;
                    System.out.println("1.Initial/IN_PROGRESS, 2.Done");
                    int lbs = sc.nextInt();
                    arl1 = taskManager.listByStatus(lbs);
                    if (arl1.size() > 0) {
                        for (Task task : arl1) {
                            System.out.println(task);
                        }
                    } else {
                        System.out.println("No Data");
                    }
                } catch (Exception e) {
                    System.out.println("No Data");
                }
            } else if (inputq == 7) {
                try {
                    ArrayList<Task> returnDueDate = taskManager.dueToday();
                    System.out.println(returnDueDate.size());
                    for (Task task : returnDueDate) {
                        System.out.println(task);
                    }
                } catch (Exception e) {
                    System.out.println("No data");
                }
            } else if (inputq >= 8) {
                break;
            }
        }
    }
}
