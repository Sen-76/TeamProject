package Controller;

import DAO.DAOSen;
import Enitiy.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ClassUserDetail", urlPatterns = {"/ClassUserDetail"})
public class ClassUserDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Need for all sevelet
            try {
                HttpSession session = request.getSession();
                User Loged = (User) session.getAttribute("Loged");
                DAOSen daosen = new DAOSen();
                if (Loged == null) {
                    request.getRequestDispatcher("Login_sen").forward(request, response);
                    return;
                }
                String classid = request.getParameter("classid");
                List<Enitiy.ClassUser> iter = daosen.AllClassUserIter(classid);
                request.setAttribute("iter", iter);
                Enitiy.ClassUser one = daosen.OneClassUser(Loged.getUser_id(), classid);
                request.setAttribute("one", one);
                request.getRequestDispatcher("jsp/ClassUser/ClassUserDetail.jsp").forward(request, response);
            } catch (Exception e) {
                request.getRequestDispatcher("404.html").forward(request, response);
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
