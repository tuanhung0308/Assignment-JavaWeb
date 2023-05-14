/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cart.Cart;
import dao.DAOCustomer;
import entity.Customer;
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
@WebServlet(name = "AddProductToCartController", urlPatterns = {"/AddProductToCartController"})
public class AddProductToCartController extends HttpServlet {

    public static final String ERROR = "DisplayProductController";
    public static final String SUCCESS = "DisplayProductController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String pid = request.getParameter("pid");
            String pname = request.getParameter("pname");
            String image = request.getParameter("image");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int productQuantityAvailable = Integer.parseInt(request.getParameter("quantityAvailable"));
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            int status = Integer.parseInt(request.getParameter("status"));
            int cateId = Integer.parseInt(request.getParameter("cateId"));
            String cid = request.getParameter("cid");
            String username = request.getParameter("username");
            
               
            request.setAttribute("cid", cid);
            request.setAttribute("username", username);

            Product pro = new Product(pid, pname, quantity, price, image, description, status, cateId);

            HttpSession session = request.getSession();
            boolean checkValidation = true;
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();

            } else {
                if (cart.getCart().containsKey(pid)) {
                    String message = "The product is added to your cart before!!!Check your cart, please!";
                    request.setAttribute("SHOPPING_MESSAGE", message);
                    return;
                }
            }

           
                pro.setQuantityAvaiable(productQuantityAvailable);
                cart.add(pro);
                session.setAttribute("CART", cart);
                String message = "Add to cart successfully!!!";
                request.setAttribute("SHOPPING_MESSAGE", message);
                url = SUCCESS;
            

        } catch (Exception e) {
            log("Error at AddToCartController" + e.toString());
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
