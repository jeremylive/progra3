package progra3mongo;

import com.mongodb.BasicDBObject;
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
    
    
    
    //CRUD RESUMEN
    public void createResumen(int numero_partido, String codEq1, String codEq2, 
                                String txtResumen, String video1, String video2)
    {
        BasicDBObject document = new BasicDBObject();
        document.put("numero_partido",numero_partido);
        document.put("codigo_equi1","'"+codEq1+"'");
        document.put("codigo_equi2","'"+codEq2+"'");
        document.put("txt_resumen","'"+txtResumen+"'");
        document.put("video1","'"+video1+"'");
        document.put("video2","'"+video2+"'");
        tabla.insert(document);
        
    }
    
    public void updateResumen(int numero_partido, String codEq1, String codEq2, 
                                String txtResumen, String video1, String video2)
    {
        BasicDBObject document = new BasicDBObject();
        document.put("numero_partido",numero_partido);
        document.put("codigo_equi1","'"+codEq1+"'");
        document.put("codigo_equi2","'"+codEq2+"'");
        document.put("txt_resumen","'"+txtResumen+"'");
        document.put("video1","'"+video1+"'");
        document.put("video2","'"+video2+"'");
        tabla.insert(document);
    }    
    
    public void readResumen()
    {
        
    }

    public void deleteResumen(int numero_partido)
    {
        
    }
    
    //CRUD 
    
}
