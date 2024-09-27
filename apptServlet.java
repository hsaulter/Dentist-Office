package dent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dent.Patient;
import dent.Appointments;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/******************************************************************************************************
 *
 * The appointment Servlet is used to access both the patient and appointment information and handle requests
 ********************************************************************************************************/
@WebServlet(urlPatterns = {"/apptServlet"})
public class apptServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String patientId)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       try (PrintWriter out = response.getWriter()) {
        HttpSession ses1 = request.getSession(true);
        HttpSession ses2 = request.getSession(true);

        Patient p1 = getPatientFromSession(ses1);
        Appointments a1 = getApptFromSession(ses2);

        String updateApptTime = request.getParameter("updateApptTime");
        String updateProCode = request.getParameter("updateProCode");

        if (updateApptTime != null && updateProCode != null) {
            a1.updateAppt(updateApptTime, updateProCode);
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Appointment Portal</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Appointments Portal</h1>");

        if (p1 != null && a1 != null) {
            out.println("<h2>Welcome " + p1.getpatientFn() + "</h2>");
            out.println("<h3>Upcoming Appointments: </h3>");
            //displayAppts(p1, a1, out);
            
            out.println("<br>");
            out.println("<ul>");
            for (Appointments appt : a1.getApptForPatient(p1.getpatientId())) {
                out.println("<li>Appointment Time: " + appt.getapptTime() + "</li>");
                out.println("<li>Procedure: " + appt.getproName() + "</li>");
                out.println("<li>Description: " + appt.getproScript() + "</li>");
                out.println("<li>Procedure Code: " + appt.getproCode()+ "</li>");
                out.println("<li>Cost: " + appt.getproCost() + "</li>");
                out.println("</ul>");
            }

            // Update Form
            out.println("<form action=\"apptServlet\" method=\"post\">");
            out.println("Update Appointment Time: <input type=\"text\" name=\"updateApptTime\">");
            out.println("<br>");
            out.println("Update Procedure Code: <input type=\"text\" name=\"updateProCode\">");
            out.println("<input type=\"submit\" value=\"Update\">");
            out.println("</form>");

            // Delete Form
            out.println("<form action=\"apptServlet\" method=\"post\">");
            out.println("Delete Appointment Time: <input type=\"text\" name=\"deleteApptTime\">");
            out.println("<input type=\"submit\" value=\"Delete\">");
            out.println("</form>");

            // Insert Form
            out.println("<form action=\"apptServlet\" method=\"post\">");
            out.println("New Appointment Time: <input type=\"text\" name=\"newApptTime\">");
            out.println("<br>");
            out.println("New Patient ID: <input type=\"text\" name=\"newPatientID\">");
            out.println("<br>");
            out.println("Procedure Code: <input type=\"text\" name=\"proCode\">");
            out.println("<input type=\"submit\" value=\"Insert\">");
            out.println("</form>");
            


        } else {
            out.println("<p>Error: Patient not found</p>");
        }

        String deleteApptTime = request.getParameter("deleteApptTime");
        if (deleteApptTime != null) {
            a1.deleteDB(deleteApptTime);
        }

        String newApptTime = request.getParameter("newApptTime");
        String newPatientID = request.getParameter("newPatientID");
        String proCode = request.getParameter("proCode");

        if (newApptTime != null && newPatientID != null) {
            a1.insertAppt(newApptTime, newPatientID, proCode);
        }
        

        out.println("</body>");
        out.println("</html>");
    }
} 
    /**********************************************************
 *The displayPatientInfo is used to display the patient information 
********************************************************** */
    private void displayPatientInfo(Patient p1, PrintWriter out) {
        out.println("<h3>Patient Account Information</h3>");
        out.println("<br>");
        out.println("<p>Name: " + p1.getpatientFn() + " " + p1.getpatientLn() + "</p>");
        out.println("<p>Address: " + p1.getpatientAdd() + "</p>");
        out.println("<p>Email: " + p1.getpatientEm() + "</p>");
        out.println("<p>Insurance: " + p1.getpatientIns() + "</p>");
        out.println("<p>Patient ID: " + p1.getpatientId() + "</p>");
        out.println("<a href=\"updatePatient.jsp?patientId=" + p1.getpatientId() + "\">Update Patient Information</a>");
    }
     
    

/**********************************************************
 *The displayAppts is used to display the patient and appt information 
********************************************************** */
 private void displayAppts(Patient p1, Appointments a1, PrintWriter out) {
        
      List<Appointments> appointments = a1.getApptForPatient(p1.getpatientId());
     if (!appointments.isEmpty()) {
            out.println("<ul>");
            for (Appointments appt : appointments) {
                out.println("<li>Appointment Time: " + appt.getapptTime() + "</li>");
                out.println("<li>Procedure: " + appt.getproName() + "</li>");
                out.println("<li>Description: " + appt.getproScript() + "</li>");
                out.println("<li>Cost: " + appt.getproCost() + "</li>");
                out.println("<br>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>No upcoming appointments.</p>");
        }
        
 }

        
     
   

         private Patient getPatientFromSession(HttpSession session) {
        if (session != null) {
            return (Patient) session.getAttribute("p1");
        }
        return null;
    }

    private Appointments getApptFromSession(HttpSession session) {
        if (session != null) {
            return (Appointments) session.getAttribute("a1");
        }
        return null;
    }


    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
  /**********************************************************
 *The doGet is used to get the patient and appointment information 
********************************************************** */
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String patientId = request.getParameter("patientId");
    Patient p1 = new Patient();
    p1.selectDB(patientId);

    // Set Appointments object (a1) using patientId
    Appointments a1 = new Appointments();
    a1.selectDB(patientId);

    // Set the objects in the session
    HttpSession session = request.getSession();
    session.setAttribute("p1", p1);
    session.setAttribute("a1", a1);
    
        processRequest(request, response, patientId);
}


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses3 = request.getSession(false);
        
        if(ses3 != null){
            
         String patientId = request.getParameter("patientId");
         String updatedPwd = request.getParameter("updatedPwd");
         String updatedFn = request.getParameter("updatedFn");
         String updatedLn = request.getParameter("updatedLn");
         String updatedAdd = request.getParameter("updatedAdd");
         String updatedEm = request.getParameter("updatedEm");
         String updatedIns = request.getParameter("updatedIns");
         
          System.out.println("Received parameters:");
        System.out.println("patientId: " + patientId);
        System.out.println("updatedPwd: " + updatedPwd);
        System.out.println("updatedFn: " + updatedFn);
        System.out.println("updatedLn: " + updatedLn);
        System.out.println("updatedAdd: " + updatedAdd);
        System.out.println("updatedEm: " + updatedEm);
        System.out.println("updatedIns: " + updatedIns);
         
         
         Patient p1 = new Patient();
          p1.updateDB(patientId, updatedPwd, updatedFn, updatedLn, updatedAdd, updatedEm, updatedIns);


        }
     
       
       

        response.sendRedirect("patient.jsp");
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    }
