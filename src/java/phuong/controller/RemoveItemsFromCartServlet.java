/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.controller;

import java.io.IOException;
import java.util.List;
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
public class RemoveItemsFromCartServlet extends HttpServlet {

    private final String VIEW_CART = "viewCart";

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
        String url = VIEW_CART;
        try {
            String[] items = request.getParameterValues("chkItems");
            if (items != null) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    CartObj cart = (CartObj) session.getAttribute("CART");
                    List<ProductDTO> list = (List<ProductDTO>) session.getAttribute("PRODUCT_LIST");
                    if (cart != null & list != null) {
                        ProductDAO dao = new ProductDAO();
                        for (int i = 0; i < items.length; i++) {
                            int sku = Integer.parseInt(items[i]);
                            int quantity = cart.getItems().get(sku).getQuantity() +
                                    list.get(sku - 1).getQuantity();
                            list.get(sku - 1).setQuantity(quantity);
                            cart.removeItemFromCart(sku);
                        }
                        session.setAttribute("CART", cart);
                    }
                }
            }
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
