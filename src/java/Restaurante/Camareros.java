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
@WebServlet(name = "Camareros", urlPatterns = {"/Camareros"})
public class Camareros extends HttpServlet {

    public String GetDB()
    {
        BD a=new BD();
        return a.CunsultarCamareros();
    }
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
            String[] aux=GetDB().split(",");
            String[] camarero;
            int index=0;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Camareros Opciones</title>");            
            out.println("</head>");
            out.println("<body>");
            if((camarero=aux[0].split("-")).length!=1)
            {//<>
               //id-nombre-apellido
                index=aux.length;
                out.println("<table class=\"egt\">");
                out.println("<tr>");
                out.println("<td>ID</td>");
                out.println("<td>Nombre</td>");
                out.println("<td>Apellido</td>");
                out.println(" </tr>");
                for(int i=0;i<aux.length;i++)
                {//id,precio,nombre,descripcion
                    camarero=aux[i].split("-");
                    out.println("<tr>");
                    out.println("<td>"+camarero[0]+"</td>");
                    out.println("<td>"+camarero[1]+"</td>");
                    out.println("<td>"+camarero[2]+"</td>");
                    out.println("</tr>");
                }  
                out.println("</table>");
            }
            else
            {
                index=0;
                out.println("<div><a>No hay Camareros registrados en este restaurante</a></div>");
            }
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            out.println("<div><a href=\"http://localhost:8080/Restaurante/Gestor?G=0\">Registro mensual de camareros</a></div>");
            out.println("<div><a href=\"http://localhost:8080/Restaurante/Gestor?G=1&index="+(index+1)+"\">Agregar Camarero</a></div>");
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
