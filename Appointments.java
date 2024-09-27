/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**********************************************************
 *The Appts Class is used to store appointment information
********************************************************** */

public class Appointments {
    
    private String apptTime, patientID, dentID, proCode, proName, proScript, proCost;
    private static final String dbAddress = "jdbc:ucanaccess://C:/Users/sault/OneDrive/Documents/Fall23/Java3/Final Project/DentistOfficeACCDB.accdb";
   private String newApptTime; 
    
    public Appointments(){
        
    apptTime = "";
    patientID = "";
    dentID = "";
    proCode = "";
    proName = "";
    proScript = "";
    proCost = "";
}
    public Appointments(String apptTime, String patientID, String dentID, String proCode, String proName, String proScript, String proCost){
        
    this.apptTime = apptTime;
    this.patientID = patientID;
    this.dentID = dentID;
    this.proCode = proCode;
    this.proName = proName;
    this.proScript = proScript;
    this.proCost = proCost; 
    
    }
    
    public void setapptTime(String apptTime){this.apptTime = apptTime;} 
    public String getapptTime(){return apptTime;}
    
    public void setpatientID(String patientID){this.patientID = patientID;}
    public String getpatientID() {return patientID;}
    
    public void setdentID(String dentID){this.dentID = dentID;}
    public String getdentID() {return dentID;}
    
    public void setproCode(String proCode){this.proCode = proCode;}
    public String getproCode() {return proCode;}
    
    public void setproName(String proName){this.proName = proName;}
    public String getproName(){return proName;}
    
    public void setproScript(String proScript){this.proScript = proScript;}
    public String getproScript(){return proScript;}
    
    public void setproCost(String proCost){this.proCost = proCost;}
    public String getproCost(){return proCost;}
    
     public void display(){
        System.out.println("Appointment Time: " + apptTime);
        System.out.println("Patient ID: " + patientID);
        System.out.println("Dentist ID: " + dentID);
        System.out.println("Procedure Code: " + proCode);
        System.out.println("Procedure: " + proName);
        System.out.println("Description: " + proScript);
        System.out.println("Cost: " + proCost);
        
       
    }
    
/**********************************************************
 *The select method is used to select the appointment information
********************************************************** */
    public void selectDB(String patientID) {
    this.patientID = patientID;
    

    try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        try (Connection con = DriverManager.getConnection(dbAddress);
             PreparedStatement stmt1 = con.prepareStatement("SELECT * FROM Appointments WHERE patId = ?");
             PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM Procedures WHERE procCode = ?")) {

            stmt1.setString(1, getpatientID());

            try (ResultSet rs1 = stmt1.executeQuery()) {
                if (rs1.next()) {
                    setapptTime(rs1.getString(1));
                    setdentID(rs1.getString(3));
                    setproCode(rs1.getString(4));

                    stmt2.setString(1, getproCode());
                    try (ResultSet rs2 = stmt2.executeQuery()) {
                        if (rs2.next()) {
                            setproName(rs2.getString(2));
                            setproScript(rs2.getString(3));
                            setproCost(rs2.getString(4));
                        }
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error in database operations!");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("No connection!");
    }
}
    
/**********************************************************
 *The insert method is used to insert appointment information
********************************************************** */
    public void insertDB(String apptTime, String patientID, String proCode){
        
        
         try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        String dbAddress = "jdbc:ucanaccess://C:/Users/sault/OneDrive/Documents/Fall23/Java3/Final Project/DentistOfficeACCDB.accdb";
        
            try(Connection con = DriverManager.getConnection(dbAddress)){
            System.out.println("Connected!");
      
         
          String sq1 = "INSERT INTO Appointments (apptDateTime, patId, procCode) VALUES(?, ?, ?) && select * from Appointments where apptDateTime = ?";
            
            String sq2 = "Insert into Procedures (procCode) Values (?) && select * from Procedures where procCode = ?";
            System.out.println(sq1);
            
             try (PreparedStatement pstmt = con.prepareStatement(sq1);
                     PreparedStatement pstmt2 = con.prepareStatement(sq2)) {
                    pstmt.setString(1, apptTime);
                    pstmt.setString(2, patientID);
                    pstmt.setString(3, dentID);
                    pstmt.setString(4, proCode);
                    

                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Appointment inserted successfully!");
                    } else {
                        System.out.println("Error: Patient not inserted!");
                    }
             
                   
                    pstmt2.setString(1, proCode);
                    pstmt2.setString(2, proName);
                    pstmt2.setString(3, proScript);
                    pstmt2.setString(4, proCost);
                    

                    affectedRows = pstmt2.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Appointment inserted successfully!");
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
 *The update method used to update appointment information
********************************************************** */
public void updateDB(String apptTime, String patientID, String proCode, String newApptTime, String newProCode) {
    this.apptTime = apptTime;
    this.patientID = patientID;
    this.proCode = proCode;
    this.newApptTime = apptTime;

    try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        try (Connection con = DriverManager.getConnection(dbAddress)) {
            System.out.println("Connected!");

            String updateApptSql = "UPDATE Appointments SET apptDateTime = ?, procCode = ? WHERE patId = ? AND procCode = ?";
            String selectProSql = "SELECT * FROM Procedures WHERE procCode = ?";

            try (PreparedStatement updateApptStmt = con.prepareStatement(updateApptSql);
                 PreparedStatement selectProStmt = con.prepareStatement(selectProSql)) {

                // Check if the procedure with the given proCode exists
                selectProStmt.setString(1, newProCode);
                try (ResultSet proResultSet = selectProStmt.executeQuery()) {
                    if (proResultSet.next()) {
                        // Procedure with proCode exists, update appointment time and procedure code
                        updateApptStmt.setString(1, newApptTime);
                        updateApptStmt.setString(2, newProCode);
                        updateApptStmt.setString(3, patientID);
                        updateApptStmt.setString(4, proCode);
                        int apptRowsUpdated = updateApptStmt.executeUpdate();

                        if (apptRowsUpdated > 0) {
                            System.out.println("Appointment updated!");
                        } else {
                            System.out.println("Error: Appointment not updated!");
                        }
                    } else {
                        System.out.println("Error: Procedure with the provided code does not exist!");
                    }
                }
            }
        }
    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        System.out.println("Error!");
    }
}

/**********************************************************
 *The delete method is used to delete appointment information
********************************************************** */
public void deleteDB(String apptTime){
        
         try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/sault/OneDrive/Documents/Fall23/Java3/Final Project/DentistOfficeACCDB.accdb");
            System.out.println("Connected!");
      
         
          String sq1;
            sq1 = "DELETE FROM Appointments where apptDateTime=?";
            
            System.out.println(sq1);
            
              try (PreparedStatement pstmt = con.prepareStatement(sq1)) {
                    pstmt.setString(1, apptTime);

                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Appointment deleted successfully!");
                    } else {
                        System.out.println("Error Appointment not deleted!");
                    }
              }
         
              
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("No connection!");
    } 

    }
    public void updateAppt(String newApptTIme, String newProCode){
        updateDB(apptTime, patientID, proCode,newApptTime, newProCode);
    }
    public void deleteAppt(){
        deleteDB(apptTime);
    }
    
    public void insertAppt(String apptTime, String patientID, String proCode){
        insertDB(apptTime, patientID, proCode);
    }
    
/**********************************************************
 *The getApptForPatient used to get the appointment information for the Patient
********************************************************** */
    public List<Appointments> getApptForPatient(String patientId) {
    List<Appointments> apptList = new ArrayList<>();

    try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        try (Connection con = DriverManager.getConnection(dbAddress)) {
            String sql = "SELECT A.*, P.* " +
                         "FROM Appointments A " +
                         "INNER JOIN Procedures P ON A.procCode = P.procCode " +
                         "WHERE A.patId = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, patientId);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String apptTime = rs.getString("apptDateTime");
                        String dentID = rs.getString("dentId");
                        String proCode = rs.getString("procCode");
                        String proName = rs.getString("procName");
                        String proScript = rs.getString("procDesc");
                        String proCost = rs.getString("cost");

                        Appointments appointment = new Appointments(apptTime, patientId, dentID, proCode, proName, proScript, proCost);
                        apptList.add(appointment);
                    }
                }
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Error in getApptForPatient!");
    }

    return apptList;
}
    /**********************************************************
 *The getApptForPatient used to get the appointment information for the Dentist
********************************************************** */
    
    public List<Appointments> getApptForDentist(String dentistId) {
    List<Appointments> apptList = new ArrayList<>();

    try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        try (Connection con = DriverManager.getConnection(dbAddress)) {
            String sql = "SELECT A.*, P.* " +
                         "FROM Appointments A " +
                         "INNER JOIN Procedures P ON A.procCode = P.procCode " +
                         "WHERE A.dentId = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, dentistId);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String apptTime = rs.getString("apptDateTime");
                        String patientId = rs.getString("patId");
                      
                        String proCode = rs.getString("procCode");
                        String proName = rs.getString("procName");
                        String proScript = rs.getString("procDesc");
                        String proCost = rs.getString("cost");

                        Appointments appointment = new Appointments(apptTime, patientId, dentID, proCode, proName, proScript, proCost);
                        apptList.add(appointment);
                    }
                }
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Error in getApptForPatient!");
    }

    return apptList;
}
    /**********************************************************
 *Main method
********************************************************** */
    public static void main(String[] args){
        
       // Account a1 = new Account();
       // a1.selectDB("90000");
       // a1.display();
      
      //  Account a1 = new Account();
       // a1.insertDB("19000", "3022", "SAV", 52);
        // a1.display();
    
      // Account a1 = new Account();
      // a1.deleteDB("19000");
      // a1.display();
       

       
    }
}
