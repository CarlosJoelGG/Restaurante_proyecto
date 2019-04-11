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
@WebServlet(name = "Platos", urlPatterns = {"/Platos"})
public class Platos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public String Platillos()
    {
        BD a=new BD();
        return a.Platos();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String Menus_Platillos=Platillos();
        String[] Platos_del_menu = Menus_Platillos.split(",");
        String[] Plato;
        int index=0;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */ /*> <*/
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Platos Opciones</title>");            
            out.println("</head>");
            out.println("<body>");
            if(Platos_del_menu[0].split("-").length==1)
            {   index=0;
                out.println("<div><a>No hay platillos registrados en este restaurante</a></div>");
            }
            else
            {   index=Platos_del_menu.length;
                out.println("<table class=\"egt\">");
                out.println("<tr>");
                out.println("<td>Platillo</td>");
                out.println("<td>Descripcion</td>");
                out.println("<td>Precio</td>");
                out.println(" </tr>");
                for(int i=0;i<Platos_del_menu.length;i++)
                {//id,precio,nombre,descripcion
                    Plato=Platos_del_menu[i].split("-");
                    out.println("<tr>");
                    out.println("<td>"+Plato[2]+"</td>");
                    out.println("<td>"+Plato[3]+"</td>");
                    out.println("<td>"+Plato[1]+"</td>");
                    out.println(" </tr>");
                }
                out.println("</table>");
            }
            out.println("<br>");
            out.println("<br>");
            out.println("<div><a href=\"http://localhost:8080/Restaurante/Gestor?G=4&Index="+index+"\">Agrenar un nuevo plato</a></div>");
            out.println("<br>");
            out.println("<br>");
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
