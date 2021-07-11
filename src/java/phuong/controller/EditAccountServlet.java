/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuong.registration.RegistrationDAO;
import phuong.registration.RegistrationDTO;
import phuong.registration.RegistrationEditError;

/**
 *
 * @author DELL
 */
public class EditAccountServlet extends HttpServlet {

    private final String EDIT_ACCOUNT_ERROR = "editError";
    private final String EDIT_ACCOUNT = "edit";
    private final String CONFIRM_PAGE = "confirm";
    private RegistrationEditError errors;
    private RegistrationDAO dao = new RegistrationDAO();

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
        String url = EDIT_ACCOUNT_ERROR;
        HttpSession session = request.getSession();
        String button = request.getParameter("btAction");
        try {
            if (button == null) {
                String username = request.getParameter("pk");
                String searchValue = request.getParameter("lastSearchValue");
                String firsttime = request.getParameter("INFORM");
                if (firsttime == null) {
                    RegistrationDTO dto = setEdittedAccount(username, searchValue);
                    if (dto != null) {
                        url = EDIT_ACCOUNT;
                        session.setAttribute("INFORM", dto);
                        session.setAttribute("lastSearchValue", searchValue);
                    }
                }
            }
            if (button.equals("Update")) {
                session.removeAttribute("INFORM");
                String username = request.getParameter("txtUsernameEdit");
                String searchValue = request.getParameter("lastSearchValue");
                String lastname = request.getParameter("txtLastNameEdit");
                String password = request.getParameter("txtPasswordEdit");
                String isAdmin = request.getParameter("chkAdminEdit");
                boolean role = false;
                if (isAdmin != null) {
                    role = true;
                }
                boolean validation = validateAccount(password, lastname);
                RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                if (validation) { //password or lastname is incorrect
                    request.setAttribute("EDIT_ERROR", errors);
                    url = EDIT_ACCOUNT;
                } else { // validation is correct
                    session.removeAttribute("EDIT_ERROR");
                    url = CONFIRM_PAGE;
                }
                session.setAttribute("INFORM", dto);
                request.setAttribute("lastSearchValue", searchValue);
            }

        } finally {
            ServletContext context = request.getServletContext();
            Map<String, String> mapper = (Map<String, String>) context.getAttribute("MAP");
            String resource = mapper.get(url);
            RequestDispatcher rq = request.getRequestDispatcher(resource);
            rq.forward(request, response);
        }
    }

    private RegistrationDTO setEdittedAccount(String username, String searchValue) {
        boolean result = false;
        RegistrationDTO dto = null;
        try {
            boolean userIsExisted = dao.retrieveInfromation(username, searchValue);
            if (userIsExisted) {
                result = true;
                dto = dao.getUser();
            }
        } catch (NamingException ex) {
            log("EditAccountServlet _ Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("EditAccountServlet _ SQL " + ex.getMessage());
        }
        return dto;
    }

    private boolean validateAccount(String password, String lastname) {
        boolean foundError = false;
        errors = new RegistrationEditError();
        if (password.trim().length() < 6 || password.trim().length() > 20) {
            foundError = true;
            errors.setPassswordLengthErr("Password must has length from 6 to 20 chars");
        }
        if (lastname.trim().length() < 6 || lastname.trim().length() > 50) {
            foundError = true;
            errors.setLastnameLengthErr("Lastname must has length from 6 to 50 chars");
        }
        return foundError;
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
