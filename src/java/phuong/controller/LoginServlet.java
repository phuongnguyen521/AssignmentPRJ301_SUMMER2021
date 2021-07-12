/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuong.registration.RegistrationDAO;

/**
 *
 * @author DELL
 */
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid";
    private final String SEARCH_PAGE = "search";
    private final String CONFIRM_LOGOUT = "confirmLogOut";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        HttpSession session = request.getSession();
        String USERNAME = (String) session.getAttribute("USERNAME");
        String url = INVALID_PAGE;
        session.removeAttribute("MSG");
        try {
            if (USERNAME != null) {
                RegistrationDAO dao = new RegistrationDAO();
                String result = dao.checkLogin(username, password);
                if (result.equals(USERNAME)){
                    String msg = "You are logged in as " + USERNAME;
                    session.setAttribute("MSG", msg);
                }
                session.setAttribute("USERNAME", USERNAME);
                url = CONFIRM_LOGOUT;
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                String result = dao.checkLogin(username, password);
                if (!result.isEmpty()) {
                    url = SEARCH_PAGE;
                    session.setAttribute("USERNAME", result);
                    //create account cookie
                    Cookie cookie = new Cookie(username, password);
                    cookie.setMaxAge(-1); // cookie exists until close browser
                    response.addCookie(cookie);
                } // end result is existed
            }
        } catch (NamingException ex) {
            log("LoginServlet _Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet _SQL" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
