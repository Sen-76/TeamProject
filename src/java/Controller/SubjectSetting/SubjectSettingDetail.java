package Controller.SubjectSetting;

import DAO.SubjectSetting.DAOSubjectSetting;
import DAO.SubjectSetting.DAOSubjectSettingDetails;
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
                SubjectSetting list = dao.SearchSetID(SetID);
                request.setAttribute("SubjectSetting", list);
                List<SubjectSetting> listType = dao.viewTypeId();
                
                request.setAttribute("typeList", listType);
                request.setAttribute("type", typeID);
                request.getRequestDispatcher("/jsp/SubjectSetting/SubjectSettingDetails.jsp").forward(request, response);
            }
            if (service.equals("UpdateDetail")) {
                String settingTitle = request.getParameter("setting_title");
                String setValue = request.getParameter("value");
                String setOrder = request.getParameter("order");
                int status = Integer.parseInt(request.getParameter("status"));
                dao1.editSubjectSetting(settingTitle, setValue, setOrder, status, status);
                response.sendRedirect("SubjectSettingList");
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
