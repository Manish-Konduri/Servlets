package com.manish.servlets;

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
    public Servlets() {

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

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            com.manish.TaskManager taskManager = new com.manish.TaskManager();
//            String name = req.getParameter("Name");
//            String description = req.getParameter("Description");
//            String date1 = req.getParameter("Date");
////        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(sc.nextLine());
//            resp.setContentType("text/html");
//            PrintWriter writer =  resp.getWriter();
//            writer.println("<h2><font color=green>The Data From Input Fields :</font></h2>");
//            writer.println("<br><font color=blue> Name Is :</font>"+name);
//            writer.println("<br><font color=blue>Description Is :<font> "+description);
//            writer.println("<br><font color=blue>Date Is :<font> "+date1);
//            Random rn = new Random();
//            int id =rn.nextInt(10000);
//            Date date = new SimpleDateFormat("yyyy-mm-dd").parse(date1);
//            taskManager.adding(id,name,description,date);
//            Enumeration<String> enm = req.getParameterNames();
//            while(enm.hasMoreElements()) {
//            System.out.println(enm.nextElement());
//            }
//            resp.setStatus(201);
//            writer.close();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}
