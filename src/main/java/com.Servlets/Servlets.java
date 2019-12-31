package com.Servlets;

import com.manish.Task;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Servlets extends HttpServlet {
    public Servlets()
    {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        com.manish.TaskManager taskManager = new com.manish.TaskManager();
        ArrayList<Task> arrayList = taskManager.display();
        JSONArray jsArray = new JSONArray(arrayList);
        //System.out.println();
        out.println(jsArray);
    }
}
