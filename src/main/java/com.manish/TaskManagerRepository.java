package com.manish;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class TaskManagerRepository implements TaskRepository {
    private static final String TASKS_JSON_FILE = "/home/manishk/Desktop/tasks.json";
    ArrayList<Task> tasks;
    ObjectMapper objectMapper = new ObjectMapper();

    public TaskManagerRepository() {
        tasks = readFromFile();
    }

    public void writeToFile(ArrayList<Task> tasks) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new FileWriter(TASKS_JSON_FILE), tasks);
//            Testing

        } catch (IOException e) {
//           /testing
            throw new IllegalStateException(e);
            //Testing
        }
    }

    public ArrayList<Task> readFromFile() {
        final File file = new File(TASKS_JSON_FILE);
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, TaskList.class);
            } catch (IOException e) {
                System.out.println("No data");
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void adding(int id, String name, String description, Date date) {
        Task d = new Task();
        d.setId(id);
        d.setName(name);
        d.setDescription(description);
        d.setDate(date);
        tasks.add(d);
        System.out.println(tasks);
        writeToFile(tasks);
    }

    @Override
    public int delete(int query) {
        if (tasks.size() > 0) {
            for (int i = 0; i < tasks.size(); i++) {
                if (query == tasks.get(i).getId()) {
                    tasks.remove(i);
                    writeToFile(tasks);
                    return 1;
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    @Override
    public ArrayList<Task> display() {
        return readFromFile();
    }

    @Override
    public ArrayList<Task> search(int q) {
        return readFromFile();
    }

    @Override
    public boolean changeStatus(int s, int i) {
        if (s == 1) {
            for (int j = 0; j < tasks.size(); j++) {
                if (tasks.get(j).getId() == i) {
                    tasks.get(j).setStatus(Status.Initial);
                    writeToFile(tasks);
                    return true;
                }
            }
        } else if (s == 2) {
            for (int j = 0; j < tasks.size(); j++) {
                if (tasks.get(j).getId() == i) {
                    tasks.get(j).setStatus(Status.IN_PROGRESS);
                    writeToFile(tasks);
                    return true;
                }
            }
        } else if (s == 3) {
            for (int j = 0; j < tasks.size(); j++) {
                if (tasks.get(j).getId() == i) {
                    tasks.get(j).setStatus(Status.Done);
                    writeToFile(tasks);
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public boolean changeStatusCheck() {

        writeToFile(tasks);
        return tasks.size() > 0;
    }

    @Override
    public ArrayList<Task> listByStatus(int query) {
        ArrayList<Task> arl = new ArrayList<>();

        if (query == 1) {
            for (Task task : tasks) {
                String qw = task.getStatus().toString();
                if (qw.equals("Initial") || qw.equals("IN_PROGRESS")) {
                    arl.add(task);
                }
            }
            Collections.sort(arl);
            return arl;
        }
        return null;
    }

    @Override
    public ArrayList<Task> dueToday() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        ArrayList<Task> arl = new ArrayList<>();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);


        for (Task task : tasks) {
            Date qw = task.getDate();

            if ((simpleDateFormat.format(qw).compareTo(String.valueOf(date)) == 0)) {
                arl.add(task);
                //tetsing
            }
        }
        //Tetsing
//        Testing
        return arl;
    }
}