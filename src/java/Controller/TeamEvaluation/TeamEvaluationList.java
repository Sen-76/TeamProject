/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.TeamEvaluation;

import DAO.DAOTeamEvaluation;
import Enitiy.TeamEvaluation;
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
 * @author tqbao
 */
@WebServlet(name = "TeamEvaluationList", urlPatterns = {"/TeamEvaluationList"})
public class TeamEvaluationList extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
            }
            try {
                if (Loged.getRole_id() > 2) {
                    request.setAttribute("messE", "Seems like you don't have permission to do this");
                    request.getRequestDispatcher("/jsp/Class/Error.jsp").forward(request, response);
                    return;
                }
            } catch (Exception e) {
            }
            String service = request.getParameter("go");
            DAOTeamEvaluation dao = new DAOTeamEvaluation();
            if (service == null) {
                service = "listAllTeamEval";
            }
            if (service.equals("addEval")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    List<TeamEvaluation> listTeam = dao.viewTeam();
                    
                    request.setAttribute("listTeam", listTeam);
                    request.getRequestDispatcher("/jsp/TeamEvaluation/addTeamEvaluation.jsp").forward(request, response);
                } else {
                    
                }
            }
            if (service.equals("listAllTeamEval")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int index = 0;
                    List<TeamEvaluation> listTeam = dao.viewTeamId();
                    int team = Integer.parseInt(request.getParameter("team"));
                    List<TeamEvaluation> list = dao.viewTeamEvalList(team);
                    List<TeamEvaluation> viewTeam = dao.viewTeam(team);
                    
                    request.setAttribute("viewTeam", viewTeam);
                    request.setAttribute("TeamEvalList", list);
                    request.setAttribute("teamList", listTeam);
                    request.getRequestDispatcher("/jsp/TeamEvaluation/TeamEvaluationList.jsp").forward(request, response);
                } else {
                    int teamEvalId = Integer.parseInt(request.getParameter("teamEvalId"));
                    
                    List<TeamEvaluation> listTeam = dao.viewTeamId();
                    request.setAttribute("teamList", listTeam);
                    request.getRequestDispatcher("/jsp/TeamEvaluation/TeamEvaluationList.jsp").forward(request, response);
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
