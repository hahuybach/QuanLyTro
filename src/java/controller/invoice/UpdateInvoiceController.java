/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.invoice;

import controller.BaseAuthController;
import dal.InvoiceDBContext;
import dal.RoomDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Invoice;
import model.Room;

/**
 *
 * @author Bach
 */
public class UpdateInvoiceController extends BaseAuthController {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateInvoice</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateInvoice at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomID = request.getParameter("roomID");
        int month = Integer.parseInt(request.getParameter("month"));
        int year = Integer.parseInt(request.getParameter("year"));
        InvoiceDBContext dbInvoice = new InvoiceDBContext();
        Invoice invoice = dbInvoice.getInvoice(month, year, roomID);
        request.setAttribute("invoice", invoice);
        RoomDBContext db = new RoomDBContext();
        ArrayList<Room> rooms = db.getRooms();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("../view/invoice/updateInvoice.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int month = Integer.parseInt(request.getParameter("month"));
        int year = Integer.parseInt(request.getParameter("year"));
        String roomID = request.getParameter("room");
        int electric_num = Integer.parseInt(request.getParameter("electricity")) ;
        int water_num = Integer.parseInt(request.getParameter("water")) ;
        int bike_num = Integer.parseInt(request.getParameter("bike")) ;
        int tv_num = Integer.parseInt(request.getParameter("tv"));
        
        Invoice i = new Invoice();
        i.setMonth(month);
        i.setYear(year);
        i.setRoomID(roomID);
        i.setElectric_num(electric_num);
        i.setWater_num(water_num);
        i.setBike_num(bike_num);
        i.setTv_num(tv_num);
        InvoiceDBContext db = new InvoiceDBContext();
        db.updateInvoice(i);
        response.sendRedirect("../invoice/search");
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
