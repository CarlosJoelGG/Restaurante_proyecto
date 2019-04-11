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
@WebServlet(name = "Gestor", urlPatterns = {"/Gestor"})
public class Gestor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
     public String MesCamarero(int id,int mes)
    {
        BD a=new BD();
        return a.ConsultaCamareroFactura(id,mes)+"";
    }
    
    public String GetCamareros()
    {
        BD a=new BD();
        return a.CunsultarCamareros();
    }
    public String GetClientes()
    {
        BD a=new BD();
        return a.Clientes();
    }
     public float GetCTotal(int id)
    {
        BD a=new BD();
        return a.ConsultaCliente(id);
    }
    public String GetPlatos()
    {
        BD a=new BD();
        return a.Platos();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String [] au2,au;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            
            int index=0;
            switch(Integer.parseInt(request.getParameter("G")))
            {
                case 0://Registro mensual de camareros
                     out.println("<title>Clientes Premiun</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<br>");
                    out.println("<br>");
                    au=GetCamareros().split(",");
                    
                    out.println("<table class=\"egt\">");
                    out.println("<tr>");
                    out.println("<td>Nombre__</td>");
                    out.println("<td>Enero</td>");
                    out.println("<td>Febrero</td>");
                    out.println("<td>Marzo</td>");
                    out.println("<td>Abril</td>");
                    out.println("<td>Mayo</td>");
                    out.println("<td>Junio</td>");
                    out.println("<td>Julio</td>");
                    out.println("<td>Agosto</td>");
                    out.println("<td>Septiembre</td>");
                    out.println("<td>Octubre</td>");
                    out.println("<td>Noviembre</td>");
                    out.println("<td>Diciembre</td>");
                    
                    
                    out.println(" </tr>");
                    for(int i=0;i<au.length;i++)
                    {//id,nombre apellido
                        au2=au[i].split("-");
                       out.println("<tr>");
                       out.println("<td>"+au2[1]+" "+au2[2]+"</td>");
                       for(int j=0;j<12;j++)
                       {
                           out.println("<td>"+MesCamarero(Integer.parseInt(au2[0]),(j+1))+"</td>");
                       }
                       out.println(" </tr>");     
                    }
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<div><a href=\"http://localhost:8080/Restaurante/Camarero\">Regresar</a></div>");
                break;
                
                case 1: //Agregar Camarero
                    index=Integer.parseInt(request.getParameter("index"));
                    out.println("<title>AddCamarero</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<form action=\"/Restaurante/Agregar\" method=\"get\">");
                    out.println("Nombre del Camarero : <input type=\"text\" name=\"NC\"><br>");
                    out.println("Apellido del Camarero: <input type=\"text\" name=\"AC\"><br>");
                    out.println("ID :<input type=\"radio\" name=\"IC\" value=\""+index+"\" checked>"+index+"<br>");
                    out.println("Tipo de peticion :<input type=\"radio\" name=\"ID\" value='1' checked>1<br>");
                    out.println("<br>");
                    out.println("<input type=\"submit\" value=\"Añadir Camarero\">");
                    out.println("</form>");
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<div><a href=\"http://localhost:8080/Restaurante/Camarero\">Regresar</a></div>");
            
                break;
                    
                case 2://Clientes 100mil de gastos
                    //ConsultaCliente da el monto para un id
                    //Clientes devuelve todos los clientes
                    out.println("<title>Clientes Premiun</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<br>");
                    out.println("<br>");
                    au=GetClientes().split(",");
                    
                    out.println("<table class=\"egt\">");
                    out.println("<tr>");
                    out.println("<td>Nombre</td>");
                    out.println("<td>Cedula</td>");
                    out.println("<td>Monto</td>");
                    out.println(" </tr>");
                    for(int i=0;i<au.length;i++)
                    {//id,precio,nombre,descripcion
                        au2=au[i].split("-");
                       if( GetCTotal(Integer.parseInt(au2[2]))>=100000.0)
                       {
                       
                        out.println("<tr>");
                        out.println("<td>"+au2[2]+"</td>");
                        out.println("<td>"+au2[0]+"</td>");
                        out.println("<td>"+GetCTotal(Integer.parseInt(au2[2]))+"</td>");
                        out.println(" </tr>");
                       }
                       else
                       {
                       }
                        
                    }
                    out.println("</table>");
                    
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<div><a href=\"http://localhost:8080/Restaurante/Clientes\">Regresar</a></div>");
                break;
                
                case 3://Agregar cliente
                    index=Integer.parseInt(request.getParameter("index"));
                    out.println("<title>AddCliente</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<form action=\"/Restaurante/Agregar\" method=\"get\">");
                    out.println("Nombre y apellido del Cliente : <input type=\"text\" name=\"NC\"><br>");
                    out.println("______________________Cedula : <input type=\"text\" name=\"CC\"><br>");
                    out.println("ID :<input type=\"radio\" name=\"IC\" value=\""+index+"\" checked>"+index+"<br>");
                    out.println("Tipo de peticion :<input type=\"radio\" name=\"ID\" value='2' checked>2<br>");
                    out.println("<br>");
                    out.println("<input type=\"submit\" value=\"Añadir Cliente\">");
                    out.println("</form>");
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<div><a href=\"http://localhost:8080/Restaurante/Clientes\">Regresar</a></div>");
                break;
                case 4://agregar plato
                    index=Integer.parseInt(request.getParameter("Index"));
                    out.println("<title>AddPlato</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<form action=\"/Restaurante/Agregar\" method=\"get\">");
                    out.println("id_________________: <input type=\"radio\" name=\"ID\" value=\"3\" checked> 3<br>");
                    out.println("Numero de Platos___: <input type=\"radio\" name=\"Index\" value="+index+" checked>"+index+"<br>");
                    out.println("Nombre del Plato____: <input type=\"text\" name=\"NP\"><br>");
                    out.println("Descripcion del Plato_: <input type=\"text\" name=\"DP\"><br>");
                    out.println("Precio del Plato_____: <input type=\"text\" name=\"PP\"><br>");
                    out.println("<br>");
                    out.println("<input type=\"submit\" value=\"Añadir Platillo\">");
                    out.println("</form>");
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<div><a href=\"http://localhost:8080/Restaurante/Platos\">Regresar</a></div>");
                break;
                
                case 5:
                
                    String Ca,Cl,P;
                    Ca=request.getParameter("Ca");
                    Cl=request.getParameter("Cl");
                    P=request.getParameter("P");
                    String HTMLChar="",cadenaCamareros,cadenaClientes,cadenaPlato;
                    cadenaCamareros=GetCamareros();
                    cadenaClientes=GetClientes();
                    cadenaPlato=GetPlatos();
                    String[] aux=cadenaCamareros.split(",");
                    String[] aux2=cadenaClientes.split(",");
                    String[] aux3=cadenaPlato.split(",");
                    String[] Camarero,Cliente,Plato;
                    String pedidos="";
                    float Total=0;
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    HTMLChar=HTMLChar+"<form name=frmTest action=\"/Restaurante/Gestor\" method=GET>";
                    HTMLChar=HTMLChar+"<p>" +"<select name=NameCamarero size=1 onChange=\"frmTest.submit();\">";
                    if((Camarero=aux[0].split("-")).length==1)
                    {
                        out.println("<title>Error al crear la factura</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<div><a>debe existir al menos un camarero registrado</a></div>");
                        out.println("");
                        out.println("");   
                    }
                    else
                    {
                        if((Cliente=aux[0].split("-")).length==1)
                        {
                            out.println("<title>Error al crear la factura</title>");            
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<div><a>debe existir al menos un cliente registrado</a></div>");
                            out.println("");
                            out.println("");
                        }
                        else
                        {
                            if(Ca.equals("N"))
                            {// 
                                out.println("<title>Escojer Camarero</title>");            
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<form name=CaCB action=\"/Restaurante/Gestor\" method=GET>");
                                out.println("<p>");
                                out.println("<select name=Ca size="+aux.length+" onChange=\"CaCB.submit();\">");
                                out.println("<option value=\"\" SELECTED>Escojer uno");
                                for(int i=0;i<aux.length;i++)
                                {//id-nombre-apellido
                                    Camarero=aux[i].split("-");
                                    out.println("<option value='"+i+"'>"+Camarero[0]+" "+Camarero[1]+" "+Camarero[2]);   
                                }
                                out.println("</select>");
                                out.println("Cliente :<input type=\"radio\" name=\"Cl\" value=\"N\" checked><br>");
                                out.println("<input type=\"radio\" name=\"G\" value=\"5\" checked><br>");
                                out.println("</p>");
                                out.println("</form>");
                            }
                        else
                        {
                            if(Cl.equals("N"))
                            {
                                Camarero=aux[Integer.parseInt(request.getParameter("Ca"))].split("-");
                                out.println("<title>Escojer Cliente</title>");            
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<form name=CaCB action=\"/Restaurante/Gestor\" method=GET>");
                                out.println("<p>");
                                out.println("Camarero :<input type=\"radio\" name=\"Ca\" value=\""+Ca+"\" checked>"+Camarero[1]+" "+Camarero[2]+"<br>");
                                out.println("<select name=Cl size="+aux2.length+" onChange=\"CaCB.submit();\">");
                                out.println("<option value=\"\" SELECTED>Escojer uno");
                                for(int i=0;i<aux2.length;i++)
                                {//id-nombre-apellido
                                    Cliente=aux2[i].split("-");
                                    out.println("<option value='"+i+"'>"+Cliente[0]+" "+Cliente[2]+" "+Cliente[1]);   
                                }
                                out.println("</select>");
                                out.println("<input type=\"radio\" name=\"P\" value=\"N\" checked><br>");
                                out.println("<input type=\"radio\" name=\"G\" value=\"5\" checked><br>");
                                out.println("</p>");
                                out.println("</form>");
                            }
                            else
                            {
                                if(P.equals("N"))
                                {//Nombre Cedula index
                                    Camarero=aux[Integer.parseInt(request.getParameter("Ca"))].split("-");
                                    Cliente=aux2[Integer.parseInt(request.getParameter("Cl"))].split("-");
                                    out.println("<title>Escojer Platillo</title>");            
                                    out.println("</head>");
                                    out.println("<body>");
                                    out.println("<form name=CaCB action=\"/Restaurante/Gestor\" method=GET>");
                                    out.println("<p>");
                                    out.println("Camarero :<input type=\"radio\" name=\"Ca\" value=\""+Ca+"\" checked>"+Camarero[1]+" "+Camarero[2]+"<br>");
                                    out.println("Cliente :<input type=\"radio\" name=\"Cl\" value=\""+Cl+"\" checked>"+Cliente[0]+" "+Cliente[1]+"<br>");
                                    out.println("<select name=P1 size="+aux3.length+" onChange=\"CaCB.submit();\">");
                                    out.println("<option value=\"\" SELECTED>Escojer uno");
                                    for(int i=0;i<aux3.length;i++)
                                    {//id,precio,nombre,descripcion
                                        Plato=aux3[i].split("-");
                                        out.println("<option value='"+i+"'>"+Plato[2]+" "+Plato[3]+" : "+Plato[1]);   
                                    }
                                    out.println("</select>");
                                    out.println("<br>");
                                    out.println("ID :<input type=\"radio\" name=\"P\" value=\"1\" checked><br>");
                                    out.println("<input type=\"radio\" name=\"G\" value=\"5\" checked><br>");
                                    out.println("Total a pagar <input type=\"radio\" name=\"TP\" value=\"0\" checked>"+Total+"<br>");
                                    out.println("</p>");
                                    out.println("</form>");
                                    }
                                    else
                                    {
                                        Camarero=aux[Integer.parseInt(request.getParameter("Ca"))].split("-");
                                        Cliente=aux2[Integer.parseInt(request.getParameter("Cl"))].split("-");
                                        out.println("<title>"+Integer.parseInt(request.getParameter("P"+(0+1)))+"</title>");            
                                        out.println("</head>");
                                        out.println("<body>");
                                        out.println("<div><a>Agrenar un nuevo plato</a></div>");
                                        out.println("<form name=CaCB action=\"/Restaurante/Gestor\" method=GET>");
                                        out.println("<p>");
                                        out.println("Camarero :<input type=\"radio\" name=\"Ca\" value=\""+Ca+"\" checked>"+Camarero[1]+" "+Camarero[2]+"<br>");
                                        out.println("Cliente :<input type=\"radio\" name=\"Cl\" value=\""+Cl+"\" checked>"+Cliente[0]+" "+Cliente[1]+"<br>");
                                        for(int i=0;i<Integer.parseInt(P);i++)
                                        {
                                            Plato=aux3[Integer.parseInt(request.getParameter("P"+(i+1)))].split("-");
                                            out.println("Pedido :<input type=\"radio\" name=\"P"+(i+1)+"\" value=\""+request.getParameter("P"+(i+1))+"\" checked>"+Plato[2]+" "+Plato[3]+" : "+Plato[1]);
                                            out.println("<br>");
                                            Total=Total+Float.parseFloat(Plato[1]);
                                            pedidos=pedidos+"&P"+(i+1)+"="+request.getParameter("P"+(i+1));
                                        }
                                        out.println("<select name=P"+(Integer.parseInt(P)+1)+" size="+aux3.length+" onChange=\"CaCB.submit();\">");
                                        out.println("<option value=\"\" SELECTED>Escojer uno");
                                        for(int i=0;i<aux3.length;i++)
                                        {//id,precio,nombre,descripcion
                                            Plato=aux3[i].split("-");
                                            out.println("<option value='"+i+"'>"+Plato[2]+" "+Plato[3]+" : "+Plato[1]);   
                                        }
                                        out.println("</select>");
                                        out.println("<br>");
                                        out.println("Pedidos realizados <input type=\"radio\" name=\"P\" value=\""+(Integer.parseInt(P)+1)+"\" checked>"+P+"<br>");
                                        
                                        out.println("Pedidos realizados <input type=\"radio\" name=\"TP\" value=\""+Total+"\" checked>"+Total+"<br>");
                                        out.println("<input type=\"radio\" name=\"G\" value=\"5\" checked><br>");
                                        out.println("</p>");
                                        out.println("</form>");
                                        out.println("<br>");
                                        out.println("<br>");
                                        out.println("<div><a href=\"http://localhost:8080/Restaurante/Agregar?G=5&ID=4&TP="+Total+"&Ca="+Ca+"&Cl="+Cl+"&P="+P+pedidos+"\">Facturar</a></div>");
                                        }
                                    }
                                }
                            }
                        }
                        out.println("<div><a href=\"http://localhost:8080/Restaurante/Menu\">Regresar</a></div>");
                        out.println("</body>");
                        out.println("</html>");
                    break;
                }
            /* TODO output your page here. You may use following sample code. */
            
            out.println("<h1></h1>");
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
