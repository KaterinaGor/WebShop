/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Client;
import entity.History;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ClientFacade;
import session.HistoryFacade;
import session.ProductFacade;

/**
 *
 * @author 37255
 */
@WebServlet(name = "WebController", urlPatterns = {
    "/showAddProduct",
    "/createProduct",
    "/listProduct",
    "/showAddClient",
    "/createClient",
    "/listClient",
    "/showBuyProduct",
    "/createHistory",
    "/listHistory",
    
    
})

public class WebController extends HttpServlet {
    @EJB ProductFacade productFacade;
    @EJB ClientFacade clientFacade;
    @EJB HistoryFacade historyFacade;

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
        response.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        
        switch (path){
            case "/showAddProduct":
                request.getRequestDispatcher("/showAddProduct.jsp")
                        .forward(request, response);
                break;
                
            case "/createProduct":
                String name = request.getParameter("name");
                String price = request.getParameter("price");
                String quantity = request.getParameter("quantity");
                
                Product product = new Product(name, new Integer(price), new Integer(quantity));
                
                productFacade.create(product);
                
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
                
            case "/listProduct":
                List<Product> listProduct = productFacade.findAll();
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("/listProduct.jsp")
                        .forward(request, response);
                break;
                
                
            case "/showAddClient":
                request.getRequestDispatcher("/showAddClient.jsp")
                        .forward(request, response);
                break;
                
            case "/createClient":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String money = request.getParameter("money");
                
                Client client = new Client(firstname, lastname, phone, new Integer(money));
                
                clientFacade.create(client);
                
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
                
            case "/listClient":
                List<Client> listClient = clientFacade.findAll();
                request.setAttribute("listClient", listClient);
                request.getRequestDispatcher("/listClient.jsp")
                        .forward(request, response);
                break;
                
                
            case "/showBuyProduct":
                listClient = clientFacade.findAll();
                request.setAttribute("listClient", listClient);
                listProduct = productFacade.findAll();
                request.setAttribute("listProduct", listProduct);
                
                request.getRequestDispatcher("/showBuyProduct.jsp")
                        .forward(request, response);
                break;
                
            case "/createHistory":
                String clientId = request.getParameter("clientId");
                String productId = request.getParameter("productId");
                
                client = clientFacade.find(new Long(clientId));
                product = productFacade.find(Long.parseLong(productId));
                
                Calendar calendar = new GregorianCalendar();
                
                History history = new History(client, product, calendar.getTime());
                
                historyFacade.create(history);
                
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
                
            case "/listHistory":
                List<History> listHistory = historyFacade.findAll();
                request.setAttribute("listHistory", listHistory);
                request.getRequestDispatcher("/listHistory.jsp")
                        .forward(request, response);
                break;
                
            default:
                throw new AssertionError();
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
