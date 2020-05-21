/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclassapp2;

import java.util.*;
import java.sql.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;


/**
 *
 * @author carlt
 */
public class Javaclassapp2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
       
      //user input to select room
      Scanner input = new Scanner(System.in);
      System.out.println("Enter the room: ");
      String roomInput = input.next();
      System.out.println("You are searching for room " + roomInput);
      
        //  logic
      { final String DATABASE_URL = "jdbc:mysql://localhost:3306/classcall"; 
      final String SELECT_QUERY =   "SELECT * FROM classinfo"; 
      
     try 
      (
       //connecting to database
      Connection connection = DriverManager.getConnection(DATABASE_URL, "root", "admin"); 
      Statement statement = connection.createStatement();    
      ResultSet resultSet= statement.executeQuery(SELECT_QUERY))
         
     { ResultSetMetaData metaData= resultSet.getMetaData(); 
     //this enables the system to output data in columns
     int numberOfColumns= metaData.getColumnCount();     
     System.out.printf("Current details in the Database:%n%n");
     for (int i= 1; i<= numberOfColumns; i++) System.out.printf("%-8s\t", metaData.getColumnName(i)); 
     System.out.println();
     while (resultSet.next()) 
     {for (int i= 1; i<= numberOfColumns; i++) System.out.printf("%-8s\t", resultSet.getObject(i)); 
     
     System.out.println(); 
     
     
     //collecting local date time and current day
        Date date=new Date();
        Date now1 = new Date();
        LocalTime now = LocalTime.now();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        System.out.println("The current time is: " + formattedDate);
        
        //CAPTURING CURRENT DAY OF THE WEEK SEPARATELY
        SimpleDateFormat dayformat= new SimpleDateFormat("EEEE"); 
        System.out.println(dayformat.format(now1));
       
        
    //remember local time is referred to as now
              
    //Initializing string "period" to be used for comparison with the period tab in the datatabse
       String period = "";
     
     //creating preassigned times to use for if-statement constraints
    
    LocalTime datemorning1 = LocalTime.parse("10:00");
    LocalTime datemorning2 = LocalTime.parse("12:30");
    LocalTime dateafternoon1 = LocalTime.parse("02:00");
    LocalTime dateafternoon2 = LocalTime.parse("04:30");
    
    
    if(now.isAfter(datemorning1) && now.isBefore(datemorning2)){
        period = "M";
    System.out.println("Below is the information of the current class in that room");
       
    }
    if(now.isAfter(dateafternoon1) && now.isBefore(dateafternoon2)){
        period = "A";
    System.out.println("Below is the information of the current class in that room");    
    }

    System.out.println();

        
   //SELECT VIA USER INPUT
        String mystmt = "select * from classinfo WHERE room = '"+ roomInput+"' AND period = '"+period+"' AND day = '"+dayformat.format(now1)+"' " ; 
            ResultSet rest = statement.executeQuery(mystmt);
        
        if (rest.next()) 
            { 
       //output objects         
                System.out.println("");
                System.out.println("room : " + rest.getString(1)); 
                System.out.println("course_code :" + rest.getString(2));
                System.out.println("course_name :" + rest.getString(3)); 
                System.out.println("lecturer :" + rest.getString(4));
                System.out.println("period :" + rest.getString(5));
                System.out.println("semester :" + rest.getString(6));
                System.out.println("year :" + rest.getString(7));
                System.out.println("day :" + rest.getString(8));
                
            System.out.println("Dear Students, the " + rest.getString(3) + " class, at room " + rest.getString(1)+ " by " 
                    + rest.getString(4)+ " is back in session " );
            }
      
     
     }
     }    
    catch (SQLException sqlException) 
      {sqlException.printStackTrace();  
    }  
        
    }
    
}
}
