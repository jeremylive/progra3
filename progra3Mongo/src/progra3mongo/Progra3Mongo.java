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
    private DB db;
    private DBCollection tabla;
    private boolean status;
    
    public Progra3Mongo()
    {
        status = false;
    }
    
    public void conexion()
    {
        //Se establece la conexion a mongo
        try{
            Mongo mongo = new Mongo("LocalHost", 27017);
            db = mongo.getDB("progra3");
            tabla = db.getCollection("participa");
            status = true;
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public boolean getStatus()
    {
        return this.status;
    }

    public DB getDb() {
        return db;
    }

    public void setDb(DB db) {
        this.db = db;
    }

    public DBCollection getTabla() {
        return tabla;
    }

    public void setTabla(DBCollection tabla) {
        this.tabla = tabla;
    }
    
    
    
}
