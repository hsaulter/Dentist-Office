package dent;


import dent.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

/******************************************
 * Haley Saulter

 ***********************************/
@WebServlet(urlPatterns = {"/patLoginServlet"})
public class patLoginServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            
        
        String id, pass;
        
        
        id = request.getParameter("userId");
        pass = request.getParameter("passWd");
        
        System.out.println("ID = " + id);
        System.out.println("Password = " + pass);
        
       
        try {
                
                HttpSession ses1 = request.getSession(true);
                if(ses1 != null){
                Patient p1 = new Patient();
                  p1.selectDB(id);
                 System.out.println("First Name = " + p1.getpatientFn());
                  
                 String passWd = p1.getpatientPwd();
                 request.setAttribute("p1", p1);
                  System.out.println("Patient added to session");
                


    if (pass.equals(passWd)) {
        RequestDispatcher rd = request.getRequestDispatcher("/patient.jsp");
        rd.forward(request, response);
    } else {
        RequestDispatcher rd = request.getRequestDispatcher("/LoginError.jsp");
        rd.forward(request, response);
    }
                }
                
} catch (Exception ex) {
    System.out.println("Error: " + ex);
} finally {
    System.out.println("LoginServlet ending..");
    out.close();
}

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
        processRequest(request, response);
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
        processRequest(request, response);
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