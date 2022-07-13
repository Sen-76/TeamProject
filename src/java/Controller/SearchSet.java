
package Controller;

import DAO.DAOSetting;
import Enitiy.Setting;
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

@WebServlet(name = "SearchSet", urlPatterns = {"/SearchSet"})
public class SearchSet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
            } 
            DAOSetting dao = new DAOSetting();

            String typeId = request.getParameter("typeId");
            String status = request.getParameter("status");
            String SearchName = request.getParameter("SearchName");

            request.setAttribute("ID1", typeId);
            request.setAttribute("sta1", status);
            
            if (typeId.equals("all") && status.equals("all") && SearchName.equals("")) {
                List<Setting> list = dao.viewSettingList();
                request.setAttribute("SettingList", list);
                response.sendRedirect("SettingListServlet");
            }
            if (!SearchName.equals("")) {
                List<Setting> listType = dao.viewTypeId();
                List<Setting> list = dao.SearchByNameS(SearchName);
                request.setAttribute("SettingList", list);
                request.setAttribute("typeList", listType);
                //response.sendRedirect("SettingListServlet");
                request.getRequestDispatcher("/jsp/ListSetting.jsp").forward(request, response);
            }
            if (!status.equals("all") || !typeId.equals("all")) {
                if (typeId.equals("all")) {
                    List<Setting> listType = dao.viewTypeId();
                    List<Setting> list = dao.searchStatus(status);
                    request.setAttribute("SettingList", list);
                    request.setAttribute("typeList", listType);
                    request.getRequestDispatcher("/jsp/ListSetting.jsp").forward(request, response);
                }
                if (status.equals("all")) {
                    
                    List<Setting> listType = dao.viewTypeId();
                    List<Setting> list = dao.searchTypeID(typeId);
                    request.setAttribute("SettingList", list);
                    request.setAttribute("typeList", listType);
                    request.getRequestDispatcher("/jsp/ListSetting.jsp").forward(request, response);
                }
                
                if(!status.equals("all") && !typeId.equals("all")){
                    List<Setting> listType = dao.viewTypeId();
                    List<Setting> list = dao.SearchTitIDAndSta(typeId, status);
                    request.setAttribute("SettingList", list);
                    request.setAttribute("typeList", listType);
                    request.getRequestDispatcher("/jsp/ListSetting.jsp").forward(request, response);
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
