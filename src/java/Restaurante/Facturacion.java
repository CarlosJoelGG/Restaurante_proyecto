package Restaurante;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@WebServlet(urlPatterns = {"/Facturacion"})
public class Facturacion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
       public String GetCliente(int id)
       {    
           BD a=new BD();
           return a.Cliente(id);
       }
       
   public String GetCamarero(int id)
   {
       BD a=new BD();
       return a.Camarero(id);
   }
    
    public String GetFacturas()
    {
        BD a=new BD();
        return a.Facturas();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {//Ca=N Cl=N P1=N
            
            String [] aux=GetFacturas().split(",");
            String [] factura;
            int index=0;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Facturas Opciones</title>");            
            out.println("</head>");
            out.println("<body>");
            
            if(aux[0].split("-").length==1)
            {   index=0;
                out.println("<div><a>No hay Facturas registrados en este restaurante</a></div>");
            }
            else
            {   index=aux.length;
                out.println("<table class=\"egt\">");
                out.println("<h1>Facturas</h1>"); 
                out.println("<tr>");
                out.println("<td>Factura</td>");
                out.println("<td>Camarero</td>");
                out.println("<td>Cliente</td>");
                out.println("<td>Total a pagar</td>");
                out.println("<td>Mesa</td>");
                out.println("<td>Fecha</td>");
                out.println(" </tr>");
                for(int i=0;i<aux.length;i++)
                {//idFactura id_Cliente id_camarero Total_pagar Mesa Fecha;
                    factura=aux[i].split("-");
                    out.println("<tr>");
                    out.println("<td>"+factura[0]+"</td>");
                    out.println("<td>"+GetCamarero(Integer.parseInt(factura[2]))+"</td>");
                    out.println("<td>"+GetCliente(Integer.parseInt(factura[1]))+"</td>");
                    out.println("<td>"+factura[3]+"</td>");
                    out.println("<td>"+factura[4]+"</td>");
                    out.println("<td>"+factura[5]+"</td>");
                    out.println(" </tr>");
                }
                out.println("</table>");
            }
            out.println("<br>");
            out.println("<br>");
            
            
            out.println("<div><a href=\"http://localhost:8080/Restaurante/Gestor?G=5&Ca=N&Cl=N&P=N\">Agregar Factura</a></div>");
            out.println("<div><a href=\"http://localhost:8080/Restaurante/Clientes\">Regresar</a></div>");
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
