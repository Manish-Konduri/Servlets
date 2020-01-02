package com.manish.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Delete extends HttpServlet {
    private com.manish.TaskManager taskManager = new com.manish.TaskManager();
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id1 = req.getParameter("id");
        PrintWriter writer =  resp.getWriter();
        if(id1==null)
        {
            writer.println("Enter Valid id");
            return;
        }
        int id=Integer.parseInt(id1);
        resp.setContentType("text/html");
        writer.println("<h2><font color=green>The Data From Input Fields :</font></h2>");
        int a = taskManager.delete(id);
        writer.close();
    }
}
