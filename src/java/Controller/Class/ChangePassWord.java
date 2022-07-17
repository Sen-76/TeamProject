/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Class;

import DAO.DAOchangePass;
import Enitiy.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ChangePassWord", urlPatterns = {"/ChangePassWord"})
public class ChangePassWord extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOchangePass daoThanh = new DAOchangePass();
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("ControllerHome").forward(request, response);
            }
            if (service == null) {
                service = "FormPass";
            }
            if (service.equals("FormPass")) {
                session.removeAttribute("mess_p");
                request.getRequestDispatcher("/jsp/ChangePass.jsp").forward(request, response);
            }
            if (service.equals("ChangeForm")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    session.removeAttribute("mess_p");
                    request.getRequestDispatcher("/jsp/ChangePass.jsp").forward(request, response);
                } else {
                    String PassNow = request.getParameter("PassNow");
                    String passNew = request.getParameter("passNew");
                    String re_pass = request.getParameter("re_pass");

                    if (PassNow.equals("") || passNew.equals("") || re_pass.equals("")) {
                        
                        request.setAttribute("PassNow", PassNow);
                        request.setAttribute("passNew", passNew);
                        request.setAttribute("re_pass", re_pass);
                        
                        session.setAttribute("mess_p", "Not null");
                        request.getRequestDispatcher("/jsp/ChangePass.jsp").forward(request, response);
                        return;
                    }
                    if (!daoThanh.encrypt(PassNow).equals(Loged.getPass())) {
                        
                        request.setAttribute("PassNow", PassNow);
                        request.setAttribute("passNew", passNew);
                        request.setAttribute("re_pass", re_pass);
                        
                        session.setAttribute("mess_p", "Your new password not same with your old password");
                        request.getRequestDispatcher("/jsp/ChangePass.jsp").forward(request, response);
                        return;
                    }

                    if (!passNew.equals(re_pass)) {
                        
                        request.setAttribute("PassNow", PassNow);
                        request.setAttribute("passNew", passNew);
                        request.setAttribute("re_pass", re_pass);
                        
                        session.setAttribute("mess_p", "New pass and re-pass are not the same");
                        request.getRequestDispatcher("/jsp/ChangePass.jsp").forward(request, response);
                        return;
                    }
                    if (daoThanh.encrypt(passNew).equals(Loged.getPass())) {
                        
                        request.setAttribute("PassNow", PassNow);
                        request.setAttribute("passNew", passNew);
                        request.setAttribute("re_pass", re_pass);
                        
                        session.setAttribute("mess_p", "Old password and new password are not the same");
                        request.getRequestDispatcher("/jsp/ChangePass.jsp").forward(request, response);
                        return;
                    }
                    if (daoThanh.encrypt(PassNow).equals(Loged.getPass()) && passNew.equals(re_pass)) {
                        
                        daoThanh.UpdatePass(re_pass, Loged.getUser_id());
                        session.setAttribute("ok", 1);
                        session.setAttribute("mess_p", "Your pass has been changed");
                        request.getRequestDispatcher("/jsp/ChangePass.jsp").forward(request, response);
                    }
                }
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
