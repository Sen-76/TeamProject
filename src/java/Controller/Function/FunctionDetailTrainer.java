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
@WebServlet(name = "FunctionDetailTrainer", urlPatterns = {"/FunctionDetailTrainer"})
public class FunctionDetailTrainer extends HttpServlet {

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
             if (service.equals("Detail")) {
                int fid = Integer.parseInt(request.getParameter("fid"));
                String feID =request.getParameter("Fe");
              String ownerID =request.getParameter("Owner");
                 String comID = request.getParameter("Com");
                Function1 f = dao.getFunction(fid);
                 List<Function1> listLevel = dao.getLevel();
                 List<Function1> listOwner = dao.getOwnerTrainer(Integer.parseInt(ownerID),fid );
                  List<Function1> listFeature = dao.getFeatureTrainer(Integer.parseInt(feID));
               // List<Feature> listClassId = dao.viewTeamClass(Loged.getUser_id());
             //   request.setAttribute("ClassList", listClassId);
               // out.print(listFeature);
              
               request.setAttribute("fu", f);
                request.setAttribute("LevelList", listLevel);
                  request.setAttribute("OwnerList", listOwner);
                   request.setAttribute("FeatureList", listFeature);
                    request.setAttribute("owner", ownerID);
                   
                     request.setAttribute("fe", feID);
                       request.setAttribute("com", comID);
                //  out.print(iterID);
                request.getRequestDispatcher("jsp/Function/FunctionDetailTrainer.jsp").forward(request, response);
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
