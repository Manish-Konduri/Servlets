package com.manish.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

public class Post extends HttpServlet {
    public Post() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrintWriter out = resp.getWriter();
            com.manish.TaskManager taskManager = new com.manish.TaskManager();
            String name = req.getParameter("Name");
            if (name == null) {
                out.println("Enter Valid Name");
                return;
            }
            String description = req.getParameter("Description");
            if (description == null) {
                out.println("Enter Valid Description");
                return;
            }
            String date1 = req.getParameter("Date");
            try {
                Date date = new SimpleDateFormat("yyyy-mm-dd").parse(date1);
                resp.setContentType("text/html");
                PrintWriter writer = resp.getWriter();
                writer.println("<h2><font color=green>The Data From Input Fields :</font></h2>");
                writer.println("<br><font color=blue> Name Is :</font>" + name);
                writer.println("<br><font color=blue>Description Is :<font> " + description);
                writer.println("<br><font color=blue>Date Is :<font> " + date);
                Random rn = new Random();
                int id = rn.nextInt(10000);
                taskManager.adding(id, name, description, date);
                Enumeration<String> enm = req.getParameterNames();

                resp.setStatus(201);
                writer.close();
            } catch (Exception e) {
                out.println("Enter Valid Date in format of yyyy-mm-dd");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
