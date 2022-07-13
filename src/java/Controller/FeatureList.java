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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "FeatureList", urlPatterns = {"/FeatureList"})
public class FeatureList extends HttpServlet {

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
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
            try {
                if (Loged.getRole_id() != 1) {
                    request.setAttribute("messE", "Seems like you don't have permission to do this");
                    request.getRequestDispatcher("/jsp/Class/Error.jsp").forward(request, response);
                    return;
                }
            } catch (Exception e) {
            }
            String service = request.getParameter("go");
            String class_code = request.getParameter("class");
            String search = request.getParameter("searchName");
            //request.setAttribute("Id", class_code);

//            
            String indexP = request.getParameter("index");
            if (indexP == null) {
                indexP = "1";
            }
            int index = Integer.parseInt(indexP);
            DAOFeature dao = new DAOFeature();
            int count = dao.getTotalList(Loged.getUser_id());
            int maxPage = count / 10;
            if (count % 10 != 0) {
                maxPage++;
            }
            List<Feature> list = dao.viewFeatureList(Loged.getUser_id(),2,index);
            request.setAttribute("maxP", maxPage);

//            List<Criteria> list1 = dao.viewCriteriaList();
           // List<Feature> list1 = dao.viewClass(Loged.getUser_id());
//out.print(list);
          //  request.setAttribute("ClassList", list1);
            request.setAttribute("FeatureList", list);
            request.getRequestDispatcher("jsp/Feature/ListFeature.jsp").forward(request, response);

            if (service.equals("updateStatus")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    response.sendRedirect("FeatureList");
                } else {
                    String featureId = request.getParameter("featureId");
                    Feature st = new Feature();
                    String sql = "SELECt * FROM feature \n"
                            + "where feature_id = " + featureId;
                    ResultSet rs = dao.getData(sql);
                    out.print(dao.getData(sql));
                    try {
                        if (rs.next()) {
                            if (rs.getInt(4) == 2) {
                                int update = dao.updateStatus(1, rs.getInt(1));
                                //out.print(update);
                            }
                            if (rs.getInt(4) == 1) {
                                int update = dao.updateStatus(2, rs.getInt(1));
                                //out.print(update);
                            }

                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(FeatureList.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    request.getRequestDispatcher("FeatureList").forward(request, response);
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
