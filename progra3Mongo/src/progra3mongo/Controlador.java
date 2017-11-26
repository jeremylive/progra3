package progra3mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import javax.swing.JOptionPane;

/**
 *
 * @author live
 */
public class Controlador 
{
    //
    private Progra3Mongo cMongo;
    
    //Constructor
    public Controlador(Progra3Mongo mongo)
    {
        this.cMongo = mongo;
    }
    
    //gets and sets
    
    
    //funciones
    
    
    //CRUD RESUMEN
    public void createResumen(int numero_partido, String codEq1, String codEq2, 
                                String txtResumen, String video1, String video2)
    {
        DBCollection tabla = cMongo.getDb().getCollection("participa");
        
        BasicDBObject document = new BasicDBObject();
        document.put("numero_partido",numero_partido);
        document.put("codigo_equi1","'"+codEq1+"'");
        document.put("codigo_equi2","'"+codEq2+"'");
        document.put("txt_resumen","'"+txtResumen+"'");
        document.put("video1","'"+video1+"'");
        document.put("video2","'"+video2+"'");
        tabla.insert(document);
        
        DBCursor cursor =  tabla.find(new BasicDBObject("nombre","jeremy"));
        
        while(cursor.hasNext())
        {
            JOptionPane.showMessageDialog(null,"nombre: "+cursor.next());
        }
    }
    
    public void updateResumen(int numero_partido, String codEq1, String codEq2, 
                                String txtResumen, String video1, String video2)
    {

    }    
    
    public void readResumen()
    {
        
    }

    public void deleteResumen(int numero_partido)
    {
        
    }
    
    //CRUD 
    
    
    
    
}
