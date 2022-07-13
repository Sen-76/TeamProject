package Controller;

import DAO.DAOSen;
import DAO.DAOUpdate;
import Enitiy.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "Update", urlPatterns = {"/Update"})
public class Update extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();

            LocalDate DateNow = java.time.LocalDate.now();
            request.setAttribute("DateNow", DateNow);

            User Log = (User) session.getAttribute("Loged");
            if (Log == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
            }
            DAOSen daos = new DAOSen();
            DAOUpdate dao = new DAOUpdate();
            String submit = request.getParameter("UpdateProfile");
            if (submit == null) {
                RequestDispatcher di = request.getRequestDispatcher("jsp/update.jsp");
                di.forward(request, response);
            } else {
                String userid = request.getParameter("userid");
                String fullname = request.getParameter("fullname");
                String gender = request.getParameter("gender");
                String dob = request.getParameter("dateofbirth");
                if (dob.equals("") || dob == null) {
                    dob = "0000-00-00";
                }
                String email = request.getParameter("email");
                if (email.endsWith("@gmail.com") || email.endsWith("@fpt.edu.vn")) {
                    System.out.println("ko van de");
                } else {
                    request.setAttribute("updated", "Your mail is not accepted");
                    RequestDispatcher di = request.getRequestDispatcher("jsp/update.jsp");
                    di.forward(request, response);
                    return;
                }

                String mobile = request.getParameter("mobile");
                // 10 so, so 0 dau tien
                if (mobile.equals("") || mobile == null) {
                    mobile = "0";
                }
                if (mobile.length() != 10 || !mobile.startsWith("0")) {
                    request.setAttribute("updated", "Your mobile phone must be about 10 digits from 0 to 9 and must be start with 0!");
                    RequestDispatcher di = request.getRequestDispatcher("jsp/update.jsp");
                    di.forward(request, response);
                    return;
                }
                try {
                    int checkM = Integer.parseInt(mobile);
                } catch (Exception e) {
                    request.setAttribute("updated", "Your mobile phone must be about 10 digits from 0 to 9 and must be start with 0!");
                    RequestDispatcher di = request.getRequestDispatcher("jsp/update.jsp");
                    di.forward(request, response);
                    return;
                }
                String fblink = request.getParameter("fblink");
                // https://facebook.com/
                if (fblink.equals("") || fblink == null) {
                    fblink = "0";
                }
                if (!fblink.startsWith("https://www.facebook.com/") && !fblink.endsWith("/")) {
                    request.setAttribute("updated", "It's not a facebook link");
                    RequestDispatcher di = request.getRequestDispatcher("jsp/update.jsp");
                    di.forward(request, response);
                }
                int n = dao.Update(userid, fullname, gender, dob, email, mobile, fblink);
                if(n>0){
                    request.setAttribute("updated", "Your profile has been updated!");
                }else{
                    request.setAttribute("updated", "Your Email is not accepted");
                }
                session.removeAttribute("Loged");
                User Loged = daos.Loged(userid);
                session.setAttribute("Loged", Loged);

                RequestDispatcher di = request.getRequestDispatcher("jsp/update.jsp");
                di.forward(request, response);
            }
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
