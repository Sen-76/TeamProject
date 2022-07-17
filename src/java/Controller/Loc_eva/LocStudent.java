/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Loc_eva;

import DAO.*;
import DAO.DAOchangePass;
import Enitiy.User;
import Enitiy.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
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
@WebServlet(name = "LocStudent", urlPatterns = {"/LocStudent"})
public class LocStudent extends HttpServlet {

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
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            User loged = (User) session.getAttribute("Loged");
            DAOchangePass dao = new DAOchangePass();
            DAOSen daoS = new DAOSen();
            if (loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
            if (service == null || service.equals("")) {
                service = "show";
            }

            if (service.equals("show")) {
                
                Vector<Class_s> vect = dao.viewClassByStudent(loged.getUser_id() + "");
                request.setAttribute("vectC", vect);
                String classid = request.getParameter("class");
                if (classid == null || classid.equals("")) {
                    classid = vect.firstElement().getId() + "";
                }
                Vector<String> vectM = dao.allMile(classid);
                request.setAttribute("vectM", vectM);
                String mile = request.getParameter("Iter");
                if(mile == null || mile.equals(""))
                    mile = vectM.firstElement();
                request.setAttribute("mile", mile);
                
                Vector<String> vectF = dao.allFunct();
                request.setAttribute("vectF", vectF);
                
                String id = request.getParameter("Tid");
                Vector<loc_evaluation> loc = dao.getMemEva(loged.getUser_id() + "", classid);
                request.setAttribute("loc", loc);

                int totalL = 0;
                for (loc_evaluation o : loc) {
                    try {
                        int a = Integer.parseInt(o.getComplexity_id());
                        int b = Integer.parseInt(o.getQuality_id());
                        totalL += (a * b / 100);
                    } catch (Exception e) {
                    }

                }
                if (totalL < 360) {
                    request.setAttribute("mess", "NOT PASSED");
                } else {
                    request.setAttribute("mess", "PASSED");
                }
                request.setAttribute("class", classid);

                request.setAttribute("total", totalL);
                request.getRequestDispatcher("/jsp/LocE/LocStudent.jsp").forward(request, response);
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
