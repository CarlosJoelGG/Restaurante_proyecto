package Restaurante;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//import javax.swing.JOptionPane;

public class BD 
{
    public String db = "Restaurante";
    //jdbc:derby://localhost:1527/sample
    public String url = "jdbc:derby://localhost:1527/"+db;
    public String user = "app";
    public String pass = "app";
    public Connection link;
    Date date = new Date();
    public DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

//System.out.println("Fecha: "+dateFormat.format(date));
    public String test()
    {
        String a="";
        a= ConeccionInit();
        ConeccionEnd();
        return a;
    }
    public String agregar(int a,String Agregado)
    {String[] C;
    
        ConeccionInit();
        PreparedStatement st;
        try
        {
            String aux="";
        switch(a)
        {   
            case 1://Camarero
                C=Agregado.split("-");
                aux="INSERT INTO Camarero(IDCAMARERO,NOMBRE_C,APELLIDO_C) VALUES("+C[0]+",'"+C[1]+"','"+C[2]+"')";
                st = link.prepareStatement(aux); 
                st.executeUpdate();  
                st.close();
            break;
            case 2://Cliente
                //index Nombre Cedula
                C=Agregado.split("-");
                aux="INSERT INTO CLIENTE(IDCLIENTE,NOMBRE_CLIENTE,CEDULA) VALUES("+C[0]+",'"+C[1]+"',"+C[2]+")";
                st = link.prepareStatement(aux); 
                st.executeUpdate();  
                st.close();
            break;
            case 3://Platillo
                //Nombre,Descripcion,Precio
                C=Agregado.split("-");
                aux="INSERT INTO Plato(IDPLATO,PRECIO,NOMBRE_P,DESCRIPCION) VALUES("+C[3]+","+C[2]+",'"+C[0]+"','"+C[1]+"')";
                st = link.prepareStatement(aux); 
                st.executeUpdate();  
                st.close();
                //Statement s = link.createStatement(); 
                //ResultSet rs = s.executeQuery (aux);
            break;
            case 4://pedidos
                C=Agregado.split("-");
                aux="INSERT INTO Pedidos (Plato_idPlato,Factura_idFactura) VALUES("+C[0]+","+C[1]+")";
                st = link.prepareStatement(aux); 
                st.executeUpdate();  
                st.close();
            break;
            case 5://Factura 
                //(index+"-"+FiCa+"-"+FiCl+"-"+FTotal));
                C=Agregado.split("-");
                aux="INSERT INTO Factura (idFactura,id_cliente,id_camarero,Total_pagar,Mesa,Fecha) VALUES("+C[0]+","+(C[2])+","+(C[1])+","+C[3]+",1,'"+dateFormat.format(date).toString()+"')";
                st = link.prepareStatement(aux); 
                st.executeUpdate();  
                st.close();
            break;
                
            default:
                ConeccionEnd();
                return "nada";
            
        }
        }
        catch(Exception ex)
        {
            ConeccionEnd();
            return "error : "+ex;
        }
        ConeccionEnd();
        return "ok";
    }
    public BD()
    {
        link = null;
    }
    public String ConeccionInit()
    {   
        link = null;
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           link = DriverManager.getConnection(this.url, this.user, this.pass);
           return "ok";
        }
        catch(Exception ex)
        {
            return "it`s not ok "+ex;
          // JOptionPane.showMessageDialog(null,"Error al iniciar la Base de datos. "+ ex);
        }
    }
    
    public void ConeccionEnd()
    {
        try
        {
            link.close();
        }
        catch(Exception ex)
        {
            //JOptionPane.showMessageDialog(null,"Error al finalizar la Base de datos. "+ ex);
        }
    }
    
    public String Platos()
    {
        String aux="";
        ConeccionInit();
        try
        {//Class.forName("com.mysql.jdbc.Driver");
            Statement s = link.createStatement(); 
            ResultSet rs = s.executeQuery ("select * from Plato");
            while(rs.next())
            {//id,precio,nombre,descripcion
                if(!aux.equals(""))
                    aux=aux+",";
                aux=aux+rs.getString("idPlato")+"-";
                aux=aux+rs.getString("Precio")+"-";
                aux=aux+rs.getString("Nombre_P")+"-";
                aux=aux+rs.getString("Descripcion");  
            }
        }
        catch(Exception ex)
        {
            //JOptionPane.showMessageDialog(null,"Error en la consulta platos. "+ ex);
        }
        ConeccionEnd();
        return aux;
    }
    
    public String Facturas()
    {
        String task="";
        ConeccionInit();
        try
        {
            Statement s = link.createStatement(); 
            ResultSet rs = s.executeQuery ("select * from Factura");
            while(rs.next()) 
            {// idFactura id_Cliente id_camarero Total_pagar Mesa Fecha;
                if(task.length()>1)
                    task=task+",";
                task = task+ rs.getString("idFactura")+"-"+rs.getString("id_Cliente")+"-"+rs.getString("id_camarero")+"-"+rs.getString("Total_pagar")+"-"+rs.getString("Mesa")+"-"+rs.getString("Fecha");
            }
        }
        catch(Exception ex)
        {
            //JOptionPane.showMessageDialog(null,"Error en la consulta de los camareros. "+ ex);
        }
        ConeccionEnd();
        return task;
    }
    
    public String CunsultarCamareros()
    {   
        String Nombre="";
        ConeccionInit();
        try
        {
            Statement s = link.createStatement(); 
            ResultSet rs = s.executeQuery ("select * from Camarero");
            while(rs.next()) 
            {//id-nombre-apellido
                if(Nombre.length()>1)
                    Nombre=Nombre+",";
                Nombre = Nombre+ rs.getString("idCamarero")+"-"+rs.getString("Nombre_C")+"-"+rs.getString("Apellido_C");
            }
        }
        catch(Exception ex)
        {
            //JOptionPane.showMessageDialog(null,"Error en la consulta de los camareros. "+ ex);
        }
        ConeccionEnd();
        return Nombre;
    }
    
     public String Cliente(int id)
    {
        String cliente="";
        ConeccionInit();
        try
        {//Nombre Cedula index
            Statement s = link.createStatement(); 
            ResultSet rs = s.executeQuery ("select * from Cliente where idCliente="+id);
            while(rs.next())
            {
                cliente =  cliente+rs.getString("Nombre_Cliente")+" "+rs.getString("Cedula");
            }
        }
        catch(Exception ex)
        {
            //JOptionPane.showMessageDialog(null,"Error en la consulta cliente. "+ ex);
        }
        
        ConeccionEnd();
        return cliente;
    }
    
    public String Camarero(int id)
    {
        String camarero="";
        ConeccionInit();
        try
        {//Nombre Cedula index
            Statement s = link.createStatement(); 
            ResultSet rs = s.executeQuery ("select * from Camarero where idCamarero="+id);
            while(rs.next())
            {
                camarero =  camarero+rs.getString("NOMBRE_C")+" "+rs.getString("APELLIDO_C");
            }
        }
        catch(Exception ex)
        {
                 return "Error : "+ex;
//JOptionPane.showMessageDialog(null,"Error en la consulta cliente. "+ ex);
        }
        
        ConeccionEnd();
        return camarero;
    }
    
    public String ConsultaCamareroFactura(int id,int mes)
    {//clientes que pasen de 100mil pesos$
        float Factura_total=0.0f;
        ConeccionInit();
        /*> <*/
       
        String Meses="";
        try
        {
            Statement s = link.createStatement(); 
            for(int i=0;i<12;i++)
            {   
                ResultSet rs = s.executeQuery ("select Total_pagar,Fecha from Factura where id_camarero="+id+" and month(Fecha)="+mes);
                while(rs.next()) 
                {
                    Factura_total = Factura_total + Float.parseFloat(rs.getString("Total_pagar"));
                }
                
            }
            Meses=Meses+Factura_total;
        }
        catch(Exception ex)
        {
            Meses="Error "+ex;
            //JOptionPane.showMessageDialog(null,"Error en la consulta camarero. "+ ex);
        }
        
        ConeccionEnd();
        
        return ""+Meses;
    }
    
    public String Clientes()
    {
        String Clientes="";
        ConeccionInit();
        try
        {//Nombre Cedula index
            Statement s = link.createStatement(); 
            ResultSet rs = s.executeQuery ("select * from Cliente");
            while(rs.next())
            {
                if(Clientes.length()>0)
                    Clientes=Clientes+",";
                Clientes =  Clientes+rs.getString("Nombre_Cliente")+"-"+rs.getString("Cedula")+"-"+rs.getString("idCliente");
            }
        }
        catch(Exception ex)
        {
            //JOptionPane.showMessageDialog(null,"Error en la consulta cliente. "+ ex);
        }
        
        ConeccionEnd();
        return Clientes;
    }
    
   public String CMes(int mes)
   {
       
       return "";
   }
    
    public float ConsultaCliente(int id)
    {//clientes que pasen de 100mil pesos$
        float Factura_total=0.0f;
        ConeccionInit();
        try
        {
            Statement s = link.createStatement(); 
            ResultSet rs = s.executeQuery ("select Total_pagar from Factura where id_cliente="+id);
            while(rs.next())
            {
                Factura_total = Factura_total + Float.parseFloat(rs.getString("Total_pagar"));
            }
        }
        catch(Exception ex)
        {
            //JOptionPane.showMessageDialog(null,"Error en la consulta cliente. "+ ex);
        }
        
        ConeccionEnd();
        
        return Factura_total;
    }
}
