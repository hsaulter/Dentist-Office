package dent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dent.Dentist;
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
 * The dentist appointment Servlet is used to access both the dentist and appointment information and handle requests
 ********************************************************************************************************/
@WebServlet(urlPatterns = {"/dentapptServlet"})
public class dentapptServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String dentistId)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       try (PrintWriter out = response.getWriter()) {
        HttpSession ses1 = request.getSession(true);
        HttpSession ses2 = request.getSession(true);

        Dentist d1 = getDentistFromSession(ses1);
        Appointments a1 = getApptFromSession(ses2);

        

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Appointment Portal</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Appointments Portal</h1>");

        if (d1 != null && a1 != null) {
            out.println("<h2>Welcome " + d1.getdentistFn() + "</h2>");
            out.println("<h3>Upcoming Appointments: </h3>");
            displayAppts(d1, a1, out);
            
            out.println("<br>");
           // out.println("<ul>");
           // for (Appointments appt : a1.getApptForPatient(p1.getpatientId())) {
           //     out.println("<li>Appointment Time: " + appt.getapptTime() + "</li>");
           //     out.println("<li>Procedure: " + appt.getproName() + "</li>");
           //     out.println("<li>Description: " + appt.getproScript() + "</li>");
           //     out.println("<li>Procedure Code: " + appt.getproCode()+ "</li>");
            //    out.println("<li>Cost: " + appt.getproCost() + "</li>");
            //    out.println("</ul>");
            }

            


            


        else {
            out.println("<p>Error: Dentist not found</p>");
        }


       
        

        out.println("</body>");
        out.println("</html>");
    }
} 
    
   // private void displayDentistInfo(Dentist d1, PrintWriter out) {
      //  out.println("<h3>Patient Account Information</h3>");
      //  out.println("<br>");
      //  out.println("<p>Name: " + p1.getpatientFn() + " " + p1.getpatientLn() + "</p>");
     //   out.println("<p>Address: " + p1.getpatientAdd() + "</p>");
     //   out.println("<p>Email: " + p1.getpatientEm() + "</p>");
     //   out.println("<p>Insurance: " + p1.getpatientIns() + "</p>");
     //   out.println("<p>Patient ID: " + p1.getpatientId() + "</p>");
     //   out.println("<a href=\"updatePatient.jsp?patientId=" + p1.getpatientId() + "\">Update Patient Information</a>");
   // }
     
    


 private void displayAppts(Dentist d1, Appointments a1, PrintWriter out) {
        
      List<Appointments> appointments = a1.getApptForDentist(d1.getdentistId());
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

        
     
   

         private Dentist getDentistFromSession(HttpSession session) {
        if (session != null) {
            return (Dentist) session.getAttribute("d1");
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
  
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String dentistId = request.getParameter("dentistId");
    System.out.println("dentistId: " + dentistId);
        if(dentistId != null && !dentistId.isEmpty()){
            Dentist d1 = new Dentist();
               d1.selectDB(dentistId);
        
      
    Appointments a1 = new Appointments();
    a1.selectDB(dentistId);

    // Set the objects in the session
    HttpSession session = request.getSession();
    session.setAttribute("d1", d1);
    session.setAttribute("a1", a1);
    
        processRequest(request, response, dentistId);
} else{
            response.getWriter().println("Error: Invalid Id");
        }
            
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
            
         String dentistId = request.getParameter("dentistId");
         String updatedPwd = request.getParameter("updatedPwd");
         String updatedFn = request.getParameter("updatedFn");
         String updatedLn = request.getParameter("updatedLn");
         String updatedEm = request.getParameter("updatedEm");
         String updatedOff = request.getParameter("updatedOff");
          System.out.println("Received parameters:");
        System.out.println("dentistId: " + dentistId);
        System.out.println("updatedPwd: " + updatedPwd);
        System.out.println("updatedFn: " + updatedFn);
        System.out.println("updatedLn: " + updatedLn);
     
         
         
         Dentist d1 = new Dentist();
          d1.updateDB(dentistId, updatedPwd, updatedFn, updatedLn, updatedEm, updatedOff);


        }
     
       
       

        response.sendRedirect("dentist.jsp");
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

