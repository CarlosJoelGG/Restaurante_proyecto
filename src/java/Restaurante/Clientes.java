/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "Clientes", urlPatterns = {"/Clientes"})
public class Clientes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public String GetDB()
    {
        BD a=new BD();
        return a.Clientes();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String[] aux=GetDB().split(",");
            String[] cliente;
            /* TODO output your page here. You may use following sample code. */
            int index=0;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Camareros Opciones</title>");            
            out.println("</head>");
            out.println("<body>");
            if((cliente=aux[0].split("-")).length!=1)
            {//<>
               //id-nombre-apellido
                index=aux.length;
                out.println("<table class=\"egt\">");
                out.println("<tr>");
                out.println("<td>Index</td>");
                out.println("<td>Nombre</td>");
                out.println("<td>Cedula</td>"); 
                out.println(" </tr>");
                for(int i=0;i<aux.length;i++)
                {//Nombre Cedula index
                    cliente=aux[i].split("-");
                    out.println("<tr>");
                    out.println("<td>"+cliente[2]+"</td>");
                    out.println("<td>"+cliente[0]+"</td>");
                    out.println("<td>"+cliente[1]+"</td>");
                    out.println("</tr>");
                }  
                out.println("</table>");
            }
            else
            {
                index=0;
                out.println("<div><a>No hay clientes registrados en este restaurante</a></div>");
            }
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            out.println("<div><a href=\"http://localhost:8080/Restaurante/Gestor?G=2\">Clientes con mas de cien mil pesos gastados en el restaurante</a></div>");
            out.println("<div><a href=\"http://localhost:8080/Restaurante/Gestor?G=3&index="+(index+1)+"\">Agregar Cliente</a></div>");
            out.println("<div><a href=\"http://localhost:8080/Restaurante/Menu\">Regresar</a></div>");
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
