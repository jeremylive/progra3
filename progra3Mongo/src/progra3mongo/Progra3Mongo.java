package progra3mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 * Bibliotecas
 */


/**
 *
 * @author live
 */
public class Progra3Mongo 
{
    DB db;
    DBCollection tabla;
    
    public Progra3Mongo()
    {
        conexion();
        
    }
    
    public void conexion()
    {
        //Se establece la conexion a mongo
        try{
            Mongo mongo = new Mongo("LocalHost", 27017);
            db = mongo .getDB("progra3");
            tabla = db.getCollection("PARTICIPA");
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
}
