package org.example;

import com.github.javafaker.Faker;

import java.sql.*;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

       try{

           Class.forName("org.sqlite.JDBC");
           Connection connection = DriverManager.getConnection("jdbc:sqlite:lab4.db");

           Statement statement = connection.createStatement();

           String createTableQuery = "CREATE TABLE STUDENT (ID VARCHAR(100), NAME VARCHAR(50), MAJOR VARCHAR(50));";
           UUID uuid = UUID.randomUUID();
            Faker faker = new Faker();

           String selectQuery = "SELECT * FROM STUDENT ORDER BY MAJOR";

            ResultSet rs = statement.executeQuery(selectQuery);

            ResultSetMetaData md = rs.getMetaData();

           for (int i = 3; i <= md.getColumnCount(); i++) {
               System.out.print(md.getColumnName(i) + "\t");
           }
           System.out.println();

           while (rs.next()) {
               for (int i = 3; i <= md.getColumnCount(); i++) {
                   System.out.print(rs.getString(i) + "\t");
               }
               System.out.println();
           }

           statement.close();
           connection.close();

       }catch (SQLException ex) {
           System.out.println("Exception " + ex.getMessage());
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }

    }
}