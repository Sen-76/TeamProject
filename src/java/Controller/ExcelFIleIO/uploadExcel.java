/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ExcelFIleIO;

import DAO.DAOExcel;
import DAO.DAOSen;
import DAO.DAOUpdate;
import DAO.DAOchangePass;
import Enitiy.*;
import Enitiy.User;
import Enitiy.UserExcel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.*;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "uploadExcel", urlPatterns = {"/uploadExcel"})
public class uploadExcel extends HttpServlet {

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
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            User loged = (User) session.getAttribute("Loged");
            DAOchangePass dao = new DAOchangePass();

            DAOSen daoS = new DAOSen();
            if (loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
//            try {
//                if (loged.getRole_id() == 1 || loged.getRole_id() == 2) {
//                    request.setAttribute("messE", "Seems like you don't have permission to do this");
//                    request.getRequestDispatcher("/jsp/Class/Error.jsp").forward(request, response);
//                    return;
//                }
//            } catch (Exception e) {
//            }

            if (service == null) {
                service = "uploadE";
            }
            if (service.equals("uploadE")) {
                Vector<Class_s> v = dao.allClass();
                request.setAttribute("vectC", v);

                request.setAttribute("count", "-1");

                Vector<Class_s> ve = dao.allClass();
                request.setAttribute("vectC", ve);

                Vector<Team> vteam = dao.viewTeamByClass(request.getParameter("class_id"));
                request.setAttribute("team", vteam);

                session.setAttribute("classID", request.getParameter("class_id"));
                request.getRequestDispatcher("/jsp/Class/UploadExcel.jsp").forward(request, response);
            }
            if (service.equals("uploadFile")) {
                String filename = null;

                DAOSen daos = new DAOSen();
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

                                Vector<Class_s> v = dao.allClass();
                                request.setAttribute("vectC", v);

                                Vector<Team> vteam = dao.viewTeamByClass(request.getParameter("class_id"));
                                request.setAttribute("team", vteam);

                                request.setAttribute("messE", "Hãy import file vào trước");
                                request.getRequestDispatcher("/jsp/Class/UploadExcel.jsp").forward(request, response);

                            } else {
                                filename = daos.RandomBullSh() + item.getName();
                                Path path = Paths.get(filename);
                                String storePath = servletContext.getRealPath("/uploads");
                                File uploadFile = new File(storePath + "/" + path.getFileName());
                                out.print(storePath);
                                item.write(uploadFile);
                            }
                        }
                    }
                    String storePath = servletContext.getRealPath("/uploads");
                    DAOExcel daoE = new DAOExcel();
                    Vector<UserExcel> v = daoE.readExcel(storePath + "/" + filename);

                    session.setAttribute("vect", v);
                    session.setAttribute("count", v.size());

                    Vector<Class_s> ve = dao.allClass();
                    request.setAttribute("vectC", ve);

                    Vector<Team> vteam = dao.viewTeamByClass(request.getParameter("class_id"));
                    request.setAttribute("team", vteam);

                    request.getRequestDispatcher("/jsp/Class/UploadExcel.jsp").forward(request, response);
                } catch (Exception e) {
                    out.print(e);
                }
            }
            if (service.equals("cfDTB")) {
                Vector<UserExcel> v = (Vector<UserExcel>) session.getAttribute("vect");
                if (v == null) {
                    Vector<Class_s> ve = dao.allClass();
                    request.setAttribute("vectC", ve);
                    String claid = (String) session.getAttribute("classID") ;
                    Vector<Team> vteam = dao.viewTeamByClass(claid);
                    request.setAttribute("team", vteam);

                    request.setAttribute("messE", "Hãy import file vào trước");
                    request.getRequestDispatcher("/jsp/Class/UploadExcel.jsp").forward(request, response);
                    return;
                }
                Set<String> setTeam = new HashSet<String>();

                String classid = (String) session.getAttribute("classID");
                for (UserExcel o : v) {
                    if (!dao.checkExistUser(o.getRollNum())) {
                        dao.insertNewUser(o);
                    }
                    o.setGroupID(o.getGroupID().substring(0, o.getGroupID().length() - 2));
                    setTeam.add(o.getGroupID());
                }
                for (String o : setTeam) {
                    if(!dao.checkTeamExist(classid, o)){
                        dao.insertNewTeam(classid, o);
                    }
                }
                for (UserExcel o : v) {
                    dao.insertClassUser(classid, o);
                }

                request.setAttribute("link", "ClassUser4Admin?class_id=" + classid);
                request.getRequestDispatcher("jsp/Class/Success.jsp").forward(request, response);
                session.removeAttribute("classID");
                session.removeAttribute("vect");
                session.removeAttribute("count");
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
