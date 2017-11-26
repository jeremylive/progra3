package progra3mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author live
 */
public class Controlador 
{
    //
    private Progra3Mongo cMongo;
    private DBCollection tablaResumen;
    private ArrayList<Integer> arreglo;
    //Constructor
    public Controlador(Progra3Mongo mongo)
    {
        this.cMongo = mongo;
        this.tablaResumen = cMongo.getDb().getCollection("resumen");
        this.arreglo = new ArrayList<>();
    }
    
    //gets and sets
    public ArrayList<Integer> getArreglo() {
        return arreglo;
    }

    public void setArreglo(int arreglo1) {
        this.arreglo.add(arreglo1);
    }
    
    //funciones
    
    
    //CRUD RESUMEN
    public int createResumen(int numero_partido, String codEq1, String codEq2, 
                                String txtResumen, String video1, String video2)
    {
        //No debe de existir el resumen en el arreglo de resumen
        if(arreglo.contains(numero_partido)){
            return 1;
        }
        arreglo.add(numero_partido);
        //Validat que este en ORACLE
        
        //creo tupla
        BasicDBObject document = new BasicDBObject();
        document.put("numero_partido",numero_partido);
        document.put("codigo_equi1","'"+codEq1+"'");
        document.put("codigo_equi2","'"+codEq2+"'");
        document.put("txt_resumen","'"+txtResumen+"'");
        document.put("video1","'"+video1+"'");
        document.put("video2","'"+video2+"'");
        //inserto tupla
        tablaResumen.insert(document);
        
        return 0;
    }
    
    public int updateResumen(int numero_partido, String txtResumen, String video1, String video2)
    {
        //Debe de existir el resumen en el arreglo de resumen
        if(!(arreglo.contains(numero_partido))){
            return 1;
        }
        //creo tupla
        BasicDBObject document = new BasicDBObject();
        //document.put("numero_partido",numero_partido);
        document.put("txt_resumen","'"+txtResumen+"'");
        document.put("video1","'"+video1+"'");
        document.put("video2","'"+video2+"'");
        //inserto tupla
        tablaResumen.update(new BasicDBObject("numero_partido", numero_partido), document);
        
        return 0;
    }    
    
    public void readResumen(int numero_partido)
    {
        DBCursor cursor =  tablaResumen.find(new BasicDBObject("numero_partido",numero_partido));
        System.out.println("encuentro tabla");
        while(cursor.hasNext())
        {
            System.out.println("nombre: "+cursor.next());
        }
        
    }

    public void deleteResumen(int numero_partido)
    {
        JOptionPane.showMessageDialog(null, "Se ejecuta la opcion eliminar tupla, en base al numero de partido");
        tablaResumen.remove(new BasicDBObject("numero_partido", numero_partido));
        
    }
    
    //CRUD 
    
    
    
    
}
