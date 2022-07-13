package Controller;

import DAO.DAOSubjectSetting;
import DAO.DAOSubjectSettingDetails;
import Enitiy.Setting;

import Enitiy.SubjectSetting;
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
@WebServlet(name = "SubjectSettingDetail", urlPatterns = {"/SubjectSettingDetail"})
public class SubjectSettingDetail extends HttpServlet {

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
            } 
            String service = request.getParameter("go");
            DAOSubjectSetting dao = new DAOSubjectSetting();
            DAOSubjectSettingDetails dao1 = new DAOSubjectSettingDetails();
            if (service == null) {
                service = "UpdateSubjectSetting";
            }
            if (service.equals("UpdateSubjectSetting")) {
                String SetID = request.getParameter("setting_id");
                String typeID = request.getParameter("Type");
                Setting list = dao.SearchSetID(SetID);
                request.setAttribute("SubjectSetting", list);
                List<SubjectSetting> listType = dao.viewTypeId();
                
                request.setAttribute("typeList", listType);
                request.setAttribute("type", typeID);
                request.getRequestDispatcher("/jsp/SubjectSettingDetails.jsp").forward(request, response);
            }
            if (service.equals("UpdateDetail")) {
                int group = Integer.parseInt(request.getParameter("group"));
                String set_order = request.getParameter("order");
                String set_title = request.getParameter("lessontype");
                String set_value = request.getParameter("value");
                int status = Integer.parseInt(request.getParameter("status"));
                int subject_id = Integer.parseInt(request.getParameter("subject_id"));
                dao1.editSubjectSetting(subject_id, subject_id, subject_id, set_title, set_value, set_order, status);
                response.sendRedirect("SubjectSettingList");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("404.html").forward(request, response);
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
