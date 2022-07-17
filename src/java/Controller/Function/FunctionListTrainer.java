/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Function;

import DAO.DAOFunction;
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
@WebServlet(name = "FunctionListTrainer", urlPatterns = {"/FunctionListTrainer"})
public class FunctionListTrainer extends HttpServlet {

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
        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");
        if (Loged == null) {
            request.getRequestDispatcher("Login_sen").forward(request, response);
            return;
        }
//        try {
//            if (Loged.getRole_id() != 2) {
//                request.setAttribute("messE", "Seems like you don't have permission to do this");
//                request.getRequestDispatcher("/jsp/Class/Error.jsp").forward(request, response);
//                return;
//            }
//        } catch (Exception e) {
//        }
        String service = request.getParameter("go");
        if (service == null) {
            service = "ListAllFunction";
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            if (service.equals("ListAllFunction")) {
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
                    List<Function1> list = dao.viewFunctionListTranier(Loged.getUser_id(), index);
                    List<Function1> TeamList = dao.viewTeamTrainer(Loged.getUser_id());
                    List<Function1> ClassList = dao.viewClassTrainer(Loged.getUser_id());
                    request.setAttribute("FunctionListTrainer", list);
                    request.setAttribute("TeamList", TeamList);
                    request.setAttribute("ClassList", ClassList);
                    request.setAttribute("maxP", maxPage);
                    request.getRequestDispatcher("/jsp/Function/FunctionListTrainer.jsp").forward(request, response);
                } else {
                    String classcode = request.getParameter("class");
                    String search = request.getParameter("searchName");
                    String team = request.getParameter("team");
                    String status = request.getParameter("statusSearch");
                    if (team.equals("all") && search.equals("") && status.equals("all") && classcode.equals("all")) {
                        List<Function1> list = dao.viewFunctionListTranier(Loged.getUser_id(), index);
                        List<Function1> TeamList = dao.viewTeamTrainer(Loged.getUser_id());
                        List<Function1> ClassList = dao.viewClassTrainer(Loged.getUser_id());
                        request.setAttribute("ClassList", ClassList);
                        request.setAttribute("FunctionListTrainer", list);
                        request.setAttribute("TeamList", TeamList);
                        request.setAttribute("maxP", maxPage);
                        request.getRequestDispatcher("/jsp/Function/FunctionListTrainer.jsp").forward(request, response);
                    }
                    if (search.equals("") && !team.equals("all")) {
                        List<Function1> searchByTeam = dao.searchByTeamTrainer(team, Loged.getUser_id());
                        List<Function1> TeamList = dao.viewTeamTrainer(Loged.getUser_id());
                        List<Function1> ClassList = dao.viewClassTrainer(Loged.getUser_id());
                        request.setAttribute("ClassList", ClassList);
                        request.setAttribute("FunctionListTrainer", searchByTeam);
                        request.setAttribute("TeamList", TeamList);
                        request.getRequestDispatcher("/jsp/Function/FunctionListTrainer.jsp").forward(request, response);
                    }

                    if (search.equals("") && !classcode.equals("all")) {
                        List<Function1> searchByClass = dao.searchByClassTrainer(classcode, Loged.getUser_id());
                        List<Function1> TeamList = dao.viewTeamTrainer(Loged.getUser_id());
                        List<Function1> ClassList = dao.viewClassTrainer(Loged.getUser_id());
                        request.setAttribute("ClassList", ClassList);
                        request.setAttribute("FunctionListTrainer", searchByClass);
                        request.setAttribute("TeamList", TeamList);
                        request.getRequestDispatcher("/jsp/Function/FunctionListTrainer.jsp").forward(request, response);
                    }

                    if (!search.equals("") && team.equals("all")) {
                        List<Function1> searchByName = dao.searchByNameTrainer(Loged.getUser_id(), search);

                        request.setAttribute("txtSearch", search);
                        List<Function1> TeamList = dao.viewTeamTrainer(Loged.getUser_id());
                        List<Function1> ClassList = dao.viewClassTrainer(Loged.getUser_id());
                        request.setAttribute("ClassList", ClassList);
                        request.setAttribute("FunctionListTrainer", searchByName);
                        request.setAttribute("TeamList", TeamList);
                        request.getRequestDispatcher("/jsp/Function/FunctionListTrainer.jsp").forward(request, response);
                    }
                    if (!search.equals("") && !team.equals("all")) {
                        List<Function1> searchByTeam = dao.searchByTeamTrainer(team, Loged.getUser_id());
                        List<Function1> TeamList = dao.viewTeamTrainer(Loged.getUser_id());
                        List<Function1> ClassList = dao.viewClassTrainer(Loged.getUser_id());
                        request.setAttribute("ClassList", ClassList);
                        request.setAttribute("FunctionListTrainer", searchByTeam);
                        request.setAttribute("TeamList", TeamList);
                        request.getRequestDispatcher("/jsp/Function/FunctionListTrainer.jsp").forward(request, response);
                    }

                    if (!search.equals("") && !classcode.equals("all")) {
                        List<Function1> searchByClass = dao.searchByClassTrainer(classcode, Loged.getUser_id());
                        List<Function1> TeamList = dao.viewTeamTrainer(Loged.getUser_id());
                        List<Function1> ClassList = dao.viewClassTrainer(Loged.getUser_id());
                        request.setAttribute("ClassList", ClassList);
                        request.setAttribute("FunctionListTrainer", searchByClass);
                        request.setAttribute("TeamList", TeamList);
                        request.getRequestDispatcher("/jsp/Function/FunctionListTrainer.jsp").forward(request, response);
                    }
                    if (search.equals("") && !status.equals("all")) {
                        List<Function1> searchByStatusTrainer = dao.searchByStatusTrainer(Loged.getUser_id(), status);
                        List<Function1> TeamList = dao.viewTeamTrainer(Loged.getUser_id());
                        List<Function1> ClassList = dao.viewClassTrainer(Loged.getUser_id());
                        request.setAttribute("ClassList", ClassList);
                        request.setAttribute("FunctionListTrainer", searchByStatusTrainer);
                        request.setAttribute("TeamList", TeamList);
                        request.getRequestDispatcher("/jsp/Function/FunctionListTrainer.jsp").forward(request, response);
                    }
                  

                    List<Function1> list = dao.viewFunctionListTranier(Loged.getUser_id(), index);
                    List<Function1> TeamList = dao.viewTeamTrainer(Loged.getUser_id());
                    List<Function1> ClassList = dao.viewClassTrainer(Loged.getUser_id());
                    request.setAttribute("ClassList", ClassList);
                    request.setAttribute("FunctionListTrainer", list);
                    request.setAttribute("TeamList", TeamList);
                    request.setAttribute("maxP", maxPage);
                    request.getRequestDispatcher("/jsp/Function/FunctionListTrainer.jsp").forward(request, response);
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
