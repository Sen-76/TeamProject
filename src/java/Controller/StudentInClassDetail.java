/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOSen;
import Enitiy.Team;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author asus
 */
@WebServlet(name = "StudentInClassDetail", urlPatterns = {"/StudentInClassDetail"})
public class StudentInClassDetail extends HttpServlet {

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
            DAOSen dao = new DAOSen();
            String update = request.getParameter("update");
            LocalDate DateNow = java.time.LocalDate.now();
            request.setAttribute("DateNow", DateNow);
            if (update != null) {
                String user_id = request.getParameter("user");
                String teamid = request.getParameter("teamid");
                String classid = request.getParameter("class");
                String lead = request.getParameter("lead");
                if (lead == null || lead.equals("")) {
                    lead = "0";
                }
                if (lead.equals("1")) {
                    List<Team> a = dao.Teama(teamid);
                    for (Team team : a) {
                        if(team.getTeam_id() == 1){
                            dao.UpdateClassUser(team.getStatus());
                        }
                    }
                }
                String dropout = request.getParameter("dropout");
                if (dropout == null || dropout.equals("")) {
                    dropout = "0000/0/0";
                }
                String note = request.getParameter("note");
                String ongoingeval = request.getParameter("ongoingeval");
                String fpresseval = request.getParameter("fpresseval");
                String ftopiceval = request.getParameter("ftopiceval");
                request.setAttribute("teamid", teamid);
                request.setAttribute("lead", lead);
                request.setAttribute("dropout", dropout);
                request.setAttribute("note", note);
                request.setAttribute("ongoingeval", ongoingeval);
                request.setAttribute("fpresseval", fpresseval);
                request.setAttribute("ftopiceval", ftopiceval);
                if (note.length() > 545) {
                    request.setAttribute("title", "Update thất bại");
                    request.setAttribute("message", "Note tối đa là 545 ký tự!");
                    request.setAttribute("theme", "Warning");
                    int user = Integer.parseInt(request.getParameter("user"));
                    Enitiy.ClassUser a = dao.OneClassUser(user, classid);
                    Enitiy.ClassUser oneClass = dao.OneClass(classid);
                    List<Team> lteam = dao.Team(classid);
                    request.setAttribute("lteam", lteam);
                    request.setAttribute("oneClass", oneClass);
                    request.setAttribute("Student", a);
                    request.getRequestDispatcher("jsp/ClassUser/StudentInClassDetail.jsp").forward(request, response);
                    return;
                }
                if (ongoingeval.length() > 145 || fpresseval.length() > 145 || ftopiceval.length() > 145) {
                    request.setAttribute("title", "Update thất bại");
                    request.setAttribute("message", "Ongoing Evalution, Final Press Evalution, Final Topic Evalution tối đa là 145 ký tự!");
                    request.setAttribute("theme", "Warning");
                    int user = Integer.parseInt(request.getParameter("user"));
                    Enitiy.ClassUser a = dao.OneClassUser(user, classid);
                    Enitiy.ClassUser oneClass = dao.OneClass(classid);
                    List<Team> lteam = dao.Team(classid);
                    request.setAttribute("lteam", lteam);
                    request.setAttribute("oneClass", oneClass);
                    request.setAttribute("Student", a);
                    request.getRequestDispatcher("jsp/ClassUser/StudentInClassDetail.jsp").forward(request, response);
                    return;
                }
                request.setAttribute("title", "Update thành công");
                request.setAttribute("message", "Đã update thành công rồi đấy!");
                request.setAttribute("theme", "Success");
                dao.UpdateClassUserDetail(classid, teamid, user_id, lead, dropout, note, ongoingeval, fpresseval, ftopiceval, "1");
            }
            int user = Integer.parseInt(request.getParameter("user"));
            String classid = request.getParameter("class");
            Enitiy.ClassUser a = dao.OneClassUser(user, classid);
            Enitiy.ClassUser oneClass = dao.OneClass(classid);
            List<Team> lteam = dao.Team(classid);
            request.setAttribute("lteam", lteam);
            request.setAttribute("oneClass", oneClass);
            request.setAttribute("Student", a);
            request.getRequestDispatcher("jsp/ClassUser/StudentInClassDetail.jsp").forward(request, response);
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
