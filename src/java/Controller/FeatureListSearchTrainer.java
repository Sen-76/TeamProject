/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOFeature;
import Enitiy.Feature;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "FeatureListSearchTrainer", urlPatterns = {"/FeatureListSearchTrainer"})
public class FeatureListSearchTrainer extends HttpServlet {

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
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
            try {
                if (Loged.getRole_id() != 2) {
                    request.setAttribute("messE", "Seems like you don't have permission to do this");
                    request.getRequestDispatcher("/jsp/Class/Error.jsp").forward(request, response);
                    return;
                }
            } catch (Exception e) {
            }
            String submit = request.getParameter("submit");

            String indexP = request.getParameter("index");
            if (indexP == null) {
                indexP = "1";
            }
            int index = Integer.parseInt(indexP);
            DAOFeature dao = new DAOFeature();

            int count = dao.getTotalListTrainer();
            int maxPage = count / 10;
            if (count % 10 != 0) {
                maxPage++;
            }

            String class_code = request.getParameter("class");
            String search = request.getParameter("searchName");
            request.setAttribute("Id", class_code);
            if (class_code.equals("all") && search.equals("")) {
                List<Feature> list = dao.viewFeatureListTranier(Loged.getUser_id(),2, index);
                request.setAttribute("FeatureList", list);
//                 request.getRequestDispatcher("jsp/ListCriteria.jsp").forward(request, response);
                response.sendRedirect("FeatureListTrainer");
            }

            if (!class_code.equals("all")) {
                List<Feature> listCri = dao.viewClassTrainer(Loged.getUser_id(),2);
                List<Feature> listSub = dao.searchClassTrainer(class_code, Loged.getUser_id(),2);
                //   out.print(listSub);
                request.setAttribute("FeatureList", listSub);
                request.setAttribute("ClassList", listCri);
                request.getRequestDispatcher("jsp/Feature/ListFeatureTrainer.jsp").forward(request, response);
            }

            if (!search.equals("")) {
                List<Feature> list = dao.searchByFeature(search);
                //  out.print(list);
                request.setAttribute("txtSearch", search);
                request.setAttribute("FeatureList", list);
                request.getRequestDispatcher("jsp/Feature/ListFeatureTrainer.jsp").forward(request, response);
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
