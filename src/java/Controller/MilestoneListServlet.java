/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOMilestone;
import Enitiy.Milestone;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "MilestoneListServlet", urlPatterns = {"/MilestoneListServlet"})
public class MilestoneListServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        DAOMilestone dao = new DAOMilestone();
        String service = request.getParameter("go");
        if (service == null) {
            service = "listAllMilestone";
        }

        try (PrintWriter out = response.getWriter()) {

            String Ajax = request.getParameter("Ajax");

            if (Ajax != null) {
                List<Milestone> listAjax = null;
                String iteName = request.getParameter("iteId");
                String classCode = request.getParameter("classId");
                if (iteName != null && !classCode.equals("all")) {
                    listAjax = dao.searchByIteNameAndClassCode(iteName, classCode);
                }
                if (iteName != null && classCode.equals("all")) {
                    listAjax = dao.searchByIteName(iteName);
                }
                if (iteName == null && !classCode.equals("all")) {
                    listAjax = dao.searchByClassCode(classCode);
                }
                out.print("<table id=\"slide\"  class=\"table table-bordered\" width=\"100%\" cellspacing=\"0\">\n"
                        + "\n"
                        + "                                        <thead>\n"
                        + "                                            <tr>\n"
                        + "                                                <th>Milestone Id</th>\n"
                        + "                                                <th>Iteration Name</th>\n"
                        + "                                                <th>Class Code</th>                                                                                           \n"
                        + "                                                <th>From Date</th>\n"
                        + "                                                <th>To Date</th>\n"
                        + "                                                <th>Status</th>\n"
                        + "                                                <th>Action</th>\n"
                        + "\n"
                        + "                                            </tr>\n"
                        + "                                        </thead>\n"
                        + "                                        <tfoot>\n"
                        + "\n"
                        + "                                            <tr>\n"
                        + "                                                <th>Milestone Id</th>\n"
                        + "                                                <th>Iteration Name</th>\n"
                        + "                                                <th>Class Code</th>                                                                                           \n"
                        + "                                                <th>From Date</th>\n"
                        + "                                                <th>To Date</th>\n"
                        + "                                                <th>Status</th>\n"
                        + "                                                <th>Action</th>\n"
                        + "                                            </tr>\n"
                        + "\n"
                        + "\n"
                        + "                                        </tfoot>\n"
                        + "                                        <tbody>\n");
                for (Milestone o : listAjax) {
                    out.print("                                                <tr>\n"
                            + "                                                    <td>" + o.getMilestone_id() + "</td>\n"
                            + "                                                    <td>" + o.getIterationName() + "</td>\n"
                            + "                                                    <td>" + o.getClassCode() + "</td>\n"
                            + "                                                    <td>" + o.getFrom_date() + "</td>\n"
                            + "                                                    <td>" + o.getTo_date() + "</td>\n"
                            + "                                                    <td>\n"
                            + "<form action=\"MilestoneListServlet?go=updateStatus\" method=\"Post\">\n"
                            + "                                                            <input type=\"hidden\" name=\"mileId\" value=\"" + o.getMilestone_id() + "\">\n"
                            + "                                                            <select id =\"status\" name=\"status\" onchange=\"this.form.submit()\">\n"
                            //                            + "                                                                <option value=\"1\" ${"+o.getStatus()+"== 1 ? \"selected\" : \"\"}>Open</option>\n"
                            + "                                                                <option value=\"1\" " + (o.getStatus() == 1 ? "selected" : "") + ">Open</option>\n"
                            + "                                                                <option value=\"2\" " + (o.getStatus() == 2 ? "selected" : "") + ">Closed</option>\n"
                            + "                                                                <option value=\"3\" " + (o.getStatus() == 3 ? "selected" : "") + ">Cancelled</option>\n"
                            + "                                                            </select>\n"
                            + "                                                        </form>");

                    out.print("                                                    </td>                                                    \n"
                            + "                                                    <td><a href=\"MilestoneListServlet?go=updateMilestone&mileId=" + o.getMilestone_id() + "&iteId=" + o.getInteration_id() + "&classId=" + o.getClass_id() + "\">Edit</a>\n"
                            + "<a href=\"MilestoneListServlet?go=deleteMilestone&mileId=" + o.getMilestone_id() + "&iteId=" + o.getInteration_id() + "&classId=" + o.getClass_id() + "\"> Delete</a></td>\n"
                            + "                                                </tr> \n");
                }
                out.print("\n"
                        + "                                        </tbody>\n"
                        + "                                    </table>");
                return;
            }

            /* TODO output your page here. You may use following sample code. */
            if (service.equals("addMilestone")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    List<Milestone> listClassCode = dao.viewAllClassCode();
                    List<Milestone> listIterationName = dao.viewAllIteName();

                    request.setAttribute("listAllIteName", listIterationName);
                    request.setAttribute("listClassCode", listClassCode);
                    request.getRequestDispatcher("/jsp/Milestone/addMilestone.jsp").forward(request, response);
                } else {
                    int iteration_id = Integer.parseInt(request.getParameter("iteId"));
                    int class_id = Integer.parseInt(request.getParameter("classId"));
                    String from_date = request.getParameter("fromDate");
                    String to_date = request.getParameter("toDate");
                    int status = Integer.parseInt(request.getParameter("status"));

                    Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
                    Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);

                    if (fromDate.compareTo(toDate) < 0) {

                        Milestone obj = new Milestone(iteration_id, class_id, from_date, to_date, status);
                        dao.addMilestone(obj);
                        response.sendRedirect("MilestoneListServlet");
                    } else {
                        List<Milestone> listClassCode = dao.viewAllClassCode();
                        List<Milestone> listIterationName = dao.viewAllIteName();

                        request.setAttribute("listAllIteName", listIterationName);
                        request.setAttribute("listClassCode", listClassCode);
                        request.setAttribute("thongbao", "To Date phai lon hon From Date");
                        request.getRequestDispatcher("/jsp/Milestone/addMilestone.jsp").forward(request, response);
                    }
                }
            }

            if (service.equals("listAllMilestone")) {

                String page = request.getParameter("page");
                int countPage;
                if (dao.countMilestone() % 10 == 0) {
                    countPage = dao.countMilestone() / 10;
                } else {
                    countPage = dao.countMilestone() / 10 + 1;
                }

                String startFrom = "";
                request.setAttribute("countPage", countPage);

                if (page == null || page.equals("")) {
                    page = "1";
                }
                try {
                    int a = Integer.parseInt(page);
                    startFrom = (a - 1) * 10 + "";
                } catch (Exception e) {
                }

                request.setAttribute("page", page);
                if (startFrom == null || startFrom.equals("")) {
                    startFrom = "0";
                }

                List<Milestone> list = dao.viewAllMilestone(startFrom);
                List<Milestone> listClassCode = dao.viewAllClassCode();

                request.setAttribute("listClassCode", listClassCode);
                request.setAttribute("list", list);
                request.getRequestDispatcher("/jsp/Milestone/MilestoneList.jsp").forward(request, response);

//                else {
//                    String iteName1 = request.getParameter("iteId");
//                    String classCode1 = request.getParameter("classId");
//
//                    if (iteName.equals("") && classCode.equals("all")) {
//                        List<Milestone> list = dao.viewAllMilestone(startFrom);
//                        List<Milestone> listClassCode = dao.viewAllClassCode();
//
//                        request.setAttribute("listClassCode", listClassCode);
//                        request.setAttribute("list", list);
//                        request.getRequestDispatcher("/jsp/Milestone/MilestoneList.jsp").forward(request, response);
//                    }
//                    if (!iteName.equals("") && !classCode.equals("all")) {
//                        List<Milestone> listClassCode = dao.viewAllClassCode();
//                        List<Milestone> searchByIteAndClass = dao.searchByIteNameAndClassCode(iteName, classCode);
//
//                        request.setAttribute("list", searchByIteAndClass);
//                        request.setAttribute("listClassCode", listClassCode);
//                        request.getRequestDispatcher("/jsp/Milestone/MilestoneList.jsp").forward(request, response);
//                    }
//                    if (iteName.equals("") && !classCode.equals("all")) {
//                        List<Milestone> searchByCode = dao.searchByClassCode(classCode);
//                        List<Milestone> listClassCode = dao.viewAllClassCode();
//
//                        request.setAttribute("listClassCode", listClassCode);
//                        request.setAttribute("list", searchByCode);
//                        request.getRequestDispatcher("/jsp/Milestone/MilestoneList.jsp").forward(request, response);
//                    }
//                    if (!iteName.equals("") && classCode.equals("all")) {
//                        List<Milestone> searchByiteName = dao.searchByIteName(iteName);
//                        List<Milestone> listClassCode = dao.viewAllClassCode();
//
//                        request.setAttribute("listClassCode", listClassCode);
//                        request.setAttribute("list", searchByiteName);
//                        request.getRequestDispatcher("/jsp/Milestone/MilestoneList.jsp").forward(request, response);
//                    }
//                    List<Milestone> searchByiteName = dao.searchByIteName(iteName);
//                    List<Milestone> searchByCode = dao.searchByClassCode(classCode);
//                    List<Milestone> searchByIteAndClass = dao.searchByIteNameAndClassCode(iteName, classCode);
//                    List<Milestone> listClassCode = dao.viewAllClassCode();
//
//                    request.setAttribute("listClassCode", listClassCode);
//                    request.setAttribute("list", searchByCode);
//                    request.getRequestDispatcher("/jsp/Milestone/MilestoneList.jsp").forward(request, response);
//                }
            }

            if (service.equals("updateMilestone")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String mileId = request.getParameter("mileId");
                    String iteId = request.getParameter("iteId");
                    String classId = request.getParameter("classId");
                    Milestone ms = dao.viewMilestoneByMilIdAndIteIdAndClassId(classId, mileId, iteId);

                    request.setAttribute("listMile", ms);
                    request.getRequestDispatcher("/jsp/Milestone/updateMilestone.jsp").forward(request, response);
                } else {
                    int milestone_id = Integer.parseInt(request.getParameter("mileId"));
                    int iteration_id = Integer.parseInt(request.getParameter("iteId"));
                    int class_id = Integer.parseInt(request.getParameter("classId"));
                    String from_date = request.getParameter("fromDate");
                    String to_date = request.getParameter("toDate");
                    int status = Integer.parseInt(request.getParameter("status"));

                    Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
                    Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);

                    if (fromDate.compareTo(toDate) < 0) {
                        Milestone obj = new Milestone(milestone_id, iteration_id, class_id, from_date, to_date, status);
                        int n = dao.updateMilestone(obj);
                        response.sendRedirect("MilestoneListServlet");
                    } else {
                        String mileId = request.getParameter("mileId");
                        String iteId = request.getParameter("iteId");
                        String classId = request.getParameter("classId");
                        Milestone ms = dao.viewMilestoneByMilIdAndIteIdAndClassId(classId, mileId, iteId);

                        request.setAttribute("thongbao", "To Date phai lon hon From Date");
                        request.setAttribute("listMile", ms);
                        request.getRequestDispatcher("/jsp/Milestone/updateMilestone.jsp").forward(request, response);;
                    }

                }
            }

            if (service.equals("deleteMilestone")) {
                String mileId = request.getParameter("mileId");
                String iteId = request.getParameter("iteId");
                String classId = request.getParameter("classId");
                int n = dao.deleteMilestone(mileId, iteId, classId);
                response.sendRedirect("MilestoneListServlet");
            }

            if (service.equals("updateStatus")) {
                int status = Integer.parseInt(request.getParameter("status"));
                String mileId = request.getParameter("mileId");
                int n = dao.updateStatus(mileId, status);
                response.sendRedirect("MilestoneListServlet");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(MilestoneListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(MilestoneListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
