/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phuong.registration.RegistrationDAO;

/**
 *
 * @author DELL
 */
public class PhuongServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login";
    private final String LOGIN_SERVLET = "loginServlet";
    private final String LOG_OUT = "logOutAccount";
    private final String SEARCH_PAGE = "search";

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
        String url = LOGIN_PAGE;
        String button = request.getParameter("btAction");
        try {
            if (button == null) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cooky : cookies) {
                        String username = cooky.getName();
                        String password = cooky.getValue();
                        RegistrationDAO dao = new RegistrationDAO();
                        String result = dao.checkLogin(username, password);
                        if (!result.isEmpty()){
                            url = LOGIN_SERVLET;
                            break;
                        } // end if 
                    } // end traversal of cookies
                } // if cookies is existed
            } else if (button.equals("Login")){
                url = LOGIN_SERVLET;
            } else if (button.equals("Log Out")){
                url = LOG_OUT;
            } else if (button.equals("Search")){
                url = SEARCH_PAGE;
            }         
        } catch (NamingException ex) {
            log("PhuongServlet _Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("PhuongServlet _SQL" + ex.getMessage());
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
