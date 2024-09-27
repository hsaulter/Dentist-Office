/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dent;

import java.sql.*;

/*********************************
 * The dentist class is used to access dentist information
 * 
 ********************************************************/
public class Dentist {
    
    private String dentistId, dentistPwd, dentistFn, dentistLn, dentistEm, dentistOff;
    
    public Dentist(){
        
        dentistId = "";
        dentistPwd = "";
        dentistFn = "";
        dentistLn = "";
        dentistEm = "";
        dentistOff = "";
        
    }
    
    public Dentist(String dId, String dPass, String dFn, String dLn, String dEm, String dOff){
        dentistId = dId;
        dentistPwd = dPass;
        dentistFn = dFn;
        dentistLn = dLn;
        dentistEm = dEm;
        dentistOff = dOff;
    }
    
    public void setdentistId(String dId){dentistId = dId;}
    public String getdentistId(){return dentistId;}
    
    public void setdentistPwd(String dPass){dentistPwd = dPass;}
    public String getdentistPwd(){return dentistPwd;}
    
    public void setdentistFn(String dFn){dentistFn = dFn;}
    public String getdentistFn(){return dentistFn;}
    
    public void setdentistLn(String dLn){dentistLn = dLn;}
    public String getdentistLn(){return dentistLn;}
    
    public void setdentistEm(String dEm){dentistEm = dEm;}
    public String getdentistEm(){return dentistEm;}
    
    public void setdentistOff(String dOff){dentistOff = dOff;}
    public String getdentistOff(){return dentistOff;}
    
    
    public void display(){
        System.out.println("Dentist ID: " + dentistId);
        System.out.println("Dentist Password: " + dentistPwd);
        System.out.println("Dentist First Name: " + dentistFn);
        System.out.println("Dentist Last Name: " + dentistLn);
        System.out.println("Dentist E-mail: " + dentistEm);
        System.out.println("Dentist Office: " + dentistOff);
        
       
    }
    
    /**********************************************************
 *The selectDB is used to get the dentist information 
********************************************************** */
     public void selectDB(String dId) {
    dentistId = dId;
    
    try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/sault/OneDrive/Documents/Fall23/Java3/Final Project/DentistOfficeACCDB.accdb");
            System.out.println("Connected!");
         Statement stmt = con.createStatement();
         
          String sq1;
            sq1 = "select * from Dentists where id='"+ getdentistId()+"'";
            
            System.out.println(sq1);
            
                ResultSet rs;
                rs = stmt.executeQuery(sq1);
                
                rs.next();
                    setdentistPwd(rs.getString(2));
                    setdentistFn(rs.getString(3));
                    setdentistLn(rs.getString(4));
                    setdentistEm(rs.getString(5));
                    setdentistOff(rs.getString(6));
                    
                    
                   
              con.close();
              
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("No connection!");
    } 
    
    }
 /**********************************************************
 *The insertDB is used to insert the dentist information 
********************************************************** */
     public void insertDB(String dId, String dpass, String dFn, String dLn, String dEm, String dOff){
        
        
         try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        String dbAddress = "jdbc:ucanaccess://C:/Users/sault/OneDrive/Documents/Fall23/Java3/Final Project/DentistOfficeACCDB.accdb";
        
            try(Connection con = DriverManager.getConnection(dbAddress)){
            System.out.println("Connected!");
      
         
          String sq1;
            sq1 = "INSERT INTO Dentists (id, passwd, firstName, lastName, email, office) VALUES(?, ?, ?, ?, ?, ?)";
            
            System.out.println(sq1);
            
             try (PreparedStatement pstmt = con.prepareStatement(sq1)) {
                    pstmt.setString(1, dId);
                    pstmt.setString(2, dpass);
                    pstmt.setString(3, dFn);
                    pstmt.setString(4, dLn);
                    pstmt.setString(5, dEm);
                    pstmt.setString(6, dOff);
                   

                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Dentist inserted successfully!");
                    } else {
                        System.out.println("Error: Dentist not inserted!");
                    }
                    
             }  
             
            }        
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("No connection!");
    } 

    
    }
     /**********************************************************
 *The updateDB is used to update the dentist information 
********************************************************** */
    public void updateDB(String dId, String dpass, String dFn, String dLn, String dEm, String dOff){
      
        try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        String dbAddress = "jdbc:ucanaccess://C:/Users/sault/OneDrive/Documents/Fall23/Java3/Final Project/DentistOfficeACCDB.accdb";
        
            try(Connection con = DriverManager.getConnection(dbAddress)){
            System.out.println("Connected!");
      
             String sq1;
            sq1 = "UPDATE Dentists SET passwd=?, firstName=?, lastName=?, email=?, office=? WHERE id = ?";
            
            System.out.println(sq1);
            
             try (PreparedStatement pstmt = con.prepareStatement(sq1)) {
              pstmt.setString(1, dpass);
              pstmt.setString(2, dFn);
                  pstmt.setString(3, dLn);
                 pstmt.setString(4, dEm);
            pstmt.setString(5, dOff);
              pstmt.setString(6, dId);
                 
              
                  System.out.println("Values in PreparedStatement:");
    System.out.println("1: " + dpass);
    System.out.println("2: " + dFn);
    System.out.println("3: " + dLn);
    System.out.println("4: " + dEm);
  
    System.out.println("6: " + dId);
 
   
                  System.out.println("Values in PreparedStatement before update: " + pstmt);
                 
                 System.out.println("Values in PreparedStatment");
                 
                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Dentist updated successfully!");
                    } else {
                        System.out.println("Error: Dentist not updated!");
                    }
                }
                    
            }
         
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("No connection!");
    } 

    }
    /**********************************************************
 *The deleteDB is used to delete the dentist information 
********************************************************** */
    public void deleteDB(String dId){
        
         try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/sault/OneDrive/Documents/Fall23/Java3/Final Project/DentistOfficeACCDB.accdb");
            System.out.println("Connected!");
      
         
          String sq1;
            sq1 = "DELETE FROM Dentists where id=?";
            
            System.out.println(sq1);
            
              try (PreparedStatement pstmt = con.prepareStatement(sq1)) {
                    pstmt.setString(1, dId);

                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Dentist deleted successfully!");
                    } else {
                        System.out.println("Error: Dentist not deleted!");
                    }
              }
         
              
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("No connection!");
    } 

    }
     public static void main(String[] args){
        
       //Dentist d1 = new Dentist();
      // d1.selectDB("D201");
      // d1.display();
      
      //  Account a1 = new Account();
       // a1.insertDB("19000", "3022", "SAV", 52);
        // a1.display();
    
      // Account a1 = new Account();
      // a1.deleteDB("19000");
      // a1.display();
       

       
    }
}
