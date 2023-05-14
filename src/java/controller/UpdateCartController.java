/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cart.Cart;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateCartController", urlPatterns = {"/UpdateCartController"})
public class UpdateCartController extends HttpServlet {
    
    public static final String ERROR = "cart.jsp";
    public static final String SUCCESS = "cart.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int productQuantityAvailable = Integer.parseInt(request.getParameter("productQuantityAvailable"));
            String pid = request.getParameter("pid");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String cid = request.getParameter("cid");
            request.setAttribute("CID", cid);

                HttpSession session = request.getSession();
                Cart cart = (Cart) session.getAttribute("CART");
                Product product = null;
                if (cart != null) {
                    for (Product dto : cart.getCart().values()) {
                        
                        if (dto.getPid().equals(pid)) {
                            String pname = dto.getPname();
                            double price = dto.getPrice();
                            String image = dto.getImage();
                            String description = dto.getDescription();
                            int status = dto.getStatus();
                            int cateId = dto.getCateId();
                            product = new Product(pid, pname, quantity, price, image, description, status, cateId);
                            product.setQuantityAvaiable(productQuantityAvailable);
                        }
                    }

                    url = SUCCESS;
                
            }
        } catch (Exception e) {
            log("Error at UpdateCartController " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
