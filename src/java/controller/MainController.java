/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGIN_ADMIN = "Login Admin";
    private static final String LOGIN_ADMIN_CONTROLLER = "LoginAdminController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String REGISTER = "Register";
    private static final String REGISTER_CONTROLLER = "RegisterController";

    private static final String DISPLAY_PRODUCT = "displayProduct";
    private static final String DISPLAY_PRODUCT_CONTROLLER = "DisplayProductController";
    private static final String SEARCH_PRODUCT = "Search";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";

    private static final String DISPLAY_PRODUCT_CATE = "listByCa";
    private static final String DISPLAY_PRODUCT_CATE_CONTROLLER = "DisplayProductByCategoryController";

    private static final String ADD_PRODUCT_TO_CART = "Add to cart";
    private static final String ADD_PRODUCT_TO_CART_CONTROLLER = "AddProductToCartController";
    private static final String VIEW_CART = "viewCart";
    private static final String VIEW_CART_CONTROLLER = "ViewCartController";
    private static final String UPDATE_TO_CART = "UpdateCart";
    private static final String UPDATE_TO_CART_CONTROLLER = "UpdateCartController";
    private static final String REMOVE_TO_CART = "RemoveCart";
    private static final String REMOVE_TO_CART_CONTROLLER = "DeleteCartController";
    private static final String CHECK_OUT = "Check out";
    private static final String CHECK_OUT_CONTROLLER = "CheckOutController";

    private static final String CREATE_CUSTOMER = "createCus";
    private static final String CREATE_CUSTOMER_CONTROLLER = "CreateCustomerController";
    private static final String DELETE_CUSTOMER = "deleteCus";
    private static final String DELETE_CUSTOMER_CONTROLLER = "DeleteCustomerController";
    private static final String GET_EDIT_CUSTOMER = "editCus";
    private static final String GET_EDIT_CUSTOMER_CONTROLLER = "GetEditCusController";
    private static final String UPDATE_CUSTOMER = "updateCus";
    private static final String UPDATE_CUSTOMER_CONTROLLER = "UpdateCustomerController";
    private static final String SEARCH_CUSTOMER = "searchCus";
    private static final String SEARCH_CUSTOMER_CONTROLLER = "SearchCustomerController";
    private static final String DISPLAY_CUSTOMER = "displayCus";
    private static final String DISPLAY_CUSTOMER_CONTROLLER = "DisplayCustomerController";

    private static final String SEARCH_PRODUCT_ADMIN = "searchPro";
    private static final String SEARCH_PRODUCT_ADMIN_CONTROLLER = "SearchProductAdminController";
    private static final String DISPLAY_All_PRODUCT = "displayAllPro";
    private static final String DISPLAY_All_PRODUCT_CONTROLLER = "DisplayAllProductController";
    private static final String CREATE_PRODUCT = "createPro";
    private static final String CREATE_PRODUCT_CONTROLLER = "CreateProductController";
    private static final String DELETE_PRODUCT = "deletePro";
    private static final String DELETE_PRODUCT_CONTROLLER = "DeleteProductController";
    private static final String GET_EDIT_PRODUCT = "editPro";
    private static final String GET_EDIT_PRODUCT_CONTROLLER = "GetEditProController";
    private static final String UPDATE_PRODUCT = "updatePro";
    private static final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductController";

    private static final String DISPLAY_BILL = "displayBill";
    private static final String DISPLAY_BILL_CONTROLLER = "DisplayBillController";
    private static final String DISPLAY_BILL_DETAIL = "displayBillDetail";
    private static final String DISPLAY_BILL_DETAIL_CONTROLLER = "DisplayBillDetailController";
    private static final String UPDATE_STATUS_BILL = "updateStatus";
    private static final String UPDATE_STATUS_BILL_CONTROLLER = "UpdateStatusBillController";
    private static final String SEARCH_BILL = "searchBill";
    private static final String SEARCH_BILL_CONTROLLER = "SearchBillController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (REGISTER.equals(action)) {
                url = REGISTER_CONTROLLER;
            } else if (DISPLAY_PRODUCT.equals(action)) {
                url = DISPLAY_PRODUCT_CONTROLLER;
            } else if (SEARCH_PRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;
            } else if (DISPLAY_PRODUCT_CATE.equals(action)) {
                url = DISPLAY_PRODUCT_CATE_CONTROLLER;
            } else if (ADD_PRODUCT_TO_CART.equals(action)) {
                url = ADD_PRODUCT_TO_CART_CONTROLLER;
            } else if (UPDATE_TO_CART.equals(action)) {
                url = UPDATE_TO_CART_CONTROLLER;
            } else if (REMOVE_TO_CART.equals(action)) {
                url = REMOVE_TO_CART_CONTROLLER;
            } else if (CHECK_OUT.equals(action)) {
                url = CHECK_OUT_CONTROLLER;
            } else if (VIEW_CART.equals(action)) {
                url = VIEW_CART_CONTROLLER;
            } else if (CREATE_CUSTOMER.equals(action)) {
                url = CREATE_CUSTOMER_CONTROLLER;
            } else if (DELETE_CUSTOMER.equals(action)) {
                url = DELETE_CUSTOMER_CONTROLLER;
            } else if (GET_EDIT_CUSTOMER.equals(action)) {
                url = GET_EDIT_CUSTOMER_CONTROLLER;
            } else if (UPDATE_CUSTOMER.equals(action)) {
                url = UPDATE_CUSTOMER_CONTROLLER;
            } else if (DISPLAY_CUSTOMER.equals(action)) {
                url = DISPLAY_CUSTOMER_CONTROLLER;
            } else if (LOGIN_ADMIN.equals(action)) {
                url = LOGIN_ADMIN_CONTROLLER;
            } else if (DISPLAY_All_PRODUCT.equals(action)) {
                url = DISPLAY_All_PRODUCT_CONTROLLER;
            } else if (CREATE_PRODUCT.equals(action)) {
                url = CREATE_PRODUCT_CONTROLLER;
            } else if (DELETE_PRODUCT.equals(action)) {
                url = DELETE_PRODUCT_CONTROLLER;
            } else if (GET_EDIT_PRODUCT.equals(action)) {
                url = GET_EDIT_PRODUCT_CONTROLLER;
            } else if (UPDATE_PRODUCT.equals(action)) {
                url = UPDATE_PRODUCT_CONTROLLER;
            } else if (DISPLAY_BILL.equals(action)) {
                url = DISPLAY_BILL_CONTROLLER;
            } else if (DISPLAY_BILL_DETAIL.equals(action)) {
                url = DISPLAY_BILL_DETAIL_CONTROLLER;
            } else if (UPDATE_STATUS_BILL.equals(action)) {
                url = UPDATE_STATUS_BILL_CONTROLLER;
            } else if (SEARCH_CUSTOMER.equals(action)) {
                url = SEARCH_CUSTOMER_CONTROLLER;
            } else if (SEARCH_PRODUCT_ADMIN.equals(action)) {
                url = SEARCH_PRODUCT_ADMIN_CONTROLLER;
            } else if (SEARCH_BILL.equals(action)) {
                url = SEARCH_BILL_CONTROLLER;
            } else {
                request.setAttribute("ERROR", "Your action is not supported!");
            }
        } catch (Exception e) {
            log("Error at: MainController " + e.toString());
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
