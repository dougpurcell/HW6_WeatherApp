/*
 * Database.java
 *
 */
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;


//Database Class
public class Database {
    
//Declaration of variables    
String myConnectString = 
     "jdbc:ucanaccess:///Users/doug/Google Drive/College/ObjectIOrientedApplicationDevelopment/hw/hw6/HW6_WeatherApplication/WeatherDatabase.accdb";

//createTable() drops the current table and creates a new one
    public void createTable() {
        
       try
        {
             // load database driver class
         Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

           
         // connect to database
         Connection con = DriverManager.getConnection(myConnectString);
         Statement stmt = con.createStatement();


            //this code may need to be commented out because an exception will be thrown
         //if this table doesn't exist in the database
         stmt.execute("DROP TABLE Weather");
         
         stmt.execute("CREATE TABLE Weather" +
                         "(DayOfWeek varchar(255)," +
                         " Forecast varchar(255), " +
                         "HighTemp varchar(255), LowTemp varchar(255))");
        
         System.out.println("Created Weather table");
         
         stmt.close();
         con.close();
        }
       // detect problems interacting with the database
      catch ( SQLException sqlException ) {
         JOptionPane.showMessageDialog( null, 
            sqlException.getMessage(), "Database Error",
            JOptionPane.ERROR_MESSAGE );
         
         System.exit( 1 );
      }//end catch block
      
      // detect problems loading database driver
      catch ( ClassNotFoundException classNotFound ) {
         JOptionPane.showMessageDialog( null, 
            classNotFound.getMessage(), "Driver Not Found",
            JOptionPane.ERROR_MESSAGE );

         System.exit( 1 );
      }//end catch block
        
   }//end createTable()

    
//this method accepts the student data as input and stores it to the database 
    public void storeRecord(String day, String forecast, String hightemp, String lowtemp){
       
        try {
         
             // load database driver class
         Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

           
         // connect to database
         Connection con = DriverManager.getConnection(myConnectString);
         
         Statement stmt = con.createStatement();
         //this Insert statement puts student info in the database
         stmt.executeUpdate("INSERT INTO Weather VALUES ('"+day+"','"  + forecast +"','" + hightemp +"','"+lowtemp+"')");
         
         
         stmt.close();
         con.close();
        }//end try
        catch(Exception e) 
        {
                e.printStackTrace();
        }//end catch

    }//end storeRecord()
}// end Database class
    

