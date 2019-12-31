package com.manish;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;

class dataBase implements TaskRepository{
    Statement statement;
    Connection connection;
    dataBase(){
        dbConnection();
    }
    public void dbConnection() {
       try{
           Class.forName("com.mysql.jdbc.Driver");

           // Connects to mysql service through a connection url and credentials
           connection=DriverManager.getConnection( "jdbc:mysql://localhost:3306/testdb","manishk","manish@145#");
           statement = connection.createStatement();
       }
       catch (Exception e){
           System.out.println("No Table");
       }
    }

    @Override
    public void adding(int id, String name, String description, Date date) throws SQLException {
        try{
        ResultSet rs = statement.executeQuery("select * from tesdb ORDER BY id DESC LIMIT 1");
        rs.next();
        id=rs.getInt("id") +1;
        }
        catch (Exception e){
            System.out.println("No data");
        }
        String status="Initial";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd");
        String dateString = simpleDateFormat.format(date);
//        System.out.println("insert into tesdb values ("+id+",'"+name+"','"+description+"','"+dateString+"','"+Initial+"')");
        String q= "insert into tesdb values ('"+id+"','"+name+"','"+description+"','"+dateString+"','"+status+"')";
        statement.executeUpdate(q);

    }

    @Override
    public int delete(int query) {
        try {

            int size = 0;
            ResultSet rs = statement.executeQuery("select * from tesdb");
            while (rs.next()) {
                size = size + 1;
            }
            statement.executeUpdate("Delete from tesdb where id = " + query);
            int size1 = 0;
            ResultSet rs1 = statement.executeQuery("select * from tesdb");
            while (rs1.next()) {
                size1 = size1 + 1;
            }
                if (size == size1) {
                    return 0;
                } else {
                    return 1;
                }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public ArrayList<Task> display() {
        ArrayList<Task> listDB = new ArrayList<>();
            try {
                ResultSet rs = statement.executeQuery("select * from tesdb");
                while (rs.next()) {
                    Task dbTask = new Task();
                    int idReturn= rs.getInt("id");
                    String nameReturn= rs.getString("name"); //gets the first column's rows.
                    String descriptionReturn= rs.getString("description");
                    Date dateReturn= rs.getDate("date");
                    Status statusReturn= Status.valueOf(rs.getString("status"));
                    dbTask.setId(idReturn);
                    dbTask.setName(nameReturn);
                    dbTask.setDescription(descriptionReturn);
                    dbTask.setDate(dateReturn);
                    dbTask.setStatus(statusReturn);
                    listDB.add(dbTask);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return listDB;
        }

    @Override
    public ArrayList<Task> search(int q) {
        ArrayList<Task> listDB = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("select * from tesdb where id = "+q);
            while (rs.next()) {
                Task dbTask = new Task();
                int idReturn= rs.getInt("id");
                String nameReturn= rs.getString("name"); //gets the first column's rows.
                String descriptionReturn= rs.getString("description");
                Date dateReturn= rs.getDate("date");
                Status statusReturn= Status.valueOf(rs.getString("status"));
                dbTask.setId(idReturn);
                dbTask.setName(nameReturn);
                dbTask.setDescription(descriptionReturn);
                dbTask.setDate(dateReturn);
                dbTask.setStatus(statusReturn);
                listDB.add(dbTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDB;
    }

    @Override
    public boolean changeStatusCheck() {
        return false;
    }

    @Override
    public boolean changeStatus(int s, int i) {
//        update users set num_points = 6000 where id = 2;

        try{
            if(s==1) {
                Status status = Status.Initial;
                if(statement.executeUpdate("Update tesdb set status ='" + status + "' where id = " + i)==1)
                    return true;
                else
                    return false;
            }
            else if(s==2) {
                Status status = Status.IN_PROGRESS;
                if(statement.executeUpdate("Update tesdb set status ='" + status+"' where id = "+i)==1)
                    return true;
                else
                    return false;
            }

            else if(s==3) {
                Status status = Status.Done;
                if(statement.executeUpdate("Update tesdb set status ='" + status+"' where id = "+i)==1)
                    return true;
                else
                    return false;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ArrayList<Task> listByStatus(int lbs) {
        ArrayList<Task> listDB = new ArrayList<>();
        try {
            Status status = Status.Initial;
            Status status1 = Status.IN_PROGRESS;
            Status status2 = Status.Done;
            ResultSet rs;
            if(lbs==1) {
                rs = statement.executeQuery("select * from tesdb where status='" + status + "' or status='" + status1 + "' order by date");

                while (rs.next()) {
                    Task dbTask = new Task();
                    int idReturn = rs.getInt("id");
                    String nameReturn = rs.getString("name"); //gets the first column's rows.
                    String descriptionReturn = rs.getString("description");
                    Date dateReturn = rs.getDate("date");
                    Status statusReturn = Status.valueOf(rs.getString("status"));
                    dbTask.setId(idReturn);
                    dbTask.setName(nameReturn);
                    dbTask.setDescription(descriptionReturn);
                    dbTask.setDate(dateReturn);
                    dbTask.setStatus(statusReturn);
                    listDB.add(dbTask);
                }
            }
            else if(lbs==2)
            {
                rs = statement.executeQuery("select * from tesdb where status='" + status2 +"' order by date");

                while (rs.next()) {
                    Task dbTask = new Task();
                    int idReturn = rs.getInt("id");
                    String nameReturn = rs.getString("name"); //gets the first column's rows.
                    String descriptionReturn = rs.getString("description");
                    Date dateReturn = rs.getDate("date");
                    Status statusReturn = Status.valueOf(rs.getString("status"));
                    dbTask.setId(idReturn);
                    dbTask.setName(nameReturn);
                    dbTask.setDescription(descriptionReturn);
                    dbTask.setDate(dateReturn);
                    dbTask.setStatus(statusReturn);
                    listDB.add(dbTask);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDB;
    }

    @Override
    public ArrayList<Task> dueToday() {
        ArrayList<Task> arl = new ArrayList<>();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        try{
            ResultSet rs = statement.executeQuery("select * from tesdb where date ='"+date+"'");
            while (rs.next()) {
                Task dbTask = new Task();
                int idReturn = rs.getInt("id");
                String nameReturn = rs.getString("name"); //gets the first column's rows.
                String descriptionReturn = rs.getString("description");
                Date dateReturn = rs.getDate("date");
                Status statusReturn = Status.valueOf(rs.getString("status"));
                dbTask.setId(idReturn);
                dbTask.setName(nameReturn);
                dbTask.setDescription(descriptionReturn);
                dbTask.setDate(dateReturn);
                dbTask.setStatus(statusReturn);
                arl.add(dbTask);
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return arl;
    }
}
