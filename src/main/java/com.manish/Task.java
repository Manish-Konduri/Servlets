package com.manish;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Comparable<Task> {
        private String name;
        private String description;
        private Date date;
        private int id;
        private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Task()
    {
        this.status = Status.Initial;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd");
        return ("Id : "+id+'\n'+"name : "+name+'\n'+"Description : "+description+'\n'+"Date : "+simpleDateFormat.format(date)+'\n'+"Status : "+ status +'\n');
    }




    @Override
    public int compareTo(Task task) {
        if(this.getDate().compareTo(task.getDate())==0)
        {
            return 0;
        }
        else if(this.getDate().compareTo(task.getDate())<0){
            return -1;
        }
        else{
            return 1;
        }
    }
}