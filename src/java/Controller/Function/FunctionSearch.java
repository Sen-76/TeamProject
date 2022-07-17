/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Function;

import DAO.DAOFeature;
import DAO.DAOFunction;
import Enitiy.Feature;
import Enitiy.Function1;
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
@WebServlet(name = "FunctionSearch", urlPatterns = {"/FunctionSearch"})
public class FunctionSearch extends HttpServlet {

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
            String submit = request.getParameter("submit");

            String indexP = request.getParameter("index");
            if (indexP == null) {
                indexP = "1";
            }
            int index = Integer.parseInt(indexP);
            DAOFunction dao = new DAOFunction();

            int count = dao.getTotalList();
            int maxPage = count / 10;
            if (count % 10 != 0) {
                maxPage++;
            }
            if (submit == null) {
                List<Function1> list = dao.viewFunctionList(Loged.getUser_id(), index);
                List<Function1> FeIDList = dao.viewTeam(Loged.getUser_id());
                List<Function1> ClassList = dao.viewClass(Loged.getUser_id());
                request.setAttribute("ClassList", ClassList);
                request.setAttribute("FunctionList", list);
                request.setAttribute("FeIDList", FeIDList);
                request.setAttribute("maxP", maxPage);
                request.getRequestDispatcher("/jsp/Function/FunctionList.jsp").forward(request, response);
            } else {
                String search = request.getParameter("searchName");
                String team = request.getParameter("team");
                String class_code = request.getParameter("class");
                String status = request.getParameter("statusSearch");
                if (team.equals("all") && search.equals("") && status.equals("all") && class_code.equals("all")) {
                    List<Function1> list = dao.viewFunctionList(Loged.getUser_id(), index);
                    List<Function1> FeIDList = dao.viewTeam(Loged.getUser_id());
                    List<Function1> ClassList = dao.viewClass(Loged.getUser_id());
                    request.setAttribute("ClassList", ClassList);
                    request.setAttribute("FunctionList", list);
                    request.setAttribute("FeIDList", FeIDList);
                    request.setAttribute("maxP", maxPage);
                    request.getRequestDispatcher("/jsp/Function/FunctionList.jsp").forward(request, response);
                }
                if (search.equals("") && !team.equals("all")) {
                    List<Function1> searchByFeature = dao.searchByTeam(Loged.getUser_id(), team);
                    List<Function1> FeIDList = dao.viewTeam(Loged.getUser_id());
                    List<Function1> ClassList = dao.viewClass(Loged.getUser_id());
                    request.setAttribute("ClassList", ClassList);
                    request.setAttribute("FunctionList", searchByFeature);
                    request.setAttribute("FeIDList", FeIDList);
                    request.getRequestDispatcher("/jsp/Function/FunctionList.jsp").forward(request, response);
                }
                if (search.equals("") && !class_code.equals("all")) {
                    List<Function1> searchByClass = dao.searchByClass(Loged.getUser_id(), class_code);
                    List<Function1> FeIDList = dao.viewTeam(Loged.getUser_id());
                    List<Function1> ClassList = dao.viewClass(Loged.getUser_id());
                    request.setAttribute("ClassList", ClassList);
                    request.setAttribute("FunctionList", searchByClass);
                    request.setAttribute("FeIDList", FeIDList);
                    request.getRequestDispatcher("/jsp/Function/FunctionList.jsp").forward(request, response);
                }
                if (!search.equals("") && team.equals("all")) {
                    List<Function1> searchByName = dao.searchByName(Loged.getUser_id(), search);
                    List<Function1> FeIDList = dao.viewTeam(Loged.getUser_id());
                    List<Function1> ClassList = dao.viewClass(Loged.getUser_id());
                    request.setAttribute("ClassList", ClassList);
                    request.setAttribute("txtSearch", search);
                    request.setAttribute("FunctionList", searchByName);
                    request.setAttribute("FeIDList", FeIDList);
                    request.getRequestDispatcher("/jsp/Function/FunctionList.jsp").forward(request, response);
                }
                if (!search.equals("") && !team.equals("all")) {
                    List<Function1> searchByFeature = dao.searchByTeam(Loged.getUser_id(), team);
                    List<Function1> FeIDList = dao.viewTeam(Loged.getUser_id());
                    List<Function1> ClassList = dao.viewClass(Loged.getUser_id());
                    request.setAttribute("ClassList", ClassList);
                    request.setAttribute("FunctionList", searchByFeature);
                    request.setAttribute("FeIDList", FeIDList);
                    request.getRequestDispatcher("/jsp/Function/FunctionList.jsp").forward(request, response);
                }

                if (search.equals("") && !status.equals("all")) {
                    List<Function1> searchByStatus = dao.searchByStatus(Loged.getUser_id(), status);
                    List<Function1> FeIDList = dao.viewTeam(Loged.getUser_id());
                    List<Function1> ClassList = dao.viewClass(Loged.getUser_id());
                    request.setAttribute("ClassList", ClassList);
                    request.setAttribute("FunctionList", searchByStatus);
                    request.setAttribute("FeIDList", FeIDList);
                    request.getRequestDispatcher("/jsp/Function/FunctionList.jsp").forward(request, response);
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
