/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOCustomer;
import entity.Customer;
import entity.CustomerError;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        CustomerError accountError = new CustomerError();
        try {
            String cid = request.getParameter("cid");
            String cname = request.getParameter("cname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            int status = Integer.parseInt(request.getParameter("status"));

            boolean checkValidation = true;
            DAOCustomer dao = new DAOCustomer();
            boolean checkDuplicateUserName = dao.checkDuplicateUserName(username);
            boolean checkDuplicateCid = dao.checkDuplicateCId(cid);
            if (checkDuplicateUserName) {
                accountError.setUsernameError("Duplicate !!!");
                checkValidation = false;
            }
            if (checkDuplicateCid) {
                accountError.setCidError("Duplicate !!!");
                checkValidation = false;
            }
            if (username.length() < 2 || username.length() > 50) {
                accountError.setUsernameError("UserName must be in[2,50]!");
                checkValidation = false;
            }
            if (!password.equals(confirm)) {
                accountError.setConfirm("Confirm password is not the same!");
                checkValidation = false;
            }
            if (cid.length() < 2 || cid.length() > 50) {
                accountError.setCnameError("Customer ID must be in[2,50]!");
                checkValidation = false;
            }
            if (cname.length() < 2 || cname.length() > 50) {
                accountError.setCnameError("Full Name must be in[2,50]!");
                checkValidation = false;
            }
            if (!phone.matches("0\\d{9}")) {
                accountError.setPhoneError("Wrong format phone!");
                checkValidation = false;
            }

            if (checkValidation) {
                Customer account = new Customer(cid, cname, username, password, address, phone, status);
                boolean checkCreate = dao.register(account);
                if (checkCreate) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("ACCOUNT_ERROR", accountError);
            }
        } catch (Exception e) {
            log("Error at CreateController" + e.toString());
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
