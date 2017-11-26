/**
 * Paquete principal
 */
package progra3mongo;
/**
 * Librerias a usar
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *-----------------------------------------------------------------------------
 * @author Jeremy Live
 * ----------------------------------------------------------------------------
 */
public class Conexion 
{
    /**
     * Variables globales
     * Establese la conexion entre SQL servers y netBeans
     */
    public static Connection contacto;
    public static String usuario;
    public static String password;
    public static boolean status;
    //Constructor
    public Conexion()
    {
        this.status = false;
    }  
    /**
     * Gets and sets
     */
    public static String getName()
    {
        return Conexion.usuario;
    }
    
    public static String getPass()
    {
        return Conexion.password;
    }
    
    public static Connection getConexion() {
        return contacto;
    }
    
    public void setConexion(Connection conexion) 
    {
        this.contacto = conexion;
    }
    /**
     * Seteo el nuevo usuario y la nueva contraseña a evaluar
     * @param usuario
     * @param password 
     */
    public static void setcuenta(String usuario, String password)
    {
        Conexion.usuario = usuario;
        Conexion.password = password;
    }
 
    /**
     * Obtengo el estado de la conexion de la BD
     * @return 
     */
    public static boolean getstatus()
    {
        return  status;
    }   
    /**
     * ########################################################################
     * Funciones
     * ########################################################################
     */
    
    //Establezco conexión de ORACLE
    public Conexion Conectar()
    {
        try{
            
            Class .forName("oracle.jdbc.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@//172.19.32.101:1521/grupo07.basedatos";
            setConexion(DriverManager.getConnection(BaseDeDatos,getName(),getPass()));
            
            if(getConexion() != null)
            {
                this.status = true; 
                System.out.println("Conexion exitosa a esquema JLIVE");
            } else {
                System.out.println("Conexion fallida a JLIVE");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return this;
    }
    
    //Fin de la clase conexión
}
