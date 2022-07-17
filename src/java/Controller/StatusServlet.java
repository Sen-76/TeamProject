
package Controller;

import DAO.DAOSetting;
import Enitiy.Setting;
import ConnectDB.ConnectJDBC;
import Controller.Setting.SettingListServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StatusServlet", urlPatterns = {"/StatusServlet"})
public class StatusServlet extends HttpServlet {
    
    Connection conn = ConnectJDBC.getConnection();
    
    ResultSet rs = null;
    PreparedStatement ps = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOSetting dao = new DAOSetting();

        String service = request.getParameter("go");
        if (service == null) {
            service = "listAllSetting";
        }
        try (PrintWriter out = response.getWriter()) {

            if (service.equals("updateStatus")) {
                
                String submit = request.getParameter("submit");
                if (submit == null) {
                    response.sendRedirect("SettingListServlet");
                } else {
                    String settingId = request.getParameter("settingId");
                    Setting st = new Setting();
                    String sql = "SELECt st.setting_id,t.type_title,st.setting_title,st.setting_value,st.display_order,st.status FROM `swp-1619-project`.setting st inner join `swp-1619-project`.type t\n"
                            + "on st.type_id = t.type_id where st.setting_id = " + settingId;
                    ResultSet rs = dao.getData(sql);
                    out.print(dao.getData(sql));
                    try {
                        if (rs.next()) {
                            if (rs.getInt(6) == 1) {
                                int update = dao.updateStatus(2, rs.getInt(1));
                            }
                            if (rs.getInt(6) == 2) {
                                int update = dao.updateStatus(1, rs.getInt(1));
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SettingListServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.sendRedirect("SettingListServlet");
 //                   request.getRequestDispatcher("ListSetting.jsp").forward(request, response);
                }

            }

        }catch (Exception e) {
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
