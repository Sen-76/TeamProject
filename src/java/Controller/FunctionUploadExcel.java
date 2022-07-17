/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOExcel;
import DAO.DAOFunction;
import DAO.DAOFunctionExcel;
import DAO.DAOMilestone;
import DAO.DAOSen;
import Enitiy.Class_s;
import Enitiy.Function1;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import Enitiy.FunctionExcel;
import Enitiy.Team;
import Enitiy.UserExcel;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "FunctionUploadExcel", urlPatterns = {"/FunctionUploadExcel"})
public class FunctionUploadExcel extends HttpServlet {

    DAOFunctionExcel dao = new DAOFunctionExcel();

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
            throws ServletException, IOException, FileUploadException {
        response.setContentType("text/html;charset=UTF-8");
        DAOFunctionExcel dao = new DAOFunctionExcel();
        String service = request.getParameter("go");
        if (service == null) {
            service = "uploadFunction";
        }
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            if (service.equals("uploadFunction")) {

                String filename = null;

                DAOSen daos = new DAOSen();
                DAOMilestone daoMile = new DAOMilestone();
                try {

                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    ServletContext servletContext = this.getServletConfig().getServletContext();
                    File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                    factory.setRepository(repository);

                    ServletFileUpload upload = new ServletFileUpload(factory);

                    List<FileItem> items = upload.parseRequest(request);

                    Iterator<FileItem> iter = items.iterator();
                    HashMap<String, String> fields = new HashMap<>();

                    while (iter.hasNext()) {
                        FileItem item = iter.next();
                        if (item.isFormField()) {
                            fields.put(item.getFieldName(), item.getString());
                        } else {
                            filename = item.getName();

                            if (filename == null || filename.equals("")) {

                                request.setAttribute("messE", "Hãy import file vào trước");
                                request.getRequestDispatcher("/jsp/Function/UploadFunction.jsp").forward(request, response);

                            } else {
                                filename = daos.RandomBullSh() + item.getName();
                                Path path = Paths.get(filename);
                                String storePath = servletContext.getRealPath("/uploads");
                                File uploadFile = new File(storePath + "/" + path.getFileName());
//                                out.print(storePath);
                                item.write(uploadFile);
                            }
                        }
                    }

                    String storePath = servletContext.getRealPath("/uploads");

                    Vector<FunctionExcel> v = dao.readExcel1(storePath + "/" + filename);

                    String classId = request.getParameter("classId");
                    Vector<Class_s> listClass = daoMile.viewAllClassByTrainer();
                    if (classId == null || classId == "") {
                        classId = listClass.firstElement().getId() + "";
                    }
                    Vector<Team> listTeam = daoMile.viewAllTeam(classId);

                    session.setAttribute("vect", v);
//                    session.setAttribute("count", v.size());
                    request.setAttribute("listTeam", listTeam);
                    request.setAttribute("listClass", listClass);
                    request.setAttribute("classId", classId);
                    request.getRequestDispatcher("/jsp/Function/UploadFunction.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (service.equals("Show")) {

                request.getRequestDispatcher("/jsp/Function/UploadFunction.jsp").forward(request, response);
            }
            
//            if (service.equals("importData")) {
//                Vector<FunctionExcel> v = (Vector<FunctionExcel>) session.getAttribute("vect");
//                if (v == null) {
//                    request.setAttribute("messE", "Hãy import file vào trước");
//                    request.getRequestDispatcher("/jsp/Function/UploadFunction.jsp").forward(request, response);
//                    return;
//                }
//                Set<String> setTeam = new HashSet<String>();
//
//                String classid = (String) session.getAttribute("classID");
//                for (UserExcel o : v) {
//                    if (!dao.checkExistUser(o.getRollNum())) {
//                        dao.insertNewUser(o);
//                    }
//                    o.setGroupID(o.getGroupID().substring(0, o.getGroupID().length() - 2));
//                    setTeam.add(o.getGroupID());
//                }
//                for (String o : setTeam) {
//                    dao.insertNewTeam(classid, o);
//                }
//                for (UserExcel o : v) {
//                    dao.insertClassUser(classid, o);
//                }
//
//                request.setAttribute("link", "ClassUser4Admin?class_id=" + classid);
//                request.getRequestDispatcher("jsp/Class/Success.jsp").forward(request, response);
//                session.removeAttribute("classID");
//                session.removeAttribute("vect");
//                session.removeAttribute("count");
//            }
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
        } catch (FileUploadException ex) {
            Logger.getLogger(FunctionUploadExcel.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (FileUploadException ex) {
            Logger.getLogger(FunctionUploadExcel.class.getName()).log(Level.SEVERE, null, ex);
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
