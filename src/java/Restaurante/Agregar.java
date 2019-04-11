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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DELL
 */
@WebServlet(name = "Agregar", urlPatterns = {"/Agregar"})
public class Agregar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String Getid="",GetNombre="",GetApellido="",GetDescripcion="",GetPrecio="",FP="";
    int GetIndex=0,GetCedula=0,FiCl,FiCa;
            float FTotal=0;
        Date date = new Date();
    public DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
  
    public String SetDB(int i,String Agregado)
    {
        BD a=new BD();
        
        return a.agregar(i, Agregado);
    }
    
    public String getFacturas()
    {
        BD a=new BD();
        
        return a.Facturas();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Getid = request.getParameter("ID");
        int aux= Integer.parseInt(Getid);
          response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            int index=0;
            switch(aux)
            {   
                case 1:
                    GetNombre=request.getParameter("NC");
                    GetApellido=request.getParameter("AC");
                    index = Integer.parseInt(request.getParameter("IC"));
                    if(GetNombre.length()<3 || GetNombre.length()>45)
                    { 
                        out.println("<title>Error</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>El Campo Nombre debe tener mas de 2 caracteres y menos de 45</h1>");
                    }
                    else
                    {
                        if(GetApellido.length()<3 || GetApellido.length()>45)
                        {
                            out.println("<title>Error</title>");            
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<h1>El Campo Apellido debe tener mas de 2 caracteres y menos de 45</h1>");
                        }
                        else
                        {
                            out.println("<title>text</title>");            
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<h1>"+SetDB(aux,(index+"-"+GetNombre+"-"+GetApellido))+"</h1>");
                        }
                    }  
                    out.println("<div><a href=\"http://localhost:8080/Restaurante/Camareros\">Regresar</a></div>");
                break;
                    
                case 2:
                    try
                    {
                        GetCedula=Integer.parseInt(request.getParameter("CC"));
                        GetNombre=request.getParameter("NC");
                        index = Integer.parseInt(request.getParameter("IC"));
                        if(GetNombre.length()<3 || GetNombre.length()>45)
                        { 
                            out.println("<title>Error</title>");            
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<h1>El Campo Nombre debe tener mas de 2 caracteres y menos de 45</h1>");
                        }
                        else
                        {
                            out.println("<title>text</title>");            
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<h1>"+SetDB(aux,(index+"-"+GetNombre+"-"+GetCedula))+"</h1>");
                        }
                    }
                    catch(Exception ex)
                    {
                        out.println("<title>Error</title>");            
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<h1>El campo Cedula solo puede contener caracteres numericos</h1>");
                    }
                    out.println("<div><a href=\"http://localhost:8080/Restaurante/Clientes\">Regresar</a></div>");
                break;
                    
                case 3:
                    GetNombre = request.getParameter("NP");
                    GetDescripcion = request.getParameter("DP");
                    GetIndex= Integer.parseInt(request.getParameter("Index"));
                    //GetPrecio = request.getParameter("PP");
                    try
                    {  /*> <*/
                        GetPrecio=Double.parseDouble(request.getParameter("PP"))+"";
                        if(GetNombre.length()<3 || GetNombre.length()>45)
                        {
                            out.println("<title>Error</title>");            
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<h1>El Campo Nombre no puede contener un nombre de 2 o menos caracteres o exceder los 45 caracteres</h1>");  
                        }
                        else
                        {
                            if(GetDescripcion.length()<1 || GetDescripcion.length()>45)
                            {
                                out.println("<title>Error</title>");            
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<h1>El Campo Descripcion no puede estar vacio ni exceder los 45 caracteres</h1>"); 
                            }
                            else
                            {
                                SetDB(aux,(GetNombre+"-"+GetDescripcion+"-"+GetPrecio+"-"+(GetIndex+1)));
                                out.println("<title>DB Respuesta</title>");            
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<h1>Datos Guardados Correctamente</h1>");
                            }
                        }
                        
                    }
                    catch(Exception e)
                    {
                        out.println("<title>Error</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>el campo precio solo puede contener numero</h1>");
                    }
                    out.println("<div><a href=\"http://localhost:8080/Restaurante/Platos\">Regresar</a></div>");
                    
                break;
                    
                case 4:
                    //TP=ID=4&Ca="+Ca+"&Cl="+Cl+"&P="+P+pedidos+"
                    out.println("<title>Facturando</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Inicio</h1>");
                    FiCl=Integer.parseInt(request.getParameter("Cl"));
                    FiCa=Integer.parseInt(request.getParameter("Ca"));
                    String [] au=getFacturas().split(",");
                    if((au[0].split("-")).length==1)
                        index=0;
                    else
                        index=au.length;
                    for(int i=0;i<Integer.parseInt(request.getParameter("P"));i++)
                    {
                        SetDB(aux,(request.getParameter("P"+(i+1))+"-"+index));
                    }
                    FTotal=Float.parseFloat(request.getParameter("TP"));
                    SetDB((aux+1),((index+1)+"-"+(FiCa+1)+"-"+(FiCl+1)+"-"+FTotal));
                    out.println("<h1>factura guardada</h1>");
                    out.println("<div><a href=\"http://localhost:8080/Restaurante/Facturacion\">Regresar</a></div>");
                    
                break;
                    
                default:
                    out.println("<title>No deberias estar aqui</title>");            
                    out.println("</head>");
                    out.println("<body>");
                break;
            }
            
            out.println("</body>");
            out.println("</html>");
        }
      
            
            
            /* TODO output your page here. You may use following sample code. */
            
           
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
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException 
    {
                
                processRequest(req, response);
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
