/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.tenant;

import controller.BaseAuthController;
import dal.RentDBContext;
import dal.RoomDBContext;
import dal.TenantDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Rent;
import model.Room;
import model.Tenant;

/**
 *
 * @author Bach
 */
public class UpdateTenantController extends BaseAuthController {

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
        int id = Integer.parseInt(request.getParameter("id"));
        TenantDBContext dbTenant = new TenantDBContext();
        Tenant tenant = dbTenant.getTenantByID(id);
        request.setAttribute("tenant", tenant);
        RentDBContext dbRent = new RentDBContext();
        Rent rent = dbRent.getRent(id);
        request.setAttribute("rent", rent);
        RoomDBContext db = new RoomDBContext();
        ArrayList<Room> rooms = db.getRooms();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("../view/tenant/updateTenant.jsp").forward(request, response);
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
        String raw_id = request.getParameter("id");
        String raw_name = request.getParameter("name");
        String raw_identificationNumber = request.getParameter("identificationNumber");
        String raw_phoneNumber = request.getParameter("phoneNumber");
        String raw_pob = request.getParameter("PoB");
        String raw_permanentResidence = request.getParameter("permanentResidence");
        String raw_dob = request.getParameter("DoB");
        String raw_startDate = request.getParameter("startDate");
        String raw_endDate = request.getParameter("endDate");
        String raw_gender = request.getParameter("gender");
        String raw_roomID = request.getParameter("room");
        //validate data
        int id = Integer.parseInt(raw_id);
        String name = raw_name;
        String identificationNumber = raw_identificationNumber;
        String phoneNumber = raw_phoneNumber;
        String PoB = raw_pob;
        String permanentResidence = raw_permanentResidence;
        Date dob = Date.valueOf(raw_dob);
        Date startDate = Date.valueOf(raw_startDate);
        Date endDate = Date.valueOf(raw_endDate);
        String roomID = raw_roomID;
        boolean gender = raw_gender.equals("male");

        
        Tenant t = new Tenant();
        t.setId(id);
        t.setDoB(dob);
        t.setFullName(name);
        t.setGender(gender);
        t.setIdentificationNumber(identificationNumber);
        t.setPermanentResidence(permanentResidence);
        t.setPhoneNumber(phoneNumber);
        t.setPoB(PoB);
        
        TenantDBContext dbTenant = new TenantDBContext();
        dbTenant.update(t, roomID, startDate, endDate);
        RoomDBContext dbRoom = new RoomDBContext();
        if(dbRoom.getRoom(roomID).isStatus()){
            dbRoom.changeStatus(roomID);
        }
        response.sendRedirect("../tenant/search");
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
