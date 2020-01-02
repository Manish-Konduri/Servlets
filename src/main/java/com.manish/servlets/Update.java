package com.manish.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Update extends HttpServlet {
    public Update()
    {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.manish.TaskManager taskManager = new com.manish.TaskManager();
        PrintWriter out = resp.getWriter();

        String id1 = req.getParameter("id");
        if(id1==null)
        {
            out.println("Enter Valid id");
            return;
        }
        int id=Integer.parseInt(id1);
        String status1 = req.getParameter("status");
        if(status1==null)
        {
            out.println("Enter Valid status Number");
            return;
        }
        int status=Integer.parseInt(status1);
        Boolean b = taskManager.changeStatus(status,id);

    }
}
