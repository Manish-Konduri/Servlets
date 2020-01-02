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

public class Search extends HttpServlet {
    public Search()
    {

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        com.manish.TaskManager taskManager = new com.manish.TaskManager();
        PrintWriter out = resp.getWriter();
        String id1 = req.getParameter("id");
        if(id1==null)
        {
            out.println("Enter Valid id");
            return;
        }
        int id=Integer.parseInt(id1);
        ArrayList<Task> searchList= new ArrayList<>();
        searchList=taskManager.search(id);
        JSONArray jsArray = new JSONArray(searchList);
        //System.out.println();
        out.println(jsArray);
    }
}
