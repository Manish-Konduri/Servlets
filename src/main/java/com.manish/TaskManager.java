package com.manish;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

public  class TaskManager{
    TaskRepository tr = new dataBase();

    public void adding(int id, String name, String description, Date date) throws SQLException {
        try {
            tr.adding(id, name, description, date);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

        public ArrayList<Task> display() {
          return tr.display();
        }

        public int delete(int q) {
          return tr.delete(q);
        }

        public ArrayList<Task> search(int q) {
          return tr.search(q);
        }
    public boolean changeStatusCheck(){
        return tr.changeStatusCheck();
    }

        public boolean changeStatus(int s, int i)
        {
        return tr.changeStatus(s,i);
        }
        public ArrayList<Task> listByStatus(int lbs){
           return tr.listByStatus(lbs);
        }
        public ArrayList<Task> dueToday(){
            return tr.dueToday();
        }
    }
