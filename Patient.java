/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dent;

import java.sql.*;

/**********************************************************
 *The Patient class is used to store the patient information 
********************************************************** */
public class Patient {
    
    private String patientId, patientPwd, patientFn, patientLn, patientAdd, patientEm, patientIns;
    
    public Patient(){
        
        patientId = "";
        patientPwd = "";
        patientFn = "";
        patientLn = "";
        patientAdd = "";
        patientEm = "";
        patientIns = "";
        
    }
    
    public Patient(String pid, String pass, String pFn, String pLn, String pAdd, String pEm, String pIns){
        patientId = pid;
        patientPwd = pass;
        patientFn = pFn;
        patientLn = pLn;
        patientAdd = pAdd;
        patientEm = pEm;
        patientIns = pIns;
    }
    
    public void setpatientId(String pid){patientId = pid;}
    public String getpatientId(){return patientId;}
    
    public void setpatientPwd(String pass){patientPwd = pass;}
    public String getpatientPwd(){return patientPwd;}
    
    public void setpatientFn(String pFn){patientFn = pFn;}
    public String getpatientFn(){return patientFn;}
    
    public void setpatientLn(String pLn){patientLn = pLn;}
    public String getpatientLn(){return patientLn;}
    
    public void setpatientAdd(String pAdd){patientAdd = pAdd;}
    public String getpatientAdd(){return patientAdd;}
    
     public void setpatientEm(String pEm){patientEm = pEm;}
    public String getpatientEm(){return patientEm;}
    
    public void setpatientIns(String pIns){patientIns = pIns;}
    public String getpatientIns(){return patientIns;}
    
    
    public void display(){
        System.out.println("Patient ID: " + patientId);
        System.out.println("Patient Password: " + patientPwd);
        System.out.println("Patient First Name: " + patientFn);
        System.out.println("Patient Last Name: " + patientLn);
        System.out.println("Patient E-mail: " + patientEm);
        System.out.println("Patient Address: " + patientAdd);
        System.out.println("Patient Insurance: " + patientIns);
        
       
    }
    
    public void selectDB(String pid) {
    patientId = pid;

    try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/sault/OneDrive/Documents/Fall23/Java3/Final Project/DentistOfficeACCDB.accdb")) {
            System.out.println("Connected!");
            String sq1 = "SELECT * FROM Patients WHERE patId=?";
            System.out.println(sq1);

            try (PreparedStatement pstmt = con.prepareStatement(sq1)) {
                pstmt.setString(1, pid);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        setpatientPwd(rs.getString(2));
                        setpatientFn(rs.getString(3));
                        setpatientLn(rs.getString(4));
                        setpatientEm(rs.getString(5));
                        setpatientAdd(rs.getString(6));
                        setpatientIns(rs.getString(7));
                    } else {
                        System.out.println("No patient found: " + pid);
                    }
                }
            }

        }

    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("No connection!");
    }
}
/**********************************************************
 *The insertDB is used to insert the patient information 
********************************************************** */
    public void insertDB(String pid, String pass, String pFn, String pLn, String pAdd, String pEm, String pIns){
        
        
         try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        String dbAddress = "jdbc:ucanaccess://C:/Users/sault/OneDrive/Documents/Fall23/Java3/Final Project/DentistOfficeACCDB.accdb";
        
            try(Connection con = DriverManager.getConnection(dbAddress)){
            System.out.println("Connected!");
      
         
          String sq1;
            sq1 = "INSERT INTO Patients (patId, passwd, firstName, lastName, addr, email, insCo) VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            System.out.println(sq1);
            
             try (PreparedStatement pstmt = con.prepareStatement(sq1)) {
                    pstmt.setString(1, pid);
                    pstmt.setString(2, pass);
                    pstmt.setString(3, pFn);
                    pstmt.setString(4, pLn);
                    pstmt.setString(5, pAdd);
                    pstmt.setString(6, pEm);
                    pstmt.setString(7, pIns);

                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Patient inserted successfully!");
                    } else {
                        System.out.println("Error: Patient not inserted!");
                    }
                    
             }  
             
            }        
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("No connection!");
    } 

    
    }
    /**********************************************************
 *The updateDB is used to update the patient information 
********************************************************** */
    public void updateDB(String pid, String pass, String pFn, String pLn, String pAdd, String pEm, String pIns){
      
        try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        String dbAddress = "jdbc:ucanaccess://C:/Users/sault/OneDrive/Documents/Fall23/Java3/Final Project/DentistOfficeACCDB.accdb";
        
            try(Connection con = DriverManager.getConnection(dbAddress)){
            System.out.println("Connected!");
      
             String sq1;
            sq1 = "UPDATE Patients SET passwd=?, firstName=?, lastName=?, addr=?, email=?, insCo=? WHERE patId = ?";
            
            System.out.println(sq1);
            
             try (PreparedStatement pstmt = con.prepareStatement(sq1)) {
              pstmt.setString(1, pass);
              pstmt.setString(2, pFn);
                  pstmt.setString(3, pLn);
                 pstmt.setString(4, pAdd);
             pstmt.setString(5, pEm);
              pstmt.setString(6, pIns);
                 pstmt.setString(7, pid);
              
                  System.out.println("Values in PreparedStatement:");
    System.out.println("1: " + pass);
    System.out.println("2: " + pFn);
    System.out.println("3: " + pLn);
    System.out.println("4: " + pAdd);
    System.out.println("5: " + pEm);
    System.out.println("6: " + pIns);
    System.out.println("7: " + pid);
   
                  System.out.println("Values in PreparedStatement before update: " + pstmt);
                 
                 System.out.println("Values in PreparedStatment");
                 
                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Patient updated successfully!");
                    } else {
                        System.out.println("Error: Patient not updated!");
                    }
                }
                    
            }
         
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("No connection!");
    } 

    }
    /**********************************************************
 *The deletDB is used to insert the patient information 
********************************************************** */
    public void deleteDB(String pid){
        
         try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/sault/OneDrive/Documents/Fall23/Java3/Final Project/DentistOfficeACCDB.accdb");
            System.out.println("Connected!");
      
         
          String sq1;
            sq1 = "DELETE FROM Patients where patId=?";
            
            System.out.println(sq1);
            
              try (PreparedStatement pstmt = con.prepareStatement(sq1)) {
                    pstmt.setString(1, pid);

                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Patient deleted successfully!");
                    } else {
                        System.out.println("Error: Patient not deleted!");
                    }
              }
         
              
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("No connection!");
    } 

    }
            
            
     public static void main(String[] args){
        
       //Patient p1 = new Patient();
      // p1.selectDB("A900");
      // p1.display();
      
    //Patient p1 = new Patient();
    //p1.updateDB("ABQ", "1234", "James", "Bond", "yah@gmail", "whatever lane", "USA");
    //p1.display();
  //  Patient p1 = new Patient();
   //  p1.insertDB("ABQ", "123", "James", "Bond", "yah@gmail", "whatever lane", "USA");
    //  p1.display();
     // Patient p1 = new Patient();
     // p1.deleteDB("ABQ");
     // p1.display();
       

       
    }
}
