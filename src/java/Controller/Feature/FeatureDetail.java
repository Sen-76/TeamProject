/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Feature;

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
@WebServlet(name = "FeatureDetail", urlPatterns = {"/FeatureDetail"})
public class FeatureDetail extends HttpServlet {

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
            DAOFeature dao = new DAOFeature();
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
            String service = request.getParameter("go");
            if (service.equals("add")) {
                List<Feature> listClassId = dao.viewTeamClass(Loged.getUser_id());
                request.setAttribute("ClassList", listClassId);
                // out.print(list);
                request.getRequestDispatcher("jsp/Feature/AddFeature.jsp").forward(request, response);
            }
            if (service.equals("addFeature")) {
                //int feature_id = Integer.parseInt(request.getParameter("feature_id"));
                int team_id = Integer.parseInt(request.getParameter("team_class"));
                String feature_name = request.getParameter("feature_name");
                String des = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                Feature c = new Feature(team_id, feature_name, status, des);
                int n = dao.addFeature(c);
                if (n > 0) {
                    request.setAttribute("title", "Add thành công");
                    request.setAttribute("message", "Vua add duoc roi day!");
                    request.setAttribute("theme", "Success");
                    request.getRequestDispatcher("FeatureList").forward(request, response);
                }
            }
            if (service.equals("Update")) {
                int fid = Integer.parseInt(request.getParameter("fid"));
                Feature f = dao.getFeature(fid);
                String classID = request.getParameter("Tid");
                List<Feature> listClassId = dao.viewTeamClass(Loged.getUser_id());
                request.setAttribute("ClassList", listClassId);
                // out.print(listIterId);
                request.setAttribute("fe", f);
                request.setAttribute("class", classID);
                //  out.print(iterID);
                request.getRequestDispatcher("jsp/Feature/FeatureDetail.jsp").forward(request, response);
            }
            if (service.equals("updateFeature")) {
                int feature_id = Integer.parseInt(request.getParameter("feature_id"));
                String des = request.getParameter("description");
                String feature_name = request.getParameter("feature_name");
                int status = Integer.parseInt(request.getParameter("status"));
                int team_class = Integer.parseInt(request.getParameter("team_class"));
                dao.updateFeature(feature_id, team_class, feature_name, status, des.trim());
                request.setAttribute("title", "Update thành công");
                request.setAttribute("message", "Vua update duoc roi day!");
                request.setAttribute("theme", "Success");
                request.getRequestDispatcher("FeatureList").forward(request, response);
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
