package com.manish;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public interface TaskRepository {
    void adding(int id,String name, String description, Date date) throws SQLException;
    int delete(int query);
    ArrayList<Task> display();
    ArrayList<Task> search(int q);
    boolean changeStatusCheck();
    boolean changeStatus(int s, int i);
    ArrayList<Task> listByStatus(int lbd);
    ArrayList<Task> dueToday();

}
