package Controller;

import DAO.DAOSen;
import DAO.DAOchangePass;
import Enitiy.Class_s;
import Enitiy.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class Home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            DAOSen daosen = new DAOSen();
            DAOchangePass daoT = new DAOchangePass();
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
            List<Enitiy.ClassUser> listCU = daosen.AllClassUser(Loged.getUser_id());
            session.setAttribute("listCU", listCU);
            
            Vector<Class_s> vect = daoT.viewClassByStudent(Loged.getUser_id() + "");
            request.setAttribute("vectC", vect);
            
            request.getRequestDispatcher("jsp/Home.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("404.html").forward(request, response);
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
