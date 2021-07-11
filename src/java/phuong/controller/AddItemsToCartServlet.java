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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuong.cart.CartObj;
import phuong.product.ProductDAO;
import phuong.product.ProductDTO;

/**
 *
 * @author DELL
 */
public class AddItemsToCartServlet extends HttpServlet {

    private final String SHOPPING_ONLINE_ERROR = "shoppingOnlineError.html";
    private final String SHOPPING_ONLINE = "shoppingServlet";

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
        String button = request.getParameter("btAction");
        int items = Integer.parseInt(request.getParameter("ItemsToCart"));
        String url = SHOPPING_ONLINE_ERROR;
        try {
            if (button.equals("Add")) {
                HttpSession session = request.getSession();
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart == null) {
                    cart = new CartObj();
                }
                ProductDAO dao = new ProductDAO();
                ProductDTO dto = dao.getProduct(items);
                if (dto != null) {
                    String sku = String.valueOf(items);
                    cart.addItemtoCart(sku, dto);
                    session.setAttribute("CART", cart);
                    boolean result = dao.updateProduct(items, "DECREASE",1);
                    if (result) {
                        url = SHOPPING_ONLINE;
                    }
                }
            }

        } catch (NamingException ex) {
            log("AddItemsToCart _Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("AddItemsToCart _SQL" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
