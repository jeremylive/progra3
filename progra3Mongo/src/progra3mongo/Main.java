package progra3mongo;

import javax.swing.JOptionPane;

/**
 *
 * @author live
 */
public class Main 
{
    public static void main(String args[]) 
    {
        //Creo controlador y interfaz
        
        
        //Creo conexion ORACLE
        Conexion conex = new Conexion();
        conex.Conectar();
        Progra3Mongo mongo = new Progra3Mongo();
        mongo.conexion();
        Controlador control = new Controlador(mongo);
        interfazLogin interfaz = new interfazLogin(control);
        
        if(conex.getstatus() && mongo.getStatus())
        {
            //
            JOptionPane.showMessageDialog(null, "Conexion exitosa a MONGODB y ORACLE ");
            
            //Visualizo la interfaz graficamente     
            interfaz.setVisible(true);    
        }
        
    }
}
