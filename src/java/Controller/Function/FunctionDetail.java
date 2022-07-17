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
@WebServlet(name = "FunctionDetail", urlPatterns = {"/FunctionDetail"})
public class FunctionDetail extends HttpServlet {

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

            String service = request.getParameter("go");
            DAOFunction dao = new DAOFunction();
            if (service.equals("Delete")) {
                int fid = Integer.parseInt(request.getParameter("fid"));
                Function1 c = dao.getDelete(fid);
                response.sendRedirect("FunctionList");
            }

            if (service.equals("Update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int fid = Integer.parseInt(request.getParameter("fid"));
                    String feID = request.getParameter("Fe");
                    String ownerID = request.getParameter("Owner");
                    String comID = request.getParameter("Com");
                    Function1 f = dao.getFunction(fid);
                    List<Function1> listLevel = dao.getLevel();
                    List<Function1> listOwner = dao.getOwner();
                    List<Function1> listFeature = dao.getFeature(Loged.getUser_id());
                    request.setAttribute("fu", f);
                    request.setAttribute("LevelList", listLevel);
                    request.setAttribute("OwnerList", listOwner);
                    request.setAttribute("FeatureList", listFeature);
                    request.setAttribute("owner", ownerID);
                    request.setAttribute("fe", feID);
                    request.setAttribute("com", comID);
                    //  out.print(iterID);
                    request.getRequestDispatcher("jsp/Function/FunctionDetail.jsp").forward(request, response);
                } else {
                    int function_id = Integer.parseInt(request.getParameter("function_id"));
                    int feature_id = Integer.parseInt(request.getParameter("feature_id"));
                    int complexity_id = Integer.parseInt(request.getParameter("complexity_id"));
                    int owner_id = Integer.parseInt(request.getParameter("owner_id"));
                    String priority = request.getParameter("priority");
                    String des = request.getParameter("description");
                    String access_roles = request.getParameter("access_roles");
                    String function_name = request.getParameter("function_name");
                    int status = Integer.parseInt(request.getParameter("status"));
                    int team_class = Integer.parseInt(request.getParameter("team_class"));

                    if (priority.matches("^\\d+$")) {
                    }

                    Function1 obj = new Function1(function_id, team_class, function_name, feature_id, access_roles, des, complexity_id, owner_id, priority, status);
                    int n = dao.update(obj);

                    request.setAttribute("title", "Update thành công");
                    request.setAttribute("message", "Vua update duoc roi day!");
                    request.setAttribute("theme", "Success");
                    request.getRequestDispatcher("FunctionList").forward(request, response);
                    //response.sendRedirect("FunctionList");
                }

            }

            if (service.equals("add")) {
                List<Function1> listClassId = dao.viewTeamClass(Loged.getUser_id());
                List<Function1> listLevel = dao.getLevel();
                List<Function1> listFeature = dao.getFeature(Loged.getUser_id());
                List<Function1> listOwner = dao.getOwner();
                request.setAttribute("LevelList", listLevel);
                request.setAttribute("OwnerList", listOwner);
                request.setAttribute("ClassList", listClassId);
                request.setAttribute("FeatureList", listFeature);
                // out.print(listClassId);
                request.getRequestDispatcher("jsp/Function/AddFunction.jsp").forward(request, response);
            }
           if (service.equals("addFunction")) {
                int team_id = Integer.parseInt(request.getParameter("team_class"));
                String function_name = request.getParameter("function_name");
                int feature_id = Integer.parseInt(request.getParameter("feature_id"));
                String des = request.getParameter("description");
                String access_roles = request.getParameter("access_roles");
                int complexity_id = Integer.parseInt(request.getParameter("complexity_id"));
                int status = Integer.parseInt(request.getParameter("status"));
                int owner_id = Integer.parseInt(request.getParameter("owner_id"));
                int priority = Integer.parseInt(request.getParameter("priority"));
                Function1 c = new Function1(team_id, function_name, feature_id, access_roles, des.trim(), complexity_id, owner_id, String.valueOf(priority), status);
                if (priority > 0 && priority <= 5) {
                    int n = dao.addFunction(c);
                    if (n > 0) {
                        request.setAttribute("title", "Add thành công");
                        request.setAttribute("message", "Vua add duoc roi day!");
                        request.setAttribute("theme", "Success");
                        request.getRequestDispatcher("FunctionList").forward(request, response);
                    }
                } else {

                    request.setAttribute("err", "Input number");
                    request.setAttribute("errP", "Input number 1-5");
                    request.setAttribute("txtName", function_name);
                    request.setAttribute("txtPri", priority);
                    request.setAttribute("txtRole", access_roles);
                    List<Function1> listClassId = dao.viewTeamClass(Loged.getUser_id());
                    List<Function1> listLevel = dao.getLevel();
                    List<Function1> listFeature = dao.getFeature(Loged.getRole_id());
                    List<Function1> listOwner = dao.getOwner();
                    request.setAttribute("LevelList", listLevel);
                    request.setAttribute("OwnerList", listOwner);
                    request.setAttribute("ClassList", listClassId);
                    request.setAttribute("FeatureList", listFeature);
                    request.getRequestDispatcher("jsp/Function/AddFunction.jsp").forward(request, response);
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
