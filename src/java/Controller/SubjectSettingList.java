package Controller;

import Controller.Setting.SettingListServlet;
import DAO.DAOSubjectSetting;
import Enitiy.Setting;
import Enitiy.User;
import Enitiy.SubjectSetting;
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

@WebServlet(name = "SubjectSettingList", urlPatterns = {"/SubjectSettingList"})
public class SubjectSettingList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");
        if (Loged == null) {
            request.getRequestDispatcher("Login_sen").forward(request, response);
        }
        
        DAOSubjectSetting dao = new DAOSubjectSetting();
        String service = request.getParameter("go");
        if (service == null) {
            service = "listAllSubjectSetting";
        }
        
        try (PrintWriter out = response.getWriter()) {
            if (service.equals("addSubjectSetting")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    List<Setting> listType = dao.viewType();
                    
                    request.setAttribute("listType", listType);
                    request.getRequestDispatcher("jsp/addSubjectSetting.jsp").forward(request, response);
                } else {
                    String subjectId = request.getParameter("subjectID");
                    int subjectSettingType = Integer.parseInt(request.getParameter("subjectSettingType"));
                    String name = request.getParameter("name");
                    String order = request.getParameter("order");
                    String value = request.getParameter("value");
                    int status = Integer.parseInt(request.getParameter("status"));
                    SubjectSetting obj = new SubjectSetting(subjectId, subjectSettingType, value, order, name, status);
                    int n = dao.addSubjectSetting(obj);
                    response.sendRedirect("SubjectSettingList");
                }
            }
            
            if (service.equals("listAllSubjectSetting")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int index = 0;
                    List<SubjectSetting> listType = dao.viewTypeId();
                    String type = request.getParameter("type");
                    List<SubjectSetting> list = dao.viewSubjectSettingList();
                    List<SubjectSetting> viewSubjectSettingType = dao.viewSubjectSettingType(type);
                    
                    request.setAttribute("viewSubjectSettingType", viewSubjectSettingType);
                    request.setAttribute("SubjectSettingList", list);
                    request.setAttribute("typeList", listType);
                    request.getRequestDispatcher("/jsp/SubjectSettingList.jsp").forward(request, response);
                } else {
                    int typeId = Integer.parseInt(request.getParameter("typeId"));
                    int status = Integer.parseInt(request.getParameter("status"));
                    String title = request.getParameter("title");
                    
                    List<SubjectSetting> listSearch = dao.searchSetting(typeId, title, status);
                    List<SubjectSetting> listType = dao.viewTypeId();
                    request.setAttribute("SubjectSettingList", listSearch);
                    request.setAttribute("typeList", listType);
                    request.getRequestDispatcher("/jsp/SubjectSettingList.jsp").forward(request, response);
                }
            }
            
            if (service.equals("updateStatus")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    response.sendRedirect("SubjectSettingList");
                } else {
                    String settingId = request.getParameter("settingId");
                    SubjectSetting ss = new SubjectSetting();
                    String sql = "select * from subject_setting \n"
                            + "where setting_id = " + settingId;
                    ResultSet rs = dao.getData(sql);
                    //out.print(dao.getData(sql));
                    try {
                        if (rs.next()) {
                            if (rs.getInt(7) == 1) {
                                int update = dao.updateStatus(2, rs.getInt(1));
                            }
                            if (rs.getInt(7) == 2) {
                                int update = dao.updateStatus(1, rs.getInt(1));
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SettingListServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.sendRedirect("SubjectSettingList");
                }
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
