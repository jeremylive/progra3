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
public class Controlador {

    //
    private Progra3Mongo cMongo;
    private DBCollection tablaResumen;
    private ArrayList<Integer> arreglo;

    //Constructor
    public Controlador(Progra3Mongo mongo) {
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
    
    public void ejeActualizar(interfazCrudAficionado crudA, boolean indicadorFoto,
                boolean indicadorCorreo)
    {
        //Valido los JcheckBox que si estan en true se habilita las casillas sino se desabilita
        if(indicadorFoto==true)
        {
            crudA.fotoAficionado.setEnabled(true);
        }else {
            crudA.fotoAficionado.setEnabled(false);
        }
        
        if(indicadorCorreo==true)
        {
            crudA.correoAficionado.setEnabled(true);
        }else{
            crudA.correoAficionado.setEnabled(false);
        }
        
        //
        
        
        
        
    }
    
    //CRUD AFICIONADO
    public void crearAficionado(String codAficionado, String contraseñaAficionado, 
                String fotoAficionado, String correoAficionado, boolean indicadorFoto,
                boolean indicadorCorreo)
    {
        
        //Validaciones 
   
        
    }
    
    public void updateAficionado(String codAficionado, String contraseñaAficionado, 
                String fotoAficionado, String correoAficionado, String indicadorFoto,
                String indicadorCorreo)
    {
        
    }
    
    
    public void eliminarAficionado(String codAficionadoEliminar)
    {
        
    }
    
    //CRUD COMENTARIO
    public void crearComentario(int cont, String txtComentario, String codigoAficionado, 
            String fechaComentario, String horaComentario, String comentPadre)
    {
        
    }
    
    
    public void updateComentario(String txtComentario, String codigoAficionado, 
            String fechaComentario, String horaComentario, String comentPadre)
    {
        
    }
    
    
    public void eliminarComentario(int numComentarioEliminar)
    {
        
    }
    
    
    //CRUD RESUMEN
    public int createResumen(int numero_partido, String codEq1, String codEq2,
            String txtResumen, String video1, String video2) {
        //No debe de existir el resumen en el arreglo de resumen
        if (arreglo.contains(numero_partido)) {
            JOptionPane.showMessageDialog(null, "Ya existe un resumen para ese partido");
            return 1;
        }
        arreglo.add(numero_partido);
        //Validat que este en ORACLE

        //creo tupla
        BasicDBObject document = new BasicDBObject();
        document.put("numero_partido", numero_partido);
        document.put("codigo_equi1", codEq1);
        document.put("codigo_equi2", codEq2);
        document.put("txt_resumen", txtResumen);
        document.put("video1", video1);
        document.put("video2", video2);
        //inserto tupla
        tablaResumen.insert(document);

        return 0;
    }

    public int updateResumen(int numero_partido, String txtResumen, String video1, String video2) {
        //Debe de existir el resumen en el arreglo de resumen
        if (!(arreglo.contains(numero_partido))) {
            JOptionPane.showMessageDialog(null, "No existe un resumen con ese numero de partido");
            return 1;
        }
        //Creo el query
        BasicDBObject query = new BasicDBObject("numero_partido", numero_partido);
        //Obtengo la tupla existente
        DBObject tuplaExistente = null;
        DBCursor cursorExistente = tablaResumen.find(query);
        if (cursorExistente.hasNext()) {
            tuplaExistente = cursorExistente.next();
        }

        //creo tupla
        BasicDBObject document = new BasicDBObject();
        document.put("numero_partido",tuplaExistente.get("numero_partido"));
        document.put("codigo_equi1",tuplaExistente.get("codigo_equi1"));
        document.put("codigo_equi2",tuplaExistente.get("codigo_equi2"));
        document.put("txt_resumen", txtResumen);
        document.put("video1", video1);
        document.put("video2", video2);
        //inserto tupla
        tablaResumen.update(query, document);

        return 0;
    }

    public void readResumen(int numero_partido) {
        //Creo el query
        BasicDBObject query = new BasicDBObject("numero_partido", numero_partido);
        //Obtengo el resultado
        DBCursor cursor = tablaResumen.find(query);
        System.out.println("encuentro tabla");
        while (cursor.hasNext()) {
            System.out.println("nombre: " + cursor.next());
        }

    }

    public void deleteResumen(int numero_partido) {
        JOptionPane.showMessageDialog(null, "Se ejecuta la opcion eliminar tupla, en base al numero de partido");
        tablaResumen.remove(new BasicDBObject("numero_partido", numero_partido));

    }

    //CRUD 
}
