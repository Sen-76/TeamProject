package Controller;

import DAO.DAOSen;
import Enitiy.User;
import Enitiy.UserGoogleDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class LoginGoogleHandler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            UserGoogleDto user = getUserInfo(accessToken);
            DAOSen dao = new DAOSen();
            List<User> list = dao.AllUser();
            for (User o : list) {
                if (user.getEmail().equals(o.getUser())) {
                    User Loged = dao.EmailLogin(user.getEmail());
                    if (Loged.getStatus() == 2) {
                        request.setAttribute("mess", "Tài khoản đã bị khóa!");
                        request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
                        return;
                    }
                    HttpSession seession = request.getSession();
                    seession.setAttribute("Loged", Loged);
                    request.getRequestDispatcher("Home").forward(request, response);
                } else {
                    String users = dao.RandomBullSh();
                    String pass = dao.RandomBullSh();
                    String passc = dao.encrypt(pass);
                    dao.AddUser(user.getEmail(), users, passc, user.getName());
                    String subject = "This mail just for test.";
                    String message = "<!DOCTYPE html>\n"
                            + "<html lang=\"en\">\n"
                            + "\n"
                            + "<head>\n"
                            + "</head>\n"
                            + "\n"
                            + "<body>\n"
                            + "    <h3 style=\"color: blue;\">This mail just for test</h3>\n"
                            + "    <div>Hmm</div>\n"
                            + "    <div>You just Login in my web.</div>\n"
                            + "    <div>Your username: " + users + ".</div>\n"
                            + "    <div>Your password: " + pass + ".</div>\n"
                            + "\n"
                            + "</body>\n"
                            + "\n"
                            + "</html>";
                    dao.send(user.getEmail(), subject, message, "ducnmhe150901@fpt.edu.vn", "sechan76");
                    User Loged = dao.EmailLogin(user.getEmail());
                    HttpSession seession = request.getSession();
                    seession.setAttribute("Loged", Loged);
                    request.getRequestDispatcher("Home").forward(request, response);
                }
            }
        }
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);

        return googlePojo;
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