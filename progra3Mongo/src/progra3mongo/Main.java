package progra3mongo;

import static java.awt.SystemColor.control;
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
        interfaz01 interfaz1 = new interfaz01(control);
        
        if(conex.getstatus() && mongo.getStatus())
        {
            //
            JOptionPane.showMessageDialog(null, "Conexion exitosa a MONGODB y ORACLE ");
            
            
            //Visualizo la interfaz graficamente     
            interfaz1.setVisible(true);    
        }
        
    }
}
